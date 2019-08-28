package info.ivicel.augmented.service.impl;

import info.ivicel.augmented.core.model.entity.Steamcn;
import info.ivicel.augmented.repository.SteamcnRepository;
import info.ivicel.augmented.service.SteamcnService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("steamcnService")
public class SteamcnServiceImpl implements SteamcnService {
    private SteamcnRepository steamcnRepository;

    @Autowired
    public SteamcnServiceImpl(SteamcnRepository steamcnRepository) {
        this.steamcnRepository = steamcnRepository;
    }

    @Override
    public Optional<Steamcn> findByAppid(Integer appid) {
        return steamcnRepository.findByAppid(appid);
    }

    @Override
    @Transactional
    public void insert(Steamcn steamcn) {
        steamcnRepository.save(steamcn);
    }

    @Override
    @Transactional
    public void remove(Steamcn steamcn) {
        steamcnRepository.delete(steamcn);
    }
}
