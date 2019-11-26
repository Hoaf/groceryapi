package hoanv.grocery.groceryapi.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "combo_has_pro", schema = "testdb", catalog = "")
@IdClass(ComboHasProEntityPK.class)
public class ComboHasProEntity {
    private int productId;
    private int comboId;
    private ComboEntity comboByComboId;

    @Id
    @Column(name = "product_id")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Id
    @Column(name = "combo_id")
    public int getComboId() {
        return comboId;
    }

    public void setComboId(int comboId) {
        this.comboId = comboId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComboHasProEntity that = (ComboHasProEntity) o;
        return productId == that.productId &&
                comboId == that.comboId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, comboId);
    }

    @ManyToOne
    @JoinColumn(name = "combo_id", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
    public ComboEntity getComboByComboId() {
        return comboByComboId;
    }

    public void setComboByComboId(ComboEntity comboByComboId) {
        this.comboByComboId = comboByComboId;
    }
}
