package info.ivicel.augmented.repository.support;

import info.ivicel.augmented.repository.JpaSpecificationExecutorWithProjection;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.query.JpaEntityGraph;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;


@Slf4j
public class JpaSpecificationExecutorWithProjectionImpl<T, ID> extends SimpleJpaRepository<T, ID>
        implements JpaSpecificationExecutorWithProjection<T, ID> {
    private final EntityManager entityManager;
    private final ProjectionFactory projectionFactory;
    private final JpaEntityInformation entityInformation;

    public JpaSpecificationExecutorWithProjectionImpl(
            JpaEntityInformation<T, ?> entityInformation,
            EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.entityInformation = entityInformation;
        this.projectionFactory = new SpelAwareProxyProjectionFactory();
    }

    @Override
    public <R> Optional<R> findOne(Specification<T> spec, Class<R> projectionClass) {
        // TypedQuery<T> query = getQuery(spec, Sort.unsorted());
        // try {
        //     T result = query.getSingleResult();
        //     return Optional.of(projectionFactory.createProjection(projectionClass, result));
        // } catch (NoResultException e) {
        //     return Optional.empty();
        // }

        // CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        // CriteriaQuery<R> query = builder.createQuery(projectionClass);
        // Root<T> root = query.from(getDomainClass());
        // query.multiselect(root.get("appid"), root.get("rarity").alias("foil"));
        // query.where(builder.equal(root.get("appid"), 503390));
        // TypedQuery typedQuery = entityManager.createQuery(query);

        // return Optional.of(projectionFactory.createProjection(projectionClass, typedQuery.getSingleResult()));
        return null;
    }

    @Override
    public <R> List<R> findAll(Specification<T> spec, Class<R> projectionClass) {
        // CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        // CriteriaQuery<R> query = builder.createQuery(projectionClass);
        // Root<T> root = query.from(getDomainClass());
        // query.multiselect(root.get("appid"), root.get("rarity").alias("foil"), builder.avg(root.get("price")).alias("average"));
        // query.where(builder.equal(root.get("appid"), 503390));
        // TypedQuery<R> typedQuery = entityManager.createQuery(query);

        // return typedQuery.getResultStream().map(r -> {
        //     System.out.println(r);
        //     return projectionFactory.createProjection(projectionClass, r);
        // }).collect(Collectors.toList());

        // CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        // CriteriaQuery<Tuple> query = builder.createTupleQuery();
        // Root<T> root = query.from(getDomainClass());
        // query.multiselect(root.get("appid"), root.get("rarity").alias("foil"), builder.avg(root.get("price")).alias("average"));
        // query.where(builder.equal(root.get("appid"), 503390));
        // TypedQuery<Tuple> typedQuery = entityManager.createQuery(query);
        // List<Tuple> results = typedQuery.getResultList();
        // results.forEach(r -> {
        //     System.out.println(r.get("average"));
        // });
        //
        // // return typedQuery.getResultList();
        // return results.stream().map(r -> projectionFactory.createProjection(projectionClass, r)).collect(Collectors.toList());
        return null;
    }

    @Override
    public <R> Page<R> findAll(Specification<T> spec, Class<R> projectionClass, Pageable pageable) {
        return null;
    }

    @Override
    public <R> Page<R> findAll(Specification<T> spec, Class<R> projectionClass, String namedEntityGraph,
            EntityGraphType type, Pageable pageable) {
        return null;
    }

    @Override
    public <R> Page<R> findAll(Specification<T> spec, Class<R> projectionClass, JpaEntityGraph dynamicEntityGraph,
            Pageable pageable) {
        return null;
    }
}
