package skk.repository;

import org.springframework.data.repository.CrudRepository;
import skk.entity.BrandExtraInfo;
import skk.entity.BrandInfo;

import java.util.List;

public interface BrandInfoRepository extends CrudRepository<BrandInfo, String> {
    List<BrandInfo> findBrandInfoByMvoId(String mvoId);
}
