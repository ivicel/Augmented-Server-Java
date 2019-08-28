package info.ivicel.augmented.service;

import info.ivicel.augmented.core.model.entity.Currency;
import java.util.List;
import java.util.Optional;

public interface CurrencyService {

    List<Currency> findByToIn(String[] list);

    void saveAll(Iterable<Currency> currencies);

    Optional<Currency> findByFromAndTo(String from, String to);
}
