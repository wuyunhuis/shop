package com.shop.mapper;


import com.shop.entity.Userlist;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserlistMapper {

    @Select("select * from userlist where sid =#{sid}")
    List<Userlist> findUserBySid(@Param(value = "sid") Integer sid);

    @Select("select max(usid) from userlist")
    Integer MaxUsid();

    @Insert("insert into userlist (usid,sid,uid) values (#{usid},#{sid},#{uid})")
    @Options(useGeneratedKeys=true, keyProperty="usid", keyColumn="usid")
    void insertUserList(@Param(value = "usid")Integer usid, @Param(value = "sid")Integer sid, @Param(value = "uid")Integer uid);

    @Update("update userlist set uid=#{i} where sid=#{sid} and uid=#{uid} ")
    void updateUserList(@Param(value = "i")Integer i, @Param(value = "sid")Integer sid, @Param(value = "uid")Integer uid);
}