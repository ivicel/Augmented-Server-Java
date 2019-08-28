package info.ivicel.augmented.core.model.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@Entity
@Table(name = "game_links")
public class GameLinks {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "appid", nullable = false)
    private int appid;

    @Column(name = "hltb_id", nullable = false)
    private int hltbId;

    @Column(name = "steam_id", nullable = false)
    private long steamId;

    @LastModifiedDate
    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;
}
