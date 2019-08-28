package info.ivicel.augmented.cotroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.ivicel.augmented.common.API;
import info.ivicel.augmented.common.AugmentedProperties;
import info.ivicel.augmented.core.model.dto.OCDataDTO;
import info.ivicel.augmented.core.model.dto.StorePageDataDTO;
import info.ivicel.augmented.core.model.dto.StorePageDataDTO.Chart;
import info.ivicel.augmented.core.model.dto.StorePageDataDTO.Hltb;
import info.ivicel.augmented.core.model.dto.StorePageDataDTO.MetacriticScore;
import info.ivicel.augmented.core.model.dto.StorePageDataDTO.Survey;
import info.ivicel.augmented.core.model.entity.EarlyAccess;
import info.ivicel.augmented.core.model.entity.Exfgls;
import info.ivicel.augmented.core.model.entity.GameLinks;
import info.ivicel.augmented.core.model.entity.GameSurvey;
import info.ivicel.augmented.core.model.entity.Metacritic;
import info.ivicel.augmented.core.model.entity.OpenCritic;
import info.ivicel.augmented.core.model.entity.SteamCharts;
import info.ivicel.augmented.core.model.entity.SteamReviews;
import info.ivicel.augmented.core.model.entity.SteamSpy;
import info.ivicel.augmented.core.result.Response;
import info.ivicel.augmented.service.DlcCategoryService;
import info.ivicel.augmented.service.EarlyAccessService;
import info.ivicel.augmented.service.ExfglsService;
import info.ivicel.augmented.service.GameLinksService;
import info.ivicel.augmented.service.GameSurveyService;
import info.ivicel.augmented.service.MetacriticService;
import info.ivicel.augmented.service.OpenCriticService;
import info.ivicel.augmented.service.SteamChartsService;
import info.ivicel.augmented.service.SteamReviewsService;
import info.ivicel.augmented.service.SteamSpyService;
import info.ivicel.augmented.utils.CurrencyUtils;
import info.ivicel.augmented.utils.Http;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

@Slf4j
@Setter(onMethod = @__(@Autowired))
@RestController
@RequestMapping(API.VERSION_URL)
public class MainController {
    private AugmentedProperties augmentedProperties;
    private SteamReviewsService steamReviewsService;
    private SteamChartsService steamChartsService;
    private DlcCategoryService dlcCategoryService;
    private EarlyAccessService earlyAccessService;
    private OpenCriticService opencriticService;
    private SteamSpyService steamSpyService;
    private GameSurveyService gameSurveyService;
    private ExfglsService exfglsService;
    private GameLinksService gameLinksService;
    private MetacriticService metacriticService;

    @GetMapping("/dlcinfo")
    public ResponseEntity dlcInfo(@RequestParam("appid") Integer appid) {
        return Response.success(dlcCategoryService.findByAppid(appid));
    }

    @GetMapping("/earlyaccess")
    public ResponseEntity earlyAccess() {
        return Response.success(earlyAccessService.findAll().
                stream().collect(Collectors.toMap(EarlyAccess::getAppid, EarlyAccess::getAppid)));
    }

    @GetMapping("/prices")
    public ResponseEntity<Response> prices(String stores, String appids, String subids,
            String bundleids, String cc, String coupon) {
        appids = Http.format(appids, i -> "app/" + i);
        subids = Http.format(subids, i -> "sub/" + i);
        bundleids = Http.format(bundleids, i -> "bundle/" + i);
        String ids = "";

        if (StringUtils.hasText(appids)) {
            ids = appids + ",";
        }

        if (StringUtils.hasText(subids)) {
            ids += subids + ",";
        }

        if (StringUtils.hasText(bundleids)) {
            ids += bundleids;
        }
        if (!StringUtils.hasText(ids)) {
            return Response.fail400();
        }

        ResponseEntity<Response> response = null;
        Http.RequestParam params = Http.requestParam()
                .add("key", augmentedProperties.getIsThereAnyDealKey())
                .add("shop", "steam")
                .add("ids", ids)
                .add("country", cc);

        if (StringUtils.hasText(stores)) {
            params.add("allowed", stores);
        }

        if (coupon != null) {
            params.add("optional", "voucher");
        }
        try {
            response = Http.getForEntity(API.ISTHEREANYDEAL, Object.class, params);
        } catch (RestClientException e) {
            log.error("Request error: ", e);
            response = Response.fail400();
        }

        return response;
    }

