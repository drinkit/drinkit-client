package ua.kiev.naiv.drinkit.cocktail.search;

import org.springframework.data.jpa.domain.Specification;
import ua.kiev.naiv.drinkit.cocktail.model.CocktailType;
import ua.kiev.naiv.drinkit.cocktail.model.CocktailType_;
import ua.kiev.naiv.drinkit.cocktail.model.Recipe;
import ua.kiev.naiv.drinkit.cocktail.model.Recipe_;

import javax.persistence.criteria.*;

/**
 * @author pkolmykov
 */
public class SearchSpecification {

    public static Specification<Recipe> byCriteria(final Criteria criteria) {
        return new Specification<Recipe>() {
            @Override
            public Predicate toPredicate(Root<Recipe> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (criteria.getCocktailTypes().size() > 0) {
                    Join<Recipe, CocktailType> cocktailTypeJoin = root.join(Recipe_.cocktailType);
                    for (int id : criteria.getCocktailTypes()) {
                        predicate = cb.and(predicate, cb.equal(cocktailTypeJoin.get(CocktailType_.id), id));
                    }
                }

//                Join<Recipe, CocktailType> join = root.join(Recipe_.cocktailType);
//                Predicate predicate = cb.equal(join.get(CocktailType_.id), 1);
//                cb.and()
                return predicate;
            }
        };
    }

}
