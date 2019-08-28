package info.ivicel.augmented.utils;

import info.ivicel.augmented.core.price.CurrencyConverter;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CurrencyUtils {
    private static CurrencyConverter converter;

    @Autowired
    public CurrencyUtils(CurrencyConverter converter) {
        if (CurrencyUtils.converter == null) {
            CurrencyUtils.converter = converter;
        }
    }

    public static Map<String, Map<String, Double>> getAllConversionTo(String[] list) {
        return converter.getAllConversionTo(list);
    }
}
