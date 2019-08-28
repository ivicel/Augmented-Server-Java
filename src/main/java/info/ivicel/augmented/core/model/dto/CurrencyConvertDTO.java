package info.ivicel.augmented.core.model.dto;

import java.util.Date;
import java.util.Map;
import lombok.Data;

@Data
public class CurrencyConvertDTO {
    private String base;

    private Date date;

    private Map<String, Double> rates;
}
