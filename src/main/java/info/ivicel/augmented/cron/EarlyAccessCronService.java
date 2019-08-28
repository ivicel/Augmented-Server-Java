package info.ivicel.augmented.cron;

import info.ivicel.augmented.core.model.entity.EarlyAccess;
import info.ivicel.augmented.service.EarlyAccessService;
import info.ivicel.augmented.utils.Http;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
// @Component
public class EarlyAccessCronService {
    private EarlyAccessService earlyAccessService;
    private Pattern pagePattern = Pattern.compile("&page=(\\d+)\"", Pattern.CASE_INSENSITIVE);
    private Pattern appidPattern = Pattern.compile("data-ds-appid=\"(\\d+)\"", Pattern.CASE_INSENSITIVE);

    @Autowired
    public EarlyAccessCronService(EarlyAccessService earlyAccessService) {
        this.earlyAccessService = earlyAccessService;
    }

    @Scheduled(cron = "* * * */7 * ?")
    public void fetchEarlyAccessData() {
        log.info("Start early access fetch...");
        int saveLimit = 0;
        int p = 1;
        int totalPages = 1;

        earlyAccessService.deleteAll();
        do {
            log.info("fetch page " + p + " of " + totalPages + " early access");
            String response = Http.getForObject("https://store.steampowered.com/search/results?"
                    + "term=&genre=Early%20Access&page=" + p, String.class);

            Matcher pageMatcher = pagePattern.matcher(response);
            if (p == 1) {
                while (pageMatcher.find()) {
                    totalPages = Integer.max(totalPages, Integer.valueOf(pageMatcher.group(1)));
                }
            }

            Matcher appidMatcher = appidPattern.matcher(response);
            if (appidMatcher.find()) {
                List<EarlyAccess> list = new LinkedList<>();
                do {
                    EarlyAccess earlyAccess = new EarlyAccess();
                    earlyAccess.setAppid(Integer.valueOf(appidMatcher.group(1)));
                    list.add(earlyAccess);
                } while (list.size() < 500 && appidMatcher.find());

                if (!list.isEmpty()) {
                    earlyAccessService.saveAll(list);
                    saveLimit += list.size();
                }
            }

            p++;
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {}
        } while (p < totalPages && saveLimit <= 500);

        log.info("Finish early access fetch...");
    }
}
