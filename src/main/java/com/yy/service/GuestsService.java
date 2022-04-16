package com.yy.service;

import com.yy.pojo.Guests;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface GuestsService {
    int addGuests(Guests guests);

    int deleteGuestsById(int id);

    int updateGuestsById(Guests guests);

    Guests queryGuestsById(int id);

    ArrayList<Guests> queryAllGuests();

    Guests queryGuestsByPhone( String phone);
}
