package boutiqaatMini.Product;

import boutiqaatMini.brand.Brand;
import boutiqaatMini.brand.BrandRepository;
import boutiqaatMini.category.Category;
import boutiqaatMini.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ProductRestController {

    private ProductService productService;

    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;

    private BrandRepository brandRepository;

    @Autowired
    public ProductRestController(ProductService productService, ProductRepository productRepository, CategoryRepository categoryRepository, BrandRepository brandRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
    }




    @GetMapping("/products/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable int productId) {

        Optional<?> product = productService.findById(productId);

        return ResponseEntity.ok(product);
    }


    @PostMapping("/create/products")
    public ResponseEntity<?> addProduct(@RequestBody ProductModel productModel) {

        Optional<?> product = productService.save(productModel);
        return ResponseEntity.ok(product);
    }


    @PutMapping("/products/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer productId, @RequestBody ProductModel productModel) {


        Optional<?> product = Optional.ofNullable(productService.update(productId, productModel));
        return ResponseEntity.ok(product);
    }

    // add mapping for DELETE
    @DeleteMapping("/products/{productId}")
    public String deleteCategory(@PathVariable int productId) {

        try {
            Optional<?> tempProduct = productService.findById(productId);

            productService.deleteById(productId);

            return "Deleted Product id - " + productId;

        } catch (RuntimeException e) {
            return ("Product Id not found - " + productId);
        }
    }


    @GetMapping("/products")
    public ResponseEntity<Map<String, Object>> getAllProducts(
            @RequestParam(required = false) Integer brandId,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id,desc") String[] sort) {

        try {
            List<ProductModel> products = new ArrayList<ProductModel>();
            Pageable paging = PageRequest.of(page, size);

            Page<ProductModel> pageTuts;


            if (brandId != null && categoryId == null) {

                Optional<Brand> brand = brandRepository.findById(brandId);

                if (brand.isPresent()) {
                    pageTuts = productRepository.findByBrandId(brandId, paging)
                            .map(ProductMapper.INSTANCE::entityToModel);
                } else {
                    pageTuts = productRepository.findAll(paging)
                            .map(ProductMapper.INSTANCE::entityToModel);
                }
            } else if (brandId == null && categoryId != null) {

                Optional<Category> category = categoryRepository.findById(categoryId);

                if (category.isPresent()) {
                    pageTuts = productRepository.findByCategoriesId(categoryId, paging)
                            .map(ProductMapper.INSTANCE::entityToModel);
                } else {
                    pageTuts = productRepository.findAll(paging)
                            .map(ProductMapper.INSTANCE::entityToModel);
                }


            } else {
                pageTuts = productRepository.findAll(paging)
                        .map(ProductMapper.INSTANCE::entityToModel);
            }


            products = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("products", products);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


