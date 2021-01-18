package com.aking.learn.service;

import com.aking.learn.pojo.User;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-18
 */
public interface UserService {
    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    User getUserById(Long id);
}
