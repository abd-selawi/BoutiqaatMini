package boutiqaatMini.tag;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TagService {

    public List<TagModel> findAll(Pageable pageable);

    public Optional<?> findById(int theId);

    public Optional<TagModel> save(TagModel tagModel);

    public TagModel update(Integer tagId, TagModel tagModel);

    public void deleteById(int theId);
}
