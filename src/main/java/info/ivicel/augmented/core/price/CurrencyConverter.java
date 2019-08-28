package info.ivicel.augmented.core.price;

import info.ivicel.augmented.core.model.dto.CurrencyConvertDTO;
import info.ivicel.augmented.core.model.entity.Currency;
import info.ivicel.augmented.core.model.entity.Currency.CurrencyEmbeddedId;
import info.ivicel.augmented.service.CurrencyService;
import info.ivicel.augmented.utils.Http;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CurrencyConverter {
    private CurrencyService currencyService;
    private Map<String, Currency> conversion;

    @Autowired
    public CurrencyConverter(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    private static final String[] SUPPORTED = {
            "GBP", "HKD", "IDR", "ILS", "DKK",
            "INR", "CHF", "MXN", "CZK", "SGD",
            "THB", "HRK", "EUR", "MYR", "NOK",
            "CNY", "BGN", "PHP", "PLN", "ZAR",
            "CAD", "ISK", "BRL", "RON", "NZD",
            "TRY", "JPY", "RUB", "KRW", "USD",
            "AUD", "HUF", "SEK"
    };
    private static final int UPDATE_TIMESTAMP = 6 * 60 * 60;

    public Map<String, Map<String, Double>> getAllConversionTo(String[] list) {
        Map<String, Long> updated = new HashMap<>();
        conversion = new HashMap<>();

        for (int i = 0; i < list.length; i++) {
            list[i] = list[i].toUpperCase();
        }

        currencyService.findByToIn(list).forEach(c -> {
            String key = makeKey(c.getEmbeddedId().getFrom(), c.getEmbeddedId().getTo());
            conversion.put(key, c);
            updated.put(key, c.getTimestamp().getTime());
        });

        long currentTime = System.currentTimeMillis();
        for (String from : SUPPORTED) {
            for (String to : list) {
                String key = makeKey(from, to);
                Currency currency = conversion.get(key);
                if (currency == null || currentTime - updated.get(key) > UPDATE_TIMESTAMP) {
                    loadAllConversionFromWeb(from, Arrays.asList(list));
                    break;
                }
            }
        }

        currencyService.saveAll(conversion.values());
        Map<String, Map<String, Double>> results = new HashMap<>();
        for (String from : SUPPORTED) {
            Map<String, Double> map = new HashMap<>();
            for (String to : list) {
                Currency currency = conversion.get(makeKey(from, to));
                if (currency != null) {
                    map.put(currency.getEmbeddedId().getTo(), currency.getRate());
                }
            }
            results.put(from, map);
        }

        return results;
    }

    private void loadAllConversionFromWeb(String from, List<String> symbols) {
        symbols.forEach(to -> {
            String key = makeKey(from, to);
            if (!conversion.containsKey(key)) {
                Currency currency = new Currency();
                currency.setEmbeddedId(CurrencyEmbeddedId.builder().from(from).to(to).build());
                conversion.put(key, currency);
            }
        });

        CurrencyConvertDTO currencyConvert = Http.getForObject("https://api.ratesapi.io/api/latest",
                CurrencyConvertDTO.class,
                Http.requestParam().add("base", from)
                        .add("symbols", String.join(",", symbols)));
        currencyConvert.getRates().forEach((k, v) -> conversion.get(makeKey(from, k)).setRate(v));
    }

    private String makeKey(String from, String to) {
        return from + "_" + to;
    }
}
