package info.ivicel.augmented.service;

import info.ivicel.augmented.core.model.entity.Steamcn;
import java.util.Optional;

public interface SteamcnService {
    Optional<Steamcn> findByAppid(Integer appid);

    void insert(Steamcn steamcn);

    void remove(Steamcn steamcn);
}
