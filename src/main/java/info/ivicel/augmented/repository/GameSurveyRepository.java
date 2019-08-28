package info.ivicel.augmented.repository;

import info.ivicel.augmented.core.model.entity.GameSurvey;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface GameSurveyRepository extends JpaSpecificationExecutorWithProjection<GameSurvey, Long> {
    List<GameSurvey> findByAppid(Integer appid);
}
