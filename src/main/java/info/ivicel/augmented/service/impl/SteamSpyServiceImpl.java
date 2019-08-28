package info.ivicel.augmented.service.impl;

import info.ivicel.augmented.core.model.entity.SteamSpy;
import info.ivicel.augmented.repository.SteamSpyRepository;
import info.ivicel.augmented.service.SteamSpyService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("steamSpyService")
public class SteamSpyServiceImpl implements SteamSpyService {
    private SteamSpyRepository steamSpyRepository;

    @Autowired
    public SteamSpyServiceImpl(SteamSpyRepository steamSpyRepository) {
        this.steamSpyRepository = steamSpyRepository;
    }

    @Override
    public Optional<SteamSpy> findByAppid(Integer appid) {
        return steamSpyRepository.findByAppid(appid);
    }

    @Override
    @Transactional
    public void insert(SteamSpy steamSpy) {
        steamSpyRepository.save(steamSpy);
    }

    @Override
    @Transactional
    public void remove(SteamSpy steamSpy) {
        steamSpyRepository.delete(steamSpy);
    }
}
