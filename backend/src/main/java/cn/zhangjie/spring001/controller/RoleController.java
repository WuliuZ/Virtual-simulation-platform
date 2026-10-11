package cn.zhangjie.spring001.controller;

import cn.zhangjie.spring001.common.Result;
import cn.zhangjie.spring001.entity.Role;
import cn.zhangjie.spring001.entity.User;
import cn.zhangjie.spring001.service.RoleService;
import cn.zhangjie.spring001.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
//告诉SpringBoot当前类是一个控制器，可以接收前端请求。交给Spring容器管理
@RestController//默认返回时会经过视图解析器
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;


    //根据编号、名称查询课程
    @GetMapping("/selectRole")
    public Result selectRole(String name) {
        List<Role> role = roleService.selectRole(name);
        return Result.success(role);
    }
    //新增虚拟仿真设备
    @PostMapping("/insertRole")
    public Result insertRole(@RequestBody Role role){
        roleService.insertRole(role);
        return Result.success();
    }
    //修改
    @PostMapping("/updateRole")
    public Result updateRole(@RequestBody Role role) {
        roleService.updateRole(role);
        return Result.success();
    }
    //删除
    @DeleteMapping("/deleteRoleById/{id}")
    public Result deleteRole(@PathVariable Integer id) {
            roleService.deleteRole(id);
            return Result.success();
    }
}
