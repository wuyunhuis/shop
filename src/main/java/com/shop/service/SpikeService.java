package com.shop.service;

import com.shop.entity.Spike;
import com.shop.mapper.SpikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("spikeService")
public class SpikeService {

    @Autowired
    SpikeMapper spikeMapper;

    public void insert(Spike spike) {
        spikeMapper.insertSpike(spike);
    }

    public List<Spike> findByBid(Integer bid) {
        return spikeMapper.findByBid(bid);
    }
    public Integer maxSid(){
        return spikeMapper.maxSid();
    }

    public List<Spike> findAll() {
        return spikeMapper.findAll();
    }

    public Spike findSpikeBySid(Integer sid) {
        return spikeMapper.findSpikeBySid(sid);
    }

    public void upateSpike(Integer states, Integer sid) {
        spikeMapper.upateSpike(states,sid);
    }

    public void updateNumber(Integer i, Integer sid) {
        spikeMapper.updateNumber(i,sid);
    }
}
