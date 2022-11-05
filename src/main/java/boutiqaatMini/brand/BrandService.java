package boutiqaatMini.brand;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BrandService {

    public List<BrandModel> findAll(Pageable pageable);

    public Optional<?> findById(int theId);

    public Optional<BrandModel> save(BrandModel brandModel);

    public BrandModel update(Integer brandId, BrandModel brandModel);

    public void deleteById(int theId);
}
