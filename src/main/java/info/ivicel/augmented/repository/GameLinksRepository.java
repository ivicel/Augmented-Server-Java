package info.ivicel.augmented.repository;

import info.ivicel.augmented.core.model.entity.GameLinks;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface GameLinksRepository extends JpaSpecificationExecutorWithProjection<GameLinks, Integer> {
    Optional<GameLinks> findByAppid(Integer appid);
}
