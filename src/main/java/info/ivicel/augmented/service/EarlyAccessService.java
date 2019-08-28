package info.ivicel.augmented.service;

import info.ivicel.augmented.core.model.entity.EarlyAccess;
import java.util.List;

public interface EarlyAccessService {

    void deleteAll();

    void saveAll(Iterable<EarlyAccess> earlyAccesses);

    List<EarlyAccess> findAll();
}
