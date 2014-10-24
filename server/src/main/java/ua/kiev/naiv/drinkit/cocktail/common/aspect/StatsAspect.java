package ua.kiev.naiv.drinkit.cocktail.common.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.kiev.naiv.drinkit.cocktail.common.DrinkitUtils;
import ua.kiev.naiv.drinkit.cocktail.service.RecipeStatisticsService;

/**
 * @author pkolmykov
 */
@Component
@Aspect
public class StatsAspect {
    @Autowired
    RecipeStatisticsService recipeStatisticsService;

    @After(value = "@annotation(EnableStats) && args(id)")
    public void writeStats(int id) {
        if (DrinkitUtils.getCurrentUserId() != null) {
            recipeStatisticsService.incrementViewsCount(id, 0);
        }
    }


}
