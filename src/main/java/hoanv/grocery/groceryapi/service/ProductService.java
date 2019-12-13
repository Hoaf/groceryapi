package hoanv.grocery.groceryapi.service;

import hoanv.grocery.groceryapi.model.CategoryEntity;
import hoanv.grocery.groceryapi.model.ProductEntity;
import hoanv.grocery.groceryapi.payload.ProductRequest;
import hoanv.grocery.groceryapi.repository.CategoryRepository;
import hoanv.grocery.groceryapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ProductService {

    @Autowired(required = true)
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public List<ProductEntity> getAll(){
        return productRepository.findAllByEnable(1);
    }

    public ProductEntity getById(int id){
        return productRepository.findByIdAndEnable(id,1);
    }

    public ProductEntity create(ProductRequest productRequest){

        CategoryEntity categoryEntity = categoryRepository.findByIdAndEnable(productRequest.getCategoryId(),1)
                .orElseThrow(()-> new RuntimeException("couldn't find categoryId"));

        ProductEntity productEntity = new ProductEntity(productRequest.getName(),
                productRequest.getPrice(),productRequest.getImage(),productRequest.getQuantity(),productRequest.getDescription(),1,productRequest.getWhosaleprice(),categoryEntity);

        return productRepository.save(productEntity);
    }

    public ProductEntity update(ProductRequest productRequest){

        ProductEntity productEntity = productRepository.findByIdAndEnable(productRequest.getId(),1);
        if(productEntity != null){
            productEntity.setName(productRequest.getName());
            productEntity.setPrice(productRequest.getPrice());
            productEntity.setImage(productRequest.getImage());
            productEntity.setQuantity(productRequest.getQuantity());
            productEntity.setDescription(productRequest.getDescription());
            productEntity.setWhosalePrice(productRequest.getWhosaleprice());
            CategoryEntity categoryEntity = categoryRepository.findByIdAndEnable(productRequest.getCategoryId(),1)
                    .orElseThrow(()-> new RuntimeException("couldn't find categoryId"));
            productEntity.setCategoryByCategoryId(categoryEntity);

            return productRepository.save(productEntity);
        }

        return null;
    }
}
