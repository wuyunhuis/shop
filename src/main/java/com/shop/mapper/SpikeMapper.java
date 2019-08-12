package com.shop.mapper;


import com.shop.entity.Spike;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SpikeMapper {

    @Insert("insert into spike (sid,price,time,state, number, pid,bid) values (#{sid},#{price}, #{time},  #{state}, #{number}, #{pid}, #{bid})")
    @Options(useGeneratedKeys=true, keyProperty="sid", keyColumn="sid")
    void insertSpike(Spike spike);

    @Select("select * from spike where bid=#{bid}")
    List<Spike> findByBid(Integer bid);

    @Select("SELECT max(sid) FROM spike ")
    Integer maxSid();

    @Select("select * from spike")
    List<Spike> findAll();

    @Select("select * from spike where sid=#{sid}")
    Spike findSpikeBySid(@Param(value = "sid") Integer sid);

    @Update("update spike set  state=#{states} where sid=#{sid}")
    void upateSpike(@Param(value = "states")Integer states, @Param(value = "sid")Integer sid);

    @Update("update spike set  number=#{i} where sid=#{sid}")
    void updateNumber(@Param(value = "i")Integer i, @Param(value = "sid")Integer sid);
}