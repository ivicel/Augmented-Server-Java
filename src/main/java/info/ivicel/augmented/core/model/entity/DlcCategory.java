package info.ivicel.augmented.core.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "dlc_category")
public class DlcCategory {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "category_name", nullable = false, length = 45)
    private String categoryName;

    @Column(name = "category_icon", nullable = false, length = 300)
    private String categoryIcon;

    @Column(name = "category_text", nullable = false, length = 300)
    private String categoryText;

    @JsonBackReference
    @OneToMany(mappedBy = "dlcCategory")
    private List<GameData> gameDatas;
}
