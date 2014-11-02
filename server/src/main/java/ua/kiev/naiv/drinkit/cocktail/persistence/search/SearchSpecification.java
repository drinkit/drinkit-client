package ua.kiev.naiv.drinkit.cocktail.persistence.search;

import org.hibernate.jpa.criteria.CriteriaBuilderImpl;
import org.hibernate.jpa.criteria.predicate.InPredicate;
import org.springframework.data.jpa.domain.Specification;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.*;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

public class SearchSpecification {

    public static Specification<Recipe> byCriteria(final Criteria criteria) {
        return (root, query, cb) -> {
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
                        (CriteriaBuilderImpl) cb, ingredientJoin.get(IngredientWithQuantity_.recipeIngredientId)
                        .get(RecipeIngredientId_.ingredient).get(Ingredient_.id), criteria.getIngredients()));
            }

            return predicate;
        };
    }

}
