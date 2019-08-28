package info.ivicel.augmented.service;

import info.ivicel.augmented.core.model.entity.ProfileStyleUsers;
import java.util.Optional;

public interface ProfileStyleUsersService {
    Optional<ProfileStyleUsers> findBySteam64(Long steamId);
}
