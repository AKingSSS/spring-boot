package com.aking.learn._01_asyn;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-19
 */
public class Client implements CSCallBack {
    private Server server;

    public Client(Server server) {
        this.server = server;
    }

    public void sendMsg(final String msg) {
        System.out.println("客户端：发送的消息为：" + msg);
        // 开启线程，异步回调
        new Thread(new Runnable() {
            @Override
            public void run() {
                server.getClientMsg(Client.this, msg);
            }
        }).start();
        System.out.println("客户端：异步发送成功");
    }

    /**
     * 获取回调状态
     *
     * @param status
     */
    @Override
    public void process(String status) {
        System.out.println("客户端：服务端回调状态为：" + status);
    }
}
