package cn.zhangjie.spring001.service;

import cn.zhangjie.spring001.entity.Role;
import cn.zhangjie.spring001.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RoleService {
    List<Role> selectRole(String name);

    void insertRole(Role role);

    void updateRole(Role role);

    void deleteRole(Integer id);

}
