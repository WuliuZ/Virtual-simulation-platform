package cn.zhangjuntao.spring001.mapper;

import cn.zhangjuntao.spring001.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
        List<User> selectAll(@Param("name")String name,@Param("phone")String phone, @Param("role_id")Integer role_id);
        boolean findByUsername(String username);
        boolean findByEmail(String email);
        boolean findByPhone(String phone);
        User findById(Integer id);
        void insertUser(User user);
        void updateUser(User user);
        void deleteUser(Integer id);
        User getUserByLoginInfo(User loginInfo);

        void deleteUserByRoleId(Integer id);

        List<User> selectUserByRoleId(Integer roleId);

        Integer deleteByIds(int[] ids);
}