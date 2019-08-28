package info.ivicel.augmented.core.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "early_access")
public class EarlyAccess {
    @Id
    @Column(name = "appid", nullable = false)
    private Integer appid;
}
