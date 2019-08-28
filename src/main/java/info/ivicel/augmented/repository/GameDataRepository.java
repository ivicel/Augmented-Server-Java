package info.ivicel.augmented.repository;

import info.ivicel.augmented.core.model.entity.GameData;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface GameDataRepository extends JpaSpecificationExecutorWithProjection<GameData, Integer> {
    List<GameData> findByAppid(Integer appid);
}
