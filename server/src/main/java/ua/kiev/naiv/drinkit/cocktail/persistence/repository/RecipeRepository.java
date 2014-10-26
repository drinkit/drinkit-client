package ua.kiev.naiv.drinkit.cocktail.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.RecipeEntity;

import java.util.List;

public interface RecipeRepository extends JpaRepository<RecipeEntity, Integer>, JpaSpecificationExecutor<RecipeEntity> {

    List<RecipeEntity> findByNameContaining(String namePart);

}
