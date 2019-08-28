package info.ivicel.augmented.service.impl;

import info.ivicel.augmented.core.model.dto.DlcInfoDTO;
import info.ivicel.augmented.repository.DlcCategoryRepository;
import info.ivicel.augmented.service.DlcCategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dlcCategoryService")
public class DlcCategoryServiceImpl implements DlcCategoryService {
    private DlcCategoryRepository dlcCategoryRepository;

    @Autowired
    public DlcCategoryServiceImpl(DlcCategoryRepository dlcCategoryRepository) {
        this.dlcCategoryRepository = dlcCategoryRepository;
    }

    @Override
    public List<DlcInfoDTO> findByAppid(Integer appid) {
        return dlcCategoryRepository.findByGameDatasAppid(appid);
    }
}
