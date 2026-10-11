package cn.zhangjie.spring001.entity;


import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.Date;

public class User {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String intro;
    private String phone;
    private LocalDateTime create_time;
    private LocalDateTime update_time;
    private Integer role_id;

    private String role_name;
    @Transient //声明我这个字段不需要和数据库对应
    private String token;

    //set方法

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", intro='" + intro + '\'' +
                ", phone='" + phone + '\'' +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                ", role_id=" + role_id +
                ", role_name='" + role_name + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User(Integer id, String name, String username, String password, String email, String intro, String phone, LocalDateTime create_time, LocalDateTime update_time, Integer type) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.intro = intro;
        this.phone = phone;
        this.create_time = create_time;
        this.update_time = update_time;
        this.role_id = role_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }

    public LocalDateTime getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(LocalDateTime update_time) {
        this.update_time = update_time;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

}
