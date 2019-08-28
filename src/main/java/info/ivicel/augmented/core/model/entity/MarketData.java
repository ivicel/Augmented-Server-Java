package info.ivicel.augmented.core.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "market_data")
public class MarketData {
    @Id
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "game", nullable = false)
    private String game;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "img", nullable = false, length = 1024)
    private String img;

    @Column(name = "appid", nullable = false)
    private int appid;

    @Column(name = "url", nullable = false, length = 1024)
    private String url;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "modified", nullable = false)
    private String modified;

    @Column(name = "rarity", nullable = false)
    private String rarity;
}
