package guru.drinkit.cocktail.persistence.repository;

import guru.drinkit.cocktail.web.dto.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);
}
