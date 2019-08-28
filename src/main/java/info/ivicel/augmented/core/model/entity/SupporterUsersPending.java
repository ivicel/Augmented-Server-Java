package info.ivicel.augmented.core.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "supporter_users_pending")
public class SupporterUsersPending {
    @Id
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "steam_id", nullable = false, length = 25)
    private String steamId;

    @Column(name = "steam_name")
    private String steamName;

    @Column(name = "real_name")
    private String realName;
}
