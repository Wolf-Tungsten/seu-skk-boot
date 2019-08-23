package skk.repository;

import org.springframework.data.repository.CrudRepository;
import skk.entity.BrandExtraInfo;

import java.util.List;

public interface BrandExtraInfoRepository extends CrudRepository<BrandExtraInfo, String> {
    List<BrandExtraInfo> findBrandExtraInfoByMvoId(String mvoId);
}
