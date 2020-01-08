package hoanv.grocery.groceryapi.payload;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ProductRequest {
    private int id;
    @Size(min = 3,message = ("name must be more than 2 characters"))
    private String name;
    @Min(value = 1,message = ("price > 0"))
    private Double price;
    @NotBlank(message = ("img can't be empty"))
    private String image;
    @Min(value = 1,message = ("quantity > 0"))
    private Integer quantity;
    @NotBlank(message = ("desc can't be empty"))
    private String description;
    @Min(value = 1,message = ("whosale price > 0"))
    private Double whosalePrice;
    private int categoryId;

    public Double getWhosalePrice() {
        return whosalePrice;
    }

    public void setWhosalePrice(Double whosalePrice) {
        this.whosalePrice = whosalePrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
