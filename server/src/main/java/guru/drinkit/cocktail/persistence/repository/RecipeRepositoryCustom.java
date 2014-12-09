package guru.drinkit.cocktail.persistence.repository;

import guru.drinkit.cocktail.persistence.search.Criteria;
import guru.drinkit.cocktail.web.dto.RecipeDto;

import java.util.List;

/**
 * Created by pkolmykov on 12/8/2014.
 */
public interface RecipeRepositoryCustom {
    List<RecipeDto> findByCriteria(Criteria criteria);
}
