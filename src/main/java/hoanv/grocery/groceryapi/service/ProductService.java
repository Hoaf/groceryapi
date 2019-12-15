package hoanv.grocery.groceryapi.service;

import hoanv.grocery.groceryapi.authenticate.IAuthenticationFacade;
import hoanv.grocery.groceryapi.exception.ResourceNotFoundException;
import hoanv.grocery.groceryapi.model.CategoryEntity;
import hoanv.grocery.groceryapi.model.ProductEntity;
import hoanv.grocery.groceryapi.payload.ProductRequest;
import hoanv.grocery.groceryapi.repository.CategoryRepository;
import hoanv.grocery.groceryapi.repository.ProductRepository;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ProductService {

    @Autowired(required = true)
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    public List<ProductEntity> getAll() {
        String currentRole = null;
        Authentication authentication = authenticationFacade.getAuthentication();
        if (authentication != null) {
            currentRole = authentication.getAuthorities().toString();
        }else{
            return null;
        }

        List<ProductEntity> result = null;
        if(currentRole.equals("[ROLE_USER]")){
             result = productRepository.findByUsernameStrAndEnable(authentication.getName(),1).orElseThrow(()->
                     new ResourceNotFoundException("","product",""));
        }else if(currentRole.equals("[ROLE_ADMIN]")){
            result = productRepository.findAllByEnable(1).orElseThrow(()->
                    new ResourceNotFoundException("","product",""));
        }

        return result;
    }

    public ProductEntity getById(int id) {
        String currentRole = null;
        Authentication authentication = authenticationFacade.getAuthentication();
        if (authentication != null) {
            currentRole = authentication.getAuthorities().toString();
        }
        ProductEntity productEntity = productRepository.findByIdAndEnable(id, 1);
        if(productEntity != null){
            if (currentRole.equals("[ROLE_ADMIN]") || authentication.getName().equals(productEntity.getUsernameStr())) {
                return productEntity;
            }
        }

        return null;
    }

    public ProductEntity create(ProductRequest productRequest) {

        String currentUser = null;
        Authentication authentication = authenticationFacade.getAuthentication();
        if (authentication != null) {
            currentUser = authentication.getName();
        } else {
            return null;
        }

        CategoryEntity categoryEntity = categoryRepository.findByIdAndEnable(productRequest.getCategoryId(), 1)
                .orElseThrow(() -> new ResourceNotFoundException("","category",productRequest.getCategoryId()));

        ProductEntity productEntity = new ProductEntity(productRequest.getName(),
                productRequest.getPrice(), productRequest.getImage(), productRequest.getQuantity(), productRequest.getDescription(), 1, productRequest.getWhosaleprice(), categoryEntity);
        productEntity.setUsernameStr(currentUser);

        return productRepository.save(productEntity);
    }

    public ProductEntity update(ProductRequest productRequest) {

        String currentRole = null;
        Authentication authentication = authenticationFacade.getAuthentication();
        if (authentication != null) {
            currentRole = authentication.getAuthorities().toString();
        }else{
            return null;
        }
        ProductEntity productEntity = productRepository.findByIdAndEnable(productRequest.getId(), 1);
        if (productEntity != null) {
            if (currentRole.equals("[ROLE_ADMIN]") || authentication.getName().equals(productEntity.getUsernameStr())) {

                productEntity.setName(productRequest.getName());
                productEntity.setPrice(productRequest.getPrice());
                productEntity.setImage(productRequest.getImage());
                productEntity.setQuantity(productRequest.getQuantity());
                productEntity.setDescription(productRequest.getDescription());
                productEntity.setWhosalePrice(productRequest.getWhosaleprice());
                CategoryEntity categoryEntity = categoryRepository.findByIdAndEnable(productRequest.getCategoryId(), 1)
                        .orElseThrow(() -> new ResourceNotFoundException("","category",productRequest.getCategoryId()));
                productEntity.setCategoryByCategoryId(categoryEntity);

                return productRepository.save(productEntity);
            }
        }

        return null;
    }

    public void initializedHibernateSearch(){
        try {
            FullTextEntityManager fullTextEntityManager =
                    Search.getFullTextEntityManager(entityManager);
            fullTextEntityManager.createIndexer().startAndWait();
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }

    public List<ProductEntity> search(String keyword){
        initializedHibernateSearch();
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(ProductEntity.class)
                .get();

        org.apache.lucene.search.Query query = queryBuilder
                .keyword()
                .onFields("name", "description")
                .matching(keyword)
                .createQuery();

        org.hibernate.search.jpa.FullTextQuery jpaQuery
                = fullTextEntityManager.createFullTextQuery(query, ProductEntity.class);
        return jpaQuery.getResultList();
    }
}
