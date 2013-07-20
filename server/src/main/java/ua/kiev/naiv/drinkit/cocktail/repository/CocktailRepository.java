package ua.kiev.naiv.drinkit.cocktail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kiev.naiv.drinkit.cocktail.model.Cocktail;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 20.07.13
 * Time: 21:47
 */
public interface CocktailRepository extends JpaRepository<Cocktail, Integer> {
}
