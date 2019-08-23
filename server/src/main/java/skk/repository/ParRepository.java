package skk.repository;


import org.springframework.data.repository.CrudRepository;
import skk.entity.Par;

import java.util.List;

public interface ParRepository extends CrudRepository<Par, String> {
    List<Par> findAll();
}
