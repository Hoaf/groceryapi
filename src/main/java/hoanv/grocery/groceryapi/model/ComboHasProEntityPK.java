package hoanv.grocery.groceryapi.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ComboHasProEntityPK implements Serializable {
    private int productId;
    private int comboId;

    @Column(name = "product_id")
    @Id
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Column(name = "combo_id")
    @Id
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
        ComboHasProEntityPK that = (ComboHasProEntityPK) o;
        return productId == that.productId &&
                comboId == that.comboId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, comboId);
    }
}
