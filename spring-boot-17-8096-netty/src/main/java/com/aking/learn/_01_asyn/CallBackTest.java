package com.aking.learn._01_asyn;

/**
 * <p>
 *  测试类
 * </p>
 *
 * @author yk
 * @date 2021-01-19
 */
public class CallBackTest {
    public static void main(String[] args) {
        Server server = new Server();
        Client client = new Client(server);

        client.sendMsg("Server,Hello~");
    }
}
