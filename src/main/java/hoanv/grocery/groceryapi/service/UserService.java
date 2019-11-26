package hoanv.grocery.groceryapi.service;

import hoanv.grocery.groceryapi.exception.AppException;
import hoanv.grocery.groceryapi.model.RoleEntity;
import hoanv.grocery.groceryapi.model.UserEntity;
import hoanv.grocery.groceryapi.repository.UserRepository;
import hoanv.grocery.groceryapi.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    PasswordEncoder passwordEncoder;

    public List<UserEntity> getAll(){
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(s)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username : " + s)
                );

        return UserPrincipal.create(user);
    }

    public UserEntity createUser(UserEntity userEntity){
        if(userRepository.existsByUsername(userEntity.getUsername())){
            return null;
        }
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        RoleEntity roleEntity = roleService.findRoleEntityByName("USER")
                .orElseThrow(() -> new AppException("User Role not set."));
        userEntity.setRoleByRole(roleEntity);

        return userRepository.save(userEntity);
    }
}
