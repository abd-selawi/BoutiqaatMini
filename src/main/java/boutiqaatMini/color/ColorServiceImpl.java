package boutiqaatMini.color;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ColorServiceImpl implements ColorService {

    private ColorRepository colorRepository;

    @Autowired
    public ColorServiceImpl(ColorRepository theColorRepository) {
        colorRepository = theColorRepository;
    }

    @Override
    public List<ColorModel> findAll() {
        return colorRepository.findAll().stream().map(ColorMapper.INSTANCE::entityToModel).collect(Collectors.toList());
    }

    @Override
    public Optional<?> findById(int theId) {
        return Optional.ofNullable(colorRepository.findById(theId).map(ColorMapper.INSTANCE::entityToModel).orElseThrow(
                () -> new NoSuchElementException("Did not find Color id - ")));
    }


    @Override
    public Optional<ColorModel> save(ColorModel theColorModel) {


        return Optional.ofNullable(ColorMapper.INSTANCE
                .entityToModel(colorRepository.save(ColorMapper.INSTANCE.modelToEntity(theColorModel))));
    }

    @Override
    public ColorModel update(Integer colorId, ColorModel colorModel) {

        ColorModel oldColor = colorRepository.findById(colorId)
                .map(ColorMapper.INSTANCE::entityToModel).orElse(colorModel);

        try {

            for (Field field : colorModel.getClass().getDeclaredFields()){
                if(!field.getName().equalsIgnoreCase("colorId") &&
                        Objects.nonNull(PropertyUtils.getProperty(colorModel, field.getName()))){
                    PropertyUtils.setProperty(oldColor, field.getName(),
                            PropertyUtils.getProperty(colorModel, field.getName()));
                }
            }
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException exception) {
            exception.printStackTrace();
        }

        return ColorMapper.INSTANCE
                .entityToModel(colorRepository.save(ColorMapper.INSTANCE.modelToEntity(oldColor)));
    }


    @Override
    public void deleteById(int theId) {
        colorRepository.deleteById(theId);
    }
}
