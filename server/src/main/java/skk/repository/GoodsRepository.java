package skk.repository;

import org.springframework.data.repository.CrudRepository;
import skk.entity.Goods;

public interface GoodsRepository extends CrudRepository<Goods,String> {

}
