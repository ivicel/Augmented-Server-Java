package info.ivicel.augmented.service.impl;

import info.ivicel.augmented.core.model.dto.AverageCardPriceDTO;
import info.ivicel.augmented.core.model.dto.MarketDataGameDTO;
import info.ivicel.augmented.core.model.dto.MarketDataPriceDTO;
import info.ivicel.augmented.core.model.entity.MarketData;
import info.ivicel.augmented.repository.MarketDataRepository;
import info.ivicel.augmented.service.MarketDataService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("marketDataService")
public class MarketDataServiceImpl implements MarketDataService {
    private MarketDataRepository marketDataRepository;

    @Autowired
    public MarketDataServiceImpl(MarketDataRepository marketDataRepository) {
        this.marketDataRepository = marketDataRepository;
    }

    @Override
    public AverageCardPriceDTO findByAppidsAndRarity(List<Integer> appids, List<Integer> foilAppids) {
        return marketDataRepository.findByAppidAndRarity(appids, foilAppids);
    }

    @Override
    @Transactional
    public void saveAll(Iterable<MarketData> iterable) {
        marketDataRepository.saveAll(iterable);
    }

    @Override
    public List<MarketDataPriceDTO> findByTypeAndAppid(String type, Integer appid, Sort sort) {
        return marketDataRepository.findByTypeAndAppid(type, appid, sort);
    }

    @Override
    public List<MarketDataGameDTO> findGameDTOByType(String type, Sort sort) {
        return marketDataRepository.findGameDTOByType(type, sort);
    }
}
