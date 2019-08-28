package info.ivicel.augmented.service.impl;

import info.ivicel.augmented.core.model.entity.SteamCharts;
import info.ivicel.augmented.repository.SteamchartsRepository;
import info.ivicel.augmented.service.SteamChartsService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("steamChartsService")
public class SteamChartsServiceImpl implements SteamChartsService {
    private SteamchartsRepository steamChartsRepository;

    @Autowired
    public SteamChartsServiceImpl(SteamchartsRepository steamChartsRepository) {
        this.steamChartsRepository = steamChartsRepository;
    }

    @Override
    public Optional<SteamCharts> findByAppid(Integer appid) {
        return steamChartsRepository.findByAppid(appid);
    }

    @Override
    @Transactional
    public void remove(SteamCharts steamCharts) {
        steamChartsRepository.delete(steamCharts);
    }

    @Override
    @Transactional
    public void insert(SteamCharts steamCharts) {
        steamChartsRepository.save(steamCharts);
    }
}
