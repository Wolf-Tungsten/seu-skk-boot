package skk.repository;

import org.springframework.data.repository.CrudRepository;
import skk.entity.DD;

import java.util.List;

public interface DDRepository extends CrudRepository<DD, String>{
    List<DD> findAll();
}
