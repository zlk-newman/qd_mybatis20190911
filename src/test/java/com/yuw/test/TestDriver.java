package com.yuw.test;

import com.yuw.bean.UserInfoBean;
import com.yuw.dao.UserInfoBeanMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TestDriver {


    /**
     * 动态查询：
     * 根据用户输入的动态查询条件，拼写动态的sql语句块，并将查询结果以List的形式进行返回。
     */
    @Test
    public void test04() {
        // 使用输入流对象加载mybatis的xml配置文件
        InputStream inputStream = TestDriver.class.getClassLoader().getResourceAsStream("config-mybatis.xml");
        // 1、构建mybatis的SqlSessionFactory类的实例对象（后期可以反转控制给Spring容器）
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 使用try-with方式释放sqlsession对象：sqlsession的事务不是自动提交的，需要手提交
        try (SqlSession session = sqlSessionFactory.openSession()) {
            // 从sqlsession中获取mapper接口的实现类对象（反射+动态代理）
            UserInfoBeanMapper userInfoBeanMapper = session.getMapper(UserInfoBeanMapper.class);

            // 模拟：查询条件实体类参数
            UserInfoBean userInfoBean = new UserInfoBean();
            userInfoBean.setIsdeleted(false);
            userInfoBean.setUseraddress("山东");
            userInfoBean.setUserage("18");
            // 第二个年龄区间的参数怎么处理
            userInfoBean.setUserage2("30");
            userInfoBean.setUsername("ad");
            // 获取一个时间
            Calendar calendar = Calendar.getInstance();
            calendar.set(2019,8,2);
            userInfoBean.setUserregdate(calendar.getTime());
            // 第二个时间区间的参数怎么处理
            // 获取第二个时间
            calendar.set(2019,8,30);
            userInfoBean.setUserregdate2(calendar.getTime());

            // 调用接口的保存方法
            List<UserInfoBean> lstUserinfoBs = userInfoBeanMapper.selectUserInfoByParams(userInfoBean);
            // 输出操作结果
            System.out.println("新增结果：" + lstUserinfoBs);

            // 手动提交sqlsession的事务，后期由spring通过aop来进行统一管理
            //session.commit(); // 查询操作不需要手动提交事务
        }
    }

    /**
     * 使用mapper接口文件，来进行数据库操作
     */
    @Test
    public void test03() {
        //String resource = "org/mybatis/example/mybatis-config.xml";
        // 使用输入流对象加载mybatis的xml配置文件
        InputStream inputStream = TestDriver.class.getClassLoader().getResourceAsStream("config-mybatis.xml");
        // 1、构建mybatis的SqlSessionFactory类的实例对象（后期可以反转控制给Spring容器）
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 使用try-with方式释放sqlsession对象：sqlsession的事务不是自动提交的，需要手提交
        try (SqlSession session = sqlSessionFactory.openSession()) {
            // 从sqlsession中获取mapper接口的实现类对象（反射+动态代理）
            UserInfoBeanMapper userInfoBeanMapper = session.getMapper(UserInfoBeanMapper.class);
            // 通过调用mapper接口的映射文件中的接口方法，来操作数据库：
            // 新增一条记录
            // 创建一个新增的实体类对象：
            UserInfoBean userInfoBean = new UserInfoBean();
            userInfoBean.setIsdeleted(false);
            userInfoBean.setUseraddress("山东济南");
            userInfoBean.setUserage("22");
            userInfoBean.setUsername("user01");
            userInfoBean.setUserpsw("user01");
            // 调用接口的保存方法
            int i = userInfoBeanMapper.insertSelective(userInfoBean);
            // 输出操作结果
            System.out.println("新增结果：" + i);

            // 手动提交sqlsession的事务，后期由spring通过aop来进行统一管理
            session.commit();
        }
    }

    /**
     * 使用mapper接口文件，来进行数据库操作
     */
    @Test
    public void test02() {
        //String resource = "org/mybatis/example/mybatis-config.xml";
        // 使用输入流对象加载mybatis的xml配置文件
        InputStream inputStream = TestDriver.class.getClassLoader().getResourceAsStream("config-mybatis.xml");
        // 1、构建mybatis的SqlSessionFactory类的实例对象（后期可以反转控制给Spring容器）
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 使用try-with方式释放sqlsession对象
        try (SqlSession session = sqlSessionFactory.openSession()) {
            // 从sqlsession中获取mapper接口的实现类对象（反射+动态代理）
            UserInfoBeanMapper userInfoBeanMapper = session.getMapper(UserInfoBeanMapper.class);
            // 通过调用mapper接口的映射文件中的接口方法，来操作数据库
            List<UserInfoBean> lstUserInfos = userInfoBeanMapper.selectByPrimaryKey(1);
            // 输出查询结果
            System.out.println("查询结果：" + lstUserInfos != null?lstUserInfos.get(0).toString():null);
        }
    }

    /**
     * 使用mybatis读取数据库的内容
     * 执行流程：
     * 创建SqlSessionFactory对象，然后创建 sqlSession对象，
     * SQLSession对象使用动态代理模式，执行底层的JDBC的PreparedStatment对象执行sql语句；
     * SQL语句从指定id的或者绑定接口方法的sql语句块中转换过来的；
     * 将查询结果转换为指定类型；
     */
    @Test
    public void test01() {
        //String resource = "org/mybatis/example/mybatis-config.xml";
        // 使用输入流对象加载mybatis的xml配置文件
        InputStream inputStream = TestDriver.class.getClassLoader().getResourceAsStream("config-mybatis.xml");
        // 1、构建mybatis的SqlSessionFactory类的实例对象（后期可以反转控制给Spring容器）
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session2 = sqlSessionFactory.openSession();
        // 2、创建一个sqlsession对象，用户执行mapper映射文件中的查询语句块
        // try-catch 异常处理的with形式,要求写在()中的对象，必须实现 AutoCloseable 接口
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ///3、使用session对象进行数据库查询操作
            /*
            selectByPrimaryKey： 是mybatis框架的mapper映射文件的sql语句块的id
            也就是，session通过id为“selectByPrimaryKey”语句块去进行数据库操作
             */
            Object obj = session.selectOne("selectByPrimaryKey", 1); // 通过主键字段值进行查询操作
            System.out.println("查询结果：" + obj);
        }
    }
}
