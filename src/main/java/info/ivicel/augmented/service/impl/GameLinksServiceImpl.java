package info.ivicel.augmented.service.impl;

import info.ivicel.augmented.core.model.entity.GameLinks;
import info.ivicel.augmented.repository.GameLinksRepository;
import info.ivicel.augmented.service.GameLinksService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("gameLinksService")
public class GameLinksServiceImpl implements GameLinksService {
    private GameLinksRepository gameLinksRepository;

    @Autowired
    public GameLinksServiceImpl(GameLinksRepository gameLinksRepository) {
        this.gameLinksRepository = gameLinksRepository;
    }

    @Override
    public Optional<GameLinks> findByAppid(Integer appid) {
        return gameLinksRepository.findByAppid(appid);
    }
}
