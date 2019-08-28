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
@Table(name = "profile_users", schema = "es_db", catalog = "")
public class ProfileUsers {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "steam64", nullable = false, length = 64)
    private String steam64;

    @Column(name = "profile_background_img", nullable = false, length = 1024)
    private String profileBackgroundImg;

    @LastModifiedDate
    @Column(name = "update_time", nullable = false)
    private Timestamp updateTime;

    @Column(name = "appid", nullable = false, length = 19)
    private String appid;
}
