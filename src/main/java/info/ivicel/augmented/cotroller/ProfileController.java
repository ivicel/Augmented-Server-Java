package info.ivicel.augmented.cotroller;

import info.ivicel.augmented.common.API;
import info.ivicel.augmented.common.AugmentedProperties;
import info.ivicel.augmented.core.model.dto.MarketDataGameDTO;
import info.ivicel.augmented.core.model.dto.MarketDataPriceDTO;
import info.ivicel.augmented.core.model.dto.SteamUserProfileDTO;
import info.ivicel.augmented.core.model.dto.SteamUserProfileDTO.Background;
import info.ivicel.augmented.core.model.dto.SupporterProjection;
import info.ivicel.augmented.core.model.entity.ProfileStyleUsers;
import info.ivicel.augmented.core.model.entity.ProfileUsers;
import info.ivicel.augmented.core.model.entity.SteamRep;
import info.ivicel.augmented.core.result.Response;
import info.ivicel.augmented.core.result.request.steamrep.SteamRepReputation;
import info.ivicel.augmented.service.MarketDataService;
import info.ivicel.augmented.service.ProfileStyleUsersService;
import info.ivicel.augmented.service.ProfileUsersService;
import info.ivicel.augmented.service.SteamRepService;
import info.ivicel.augmented.service.SupporterUsersService;
import info.ivicel.augmented.utils.Http;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Setter(onMethod = @__({@Autowired}))
@RestController
@RequestMapping(API.VERSION_URL + "/profile")
public class ProfileController {
    private static final long STEAMREP_CACHE_TIME = 24 * 60 * 60;

    private MarketDataService marketDataService;
    private SupporterUsersService supporterUsersService;
    private ProfileStyleUsersService profileStyleUsersService;
    private ProfileUsersService profileUsersService;
    private SteamRepService steamRepService;
    private AugmentedProperties augmentedProperties;

    @GetMapping("/background/background")
    public ResponseEntity<Response> background(@RequestParam Integer appid) {
        List<MarketDataPriceDTO> results = marketDataService.findByTypeAndAppid("background", appid,
                Sort.by(Order.desc("name")));

        return Response.success(results.stream().map(r -> {
            List<String> items = new ArrayList<>();
            items.add(r.getImg());
            items.add(r.getName().replace("\\s*\\(Profile Background\\)", ""));
            return items;
        }).collect(Collectors.toList()));
    }

    @GetMapping("/background/games")
    public ResponseEntity<Response> game() {
        List<MarketDataGameDTO> results = marketDataService.findGameDTOByType("background",
                Sort.by(Order.asc("title")));

        return Response.success(results.stream().map(r -> new Object[]{r.getAppid(), r.getTitle()})
                .collect(Collectors.toList()));
    }

    // todo: profile background delete
    @GetMapping("/background/edit/delete")
    public ResponseEntity backgroundDelete() {
        return ResponseEntity.ok("edit-delete");
    }

    // todo: profile background save
    @GetMapping("/background/edit/save")
    public ResponseEntity backgroundSave(String appid, String img) {
        // String returnUrl = "https://steamcommunity.com/my/profile";
        // if (StringUtils.isEmpty(appid) || StringUtils.isEmpty(img)) {
        //     return
        // }

        return null;
    }

    // todo: profile style delete
    @GetMapping("/style/edit/delete")
    public ResponseEntity styleDelete() {
        return null;
    }

    // todo: profile style save
    @GetMapping("/style/edit/save")
    public ResponseEntity styleSave() {
        return null;
    }

    @GetMapping("/profile")
    public ResponseEntity profile(@RequestParam("profile") Long steam64) {
        List<SupporterProjection> supporterProjectionList =
                supporterUsersService.findSupporterProjectionBySteamId(steam64.toString(),
                        Sort.by("supporterBadges"));

        Optional<ProfileStyleUsers> optionalProfileStyleUsers =
                profileStyleUsersService.findBySteam64(steam64);

        Optional<ProfileUsers> optionalProfileUsers =
                profileUsersService.findBySteam64(steam64.toString());

        SteamUserProfileDTO.SteamUserProfileDTOBuilder result = SteamUserProfileDTO.builder();
        result.steamrep(getSteamRep(steam64)).badges(supporterProjectionList);
        optionalProfileStyleUsers.ifPresent(s -> result.style(s.getProfileStyle()));

        ProfileUsers profileUsers = optionalProfileUsers.orElseGet(ProfileUsers::new);
        result.bg(Background.builder().appid(profileUsers.getAppid())
                .img(profileUsers.getProfileBackgroundImg()).build());

        return Response.success(result.build());
    }

    public List<String> getSteamRep(Long steam64) {
        long currentTime = System.currentTimeMillis();
        List<String> result = Collections.emptyList();

        Optional<SteamRep> optionalSteamRep = steamRepService.findBySteam64(steam64);
        if (optionalSteamRep.isPresent() &&
                currentTime - optionalSteamRep.get().getAccessTime().getTime() < STEAMREP_CACHE_TIME) {
            return Arrays.asList(optionalSteamRep.get().getRep().split(","));
        }

        steamRepService.removeBySteam64(steam64);

        return fetchNewRep(steam64);
    }

    public List<String> fetchNewRep(Long steam64) {
        String steamRepEndpoint = augmentedProperties.getSteamRepEndpoint();
        if (StringUtils.isEmpty(steamRepEndpoint)) {
            return Collections.emptyList();
        }

        if (!steamRepEndpoint.endsWith("/")) {
            steamRepEndpoint += "/";
        }

        steamRepEndpoint += steam64;

        SteamRepReputation repReputation = Http.getForObject(steamRepEndpoint, SteamRepReputation.class);
        if ("exists".equals(repReputation.getStatus())) {
            String rep = repReputation.getReputation().getFull();
            steamRepService.insert(SteamRep.builder().steam64(steam64).rep(rep).build());

            return Arrays.asList(rep.split(","));
        }

        return Collections.emptyList();
    }
}
