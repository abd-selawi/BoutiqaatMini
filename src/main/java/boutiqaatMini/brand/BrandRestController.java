package boutiqaatMini.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BrandRestController {

    private BrandService brandService;

    @Autowired
    public BrandRestController(BrandService theBrandService){
        brandService = theBrandService;
    }

    // expose "/Brand" and return list of Categories
    @GetMapping("/brands")
    public List<BrandModel> findAll(Pageable pageable) {
        return brandService.findAll(pageable);
    }

    // add mapping for GET /brands/{brandId}

        @GetMapping("/brands/{brandId}")
    public ResponseEntity<?> getBrand(@PathVariable int brandId) {

            Optional<?> brand = brandService.findById(brandId);
            return ResponseEntity.ok(brand);

    }

    //add mapping for POST /brands - add new brand


    @PostMapping("/brands")
    public ResponseEntity<?> createBrand(@RequestBody BrandModel brandModel) {


        Optional<?> brand = brandService.save(brandModel);
        return ResponseEntity.ok(brand);

    }


    @PutMapping("/brands/{brandId}")
    public ResponseEntity<?> updateBrand(@PathVariable Integer brandId, @RequestBody BrandModel brandModel) {

        Optional<?> brand = Optional.ofNullable(brandService.update(brandId, brandModel));
        return ResponseEntity.ok(brand);
    }


    // add mapping for DELETE /brands/{brandId} - delete brands

    @DeleteMapping("/brands/{brandId}")
    public String deleteBrand(@PathVariable int brandId) {

        try {
            Optional<?> tempBrand = brandService.findById(brandId);

            brandService.deleteById(brandId);

            return "Deleted Brand id - " + brandId;

        } catch (RuntimeException e) {
            return ("Brand Id not found - " + brandId);
        }
    }

}


