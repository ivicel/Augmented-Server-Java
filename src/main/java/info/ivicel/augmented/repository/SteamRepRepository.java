package info.ivicel.augmented.repository;

import info.ivicel.augmented.core.model.entity.SteamRep;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SteamRepRepository extends JpaRepository<SteamRep, Long> {
    Optional<SteamRep> findBySteam64(long steam64);

    @Modifying
    @Query("delete from SteamRep where steam64 = :steam64")
    void removeBySteam64(@Param("steam64") long steam64);

}
