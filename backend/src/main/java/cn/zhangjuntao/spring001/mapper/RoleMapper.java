package cn.zhangjuntao.spring001.mapper;

import cn.zhangjuntao.spring001.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface RoleMapper {

   List<Role> selectRole(String name);

   void insertRole(Role role) ;

   void updateRole(Role role) ;

   void deleteRole(Integer id) ;
}
