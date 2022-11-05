package boutiqaatMini.color;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ColorRestController {

    private ColorService colorService;

    @Autowired
    public ColorRestController(ColorService theColorService){
        colorService = theColorService;
    }

    // expose "/colors" and return list of Categories
    @GetMapping("/colors")
    public List<ColorModel> findAll() {
        return colorService.findAll();
    }

    // add mapping for GET /brands/{brandId}

    @GetMapping("/colors/{colorId}")
    public ResponseEntity<?> getColor(@PathVariable int colorId) {

        Optional<?> color = colorService.findById(colorId);

        return ResponseEntity.ok(color);
    }



    // add mapping for POST /colors - add new brand

    @PostMapping("/colors")
    public ResponseEntity<?> createColor(@RequestBody ColorModel colorModel) {

        Optional<?> color =colorService.save(colorModel);
        return ResponseEntity.ok(color);

    }


    @PutMapping("/colors/{colorId}")
    public ResponseEntity<?> updateColor(@PathVariable Integer colorId, @RequestBody ColorModel colorModel) {


        Optional<?> color = Optional.ofNullable(colorService.update(colorId, colorModel));
        return ResponseEntity.ok(color);
    }

    // add mapping for DELETE /colors/{colorId} - delete colors

    @DeleteMapping("/colors/{colorId}")
    public String deleteColor(@PathVariable int colorId) {

        try {
            Optional<?> tempColor = colorService.findById(colorId);

            colorService.deleteById(colorId);

            return "Deleted Color id - " + colorId;

        } catch (RuntimeException e) {
            return ("Color Id not found - " + colorId);
        }
    }


}


