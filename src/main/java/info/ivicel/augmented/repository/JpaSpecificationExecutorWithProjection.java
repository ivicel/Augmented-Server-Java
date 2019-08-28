package info.ivicel.augmented.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.query.JpaEntityGraph;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface JpaSpecificationExecutorWithProjection<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    <R> Optional<R> findOne(Specification<T> spec, Class<R> projectionClass);

    <R> List<R> findAll(Specification<T> spec, Class<R> projectionClass);

    <R> Page<R> findAll(Specification<T> spec, Class<R> projectionClass, Pageable pageable);

    <R> Page<R> findAll(Specification<T> spec, Class<R> projectionClass, String namedEntityGraph,
            EntityGraph.EntityGraphType type, Pageable pageable);

    <R> Page<R> findAll(Specification<T> spec, Class<R> projectionClass, JpaEntityGraph dynamicEntityGraph,
            Pageable pageable);
}
