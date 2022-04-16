package com.yy.controller;

import com.yy.pojo.Guests;
import com.yy.service.GuestsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/guests")
public class GuestsController {
    @Autowired
    GuestsServiceImpl guestsService;
    @RequestMapping("/add")
    public ModelAndView add(Guests guests){
        ModelAndView view = new ModelAndView();
        guestsService.addGuests(guests);
        view.setViewName("suc_g");
        return view;
    }
    
    
    @RequestMapping("/delete")
    public  String delete(int id){
        guestsService.deleteGuestsById(id);
        return "redirect:/guests/list";
    }
    
    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView view = new ModelAndView();
        List<Guests> guests = guestsService.queryAllGuests();
        view.addObject("list",guests);
        view.setViewName("guests_list");
    return view;
    }
    
    @RequestMapping("/update1")
    public ModelAndView update1(int id){
        ModelAndView modelAndView = new ModelAndView();
        Guests guests = guestsService.queryGuestsById(id);
        modelAndView.addObject("g",guests);
        modelAndView.setViewName("guests_update");
        return modelAndView;
    }
    
    @RequestMapping("/update2")
    public String update2(Guests guests){
        System.out.println(guests);
        guestsService.updateGuestsById(guests);
        return "redirect:/guests/list";
    }
    
    @RequestMapping("/find")
    public ModelAndView find(String findByPhone){
        ModelAndView mv = new ModelAndView();
        Guests guests = guestsService.queryGuestsByPhone(findByPhone);
        List<Guests> guestsList = new ArrayList<Guests>();
        guestsList.add(guests);
        if (guests==null){
            guestsList=guestsService.queryAllGuests();
            mv.addObject("error","未查询出结果");
        }
        mv.addObject("list",guestsList);
        mv.setViewName("guests_list");
        return mv;
    }
    
}
