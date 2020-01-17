package hoanv.grocery.groceryapi.controller;

import hoanv.grocery.groceryapi.model.CategoryEntity;
import hoanv.grocery.groceryapi.model.ProductEntity;
import hoanv.grocery.groceryapi.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Api(value="onlinestore", description="Operations pertaining to products in Online Store")
@RequestMapping("/api/category")
@CrossOrigin(origins= {"http://localhost:3000"})
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "View a list of available products",response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/categories")
    public ResponseEntity<?> findById(){
        Optional<CategoryEntity> result=null;
        try{
            result = categoryService.findAll();
            if(result == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("category doesn't exist");
            }
        }catch (RuntimeException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/category")
    public ResponseEntity<?> findById(@RequestParam("id")int id){
        CategoryEntity result=null;
        try{
            result = categoryService.findById(id);
            if(result == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("category doesn't exist");
            }
        }catch (RuntimeException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }

        return ResponseEntity.ok(result);
    }
}
