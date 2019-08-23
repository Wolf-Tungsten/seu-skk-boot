package skk.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import skk.entity.BvoWish;

import java.util.List;


public interface BvowishRepository extends CrudRepository<BvoWish,String> {
    @Transactional
    void deleteAllByBvoidAndGoodsid(String bvoid,String goodsid);
    void deleteByBvoidAndGoodsid(String bvoid,String goodsid);
}
