package skk.repository;

import org.springframework.data.repository.CrudRepository;
import skk.entity.BvoWish;

public interface WishListRepository extends CrudRepository<BvoWish,String> {

}
