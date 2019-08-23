package skk.repository;

import org.springframework.data.repository.CrudRepository;
import skk.entity.Wallet;

public interface WalletRepository extends CrudRepository<Wallet, String> {
}
