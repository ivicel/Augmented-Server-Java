package info.ivicel.augmented.service.impl;

import info.ivicel.augmented.core.model.entity.GameData;
import info.ivicel.augmented.repository.GameDataRepository;
import info.ivicel.augmented.service.GameDataService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("gameDataService")
public class GameDataServiceImpl implements GameDataService {
    private GameDataRepository gamedataRepository;

    @Autowired
    public GameDataServiceImpl(GameDataRepository gamedataRepository) {
        this.gamedataRepository = gamedataRepository;
    }

    @Override
    public List<GameData> findByAppid(Integer appid) {
        return gamedataRepository.findByAppid(appid);
    }
}
