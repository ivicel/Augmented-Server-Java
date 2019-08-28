package info.ivicel.augmented.core.model.entity;

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
@Table(name = "steamcn")
@EntityListeners(AuditingEntityListener.class)
public class Steamcn {
    @Id
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "appid", nullable = false)
    private int appid;

    @Column(name = "json", nullable = false, length = 4096)
    private String json;

    @LastModifiedDate
    @Column(name = "access_time", nullable = false)
    private Date accessTime;
}
