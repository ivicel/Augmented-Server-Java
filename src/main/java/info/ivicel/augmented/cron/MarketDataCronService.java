package info.ivicel.augmented.cron;

import info.ivicel.augmented.core.result.request.steamtools.CardData;
import info.ivicel.augmented.service.MarketDataService;
import info.ivicel.augmented.utils.Http;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


// todo: fetch market data from steam.tools
@Slf4j
// @Component
public class MarketDataCronService {
    private MarketDataService marketDataService;
    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_5) "
            + "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36";
    private static final String REFERER = "https://steam.tools/";

    @Autowired
    public MarketDataCronService(MarketDataService marketDataService) {
        this.marketDataService = marketDataService;
    }

    @Scheduled(cron = "* * * */7 * *")
    public void fetchMarketData() {
        log.info("Fetch market data");

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Referer", REFERER);
        headers.add("User-Agent", USER_AGENT);
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<CardData> cardDataResponseEntity = Http.exchange(
                "https://cdn.steam.tools/data/set_data.json", HttpMethod.GET, httpEntity, CardData.class);

        log.info("Finish fetch market data");
    }
}
