package cn.zhangjuntao.spring001.controller;

import cn.zhangjuntao.spring001.common.Result;
import cn.zhangjuntao.spring001.common.JwtTokenUtils;
import cn.zhangjuntao.spring001.entity.User;
import cn.zhangjuntao.spring001.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
//告诉SpringBoot当前类是一个控制器，可以接收前端请求。交给Spring容器管理
@RestController//默认返回时会经过视图解析器
@RequestMapping("/user")
public class UserController {

    //我现在是不是需要去业务层处理逻辑 可能要计算等问题的逻辑
    @Autowired
    UserService userService;

    @GetMapping("/selectAll")
    public Result selectAll(String name,String phone,Integer role_id) {
        List<User> users = userService.selectAll(name,phone,role_id);
        return Result.success(users);
    }

//    @PostMapping("/register")
//    public Result register(@RequestBody User user) {
//        userService.insertUser(user);
//
//        System.out.println(user);
//        return Result.success();
//    }
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        // 检查用户名是否已存在
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            user.setEmail(null);
        }
        if (user.getPhone() == null || user.getPhone().isEmpty()) {
            user.setPhone(null);
        }
        if(user.getUsername()==null||user.getUsername().isEmpty()){
            return Result.error("用户名不能为空");
        }

        boolean hasUser1 = userService.findByUsername(user.getUsername());
        boolean hasUser2 = userService.findByEmail(user.getEmail());
        boolean hasUser3 = userService.findByPhone(user.getPhone());


        if (hasUser1) {
            return Result.error("账号已存在");
        }
        // 检查邮箱是否已存在
        if (hasUser2) {
            return Result.error("邮箱已存在");
        }
        // 检查手机号是否已存在
        if (hasUser3) {
            return Result.error("手机号已存在");
        }
        // 如果都不存在，保存新用户
        userService.insertUser(user);

        System.out.println(user);
        return Result.success();
    }


    @PostMapping("/login")
    public Result login(@RequestBody User loginInfo) {
        // 根据提交表单查询数据库
        User user = userService.getUserByLoginInfo(loginInfo);
        if (user == null) {
            return Result.error("请检查账号或密码信息");
        }
        String token = JwtTokenUtils.getToken(user.getId().toString(), user.getPassword());
        user.setToken(token);

        System.out.println(user);
        return Result.success(user);
    }

    @PostMapping("/update")
    public Result updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return Result.success();
    }

    @GetMapping("/del/{ids}")
    public Result deleteByIds(@PathVariable int[] ids) {
        Integer i = userService.deleteByIds(ids);
        if (i == 0) {
            return Result.error();
        }
        return Result.success();
    }

}

