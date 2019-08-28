package info.ivicel.augmented.service.impl;

import info.ivicel.augmented.core.model.entity.ProfileStyleUsers;
import info.ivicel.augmented.repository.ProfileStyleUsersRepository;
import info.ivicel.augmented.service.ProfileStyleUsersService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("profileStyleUsersService")
public class ProfileStyleUsersServiceImpl implements ProfileStyleUsersService {
    private ProfileStyleUsersRepository profileStyleUsersRepository;

    @Autowired
    public ProfileStyleUsersServiceImpl(
            ProfileStyleUsersRepository profileStyleUsersRepository) {
        this.profileStyleUsersRepository = profileStyleUsersRepository;
    }

    @Override
    public Optional<ProfileStyleUsers> findBySteam64(Long steamId) {
        return profileStyleUsersRepository.findBySteam64(steamId);
    }
}
