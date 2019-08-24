package skk.repository;

import org.springframework.data.repository.CrudRepository;
import skk.entity.Orders;

import java.util.List;

public interface OrderRepository extends CrudRepository<Orders,String> {
    Orders findAllById(String id);
}
