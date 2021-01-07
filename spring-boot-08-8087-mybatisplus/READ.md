- 驼峰命名处理：配置文件 √
- 排序 √
- 自定义sql: 简单sql实用注解，复杂sql实用传统xml方式
- 自动填充：handle 处理 √ 
- 获取插入id √
- 分页插件：针对do，dto，mybatis-plus 仅支持一种泛型 （待处理，需二次封装×）
- 简单统计：（暂无）
- 时间范围查询(比较查询)：代码中实现 √
- 默认逻辑删除为0进行查询: 配置文件&配置类&注解 三部曲 √
- 自动代码生成器：spring-boot-09-8088-autogenerator（暂无）
- 多数据源：（暂无）
- 处理 bean 相互转换出现的问题: hutool BeanUtil.copyProperties √


```sql
CREATE DATABASE /*!32312 IF NOT EXISTS*/`Schema` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `Schema`;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_modifier_id` bigint(20) DEFAULT NULL COMMENT '最后修改者ID',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  `deleted` smallint(1) DEFAULT '0' COMMENT '是否已删除，1:是，0:否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;
```

