package skk.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import skk.entity.Store;

import java.util.List;

public interface StoreRepository extends CrudRepository<Store, String> {
    @Transactional
    List<Store> findAllBysellerid(String sellerid);
}
