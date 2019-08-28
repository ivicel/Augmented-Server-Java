package info.ivicel.augmented.service.impl;

import info.ivicel.augmented.core.model.entity.SteamRep;
import info.ivicel.augmented.repository.SteamRepRepository;
import info.ivicel.augmented.service.SteamRepService;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("steamRepService")
public class SteamRepServiceImpl implements SteamRepService {
    private SteamRepRepository steamRepRepository;

    @Autowired
    public SteamRepServiceImpl(SteamRepRepository steamRepRepository) {
        this.steamRepRepository = steamRepRepository;
    }

    @Override
    public Optional<SteamRep> findBySteam64(Long steamId) {
        return steamRepRepository.findBySteam64(steamId);
    }

    @Override
    @Transactional
    public void removeBySteam64(Long steamId) {
        steamRepRepository.removeBySteam64(steamId);
    }

    @Override
    @Transactional
    public void insert(SteamRep steamRep) {
        steamRepRepository.save(steamRep);
    }
}
