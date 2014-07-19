package ua.kiev.naiv.drinkit.cocktail.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.RecipeEntity;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 20.07.13
 * Time: 21:47
 */
public interface RecipeRepository extends JpaRepository<RecipeEntity, Integer>, JpaSpecificationExecutor<RecipeEntity> {
}
