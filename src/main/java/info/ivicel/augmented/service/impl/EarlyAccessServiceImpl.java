package info.ivicel.augmented.service.impl;

import info.ivicel.augmented.core.model.entity.EarlyAccess;
import info.ivicel.augmented.repository.EarlyAccessRepository;
import info.ivicel.augmented.service.EarlyAccessService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("earlyAccessService")
public class EarlyAccessServiceImpl implements EarlyAccessService {
    private EarlyAccessRepository earlyAccessRepository;

    @Autowired
    public EarlyAccessServiceImpl(EarlyAccessRepository earlyAccessRepository) {
        this.earlyAccessRepository = earlyAccessRepository;
    }

    @Override
    public void deleteAll() {
        earlyAccessRepository.deleteAll();
    }

    @Override
    public void saveAll(Iterable<EarlyAccess> earlyAccesses) {
        earlyAccessRepository.saveAll(earlyAccesses);
    }

    @Override
    public List<EarlyAccess> findAll() {
        return earlyAccessRepository.findAll();
    }
}
