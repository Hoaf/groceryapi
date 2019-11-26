package hoanv.grocery.groceryapi.repository;

import hoanv.grocery.groceryapi.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {
    ProductEntity findById(int id);

}
