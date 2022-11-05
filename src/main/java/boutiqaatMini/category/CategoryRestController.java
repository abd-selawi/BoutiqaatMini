package boutiqaatMini.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategoryRestController {

    private CategoryService categoryService;

    @Autowired
    public CategoryRestController(CategoryService theCategoryService){
        categoryService = theCategoryService;
    }

    // expose "/Categories" and return list of Categories
    @GetMapping("/categories")
    public List<CategoryModel> findAll(Pageable pageable) {
        return categoryService.findAll(pageable);
    }



    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<?> getCategory(@PathVariable int categoryId) {

        Optional<?> category = categoryService.findById(categoryId);

        return ResponseEntity.ok(category);
    }


        // add mapping for POST /categories - add new Category

    @PostMapping("/categories")
    public ResponseEntity<?> createCategory(@RequestBody CategoryModel categoryModel) {

            Optional<?> category =categoryService.save(categoryModel);
            return ResponseEntity.ok(category);

    }


    @PutMapping("/categories/{categoryId}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer categoryId, @RequestBody CategoryModel categoryModel) {


        Optional<?> category = Optional.ofNullable(categoryService.update(categoryId, categoryModel));
        return ResponseEntity.ok(category);

    }

    // add mapping for DELETE /categories/{categoryId} - delete categories

    @DeleteMapping("/categories/{categoryId}")
    public String deleteCategory(@PathVariable int categoryId) {

        try {
            Optional<?> tempCategory = categoryService.findById(categoryId);

            categoryService.deleteById(categoryId);

            return "Deleted Category id - " + categoryId;

        } catch (RuntimeException e) {
            return ("Category Id not found - " + categoryId);
        }
    }

}


