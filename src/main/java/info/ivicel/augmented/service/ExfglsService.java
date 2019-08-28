package info.ivicel.augmented.service;

import info.ivicel.augmented.core.model.entity.Exfgls;
import java.util.Optional;

public interface ExfglsService {

    Optional<Exfgls> findByAppid(Integer appid);

}