    @GetMapping("/rates")
    public ResponseEntity rates(@RequestParam String to) {
        String[] list = to.split(",");
        if (list.length <= 0) {
            return Response.fail400();
        }

        Map<String, Map<String, Double>> results = CurrencyUtils.getAllConversionTo(list);

        return Response.success(results);
    }

    @GetMapping("/storepagedata")
    public ResponseEntity<Response> storePageData(@RequestParam("appid") Integer appid,
            @RequestParam(value = "r_all", required = false) Integer reviewsAll,
            @RequestParam(value = "r_pos", required = false) Integer reviewsPositive,
            @RequestParam(value = "r_stm", required = false) Integer reviewsPurchasedOnSteam,
            String mcurl, String oc) {
        StorePageDataDTO storePageDataDTO = new StorePageDataDTO();

        if (!StringUtils.isEmpty(oc)) {
            storePageDataDTO.setOc(getOpenCriticData(appid));
        }

        storePageDataDTO.setSteamSpy(getSteamSpy(appid));
        storePageDataDTO.setSurvey(getSurvey(appid));
        storePageDataDTO.setExfgls(getExfgls(appid));
        storePageDataDTO.setHltb(getHtlb(appid));

        if (!StringUtils.isEmpty(mcurl) &&
                "https://www.metacritic.com/".equalsIgnoreCase(mcurl.trim().substring(0, 27))) {
            storePageDataDTO.setMetacriticScore(MetacriticScore.builder()
                    .userScore(getMetacritic(mcurl)).build());
        }

        return Response.success(storePageDataDTO);
    }

    // store steam reviews
    private void getSteamView(Integer appid, Integer reviewsAll,
            Integer reviewsPositive, Integer reviewsPurchasedOnSteam) {
        long currentTime = System.currentTimeMillis();
        Optional<SteamReviews> steamReviews = steamReviewsService.findByAppid(appid);
        SteamReviews review;

        if (steamReviews.isPresent()) {
            review = steamReviews.get();
            if (currentTime - review.getUpdateTime().getTime() >= 43200) {
                if (reviewsAll > review.getTotal() ||
                        reviewsPositive >= review.getPos() ||
                        reviewsPurchasedOnSteam >= review.getStm() ||
                        currentTime - review.getUpdateTime().getTime() >= 25900) {
                    review.setPos(reviewsPositive);
                    review.setTotal(reviewsAll);
                    review.setStm(reviewsPurchasedOnSteam);
                    steamReviewsService.update(review);
                }
            }
        } else {
            review = new SteamReviews();
            review.setAppid(appid);
            review.setPos(reviewsPositive);
            review.setTotal(reviewsAll);
            review.setStm(reviewsPurchasedOnSteam);
            steamReviewsService.add(review);
        }
    }

    // getForEntity SteamChart data
    private Chart getSteamChart(Integer appid, Integer reviewsAll,
            Integer reviewsPositive, Integer reviewsPurchasedOnSteam) {
        long currentTime = System.currentTimeMillis();
        Optional<SteamCharts> optionalSteamCharts = steamChartsService.findByAppid(appid);

        if (optionalSteamCharts.isPresent()) {
            SteamCharts steamCharts = optionalSteamCharts.get();
            if (currentTime - steamCharts.getAccessTime().getTime() >= 3600) {
                steamChartsService.remove(steamCharts);
                return getNewChartValue(appid);
            } else {
                return Chart.builder().current(steamCharts.getOneHour())
                        .peaktoday(steamCharts.getOneDay())
                        .peakall(steamCharts.getAllTime())
                        .build();
            }
        }

        return getNewChartValue(appid);
    }

