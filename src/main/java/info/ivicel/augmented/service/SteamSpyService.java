package info.ivicel.augmented.service;

import info.ivicel.augmented.core.model.entity.SteamSpy;
import java.util.Optional;

public interface SteamSpyService {
    Optional<SteamSpy> findByAppid(Integer appid);

    void insert(SteamSpy steamSpy);

    void remove(SteamSpy steamSpy);
}
