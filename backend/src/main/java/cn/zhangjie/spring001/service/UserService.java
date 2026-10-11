package cn.zhangjie.spring001.service;

import cn.zhangjie.spring001.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService {
    //List<User> selectAll();
    User findById(Integer id);
    void insertUser(User user);
    void updateUser(User user);
    void deleteUser(Integer id);
    User getUserByLoginInfo(User loginInfo);

    List<User> selectAll(String name,String phone,Integer role_id);

    boolean findByUsername(String username);

    boolean findByEmail(String email);

    boolean findByPhone(String phone);



    Integer deleteByIds(int[] ids);
}
