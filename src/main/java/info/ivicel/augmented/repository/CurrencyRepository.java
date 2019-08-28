package info.ivicel.augmented.repository;

import info.ivicel.augmented.core.model.entity.Currency;
import info.ivicel.augmented.core.model.entity.Currency.CurrencyEmbeddedId;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends
        JpaSpecificationExecutorWithProjection<Currency, CurrencyEmbeddedId> {

    List<Currency> findByEmbeddedIdToIn(String[] list);

    Optional<Currency> findByEmbeddedIdFromAndEmbeddedIdTo(String from, String to);
}
