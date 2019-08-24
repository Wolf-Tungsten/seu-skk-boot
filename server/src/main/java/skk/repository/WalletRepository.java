package skk.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import skk.entity.Wallet;

import java.util.List;
import java.util.Optional;

public interface WalletRepository extends CrudRepository<Wallet, String> {
   List<Wallet> findAllByUserid(String id);
}
