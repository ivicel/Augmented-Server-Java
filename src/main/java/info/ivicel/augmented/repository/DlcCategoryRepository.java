package info.ivicel.augmented.repository;

import info.ivicel.augmented.core.model.dto.DlcInfoDTO;
import info.ivicel.augmented.core.model.entity.DlcCategory;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DlcCategoryRepository extends JpaSpecificationExecutorWithProjection<DlcCategory, Integer> {
    @Query(value = "select d.category_name as name, d.category_icon as icon, d.category_text as `desc`, "
            + " count(*) as count from dlc_category as d join gamedata as g on d.id = g.dlc_category "
            + " where g.appid = :appid group by g.appid, g.dlc_category order by count desc",
            nativeQuery = true)
    List<DlcInfoDTO> findByGameDatasAppid(@Param("appid") Integer appid);
}
