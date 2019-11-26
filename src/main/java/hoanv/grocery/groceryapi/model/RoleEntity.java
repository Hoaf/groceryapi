package hoanv.grocery.groceryapi.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "role", schema = "testdb", catalog = "")
public class RoleEntity {
    private int roleid;
    private String name;

    @Id
    @Column(name = "roleid")
    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return roleid == that.roleid &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleid, name);
    }
}
