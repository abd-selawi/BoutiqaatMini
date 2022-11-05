package boutiqaatMini.brand;

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
public class BrandServiceImpl implements BrandService {

    private BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository theBrandRepository) {
        brandRepository = theBrandRepository;

    }

    @Override
    public List<BrandModel> findAll(Pageable pageable) {
        return brandRepository.findAll(pageable).stream().map(BrandMapper.INSTANCE::entityToModel).collect(Collectors.toList());
    }


    @Override
    public Optional<?> findById(int theId) {
        return Optional.ofNullable(brandRepository.findById(theId).map(BrandMapper.INSTANCE::entityToModel).orElseThrow(
                () -> new NoSuchElementException("Did not find Brand id - ")));
    }


    @Override
    public Optional<BrandModel> save(BrandModel brandModel) {


        return Optional.ofNullable(BrandMapper.INSTANCE
                .entityToModel(brandRepository.save(BrandMapper.INSTANCE.modelToEntity(brandModel))));
    }

    @Override
    public BrandModel update(Integer brandId, BrandModel brandModel) {

        BrandModel oldBrand = brandRepository.findById(brandId)
                .map(BrandMapper.INSTANCE::entityToModel).orElse(brandModel);

        try {

            for (Field field : brandModel.getClass().getDeclaredFields()){
                if(!field.getName().equalsIgnoreCase("brandId") &&
                    Objects.nonNull(PropertyUtils.getProperty(brandModel, field.getName()))){
                        PropertyUtils.setProperty(oldBrand, field.getName(),
                            PropertyUtils.getProperty(brandModel, field.getName()));
                }
            }
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException exception) {
            exception.printStackTrace();
        }

        return BrandMapper.INSTANCE
                .entityToModel(brandRepository.save(BrandMapper.INSTANCE.modelToEntity(oldBrand)));

    }

    @Override
    public void deleteById(int theId) {
        brandRepository.deleteById(theId);
    }
}



//    @Override
//    public Brand findById(int theId) {
//        Optional<Brand> result = brandRepository.findById(theId);
//
//        Brand theBrand = null;
//
//        if(result.isPresent()) {
//            theBrand= result.get();
//        }else{
//            // we didn't find the category
//            throw new RuntimeException("Did not find Brand id - "+ theId);
//        }
//
//
//        return theBrand;
//    }
//
//
////    @Override
////    public Optional<?> retrive(int theId) {
////        return Optional.ofNullable(categoryRepository.findById(theId).orElseThrow(
////                () -> new NoSuchElementException("Did not find Category id - ")));
////    }
//
//    @Override
//    public void save(Brand theBrand) {
//        brandRepository.save(theBrand);
//    }
//
//    @Override
//    public void deleteById(int theId) {
//        brandRepository.deleteById(theId);
//    }

