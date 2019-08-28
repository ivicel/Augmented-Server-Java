package info.ivicel.augmented.service.impl;

import info.ivicel.augmented.core.model.dto.SupporterProjection;
import info.ivicel.augmented.repository.SupporterUsersRepository;
import info.ivicel.augmented.service.SupporterUsersService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("supporterUsersService")
public class SupporterUsersServiceImpl implements SupporterUsersService {
    private SupporterUsersRepository supporterUsersRepository;

    @Autowired
    public SupporterUsersServiceImpl(SupporterUsersRepository supporterUsersRepository) {
        this.supporterUsersRepository = supporterUsersRepository;
    }

    @Override
    public List<SupporterProjection> findSupporterProjectionBySteamId(String steamId, Sort sort) {
        return supporterUsersRepository.findSupporterProjectionBySteamId(steamId, sort);
    }
}
