package info.ivicel.augmented.repository;

import info.ivicel.augmented.core.model.entity.ProfileStyleUsers;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileStyleUsersRepository extends
        JpaSpecificationExecutorWithProjection<ProfileStyleUsers, Integer> {
    Optional<ProfileStyleUsers> findBySteam64(long steam64);
}
