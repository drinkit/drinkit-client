package ua.kiev.naiv.drinkit.cocktail.persistence.model;

import ua.kiev.naiv.drinkit.cocktail.persistence.search.Criteria;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class RecipeComparatorByCriteria implements Comparator<RecipeEntity> {

    private final Criteria criteria;

    public RecipeComparatorByCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public int compare(RecipeEntity recipeEntity1, RecipeEntity recipeEntity2) {
        int result = getOverlapRate(recipeEntity2) - getOverlapRate(recipeEntity1);
        if(result == 0){
            result = recipeEntity1.getIngredientIds().size()- recipeEntity2.getIngredientIds().size();
        }
        return result;
    }

    private int getOverlapRate(RecipeEntity recipeEntity){
        Set<Integer> tmp = new HashSet<>(criteria.getIngredients());
        tmp.retainAll(recipeEntity.getIngredientIds());
        return tmp.size();
    }
}
