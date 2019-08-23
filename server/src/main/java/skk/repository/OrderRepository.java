package skk.repository;

import org.springframework.data.repository.CrudRepository;
import skk.entity.Order;

public interface OrderRepository extends CrudRepository<Order,String> {
    Order findAllById(String id);
}
