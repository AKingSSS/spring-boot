package com.aking.learn.service.impl;

import com.aking.learn.domain.UserDO;
import com.aking.learn.dto.UserDTO;
import com.aking.learn.mapper.UserMapper;
import com.aking.learn.query.UserQuery;
import com.aking.learn.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-06
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDO,UserDTO, UserQuery, UserMapper> implements UserService {
}
