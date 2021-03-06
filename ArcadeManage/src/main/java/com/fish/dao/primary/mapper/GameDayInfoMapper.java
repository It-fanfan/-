package com.fish.dao.primary.mapper;

import com.fish.dao.primary.model.GameDayInfo;

import java.util.List;

public interface GameDayInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GameDayInfo record);

    int insertSelective(GameDayInfo record);

    GameDayInfo selectByPrimaryKey(Integer id);
    GameDayInfo selectByCode(String  ddCode);
    List<GameDayInfo> selectAll();

    int updateByPrimaryKeySelective(GameDayInfo record);

    int updateByPrimaryKey(GameDayInfo record);

    List<GameDayInfo> selectAllGroup();

    Integer selectQMaxId();

    Integer selectSMaxId();
}