package net.javarush.usermanager.model;
import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name="user")
public class User {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="age")
    private int age;
    @Column(name="isAdmin")
    private int isAdmin;
    @Column(name="createDate")
    private Date createDate;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIsAdmin() {
        return isAdmin;
    }
    public void setIsAdmin(int admin) {
        isAdmin = admin;
    }
    public String getCreateDate() {
        return createDate.toString();
    }
    public void setCreateDate() {
        this.createDate = new Date();
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isAdmin=" + isAdmin +
                ", createDate=" + createDate +
                '}';
    }
}