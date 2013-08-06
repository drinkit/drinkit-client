package ua.kiev.naiv.drinkit.cocktail.search;

import org.springframework.data.jpa.domain.Specification;
import ua.kiev.naiv.drinkit.cocktail.model.*;

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

                if (criteria.getOptions().size() > 0) {
                    Join<Recipe, Option> optionJoin = root.join(Recipe_.options);
                    for (int id : criteria.getOptions()) {
                        predicate = cb.and(predicate, cb.equal(optionJoin.get(Option_.id), id));
                    }
                }

                if (criteria.getIngredients().size() > 0) {
                    Join<Recipe, IngredientWithQuantity> ingredientJoin = root.join(Recipe_.ingredientsWithQuantities);
                    Predicate orPredicate = cb.disjunction();
                    for (int id : criteria.getIngredients()) {
                        orPredicate = cb.or(orPredicate, cb.equal(ingredientJoin.get(IngredientWithQuantity_.cocktailIngredientId)
                                .get(CocktailIngredientId_.ingredient).get(Ingredient_.id), id));
//                                CocktailIngredientId_.ingredient)
//                                .get(Ingredient_.id), id));
                    }
                    predicate = cb.and(predicate, orPredicate);
                }

//                Join<Recipe, CocktailType> join = root.join(Recipe_.cocktailType);
//                Predicate predicate = cb.equal(join.get(CocktailType_.id), 1);
//                cb.and()
                return predicate;
            }
        };
    }

}
