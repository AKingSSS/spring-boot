package aking.learn.service.impl;

import com.aking.learn.pojo.User;
import com.aking.learn.service.UserService;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * <p>
 *  注意： @Service 使用的是 dubbo 包
 * </p>
 *
 * @author yk
 * @date 2021-01-18
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * 根据id获取用户信息
     *
     * @param id
     * @return
     */
    @Override
    public User getUserById(Long id) {
        User user = new User().setName("8094" + id)
                .setAge(id > 18 ? 18 : Integer.parseInt(String.valueOf(id)));
        return user;
    }
}
