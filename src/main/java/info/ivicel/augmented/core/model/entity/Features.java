package info.ivicel.augmented.core.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class Features {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "category", nullable = false, length = 3)
    private String category;

    @Column(name = "name", nullable = false, length = 500)
    private String name;

    @Column(name = "active", nullable = false)
    private byte active;

    @Column(name = "screenshot_before", nullable = false, length = 255)
    private String screenshotBefore;

    @Column(name = "screenshot_after", nullable = false, length = 255)
    private String screenshotAfter;

    @Column(name = "description", nullable = false, length = -1)
    private String description;

}
