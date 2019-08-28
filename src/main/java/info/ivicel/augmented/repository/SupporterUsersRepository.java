package info.ivicel.augmented.repository;

import info.ivicel.augmented.core.model.dto.SupporterProjection;
import info.ivicel.augmented.core.model.entity.SupporterUsers;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public interface SupporterUsersRepository extends
        JpaSpecificationExecutorWithProjection<SupporterUsers, Integer> {

    List<SupporterProjection> findSupporterProjectionBySteamId(String steamId, Sort sort);

}
