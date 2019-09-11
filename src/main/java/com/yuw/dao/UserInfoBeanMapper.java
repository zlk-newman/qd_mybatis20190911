package com.yuw.dao;

import com.yuw.bean.UserInfoBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
 * mapper接口文件和mapper映射文件之间有一一对应的关系：
 * mapper接口中的方法名需要和mapper映射文件中的一个sql语句块的id之间具有一一对应的关系；
 * mapper接口文件的方法的参数列表和mapper映射文件中的需要占位符参数之间个数、顺序、类型之间要一一对应；
 * mapper接口文件的包路径名和mapper映射文件中的namespace属性的值是一一对应的，这样就可以确定二者之间的对应关系；
 */

public interface UserInfoBeanMapper {

    int deleteByPrimaryKey(Integer userid);

    int insert(UserInfoBean record);

    /**
     * 新增操作
     *
     * @param record 新增实体类参数
     * @return 新增成功的记录条数
     */
    int insertSelective(UserInfoBean record);

    /**
     * 可以使用 @Param注解 和 mapper映射文件中的 ${占位符变量名} 保持一致
     *
     * @param id
     * @return
     */
    List<UserInfoBean> selectByPrimaryKey(@Param("userid") Integer id);

    int updateByPrimaryKeySelective(UserInfoBean record);

    int updateByPrimaryKey(UserInfoBean record);

    // 追加业务逻辑处理

    /**
     * 动态查询操作
     *
     * @param record 查询的参数实体
     * @return 查询结果集
     */
    List<UserInfoBean> selectUserInfoByParams(UserInfoBean record);
}