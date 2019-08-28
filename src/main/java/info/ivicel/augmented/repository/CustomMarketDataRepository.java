package info.ivicel.augmented.repository;

import info.ivicel.augmented.core.model.dto.AverageCardPriceDTO;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomMarketDataRepository {
    AverageCardPriceDTO findByAppidAndRarity(List<Integer> appids, List<Integer> foilAppids);
}
