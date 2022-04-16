package com.yy.service;

import com.yy.dao.VipMapper;
import com.yy.pojo.Vip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class VipServiceImpl implements VipService {
  
    
    private VipMapper vipMapper;
    @Autowired
    public void setVipMapper(VipMapper vipMapper) {
        this.vipMapper = vipMapper;
    }

    public int addVip(Vip vip) {
        return vipMapper.addVip(vip);
    }

    public int deleteVipById(int id) {
        return vipMapper.deleteVipById(id);
    }

    public int updateVipById(Vip vip) {
        return vipMapper.updateVipById(vip);
    }

    public Vip queryVipById(int id) {
        return vipMapper.queryVipById(id);
    }

    public ArrayList<Vip> queryAllVip() {
        return vipMapper.queryAllVip();
    }

    public Vip queryVipByPhone(String phone) {
        return vipMapper.queryVipByPhone(phone);
    }
}
