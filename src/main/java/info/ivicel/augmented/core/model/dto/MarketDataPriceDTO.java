package info.ivicel.augmented.core.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class MarketDataPriceDTO {
    private String name;

    private String game;

    private String img;

    private String url;

    private Double price;

    public MarketDataPriceDTO(String name, String game, String img, String url, Double price) {
        this.name = name;
        this.game = game;
        this.img = img;
        this.url = url;
        this.price = price;
    }

    public MarketDataPriceDTO(String name, String img) {
        this.name = name;
        this.img = img;
    }
}
