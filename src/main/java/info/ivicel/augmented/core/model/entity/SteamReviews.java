package info.ivicel.augmented.core.model.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@Entity
@Table(name = "steam_reviews")
public class SteamReviews {

    @Id
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "appid", nullable = false)
    private int appid;

    @Column(name = "total", nullable = false)
    private int total;

    @Column(name = "pos", nullable = false)
    private int pos;

    @Column(name = "stm", nullable = false)
    private int stm;

    @LastModifiedDate
    @Column(name = "update_time", nullable = false)
    private Date updateTime;
}
