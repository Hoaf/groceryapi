package hoanv.grocery.groceryapi.controller;

import hoanv.grocery.groceryapi.model.UserEntity;
import hoanv.grocery.groceryapi.payload.ApiResponse;
import hoanv.grocery.groceryapi.payload.JwtAuthenticationResponse;
import hoanv.grocery.groceryapi.payload.LoginRequest;
import hoanv.grocery.groceryapi.payload.SignUpRequest;
import hoanv.grocery.groceryapi.security.JwtTokenProvider;
import hoanv.grocery.groceryapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserContronller {
    @Autowired
    private UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Secured("ROLE_ADMIN")
//    @PreAuthorize("hasRole('ADMIN')")15031997
//    @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> getAll(){
        List<UserEntity> result = userService.getAll();
        if(result == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

        UserEntity user = new UserEntity(signUpRequest.getUsername(),
                signUpRequest.getPassword(),signUpRequest.getName());

        UserEntity result = userService.createUser(user);
        if(result == null){
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}
