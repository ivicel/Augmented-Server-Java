package info.ivicel.augmented.service.impl;

import info.ivicel.augmented.core.model.entity.Currency;
import info.ivicel.augmented.repository.CurrencyRepository;
import info.ivicel.augmented.service.CurrencyService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("currencyService")
public class CurrencyServiceImpl implements CurrencyService {
    private CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public List<Currency> findByToIn(String[] list) {
        return currencyRepository.findByEmbeddedIdToIn(list);
    }

    @Override
    public void saveAll(Iterable<Currency> currencies) {
        currencyRepository.saveAll(currencies);
    }

    @Override
    public Optional<Currency> findByFromAndTo(String from, String to) {
        return currencyRepository.findByEmbeddedIdFromAndEmbeddedIdTo(from, to);
    }
}
