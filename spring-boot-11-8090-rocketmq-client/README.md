### RocketMq入门级学习
- [参考官网](http://rocketmq.apache.org/docs/quick-start/)
- [阿里云文档](https://help.aliyun.com/product/29530.html?spm=a2c4g.11186623.6.540.4b1d6cb14gC174)

### RocketMq 入门案例

- simple（普通，默认是 cluster模式，一个消息只能消费一次）
    - producer
        - AsyncProducer（异步）
        - SyncProducer（同步）
        - OnewayProducer（单向）
- order（顺序）
- Broadcasting（广播模式，一个消息可以多次消费）
- Schedule（计划，针对 provider）
- Batch
    - total size of msgs <= 1MB
- transaction
    - ![Image text](https://static-aliyun-doc.oss-cn-hangzhou.aliyuncs.com/assets/img/zh-CN/1579264061/p177406.png)
      
    



