package skk.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import skk.entity.Wallet;

import java.util.Optional;

public interface WalletRepository extends CrudRepository<Wallet, String> {
    @Transactional
    //Optional<Wallet> findBy(String id);
    Optional<Wallet> findByuserid(String userid);
}
