package info.ivicel.augmented.core.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "supporter_badges")
public class SupporterBadges {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "img")
    private String img;
}
