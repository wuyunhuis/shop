package com.shop.service;

import com.shop.entity.Spelllist;
import com.shop.entity.Userlist;
import com.shop.mapper.SpelllistMapper;
import com.shop.mapper.UserlistMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("spelllistService")
public class SpelllistService {
    @Autowired
    private  SpelllistMapper spelllistMapper;

    @Autowired
    private UserlistMapper userlistMapper;

    public void insert(Spelllist spelllist) {
        spelllistMapper.insertSpike(spelllist);
    }

    public List<Spelllist> findAll() {
        return spelllistMapper.findAll();
    }
    public List<Spelllist> findByBid(Integer bid) {
        return spelllistMapper.findByBid(bid);
    }

    public  Integer maxSid(){
        return spelllistMapper.maxSid();
    }

    public Spelllist findBySid(Integer sid) {
        return spelllistMapper.findBySid(sid);
    }

    public List<Userlist> findUserBySid(Integer sid) {
        return userlistMapper.findUserBySid(sid);
    }

    public void updateExitnum(Integer eixtnum, Integer sid) {
        spelllistMapper.updateExitnum(eixtnum, sid);
    }

    public void insertUserList(Integer usid, Integer sid, Integer uid) {
        userlistMapper.insertUserList(usid,sid,uid);
    }
    public  Integer findMaxUsid(){
        return userlistMapper.MaxUsid();
    }

    public void updateExitnumAndState(Integer i, Integer eixtnum, Integer sid) {
        spelllistMapper.updateExitnumAndState(i,eixtnum, sid);
    }

    public void updateNumber( Integer number,Integer sid) {
        spelllistMapper.updateNumber(number,sid);
    }

    public void updateUserList(Integer i, Integer sid, Integer uid) {
        userlistMapper.updateUserList(i,sid,uid);
    }
}
