package hoanv.grocery.groceryapi.repository;

import hoanv.grocery.groceryapi.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Integer> {
    Optional<CategoryEntity> findByIdAndEnable(int id, int enable);
    Optional<CategoryEntity> findAllByEnable(int enable);
}
