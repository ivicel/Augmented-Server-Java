package info.ivicel.augmented.service;

import info.ivicel.augmented.core.model.entity.Metacritic;
import java.util.Optional;

public interface MetacriticService {

    Optional<Metacritic> findByMcurl(String mcurl);

    void remove(Metacritic metacritic);

    void insert(Metacritic metacritic);

}
