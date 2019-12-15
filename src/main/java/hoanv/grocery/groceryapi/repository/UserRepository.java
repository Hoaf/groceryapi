package hoanv.grocery.groceryapi.repository;

import hoanv.grocery.groceryapi.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    Optional<UserEntity> findByUsernameAndEnable(String username,int enable);
    Boolean existsByUsername(String username);
}
