package hoanv.grocery.groceryapi.service;

import hoanv.grocery.groceryapi.model.RoleEntity;
import hoanv.grocery.groceryapi.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Optional<RoleEntity> findRoleEntityByName(String name){
        return roleRepository.findRoleEntityByName(name);
    }
}
