package hoanv.grocery.groceryapi.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "orderdetail", schema = "testdb", catalog = "")
public class OrderdetailEntity {
    private int orderdetailid;
    private Integer status;
    private Double discountbyprice;
    private Integer discountbypercent;
    private Integer quantity;
    private Timestamp created;
    private OrderEntity orderByOrderid;

    @Id
    @Column(name = "orderdetailid")
    public int getOrderdetailid() {
        return orderdetailid;
    }

    public void setOrderdetailid(int orderdetailid) {
        this.orderdetailid = orderdetailid;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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

    @Basic
    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "created")
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderdetailEntity that = (OrderdetailEntity) o;
        return orderdetailid == that.orderdetailid &&
                Objects.equals(status, that.status) &&
                Objects.equals(discountbyprice, that.discountbyprice) &&
                Objects.equals(discountbypercent, that.discountbypercent) &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(created, that.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderdetailid, status, discountbyprice, discountbypercent, quantity, created);
    }

    @ManyToOne
    @JoinColumn(name = "orderid", referencedColumnName = "orderid", nullable = false)
    public OrderEntity getOrderByOrderid() {
        return orderByOrderid;
    }

    public void setOrderByOrderid(OrderEntity orderByOrderid) {
        this.orderByOrderid = orderByOrderid;
    }
}
