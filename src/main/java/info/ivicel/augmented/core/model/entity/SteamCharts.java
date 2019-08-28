package info.ivicel.augmented.core.model.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "steamcharts")
public class SteamCharts {
    @Id
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "appid", nullable = false)
    private int appid;

    @Column(name = "one_hour", nullable = false, length = 11)
    private String oneHour;

    @Column(name = "one_day", nullable = false, length = 11)
    private String oneDay;

    @Column(name = "all_time", nullable = false, length = 11)
    private String allTime;

    @Column(name = "access_time", nullable = false)
    private Timestamp accessTime;
}
