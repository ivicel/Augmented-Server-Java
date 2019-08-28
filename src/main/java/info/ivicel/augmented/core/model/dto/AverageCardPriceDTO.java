package info.ivicel.augmented.core.model.dto;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Data;

@Data
public class AverageCardPriceDTO {

    private Map<Integer, Map<String, Map<String, Double>>> data = new LinkedHashMap<>();

    public void add(Integer appid, String rarity, Double average) {
        Map<String, Double> price = new HashMap<>();
        price.put("average", average);

        Map<String, Map<String, Double>> t = data.computeIfAbsent(appid, k -> new HashMap<>());
        t.put(rarity, price);
    }
}
