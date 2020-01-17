package hoanv.grocery.groceryapi.service;

import hoanv.grocery.groceryapi.exception.ResourceNotFoundException;
import hoanv.grocery.groceryapi.model.CategoryEntity;
import hoanv.grocery.groceryapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Optional<CategoryEntity> findAll(){
        Optional<CategoryEntity> result = Optional.ofNullable(categoryRepository.findAllByEnable(1).orElseThrow(()
                -> new ResourceNotFoundException("", "categories", "")));

        return result;
    }

    public CategoryEntity findById(int id){
        CategoryEntity rs = categoryRepository.findByIdAndEnable(id,1).
                orElseThrow(()-> new ResourceNotFoundException("","category",""));

        return rs;
    }
}
