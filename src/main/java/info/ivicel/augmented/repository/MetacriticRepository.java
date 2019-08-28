package info.ivicel.augmented.repository;

import info.ivicel.augmented.core.model.entity.Metacritic;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetacriticRepository extends JpaRepository<Metacritic, Long> {

    Optional<Metacritic> findByMcurl(String mcurl);
}