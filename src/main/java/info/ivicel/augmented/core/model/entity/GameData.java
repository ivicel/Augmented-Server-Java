package info.ivicel.augmented.core.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "gamedata")
public class GameData {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "appid", nullable = false)
    private int appid;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "dlc_category", nullable = false)
    private DlcCategory dlcCategory;
}
