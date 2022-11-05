package boutiqaatMini.color;

import java.util.List;
import java.util.Optional;

public interface ColorService {

    public List<ColorModel> findAll();

    public Optional<?> findById(int theId);

    public Optional<ColorModel> save(ColorModel colorModel);

    public ColorModel update(Integer colorId, ColorModel colorModel);

    public void deleteById(int theId);
}
