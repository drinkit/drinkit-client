package guru.drinkit.cocktail.persistence.repository;

import guru.drinkit.cocktail.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
