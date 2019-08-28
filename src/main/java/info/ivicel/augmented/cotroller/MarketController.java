package info.ivicel.augmented.cotroller;

import info.ivicel.augmented.common.API;
import info.ivicel.augmented.core.model.dto.AverageCardPriceDTO;
import info.ivicel.augmented.core.model.dto.MarketDataPriceDTO;
import info.ivicel.augmented.core.model.entity.Currency;
import info.ivicel.augmented.core.result.Response;
import info.ivicel.augmented.service.CurrencyService;
import info.ivicel.augmented.service.MarketDataService;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(API.VERSION_URL + "/market")
public class MarketController {
    private MarketDataService marketDataService;
    private CurrencyService currencyService;

    @Autowired
    public MarketController(MarketDataService marketDataService, CurrencyService currencyService) {
        this.marketDataService = marketDataService;
        this.currencyService = currencyService;
    }

    @GetMapping("/averagecardprices")
    public ResponseEntity<Response> averageCardPrices(@RequestParam String currency, String appids, String foilappids) {
        Optional<Currency> optionalCurrency = currencyService.findByFromAndTo("USD", currency.toUpperCase());
        if (!optionalCurrency.isPresent()) {
            return Response.fail400();
        }

        List<Integer> normalAppids = Collections.emptyList();
        List<Integer> foilAppids = Collections.emptyList();
        if (StringUtils.hasText(appids)) {
            normalAppids = Arrays.stream(appids.split(","))
                    .map(Integer::valueOf).collect(Collectors.toList());
        }
        if (StringUtils.hasText(foilappids)) {
            foilAppids = Arrays.stream(foilappids.split(","))
                    .map(Integer::valueOf).collect(Collectors.toList());
        }

        if (normalAppids.isEmpty() && foilAppids.isEmpty()) {
            return Response.fail400();
        }

        AverageCardPriceDTO averageCardPriceDTO = marketDataService.findByAppidsAndRarity(
                normalAppids, foilAppids);
        return Response.success(averageCardPriceDTO.getData());
    }

    @GetMapping("/cardprices")
    public ResponseEntity<Response> cardPrices(@RequestParam Integer appid, @RequestParam String currency) {
        Optional<Currency> optionalCurrency = currencyService.findByFromAndTo("USD", currency);
        if (!optionalCurrency.isPresent()) {
            return Response.fail400();
        }

        List<MarketDataPriceDTO> results = marketDataService.findByTypeAndAppid("card", appid,
                Sort.by("name"));

        return Response.success(results.stream().peek(
                r -> r.setPrice(r.getPrice() * optionalCurrency.get().getRate()))
                .collect(Collectors.toMap(MarketDataPriceDTO::getName, Function.identity())));
    }
}
