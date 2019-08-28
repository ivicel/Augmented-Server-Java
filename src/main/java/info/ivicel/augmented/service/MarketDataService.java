package info.ivicel.augmented.service;

import info.ivicel.augmented.core.model.dto.AverageCardPriceDTO;
import info.ivicel.augmented.core.model.dto.MarketDataGameDTO;
import info.ivicel.augmented.core.model.dto.MarketDataPriceDTO;
import info.ivicel.augmented.core.model.entity.MarketData;
import java.util.List;
import org.springframework.data.domain.Sort;

public interface MarketDataService {
    AverageCardPriceDTO findByAppidsAndRarity(List<Integer> appids, List<Integer> foilAppids);

    void saveAll(Iterable<MarketData> iterable);

    List<MarketDataPriceDTO> findByTypeAndAppid(String type, Integer appid, Sort sort);

    List<MarketDataGameDTO> findGameDTOByType(String type, Sort sort);
}
