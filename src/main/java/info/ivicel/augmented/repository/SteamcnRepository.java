package info.ivicel.augmented.repository;

import info.ivicel.augmented.core.model.entity.Steamcn;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SteamcnRepository extends JpaRepository<Steamcn, Long> {
    Optional<Steamcn> findByAppid(Integer appid);
}
