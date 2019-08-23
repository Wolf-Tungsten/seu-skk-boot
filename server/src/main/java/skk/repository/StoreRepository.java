package skk.repository;

import org.springframework.data.repository.CrudRepository;
import skk.entity.Store;

public interface StoreRepository extends CrudRepository<Store,String> {
}
