package info.ivicel.augmented.core.model.entity;

import java.sql.Timestamp;
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
@Table(name = "opencritic")
@EntityListeners(AuditingEntityListener.class)
public class OpenCritic {
    @Id
    @Column(name = "appid", nullable = false)
    private Integer appid;

    @Column(name = "json", nullable = false, length = 5000)
    private String json;

    @LastModifiedDate
    @Column(name = "access_time", nullable = false)
    private Timestamp accessTime;
}
