package skk.repository;

import org.springframework.data.repository.CrudRepository;
import skk.entity.Goods;

import java.util.List;

public interface GoodsRepository extends CrudRepository<Goods,String> {
    List<Goods> findByName(String name);
    List<Goods> findAllByNameLike(String name);
    void deleteById(String id);
}
