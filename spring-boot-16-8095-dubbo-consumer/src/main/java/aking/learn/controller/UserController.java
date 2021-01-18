package aking.learn.controller;

import com.aking.learn.pojo.User;
import com.aking.learn.service.UserService;
import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  zk 调用
 *  当有多个服务时，缺省为负载均衡调用；
 *
 *  直连方式：
 *  dubbo://localhost:20890
 * </p>
 *
 * @author yk
 * @date 2021-01-18
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Reference(group = "${dubbo.provider.group}", version = "${dubbo.provider.version}")
    private UserService userService;

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("/getUserInfo/{id}")
    public User getUserInfo(@PathVariable("id") Long id) {
        try {
            User user = this.userService.getUserById(id);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