    // opencritic data
    private OCDataDTO getOpenCriticData(Integer appid) {
        Optional<OpenCritic> optionalOpencritic = opencriticService.findByAppid(appid);
        if (optionalOpencritic.isPresent()) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                return mapper.readValue(optionalOpencritic.get().getJson(), OCDataDTO.class);
            } catch (IOException e) {
                log.error("Error to parse string to json of " + appid);
                return new OCDataDTO();
            }
        }

        return getNewOCValue(appid);
    }

    // steam spy data
    private SteamSpy getSteamSpy(Integer appid) {
        Optional<SteamSpy> optionalSteamSpy = steamSpyService.findByAppid(appid);
        if (optionalSteamSpy.isPresent()) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - optionalSteamSpy.get().getAccessTime().getTime() < 43200) {
                return optionalSteamSpy.get();
            }

            steamSpyService.remove(optionalSteamSpy.get());
        }

        return getNewSteamSpyValue(appid);
    }

    // WSGF data
    private void getWSGFData(Integer appid) {
        String url = augmentedProperties.getWSGFEndpoint().endsWith("/") ?
                String.format("%s%d", augmentedProperties.getWSGFEndpoint(), appid) :
                String.format("%s/%d", augmentedProperties.getWSGFEndpoint(), appid);

        String response = Http.getForObject(url, String.class);

    }

    // survey data
    private Survey getSurvey(Integer appid) {
        List<GameSurvey> gameSurveys = gameSurveyService.findByAppid(appid);

        Survey survey = new Survey();
        if (gameSurveys.isEmpty()) {
            survey.setSuccess(false);
        } else {
            survey.setSuccess(true);
            survey.setResponse(gameSurveys.size());
        }

        Map<String, Integer> fr = new HashMap<>();
        fr.put("30", 0);
        fr.put("fi", 0);
        fr.put("va", 0);
        fr.put("ns", 0);

        int gsY = 0, gsN = 0;
        int nvidia = 0, amd = 0, intel = 0, other = 0;
        int nvidiaY = 0, amdY = 0, intelY = 0, otherY = 0;
        int less = 1, hd = 1, wqhd = 1, fk = 1;
        int lessY = 0, hdY = 0, wqhdY = 0, fkY = 0;

        for (GameSurvey gameSurvey : gameSurveys) {
            if (StringUtils.isEmpty(gameSurvey.getFr())) {
                continue;
            }

            int f = fr.getOrDefault(gameSurvey.getFr(), 0);
            fr.put(gameSurvey.getFr(), ++f);

            boolean flag = gameSurvey.getFs().equalsIgnoreCase("yes");

            if (gameSurvey.getMr().equalsIgnoreCase("less")) {
                less++;
                if (flag) {
                    lessY++;
                }
            } else if (gameSurvey.getMr().equalsIgnoreCase("hd")) {
                hd++;
                if (flag) {
                    hdY++;
                }
            } else if (gameSurvey.getMr().equalsIgnoreCase("wqhd")) {
                wqhd++;
                if (flag) {
                    wqhdY++;
                }
            } else if (gameSurvey.getMr().equalsIgnoreCase("4k")) {
                fk++;
                if (flag) {
                    fkY++;
                }
            }

            String gs = gameSurvey.getGs();
            if (gs.equalsIgnoreCase("yes")) {
                gsY++;
            } else if (gs.equalsIgnoreCase("no")) {
                gsN++;
            }

            String gc = gameSurvey.getGc();
            flag = gameSurvey.getPw().equalsIgnoreCase("yes");
            if (gc.equalsIgnoreCase("nvidia")) {
                nvidia++;
                if (flag) {
                    nvidiaY++;
                }
            } else if (gc.equalsIgnoreCase("amd")) {
                amd++;
                if (flag) {
                    amdY++;
                }
            } else if (gc.equalsIgnoreCase("intel")) {
                intel++;
                if (flag) {
                    intelY++;
                }
            } else if (gc.equalsIgnoreCase("ns")) {
                other++;
                if (flag) {
                    otherY++;
                }
            }
        }

        int numRows = gameSurveys.isEmpty() ? 1 : gameSurveys.size();

        if (fr.get("30") >= fr.get("fi") && fr.get("30") >= fr.get("va")) {
            survey.setFr("30");
            survey.setFrp(Math.round(fr.get("30") / (float) numRows) * 100);
        } else if (fr.get("fi") > fr.get("30") && fr.get("fi") > fr.get("va")) {
            survey.setFr("fi");
            survey.setFrp(Math.round(fr.get("fi") / (float) numRows) * 100);
        } else if (fr.get("va") > fr.get("30") && fr.get("va") >= fr.get("fi")) {
            survey.setFr("va");
            survey.setFrp(Math.round(fr.get("va") / (float) numRows) * 100);
        }

        float lessAvg = (float) lessY / less;
        float hdAvg = (float) hdY / hd;
        float wqhdAvg = (float) wqhdY / wqhd;
        float fkAvg = (float) fkY / fk;

        if (fkAvg >= wqhdAvg && fkAvg >= hdAvg && fkAvg >= lessAvg) {
            survey.setMr("4k");
        } else if (wqhdAvg >= fkAvg && wqhdAvg >= hdAvg && wqhdAvg >= lessAvg) {
            survey.setMr("wqhd");
        } else if (hdAvg >= fkAvg && hdAvg >= wqhdAvg && hdAvg >= lessAvg) {
            survey.setMr("hd");
        } else {
            survey.setMr("less");
        }

        // Determine Game Settings rendered
        survey.setGs(gsY >= gsN);

        // Determine satisfaction rates rendered
        if (nvidia > 0) {
            survey.setNvidia(Math.round((float) nvidiaY / nvidia) * 100);
        }

        if (amd > 0) {
            survey.setAmd(Math.round((float) amdY / amd) * 100);
        }

        if (intel > 0) {
            survey.setIntel(Math.round((float) intelY / intel) * 100);
        }

        if (other > 0) {
            survey.setOther(Math.round((float) otherY / other) * 100);
        }

        return survey;
    }

    // EXFGLS data
    private info.ivicel.augmented.core.model.dto.StorePageDataDTO.Exfgls getExfgls(Integer appid) {
        Optional<Exfgls> optionalExfgls = exfglsService.findByAppid(appid);

        return info.ivicel.augmented.core.model.dto.StorePageDataDTO.Exfgls.builder()
                .appid(appid)
                .excluded(optionalExfgls.isPresent())
                .build();
    }

    // htlb data
    private Hltb getHtlb(Integer appid) {
        Optional<GameLinks> optionalGameLinks = gameLinksService.findByAppid(appid);
        Hltb result = new Hltb();
        result.setSuccess(false);

        if (optionalGameLinks.isPresent()) {
            String mainStory = "", mainExtras = "", comp = "";
            GameLinks g = optionalGameLinks.get();
            String hltbUrl = "http://www.howlongtobeat.com/game.php?id=" + g.getHltbId();
            String hltbSumitUrl = "https://howlongtobeat.com/submit.php?s=add&gid=" + g.getHltbId();

            String response = Http.getForObject(hltbUrl, String.class);

            Matcher mainStoryMatcher = Pattern.compile("<h5>Main Story</h5>\\n(.+)\\n").matcher(response),
                    mainExtrasMatcher = Pattern.compile("<h5>Main \\+ Extras</h5>\\n(.+)\\n").matcher(response),
                    compMatcher = Pattern.compile("<h5>Completionist</h5>\\n(.+)\\n/").matcher(response);

            if (mainStoryMatcher.find()) {
                mainStory = mainStoryMatcher.group(1).trim();
            }

            if (mainExtrasMatcher.find()) {
                mainExtras = mainExtrasMatcher.group(1).trim();
            }

            if (compMatcher.find()) {
                comp = compMatcher.group(1).trim();
            }

            result.setSuccess(true);
            result.setMainStory(mainStory);
            result.setMainExtra(mainExtras);
            result.setComp(comp);
            result.setUrl(hltbUrl);
            result.setSubmitUrl(hltbSumitUrl);
        }

        return result;
    }

    // optional Metacritic data
    private double getMetacritic(String mcurl) {
        Optional<Metacritic> optionalMetacritic = metacriticService.findByMcurl(mcurl);
        if (optionalMetacritic.isPresent()) {
            long currentTime = System.currentTimeMillis();
            Metacritic metacritic = optionalMetacritic.get();

            if (currentTime - metacritic.getAccessTime().getTime() < 28800) {
                return metacritic.getScore();
            }

            metacriticService.remove(metacritic);
        }

        return getNewMetacriticValue(mcurl);
    }

    private Chart getNewChartValue(Integer appid) {
        // Redirect some apps
        switch (appid) {
            case 201270:
                appid = 34330;
                break;

            case 262341:
                appid = 39210;
                break;

            case 262342:
                appid = 39210;
                break;
        }

        String[] nums = new String[]{null, null, null};
        try {
            String url = "http://steamcharts.com/app/" + appid;
            String response = Http.getForObject(url, String.class);

            String findStart = "<div id=\"app-heading\" class=\"content\">";
            String findEnd = "<div id=\"app-hours-content\" class=\"content\">";

            int start = response.indexOf(findStart);
            int end = response.indexOf(findEnd);

            String subString = response.substring(start, end - start);
            Pattern pattern = Pattern.compile("<span\\s+class=\"num\">(.+)</span>");
            Matcher matcher = pattern.matcher(subString);

            for (int i = 0; i < 3 && matcher.find(); i++) {
                nums[i] = matcher.group(1);
            }

            SteamCharts steamCharts = new SteamCharts();
            steamCharts.setAppid(appid);
            steamCharts.setOneHour(nums[0]);
            steamCharts.setOneDay(nums[1]);
            steamCharts.setAllTime(nums[2]);
            steamChartsService.insert(steamCharts);

        } catch (RestClientException e) {
            // nothing
        }

        return Chart.builder().current(nums[0]).peaktoday(nums[1]).peakall(nums[2]).build();
    }

    private OCDataDTO getNewOCValue(Integer appid) {
        String url = augmentedProperties.getOpenCriticEndpoint();
        String key = augmentedProperties.getOpenCriticKey();
        if (StringUtils.isEmpty(url) || StringUtils.isEmpty(key)) {
            return new OCDataDTO();
        }

        if (!url.endsWith("/")) {
            url += "/";
        }

        url = url + appid + "&key=" + key;

        String response = Http.getForObject(url, String.class);
        response = response.replaceAll("\\\\/", "/");
        response = response.replaceAll("\u2013", "-");
        response = response.replace("\u00a0", " ");

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            OCDataDTO ocDataDTO = objectMapper.readValue(response, OCDataDTO.class);

            OpenCritic openCritic = new OpenCritic();
            openCritic.setAppid(appid);
            openCritic.setJson(objectMapper.writeValueAsString(ocDataDTO));

            opencriticService.insert(openCritic);

            return ocDataDTO;
        } catch (IOException e) {
            log.error("Can not fetch new OC data for " + appid + ": " + e.getMessage());
        }

        return new OCDataDTO();
    }

    private SteamSpy getNewSteamSpyValue(Integer appid) {
        String url = augmentedProperties.getSteamSpyEndpoint();
        if (!url.endsWith("/")) {
            url += "/";
        }

        url += appid;

        SteamSpy response = Http.getForObject(url, SteamSpy.class);
        response.setAppid(appid);
        steamSpyService.insert(response);

        return response;
    }

    private double getNewMetacriticValue(String mcurl) {
        String response = Http.getForObject(mcurl, String.class);
        Matcher matcher = Pattern.compile("metascore_w user(.+)\">(.+)</div>").matcher(response);

        try {
            if (matcher.find()) {
                double score = Double.valueOf(matcher.group(2));

                Metacritic metacritic = new Metacritic();
                metacritic.setMcurl(mcurl);
                metacritic.setScore(score);
                metacriticService.insert(metacritic);

                return score;
            }
        } catch (NumberFormatException e) {
            log.error("Fetch metacritic score error: " + e.getMessage());
        }

        return 0;
    }
}
