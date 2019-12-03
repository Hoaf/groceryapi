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
        return productRepository.findAll();
    }

    public ProductEntity getById(int id){
        return productRepository.findById(id);
    }

    public ProductEntity create(ProductRequest productRequest){

        CategoryEntity categoryEntity = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(()-> new RuntimeException("couldn't find categoryId"));
        Byte enable = Byte.parseByte("1");

        ProductEntity productEntity = new ProductEntity(productRequest.getName(),
                productRequest.getPrice(),productRequest.getImage(),productRequest.getQuantity(),productRequest.getDescription(),enable,categoryEntity);

        return productRepository.save(productEntity);
    }

    public ProductEntity update(ProductRequest productRequest){

        ProductEntity productEntity = productRepository.findById(productRequest.getId());
        if(productEntity != null){
            productEntity.setName(productRequest.getName());
            productEntity.setPrice(productRequest.getPrice());
            productEntity.setImage(productRequest.getImage());
            productEntity.setQuantity(productRequest.getQuantity());
            productEntity.setDescription(productRequest.getDescription());
            CategoryEntity categoryEntity = categoryRepository.findById(productRequest.getCategoryId())
                    .orElseThrow(()-> new RuntimeException("couldn't find categoryId"));
            productEntity.setCategoryByCategoryId(categoryEntity);
            return productRepository.save(productEntity);
        }

        return null;
    }
}