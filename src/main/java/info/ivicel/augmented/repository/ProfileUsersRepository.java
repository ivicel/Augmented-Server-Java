package info.ivicel.augmented.repository;

import info.ivicel.augmented.core.model.entity.ProfileUsers;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileUsersRepository extends JpaSpecificationExecutorWithProjection<ProfileUsers, Integer> {
    Optional<ProfileUsers> findBySteam64(String steam64);
}
