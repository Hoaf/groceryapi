package hoanv.grocery.groceryapi.repository;

import hoanv.grocery.groceryapi.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {
    ProductEntity findByIdAndEnable(int id,int enable);
    Optional<List<ProductEntity>> findAllByEnable(int enable);
    Optional<List<ProductEntity>> findByUsernameStrAndEnable(String username,int enable);
}
