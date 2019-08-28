package info.ivicel.augmented.service.impl;

import info.ivicel.augmented.core.model.entity.Exfgls;
import info.ivicel.augmented.repository.ExfglsRepository;
import info.ivicel.augmented.service.ExfglsService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("exfglsService")
public class ExfglsServiceImpl implements ExfglsService {
    private ExfglsRepository exfglsRepository;

    @Autowired
    public ExfglsServiceImpl(ExfglsRepository exfglsRepository) {
        this.exfglsRepository = exfglsRepository;
    }

    @Override
    public Optional<Exfgls> findByAppid(Integer appid) {
        return exfglsRepository.findByAppid(appid);
    }
}
