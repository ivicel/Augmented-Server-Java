package info.ivicel.augmented.service;

import info.ivicel.augmented.core.model.entity.GameLinks;
import java.util.Optional;

public interface GameLinksService {

    Optional<GameLinks> findByAppid(Integer appid);
}
