package ua.kiev.naiv.drinkit.cocktail.persistence.repository;

import org.springframework.data.repository.Repository;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.CocktailType;

import java.util.List;

public interface CocktailTypeRepository extends Repository<CocktailType, Integer> {

    CocktailType findOne(Integer id);

    List<CocktailType> findAll();

}
