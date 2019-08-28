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
@Table(name = "game_survey")
public class GameSurvey {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "appid", nullable = false)
    private int appid;

    @LastModifiedDate
    @Column(name = "timestamp", nullable = false)
    private Date timestamp;

    @Column(name = "steamid", nullable = false)
    private long steamid;

    @Column(name = "mr", nullable = false, length = 4)
    private String mr;

    @Column(name = "fs", nullable = false, length = 3)
    private String fs;

    @Column(name = "fr", nullable = false, length = 2)
    private String fr;

    @Column(name = "gs", nullable = false, length = 3)
    private String gs;

    @Column(name = "pw", nullable = false, length = 3)
    private String pw;

    @Column(name = "gc", nullable = false, length = 6)
    private String gc;
}
