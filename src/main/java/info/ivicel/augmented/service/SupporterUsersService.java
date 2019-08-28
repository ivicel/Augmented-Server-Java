package info.ivicel.augmented.service;

import info.ivicel.augmented.core.model.dto.SupporterProjection;
import java.util.List;
import org.springframework.data.domain.Sort;

public interface SupporterUsersService {

    List<SupporterProjection> findSupporterProjectionBySteamId(String steamId, Sort sort);
}
