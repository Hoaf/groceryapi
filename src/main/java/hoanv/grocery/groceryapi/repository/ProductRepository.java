package hoanv.grocery.groceryapi.repository;

import hoanv.grocery.groceryapi.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {
    ProductEntity findByIdAndEnable(int id,int enable);
    List<ProductEntity> findAllByEnable(int enable);
}
