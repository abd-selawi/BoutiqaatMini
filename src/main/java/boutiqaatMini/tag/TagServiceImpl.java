package boutiqaatMini.tag;


import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TagServiceImpl implements TagService {

    private TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository theTagRepository) {
        tagRepository = theTagRepository;
    }


    @Override
    public List<TagModel> findAll(Pageable pageable) {
        return tagRepository.findAll(pageable).stream().map(TagMapper.INSTANCE::entityToModel).collect(Collectors.toList());
    }

    @Override
    public Optional<?> findById(int theId) {
        return Optional.ofNullable(tagRepository.findById(theId).map(TagMapper.INSTANCE::entityToModel).orElseThrow(
                () -> new NoSuchElementException("Did not find Tag id - ")));
    }

    @Override
    public Optional<TagModel> save(TagModel tagModel) {

        return Optional.ofNullable(TagMapper.INSTANCE
                .entityToModel(tagRepository.save(TagMapper.INSTANCE.modelToEntity(tagModel))));
    }

    @Override
    public TagModel update(Integer tagId, TagModel tagModel) {
        TagModel oldTag = tagRepository.findById(tagId)
                .map(TagMapper.INSTANCE::entityToModel).orElse(tagModel);

        try {

            for (Field field : tagModel.getClass().getDeclaredFields()){
                if(!field.getName().equalsIgnoreCase("tagId") &&
                        Objects.nonNull(PropertyUtils.getProperty(tagModel, field.getName()))){
                    PropertyUtils.setProperty(oldTag, field.getName(),
                            PropertyUtils.getProperty(tagModel, field.getName()));
                }
            }
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException exception) {
            exception.printStackTrace();
        }

        return TagMapper.INSTANCE
                .entityToModel(tagRepository.save(TagMapper.INSTANCE.modelToEntity(oldTag)));
    }

    @Override
    public void deleteById(int theId) {

    }
}
