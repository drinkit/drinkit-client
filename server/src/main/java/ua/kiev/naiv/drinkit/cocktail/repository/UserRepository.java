package ua.kiev.naiv.drinkit.cocktail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kiev.naiv.drinkit.cocktail.model.User;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 16.03.14
 * Time: 19:24
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
