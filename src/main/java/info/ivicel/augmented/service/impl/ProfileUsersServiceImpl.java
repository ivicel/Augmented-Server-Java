package info.ivicel.augmented.service.impl;

import info.ivicel.augmented.core.model.entity.ProfileUsers;
import info.ivicel.augmented.repository.ProfileUsersRepository;
import info.ivicel.augmented.service.ProfileUsersService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("profileUsersService")
public class ProfileUsersServiceImpl implements ProfileUsersService {
    private ProfileUsersRepository profileUsersRepository;

    @Autowired
    public ProfileUsersServiceImpl(ProfileUsersRepository profileUsersRepository) {
        this.profileUsersRepository = profileUsersRepository;
    }

    @Override
    public Optional<ProfileUsers> findBySteam64(String steamId) {
        return profileUsersRepository.findBySteam64(steamId);
    }
}
