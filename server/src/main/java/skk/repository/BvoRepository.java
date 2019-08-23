package skk.repository;

import org.springframework.data.repository.CrudRepository;
import skk.entity.Bvo;

public interface BvoRepository extends CrudRepository<Bvo,String> {
   // Bvo findAllByUserid(String userid); //根据user外键查

}
