package com.aelns.dao;

import com.aelns.model.PlatformApplication;
import com.aelns.model.PlatformApplicationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlatformApplicationMapper {
    int countByExample(PlatformApplicationExample example);

    int deleteByExample(PlatformApplicationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PlatformApplication record);

    int insertSelective(PlatformApplication record);

    List<PlatformApplication> selectByExample(PlatformApplicationExample example);

    PlatformApplication selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PlatformApplication record, @Param("example") PlatformApplicationExample example);

    int updateByExample(@Param("record") PlatformApplication record, @Param("example") PlatformApplicationExample example);

    int updateByPrimaryKeySelective(PlatformApplication record);

    int updateByPrimaryKey(PlatformApplication record);
}