package info.ivicel.augmented.repository;

import info.ivicel.augmented.core.model.entity.SteamCharts;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SteamchartsRepository extends JpaRepository<SteamCharts, Long> {
    Optional<SteamCharts> findByAppid(Integer appid);
}