package ua.kiev.naiv.drinkit.cocktail.persistence.search;

import org.hibernate.jpa.criteria.CriteriaBuilderImpl;
import org.hibernate.jpa.criteria.predicate.InPredicate;
import org.springframework.data.jpa.domain.Specification;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.*;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

/**
 * @author pkolmykov
 */
public class SearchSpecification {

    public static Specification<RecipeEntity> byCriteria(final Criteria criteria) {
        return (root, query, cb) -> {
            query.groupBy(root.get(RecipeEntity_.id));
            Predicate predicate = cb.conjunction();

            if (criteria.getCocktailTypes().size() > 0) {
                Join<RecipeEntity, CocktailType> cocktailTypeJoin = root.join(RecipeEntity_.cocktailType);
                predicate = cb.and(predicate, new InPredicate<>(
                        (CriteriaBuilderImpl) cb, cocktailTypeJoin.get(CocktailType_.id), criteria.getCocktailTypes()));
            }

            if (criteria.getOptions().size() > 0) {
                Join<RecipeEntity, Option> optionJoin = root.join(RecipeEntity_.options);
                predicate = cb.and(predicate, new InPredicate<>(
                        (CriteriaBuilderImpl) cb, optionJoin.get(Option_.id), criteria.getOptions()));
                query.having(cb.greaterThanOrEqualTo(cb.count(optionJoin), (long) criteria.getOptions().size()));
            }

            if (criteria.getIngredients().size() > 0) {
                Join<RecipeEntity, IngredientWithQuantity> ingredientJoin = root.join(RecipeEntity_.ingredientsWithQuantities);
                predicate = cb.and(predicate, new InPredicate<>(
                        (CriteriaBuilderImpl) cb, ingredientJoin.get(IngredientWithQuantity_.cocktailIngredientId)
                        .get(CocktailIngredientId_.ingredient).get(Ingredient_.id), criteria.getIngredients()));
            }

            return predicate;
        };
    }

}
