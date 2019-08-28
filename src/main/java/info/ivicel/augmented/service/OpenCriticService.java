package info.ivicel.augmented.service;

import info.ivicel.augmented.core.model.entity.OpenCritic;
import java.util.Optional;

public interface OpenCriticService {
    Optional<OpenCritic> findByAppid(Integer appid);

    void insert(OpenCritic openCritic);
}
