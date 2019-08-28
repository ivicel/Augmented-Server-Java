package info.ivicel.augmented.service;

import info.ivicel.augmented.core.model.entity.SteamRep;
import java.util.Optional;

public interface SteamRepService {
    Optional<SteamRep> findBySteam64(Long steamId);

    void removeBySteam64(Long steamId);

    void insert(SteamRep steamRep);
}
