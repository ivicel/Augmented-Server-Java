package info.ivicel.augmented.core.model.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "steamrep")
@EntityListeners(AuditingEntityListener.class)
public class SteamRep {
    @Builder
    public SteamRep(long steam64, String rep) {
        this.steam64 = steam64;
        this.rep = rep;
    }

    @Id
    @Column(name = "steam64", nullable = false)
    private long steam64;

    @Column(name = "rep", nullable = false)
    private String rep;

    @LastModifiedDate
    @Column(name = "access_time", nullable = false)
    private Date accessTime;
}
