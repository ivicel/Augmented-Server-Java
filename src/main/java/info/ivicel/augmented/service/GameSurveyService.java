package info.ivicel.augmented.service;

import info.ivicel.augmented.core.model.entity.GameSurvey;
import java.util.List;

public interface GameSurveyService {
    List<GameSurvey> findByAppid(Integer appid);
}
