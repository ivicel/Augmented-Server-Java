package info.ivicel.augmented.repository.support;

import info.ivicel.augmented.core.model.dto.AverageCardPriceDTO;
import info.ivicel.augmented.core.model.entity.MarketData;
import info.ivicel.augmented.core.model.entity.MarketData_;
import info.ivicel.augmented.repository.CustomMarketDataRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class CustomMarketDataRepositoryImpl implements CustomMarketDataRepository {
    private static final String AVERAGE = "average";
    private static final String COUNT = "count";
    private static final String RARITY = "foil";
    private static final String APPID = "appid";
    private final EntityManager entityManager;

    public CustomMarketDataRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public AverageCardPriceDTO findByAppidAndRarity(List<Integer> appids, List<Integer> foilAppids) {
        // SELECT appid, rarity = 'foil' AS foil, AVG(price) AS average, count(*) AS `count`
        // FROM  market_data WHERE TYPE = 'card'
        // AND ((appid IN (503390) AND rarity != 'foil') OR (appid IN (503390) AND rarity = 'foil'));
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = builder.createTupleQuery();
        Root<MarketData> root = query.from(MarketData.class);
        query.multiselect(root.get(MarketData_.appid).alias(APPID),
                root.get(MarketData_.rarity).alias(RARITY),
                builder.avg(root.get(MarketData_.price)).alias(AVERAGE),
                builder.count(root.get(MarketData_.price)).alias(COUNT));

        Predicate predicate = builder.equal(root.get(MarketData_.type), "card");
        Predicate containsPredicate = getPredicate(builder, root, appids, foilAppids);
        if (containsPredicate != null) {
            predicate = builder.and(predicate, containsPredicate);
        }

        query.where(predicate);
        query.groupBy(root.get(MarketData_.appid), root.get(MarketData_.rarity));

        AverageCardPriceDTO av = new AverageCardPriceDTO();
        entityManager.createQuery(query).getResultList().forEach(r ->
                    av.add((Integer) r.get(APPID), (String) r.get(RARITY), (Double) r.get(AVERAGE))
        );

        return av;
    }

    private Predicate getPredicate(CriteriaBuilder builder, Root<MarketData> root,
            List<Integer> appids, List<Integer> foilAppids) {
        Predicate appidPredicate = null;
        if (appids != null && !appids.isEmpty()) {
            Predicate p1 = root.get(MarketData_.appid).in(appids);
            Predicate p2 = builder.notEqual(root.get(MarketData_.rarity), "foil");
            appidPredicate = builder.and(p1, p2);
        }

        Predicate foilAppidPredicate = null;
        if (foilAppids != null && !foilAppids.isEmpty()) {
            foilAppidPredicate = builder.and(root.get("appid").in(foilAppids),
                    builder.equal(root.get("rarity"), "foil"));
            if (appidPredicate != null) {
                foilAppidPredicate = builder.or(appidPredicate, foilAppidPredicate);
            }
        }

        return foilAppidPredicate != null ? foilAppidPredicate : appidPredicate;
    }
}
