package ua.kiev.naiv.drinkit.cocktail.search;

import org.hibernate.ejb.criteria.CriteriaBuilderImpl;
import org.hibernate.ejb.criteria.predicate.InPredicate;
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
                query.groupBy(root.get(Recipe_.id));
                Predicate predicate = cb.conjunction();

                if (criteria.getCocktailTypes().size() > 0) {
                    Join<Recipe, CocktailType> cocktailTypeJoin = root.join(Recipe_.cocktailType);
                    predicate = cb.and(predicate, new InPredicate<>(
                            (CriteriaBuilderImpl) cb, cocktailTypeJoin.get(CocktailType_.id), criteria.getCocktailTypes()));
                }

                if (criteria.getOptions().size() > 0) {
                    Join<Recipe, Option> optionJoin = root.join(Recipe_.options);
                    predicate = cb.and(predicate, new InPredicate<>(
                            (CriteriaBuilderImpl) cb, optionJoin.get(Option_.id), criteria.getOptions()));
                    query.having(cb.greaterThanOrEqualTo(cb.count(optionJoin), (long) criteria.getOptions().size()));
                }

                if (criteria.getIngredients().size() > 0) {
                    Join<Recipe, IngredientWithQuantity> ingredientJoin = root.join(Recipe_.ingredientsWithQuantities);
                    predicate = cb.and(predicate, new InPredicate<>(
                            (CriteriaBuilderImpl) cb, ingredientJoin.get(IngredientWithQuantity_.cocktailIngredientId)
                            .get(CocktailIngredientId_.ingredient).get(Ingredient_.id), criteria.getIngredients()));
                }

                return predicate;
            }
        };
    }

}
