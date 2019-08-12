package com.shop.mapper;


import com.shop.entity.Spelllist;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface SpelllistMapper {

    void insertSpike(Spelllist spelllist);

    @Select("select * from spelllist")
    List<Spelllist> findAll();

    @Select("select * from spelllist where bid=#{bid}")
    List<Spelllist> findByBid(@Param(value = "bid") Integer bid);

    @Select("SELECT max(sid) from spelllist")
    Integer maxSid();

    @Select("select * from spelllist where sid=#{sid}")
    Spelllist findBySid(@Param(value = "sid")Integer sid);

    @Update("update spelllist set eixtnum=#{eixtnum} where sid=#{sid}")
    void updateExitnum(@Param(value = "eixtnum")Integer eixtnum, @Param(value = "sid")Integer sid);

    @Update("update spelllist set  state=#{i},eixtnum=#{eixtnum} where sid=#{sid}")
    void updateExitnumAndState(@Param(value = "i")Integer i,@Param(value = "eixtnum")Integer eixtnum, @Param(value = "sid")Integer sid);

    @Update("update spelllist set  number=#{number} where sid=#{sid}")
    void updateNumber(@Param(value = "number")Integer number, @Param(value = "sid")Integer sid);
}