package boutiqaatMini.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TagRestController {

    private TagService tagService;

    @Autowired
    public TagRestController(TagService theTagService){
        tagService = theTagService;
    }

    // expose "/Tags" and return list of Categories
    @GetMapping("/tags")
    public List<TagModel> findAll(Pageable pageable) {
        return tagService.findAll(pageable);
    }

    // add mapping for GET /tags/{tagId}

    @GetMapping("/tags/{tagId}")
    public ResponseEntity<?> getTag(@PathVariable int tagId) {

        Optional<?> tag = tagService.findById(tagId);

        return ResponseEntity.ok(tag);

    }

    // add mapping for POST /tags - add new Tag

    @PostMapping("/tags")
    public ResponseEntity<?> addTag(@RequestBody TagModel tagModel) {


        Optional<?> tag = tagService.save(tagModel);

        return ResponseEntity.ok(tag);
    }

    @PutMapping("/tags/{tagId}")
    public ResponseEntity<?> updateTag (@PathVariable Integer tagId, @RequestBody TagModel tagModel) {

        Optional<?> tag = Optional.ofNullable(tagService.update(tagId, tagModel));
        return ResponseEntity.ok(tag);
    }

    // add mapping for DELETE /tags/{tagId} - delete tags

    @DeleteMapping("/tags/{tagId}")
    public String deleteTag(@PathVariable int tagId) {

        try {
            Optional<?> tempTag = tagService.findById(tagId);

            tagService.deleteById(tagId);

            return "Deleted Tag id - " + tagId;

        } catch (RuntimeException e) {
            return ("Tag Id not found - " + tagId);
        }
    }

}


