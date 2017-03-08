package com.aelns.dao;

import com.aelns.model.City;
import com.aelns.model.CityExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 这个DAO层的接口是通过工具自动生成的, 不用自己写, Mybatis的用法还有写定制的SQL和注解写SQL, 使用到时可以google一下<br/>
 *
 * 一般使用模板方法而不是自己写SQL, 开发速度会快一些(推荐),<br/>
 * 可以避免踩很多坑, 调试也方便一些(使用JRebel或者其他热部署工具的情况下)
 *
 * */
public interface CityMapper {

    /**
     * 根据模板统计
     * @param example
     * @return 统计数量
     */
    int countByExample(CityExample example);

    /**
     * 根据一个模板删除,  不推荐使用, 删除表记录应该使用主键或者索引, 避免锁表
     * @param example
     * @return
     */
    int deleteByExample(CityExample example);

    /**
     * 根据主键删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入数据, 不管字段是否为空
     * @param record
     * @return 受影响的行数
     */
    int insert(City record);

    /**
     * 插入数据, 只插入不为空的数据
     * @param record
     * @return 受影响的行数
     */
    int insertSelective(City record);

    /**
     * 根据模板查询数据
     * @param example
     * @return list 集合
     */
    List<City> selectByExample(CityExample example);

    /**
     * 根据主键ID查询
     * @param id
     * @return
     */
    City selectByPrimaryKey(Integer id);

    /**
     * 根据模板条件进行更新, 只更新不为NULL的字段, 不推荐使用, 更新表记录应该使用主键或者索引, 避免锁表
     * @param record
     * @param example
     * @return 受影响的行数
     */
    int updateByExampleSelective(@Param("record") City record, @Param("example") CityExample example);

    /**
     * 根据模板进行更新, 不管字段是否为空, , 不推荐使用, 更新表记录应该使用主键或者索引, 避免锁表
     * @param record
     * @param example
     * @return 受影响的行数
     */
    int updateByExample(@Param("record") City record, @Param("example") CityExample example);

    /**
     * 根据主键进行更新, 只更新不为NULL的字段, 推荐
     * @param record
     * @return 受影响的行数
     */
    int updateByPrimaryKeySelective(City record);

    /**
     * 根据主键进行更新, 不管字段是否为空
     * @param record
     * @return 受影响的行数
     */
    int updateByPrimaryKey(City record);
}