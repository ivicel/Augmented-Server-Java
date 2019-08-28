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
@Table(name = "metacritic")
@EntityListeners(AuditingEntityListener.class)
public class Metacritic {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "mcurl", nullable = false)
    private String mcurl;

    @Column(name = "score", nullable = false)
    private double score;

    @LastModifiedDate
    @Column(name = "access_time", nullable = false)
    private Timestamp accessTime;
}
