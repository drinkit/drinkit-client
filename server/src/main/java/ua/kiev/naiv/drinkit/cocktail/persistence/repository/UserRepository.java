package ua.kiev.naiv.drinkit.cocktail.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kiev.naiv.drinkit.cocktail.persistence.model.User;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 16.03.14
 * Time: 19:24
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
