package hoanv.grocery.groceryapi.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "testdb", catalog = "")
public class UserEntity {
    private String username;
    private String password;
    private String fullname;
    private String phone;
    private Integer age;
    private Boolean sex;
    private String image;
    private int enable;
    private RoleEntity roleByRole;

    private List<ProductEntity> productEntityList;

    @OneToMany(mappedBy = "username")
    public List<ProductEntity> getProductEntityList() {
        return productEntityList;
    }

    public void setProductEntityList(List<ProductEntity> productEntityList) {
        this.productEntityList = productEntityList;
    }

    public UserEntity() {
    }

    public UserEntity(String username, String password, String fullname) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
    }

    @Id
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "fullname")
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "sex")
    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
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
    @Column(name = "enable")
    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return enable == that.enable &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(fullname, that.fullname) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(age, that.age) &&
                Objects.equals(sex, that.sex) &&
                Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, fullname, phone, age, sex, image, enable);
    }

    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "roleid")
    public RoleEntity getRoleByRole() {
        return roleByRole;
    }

    public void setRoleByRole(RoleEntity roleByRole) {
        this.roleByRole = roleByRole;
    }
}
