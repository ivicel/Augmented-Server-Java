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
@Table(name = "profile_style_users")
public class ProfileStyleUsers {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "steam64", nullable = false)
    private long steam64;

    @Column(name = "profile_style", nullable = false, length = 13)
    private String profileStyle;

    @LastModifiedDate
    @Column(name = "update_time", nullable = false)
    private Timestamp updateTime;
}
