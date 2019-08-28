package info.ivicel.augmented.service;

import info.ivicel.augmented.core.model.entity.SteamCharts;
import java.util.Optional;

public interface SteamChartsService {
    Optional<SteamCharts> findByAppid(Integer appid);

    void remove(SteamCharts steamCharts);

    void insert(SteamCharts steamCharts);
}
