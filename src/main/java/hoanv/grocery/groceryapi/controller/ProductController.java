package hoanv.grocery.groceryapi.controller;

import hoanv.grocery.groceryapi.model.ProductEntity;
import hoanv.grocery.groceryapi.payload.ProductRequest;
import hoanv.grocery.groceryapi.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value="onlinestore", description="Operations pertaining to products in Online Store")
@RequestMapping("/api/product")
@CrossOrigin(origins= {"http://localhost:3000"})
public class ProductController {
    @Autowired
    private ProductService productService;

    @ApiOperation(value = "View a list of available products",response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/products")
    public ResponseEntity<?> getAll(){
        List<ProductEntity> result=null;
        try{
            result = productService.getAll();
            if(result == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("product doesn't exist in your inventory");
            }
        }catch (RuntimeException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        ProductEntity result = productService.getById(id);
        if(result == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("product doesn't exist in your inventory");
        }
        return ResponseEntity.ok(result);
    }

    @Secured("ROLE_USER")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ProductRequest productRequest){
        ProductEntity result = null;
        try{
            result = productService.create(productRequest);
            if(result == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("create failed");
            }
        }catch (RuntimeException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody ProductRequest productRequest){
        ProductEntity result=null;
        try{
            result = productService.update(productRequest);
            if(result == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("update failed");
            }
        }catch (RuntimeException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }

        return ResponseEntity.ok(result);
    }
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam(name="id") int id ){
        ProductEntity result=null;
        try{
             if(productService.delete(id)){
                 return ResponseEntity.ok("product :"+id+" deleted");
             }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("deleted failed");
            }
        }catch (RuntimeException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

//    @Secured("ROLE_ADMIN")
    @GetMapping
    public ResponseEntity<?> search(@RequestParam("keyword") String keyword){
        List<ProductEntity> result = productService.search(keyword);
        if(result == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("empty data");
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/testupload")
    public ResponseEntity<?> uploadImage(InputStream file){
        List<ProductEntity> result = new ArrayList<>();
//        if(result == null){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("empty data");
//        }
        return ResponseEntity.ok(result);
    }
}
