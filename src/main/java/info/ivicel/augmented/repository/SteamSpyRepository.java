package info.ivicel.augmented.repository;

import info.ivicel.augmented.core.model.entity.SteamSpy;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SteamSpyRepository extends JpaRepository<SteamSpy, Integer> {
    Optional<SteamSpy> findByAppid(Integer appid);
}
