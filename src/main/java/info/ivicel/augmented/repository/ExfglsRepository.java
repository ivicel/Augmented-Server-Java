package info.ivicel.augmented.repository;

import info.ivicel.augmented.core.model.entity.Exfgls;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface ExfglsRepository extends JpaSpecificationExecutorWithProjection<Exfgls, Integer> {

    Optional<Exfgls> findByAppid(Integer appid);
}
