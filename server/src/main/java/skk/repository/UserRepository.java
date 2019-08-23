package skk.repository;

import org.springframework.data.repository.CrudRepository;
import skk.entity.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {
    int countByUsername(String username);
    List<User> findByToken(String token);
}
