package cn.zhangjuntao.spring001.service.impl;

import cn.zhangjuntao.spring001.common.Result;
import cn.zhangjuntao.spring001.entity.Role;
import cn.zhangjuntao.spring001.entity.User;
import cn.zhangjuntao.spring001.mapper.RoleMapper;
import cn.zhangjuntao.spring001.mapper.UserMapper;
import cn.zhangjuntao.spring001.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    UserMapper userMapper;
    @Override
    public List<Role> selectRole(String name){
        List<Role> list = roleMapper.selectRole(name);
        return list;
    };

    public void insertRole(Role role){
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        // 设置创建时间（如果需要，您可以指定时区）
        role.setCreate_time(now);
        // 设置更新时间（如果需要，您可以指定时区）
        role.setUpdate_time(now);

        roleMapper.insertRole(role);
    };

    public void updateRole(Role role){
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();

        // 设置更新时间（如果需要，您可以指定时区）
        role.setUpdate_time(now);

        roleMapper.updateRole(role);
    };

    public void deleteRole(Integer id){
        List<User> user = userMapper.selectUserByRoleId(id);
        if(user.size()>0){
            userMapper.deleteUserByRoleId(id);
            roleMapper.deleteRole(id);
        }else{
            roleMapper.deleteRole(id);
        }
    };
}
