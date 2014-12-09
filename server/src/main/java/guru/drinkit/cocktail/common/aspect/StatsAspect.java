//package guru.drinkit.cocktail.common.aspect;
//
//import guru.drinkit.cocktail.service.RecipeStatisticsService;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import guru.drinkit.cocktail.common.DrinkitUtils;
//
///**
// * @author pkolmykov
// */
//@Component
//@Aspect
//public class StatsAspect {
//    @Autowired
//    RecipeStatisticsService recipeStatisticsService;
//
//    @After(value = "@annotation(EnableStats) && args(id)")
//    public void writeStats(int id) {
//        if (DrinkitUtils.getCurrentUserId() != null) {
//            recipeStatisticsService.incrementViewsCount(id, 0);
//        }
//    }
//
//
//}
