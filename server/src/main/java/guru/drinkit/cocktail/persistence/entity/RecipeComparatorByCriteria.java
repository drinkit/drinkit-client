//package guru.drinkit.cocktail.persistence.entity;
//
//import guru.drinkit.cocktail.persistence.search.Criteria;
//import guru.drinkit.cocktail.web.dto.RecipeDto;
//
//import java.util.Comparator;
//import java.util.HashSet;
//import java.util.Set;
//
//public class RecipeComparatorByCriteria implements Comparator<RecipeDto> {
//
//    private final Criteria criteria;
//
//    public RecipeComparatorByCriteria(Criteria criteria) {
//        this.criteria = criteria;
//    }
//
//    @Override
//    public int compare(RecipeDto recipe1, RecipeDto recipe2) {
//        int result = getOverlapRate(recipe2) - getOverlapRate(recipe1);
//        if(result == 0){
//            result = recipe1.getIngredientIds().size() - recipe2.getIngredientIds().size();
//        }
//        return result;
//    }
//
//    private int getOverlapRate(RecipeDto recipe) {
//        Set<Integer> tmp = new HashSet<>(criteria.getIngredients());
//        tmp.retainAll(recipe.ge());
//        return tmp.size();
//    }
//}
