package hoanv.grocery.groceryapi.repository;

import hoanv.grocery.groceryapi.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity,Integer> {
    Optional<RoleEntity> findRoleEntityByName(String name);
}
