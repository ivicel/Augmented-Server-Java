package info.ivicel.augmented.core.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "supporter_users")
public class SupporterUsers {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "steam_id", nullable = false, length = 25)
    private String steamId;

    @OneToOne
    @JoinColumn(name = "badge_id", nullable = false)
    private SupporterBadges supporterBadges;

    @Column(name = "link")
    private String link;

    @Column(name = "email")
    private String email;
}
