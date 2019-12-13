package hoanv.grocery.groceryapi.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "order", schema = "testdb", catalog = "")
public class OrderEntity {
    private int orderid;
    private String supplier;
    private Timestamp created;
    private int status;
    private Double discountbyprice;
    private Integer discountbypercent;

    @Id
    @Column(name = "orderid")
    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    @Basic
    @Column(name = "supplier")
    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    @Basic
    @Column(name = "created")
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "discountbyprice")
    public Double getDiscountbyprice() {
        return discountbyprice;
    }

    public void setDiscountbyprice(Double discountbyprice) {
        this.discountbyprice = discountbyprice;
    }

    @Basic
    @Column(name = "discountbypercent")
    public Integer getDiscountbypercent() {
        return discountbypercent;
    }

    public void setDiscountbypercent(Integer discountbypercent) {
        this.discountbypercent = discountbypercent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return orderid == that.orderid &&
                status == that.status &&
                Objects.equals(supplier, that.supplier) &&
                Objects.equals(created, that.created) &&
                Objects.equals(discountbyprice, that.discountbyprice) &&
                Objects.equals(discountbypercent, that.discountbypercent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderid, supplier, created, status, discountbyprice, discountbypercent);
    }
}
