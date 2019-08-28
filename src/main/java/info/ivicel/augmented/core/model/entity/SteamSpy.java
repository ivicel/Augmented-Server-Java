package info.ivicel.augmented.core.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Table(name = "steamspy")
@EntityListeners(AuditingEntityListener.class)
public class SteamSpy {
    @JsonIgnore
    @Id
    @Column(name = "appid", nullable = false)
    private int appid;

    @Column(name = "owners", nullable = false, length = 30)
    private String owners;

    @JsonProperty("owners_variance")
    @Column(name = "owners_variance")
    private Integer ownersVariance;

    @JsonProperty("players_forever")
    @Column(name = "players_forever")
    private Integer playersForever;

    @JsonProperty("players_forever_variance")
    @Column(name = "players_forever_variance")
    private Integer playersForeverVariance;

    @JsonProperty("players_2weeks")
    @Column(name = "players_2weeks")
    private Integer players2Weeks;

    @JsonProperty("players_2weeks_variance")
    @Column(name = "players_2weeks_variance")
    private Integer players2WeeksVariance;

    @JsonProperty("average_forever")
    @Column(name = "average_forever", nullable = false)
    private int averageForever;

    @JsonProperty("average_2weeks")
    @Column(name = "average_2weeks", nullable = false)
    private int average2Weeks;

    @JsonIgnore
    @LastModifiedDate
    @Column(name = "access_time", nullable = false)
    private Date accessTime;
}
