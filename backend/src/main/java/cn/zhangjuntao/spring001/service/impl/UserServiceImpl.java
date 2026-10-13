package cn.zhangjuntao.spring001.service.impl;

import cn.zhangjuntao.spring001.entity.User;
import cn.zhangjuntao.spring001.mapper.UserMapper;
import cn.zhangjuntao.spring001.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    //调用持久层的接口,执行sql
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> selectAll(String name,String phone,Integer role_id) {
        List<User> list = userMapper.selectAll(name,phone,role_id);
        return list;
    }

    public boolean findByUsername(String username){
        return userMapper.findByUsername(username);
    };

    public boolean findByEmail(String email){
        return userMapper.findByEmail(email);
    };

    public boolean findByPhone(String phone){
        return userMapper.findByPhone(phone);
    };
    @Override
    public Integer deleteByIds(int[] ids) {
        return userMapper.deleteByIds(ids);
    }

    public User findById(Integer id) {
            return userMapper.findById(id);
        }

        public void insertUser(User user) {
            // 获取当前时间
            LocalDateTime now = LocalDateTime.now();
            // 设置创建时间（如果需要，您可以指定时区）
            user.setCreate_time(now);
            // 设置更新时间（如果需要，您可以指定时区）
            user.setUpdate_time(now);
            // 调用 mapper 方法插入数据

            userMapper.insertUser(user);
        }

        public void updateUser(User user) {
            // 获取当前时间
            LocalDateTime now = LocalDateTime.now();
            // 设置创建时间（如果需要，您可以指定时区）
            user.setUpdate_time(now);

        userMapper.updateUser(user);
        }

        public void deleteUser(Integer id) {
            userMapper.deleteUser(id);
        }

        public User getUserByLoginInfo(User loginInfo){return userMapper.getUserByLoginInfo(loginInfo);}
}
