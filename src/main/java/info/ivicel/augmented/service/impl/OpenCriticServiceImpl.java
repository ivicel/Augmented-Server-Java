package info.ivicel.augmented.service.impl;

import info.ivicel.augmented.core.model.entity.OpenCritic;
import info.ivicel.augmented.repository.OpenCriticRepository;
import info.ivicel.augmented.service.OpenCriticService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("openCriticService")
public class OpenCriticServiceImpl implements OpenCriticService {
    private OpenCriticRepository openCriticRepository;

    @Autowired
    public OpenCriticServiceImpl(OpenCriticRepository openCriticRepository) {
        this.openCriticRepository = openCriticRepository;
    }

    @Override
    public Optional<OpenCritic> findByAppid(Integer appid) {
        return openCriticRepository.findByAppid(appid);
    }

    @Override
    @Transactional
    public void insert(OpenCritic openCritic) {
        openCriticRepository.save(openCritic);
    }
}
