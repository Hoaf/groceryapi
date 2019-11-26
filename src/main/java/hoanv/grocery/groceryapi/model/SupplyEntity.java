package hoanv.grocery.groceryapi.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "supply", schema = "testdb", catalog = "")
public class SupplyEntity {
    private int supplyId;
    private int productId;
    private int suppliersId;
    private Integer quantity;
    private Double pricePerUnit;
    private Timestamp dateSupply;

    @Id
    @Column(name = "supplyID")
    public int getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(int supplyId) {
        this.supplyId = supplyId;
    }

    @Basic
    @Column(name = "product_id")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "suppliers_id")
    public int getSuppliersId() {
        return suppliersId;
    }

    public void setSuppliersId(int suppliersId) {
        this.suppliersId = suppliersId;
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
    @Column(name = "pricePerUnit")
    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    @Basic
    @Column(name = "dateSupply")
    public Timestamp getDateSupply() {
        return dateSupply;
    }

    public void setDateSupply(Timestamp dateSupply) {
        this.dateSupply = dateSupply;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupplyEntity that = (SupplyEntity) o;
        return supplyId == that.supplyId &&
                productId == that.productId &&
                suppliersId == that.suppliersId &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(pricePerUnit, that.pricePerUnit) &&
                Objects.equals(dateSupply, that.dateSupply);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supplyId, productId, suppliersId, quantity, pricePerUnit, dateSupply);
    }
}
