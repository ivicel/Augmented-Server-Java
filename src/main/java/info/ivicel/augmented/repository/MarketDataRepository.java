package info.ivicel.augmented.repository;

import info.ivicel.augmented.core.model.dto.MarketDataGameDTO;
import info.ivicel.augmented.core.model.dto.MarketDataPriceDTO;
import info.ivicel.augmented.core.model.entity.MarketData;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketDataRepository extends CustomMarketDataRepository,
        JpaSpecificationExecutorWithProjection<MarketData, String> {

    @Query("select new info.ivicel.augmented.core.model.dto.MarketDataPriceDTO(name, game, img, url, price) "
            + " from MarketData where type = :type and appid = :appid")
    List<MarketDataPriceDTO> findByTypeAndAppid(@Param("type") String type,
            @Param("appid") Integer appid, Sort sort);

    List<MarketDataGameDTO> findGameDTOByType(String type, Sort sort);
}
