package info.ivicel.augmented.service;

import info.ivicel.augmented.core.model.dto.DlcInfoDTO;
import java.util.List;

public interface DlcCategoryService {
    List<DlcInfoDTO> findByAppid(Integer appid);
}
