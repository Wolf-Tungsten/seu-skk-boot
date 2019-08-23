package skk.repository;

import org.springframework.data.repository.CrudRepository;
import skk.entity.TranRecord;

import java.util.List;

public interface TranRecordRepository extends CrudRepository<TranRecord,String> {
    List<TranRecord> findAll();
}
