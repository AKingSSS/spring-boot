package com.aking.learn._05_netty._07protocoltcp;

import lombok.Data;

/**
 * <p>
 *  协议包
 * </p>
 *
 * @author yk
 * @date 2021-02-07
 */
@Data
public class MessageProtocol {
    private int len;
    private byte[] content;
}
