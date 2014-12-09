package guru.drinkit.repository;

import guru.drinkit.domain.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by pkolmykov on 12/8/2014.
 */
public class RecipeRepositoryImpl implements RecipeRepositoryCustom {

    @Autowired
    private MongoOperations operations;

    @Override
    public List<Recipe> findByCriteria(guru.drinkit.common.Criteria criteria) {
        Criteria mongoCriteria = new Criteria();
        if (criteria.getIngredients().size() > 0) {
            mongoCriteria.and("cocktailIngredients").elemMatch(where("0").in(criteria.getIngredients()));
        }
        if (criteria.getOptions().size() > 0) {
            mongoCriteria.and("options").all(criteria.getOptions());
        }
        if (criteria.getCocktailTypes().size() > 0) {
            mongoCriteria.and("cocktailTypeId").in(criteria.getCocktailTypes());
        }

        return operations.find(query(mongoCriteria), Recipe.class);
    }
}
