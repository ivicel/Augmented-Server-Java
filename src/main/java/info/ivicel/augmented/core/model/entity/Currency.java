package info.ivicel.augmented.core.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
public class Currency {
    @EmbeddedId
    private CurrencyEmbeddedId embeddedId;

    @Column(name = "rate", nullable = false)
    private double rate;

    @LastModifiedDate
    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;

    @Data
    @Builder
    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CurrencyEmbeddedId implements Serializable {
        private static final long serialVersionUID = 4296638048949526941L;

        @Column(name = "[from]", nullable = false, length = 3)
        private String from;

        @Column(name = "[to]", nullable = false, length = 3)
        private String to;
    }

}
