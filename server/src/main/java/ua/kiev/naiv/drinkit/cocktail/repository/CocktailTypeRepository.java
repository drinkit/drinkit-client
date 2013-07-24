package ua.kiev.naiv.drinkit.cocktail.repository;

import org.springframework.data.repository.Repository;
import ua.kiev.naiv.drinkit.cocktail.model.CocktailType;

public interface CocktailTypeRepository extends Repository<CocktailType, Integer> {

    CocktailType findOne(Integer id);

    Iterable<CocktailType> findAll();

}
