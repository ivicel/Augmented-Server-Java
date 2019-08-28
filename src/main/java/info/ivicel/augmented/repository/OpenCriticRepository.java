package info.ivicel.augmented.repository;

import info.ivicel.augmented.core.model.entity.OpenCritic;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpenCriticRepository extends JpaRepository<OpenCritic, Integer> {
    Optional<OpenCritic> findByAppid(Integer appid);
}
