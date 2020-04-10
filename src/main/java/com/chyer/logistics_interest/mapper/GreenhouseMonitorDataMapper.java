package com.chyer.logistics_interest.mapper;

import com.chyer.logistics_interest.entity.GreenhouseMonitorData;

public interface GreenhouseMonitorDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GreenhouseMonitorData record);

    int insertSelective(GreenhouseMonitorData record);

    GreenhouseMonitorData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GreenhouseMonitorData record);

    int updateByPrimaryKey(GreenhouseMonitorData record);
}