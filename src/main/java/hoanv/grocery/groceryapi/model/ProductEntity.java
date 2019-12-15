package hoanv.grocery.groceryapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product", schema = "testdb", catalog = "")
public class ProductEntity {
    private int id;
    private String name;
    private Double price;
    private String image;
    private Integer quantity;
    private String description;
    private int enable;
    private Double whosalePrice;
    private String usernameStr;
    private UserEntity username;
    private CategoryEntity categoryByCategoryId;

    @Column(name="username")
    public String getUsernameStr() {
        return usernameStr;
    }

    public void setUsernameStr(String usernameStr) {
        this.usernameStr = usernameStr;
    }

    public ProductEntity() {
    }

    public ProductEntity(String name, Double price, String image, Integer quantity, String description, int enable, Double whosalePrice, CategoryEntity categoryByCategoryId) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
        this.description = description;
        this.enable = enable;
        this.whosalePrice = whosalePrice;
        this.categoryByCategoryId = categoryByCategoryId;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false,insertable = false,updatable = false)
    @JsonIgnore
    public UserEntity getUsername() {
        return username;
    }

    public void setUsername(UserEntity username) {
        this.username = username;
    }

    @Basic
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "enable")
    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    @Basic
    @Column(name = "whosale_price")
    public Double getWhosalePrice() {
        return whosalePrice;
    }

    public void setWhosalePrice(Double whosalePrice) {
        this.whosalePrice = whosalePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(price, that.price) &&
                Objects.equals(image, that.image) &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(description, that.description) &&
                Objects.equals(enable, that.enable) &&
                Objects.equals(whosalePrice, that.whosalePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, image, quantity, description, enable, whosalePrice);
    }

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    public CategoryEntity getCategoryByCategoryId() {
        return categoryByCategoryId;
    }

    public void setCategoryByCategoryId(CategoryEntity categoryByCategoryId) {
        this.categoryByCategoryId = categoryByCategoryId;
    }
}
