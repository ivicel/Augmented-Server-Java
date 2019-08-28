package info.ivicel.augmented.service.impl;

import info.ivicel.augmented.core.model.entity.Metacritic;
import info.ivicel.augmented.repository.MetacriticRepository;
import info.ivicel.augmented.service.MetacriticService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("metacriticService")
public class MetacriticServiceImpl implements MetacriticService {
    private MetacriticRepository metacriticRepository;

    @Autowired
    public MetacriticServiceImpl(MetacriticRepository metacriticRepository) {
        this.metacriticRepository = metacriticRepository;
    }

    @Override
    public Optional<Metacritic> findByMcurl(String mcurl) {
        return metacriticRepository.findByMcurl(mcurl);
    }

    @Override
    @Transactional
    public void remove(Metacritic metacritic) {
        metacriticRepository.delete(metacritic);

    }

    @Override
    @Transactional
    public void insert(Metacritic metacritic) {
        metacriticRepository.save(metacritic);
    }
}
