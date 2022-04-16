package com.yy.dao;

import com.yy.pojo.Guests;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public interface GuestsMapper {
    
    int addGuests(Guests guests);
    
    int deleteGuestsById(@Param("id") int id);
    
    int updateGuestsById(Guests guests);
    
    Guests queryGuestsById(@Param("id") int id);
    
    ArrayList<Guests> queryAllGuests();
    
    Guests queryGuestsByPhone(@Param("phone") String phone);
}
