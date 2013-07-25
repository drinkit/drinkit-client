package ua.kiev.naiv.drinkit.cocktail.repository;

import org.springframework.data.repository.Repository;
import ua.kiev.naiv.drinkit.cocktail.model.CocktailType;

import java.util.List;
import java.util.Set;

public interface CocktailTypeRepository extends Repository<CocktailType, Integer> {

    CocktailType findOne(Integer id);

    List<CocktailType> findAll();

}
