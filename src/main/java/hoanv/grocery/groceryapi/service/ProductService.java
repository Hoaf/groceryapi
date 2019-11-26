package hoanv.grocery.groceryapi.service;

import hoanv.grocery.groceryapi.model.ProductEntity;
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
    @PersistenceContext
    private EntityManager entityManager;

    public List<ProductEntity> getAll(){
        return productRepository.findAll();
    }

    public ProductEntity getById(int id){
        return productRepository.findById(id);
    }
}
