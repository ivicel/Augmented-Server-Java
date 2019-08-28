package info.ivicel.augmented.service;

import info.ivicel.augmented.core.model.entity.GameData;
import java.util.List;

public interface GameDataService {

    List<GameData> findByAppid(Integer appid);
}
