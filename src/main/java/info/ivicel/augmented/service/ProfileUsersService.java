package info.ivicel.augmented.service;

import info.ivicel.augmented.core.model.entity.ProfileUsers;
import java.util.Optional;

public interface ProfileUsersService {
    Optional<ProfileUsers> findBySteam64(String steamId);
}
