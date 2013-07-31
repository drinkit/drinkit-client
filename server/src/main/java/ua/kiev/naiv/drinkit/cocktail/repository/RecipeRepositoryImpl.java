package ua.kiev.naiv.drinkit.cocktail.repository;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import ua.kiev.naiv.drinkit.cocktail.model.Recipe;
import ua.kiev.naiv.drinkit.cocktail.search.CriteriaPOJO;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * @author pkolmykov
 */
public class RecipeRepositoryImpl extends SimpleJpaRepository<Recipe, Integer> implements RecipeRepository {
    private final EntityManager entityManager;

    public RecipeRepositoryImpl(Class domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public List<Recipe> findByCriteria(CriteriaPOJO criteria) {
        CriteriaQuery<Recipe> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Recipe.class);
//        Metamodel m = entityManager.cregetMetamodel();
//        EntityType<Recipe> Recipe_ = m.entity(Recipe.class);
////        EntityType<Ingredient> Ingredient_ = m.entity(Ingredient.class);
//        EntityType<CocktailType> CocktailType_ = m.entity(CocktailType.class);
//        Root<Recipe> recipeRoot = criteriaQuery.from(Recipe.class);
//        Join<Recipe, CocktailType> cocktailTypeJoin = recipeRoot.join(Recipe_.cocktailType)
        return null;
    }
}
