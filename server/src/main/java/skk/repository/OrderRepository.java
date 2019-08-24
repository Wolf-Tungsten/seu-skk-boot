package skk.repository;

import org.springframework.data.repository.CrudRepository;
import skk.entity.Orders;

import java.util.List;

public interface OrderRepository extends CrudRepository<Orders,String> {
    List<Orders> findAllByMvoId(String id);
    List<Orders> findAllByBvoId(String id);
    Orders findALLById(String id);
}
