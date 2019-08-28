package info.ivicel.augmented.service.impl;

import info.ivicel.augmented.core.model.entity.GameSurvey;
import info.ivicel.augmented.repository.GameSurveyRepository;
import info.ivicel.augmented.service.GameSurveyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("gameSurveyService")
public class GameSurveyServiceImpl implements GameSurveyService {
    private GameSurveyRepository gameSurveyRepository;

    @Autowired
    public GameSurveyServiceImpl(GameSurveyRepository gameSurveyRepository) {
        this.gameSurveyRepository = gameSurveyRepository;
    }

    @Override
    public List<GameSurvey> findByAppid(Integer appid) {
        return gameSurveyRepository.findByAppid(appid);
    }
}
