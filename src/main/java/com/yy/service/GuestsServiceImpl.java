package com.yy.service;

import com.yy.dao.GuestsMapper;
import com.yy.pojo.Guests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class GuestsServiceImpl implements GuestsService {
   
    private GuestsMapper guestsMapper;
    @Autowired
    public void setGuestsMapper(GuestsMapper guestsMapper) {
        this.guestsMapper = guestsMapper;
    }

    public int addGuests(Guests guests) {
        return guestsMapper.addGuests(guests);
    }

    public int deleteGuestsById(int id) {
        return guestsMapper.deleteGuestsById(id);
    }

    public int updateGuestsById(Guests guests) {
        return guestsMapper.updateGuestsById(guests);
    }

    public Guests queryGuestsById(int id) {
        return guestsMapper.queryGuestsById(id);
    }

    public ArrayList<Guests> queryAllGuests() {
        return guestsMapper.queryAllGuests();
    }

    public Guests queryGuestsByPhone(String phone) {
        return guestsMapper.queryGuestsByPhone(phone);
    }
}
