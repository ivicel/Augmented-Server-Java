package info.ivicel.augmented.repository;

import info.ivicel.augmented.core.model.entity.EarlyAccess;
import org.springframework.stereotype.Repository;

@Repository
public interface EarlyAccessRepository extends JpaSpecificationExecutorWithProjection<EarlyAccess, Integer> {
}
