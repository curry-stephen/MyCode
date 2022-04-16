package com.yy.controller;

import com.mysql.cj.util.DnsSrv;
import com.yy.pojo.Home;
import com.yy.service.HomeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/home")
public class HomeController {
@Autowired
    HomeServiceImpl homeService;

@PostMapping("/add")
    public String add(Home home, Model model) throws IOException {
    String sqlPath=null;
    //定义保存的本地路径
    String localPath="D:\\Hotel\\web\\upload";
    //定义文件名
    String filename=null;
    System.out.println(home);
    if (!home.getFile().isEmpty()){
        //生成UUID作为文件名称
        String uuid= UUID.randomUUID().toString().replace("-","");
        //获得文件类型，（可以判断如果不是图片，禁止上传）
        String contentType=home.getFile().getContentType();
        //获得文件后缀名
        String suffixName = contentType.substring(contentType.indexOf("/") + 1);
        
        //得到文件名
        filename=uuid+"."+suffixName;
        System.out.println(filename);
        //文件保存路径
        home.getFile().transferTo(new File(localPath+filename));
    }
    //把图片相对路径保存到数据库
    sqlPath="/upload/"+filename;
    System.out.println(sqlPath);
    home.setImg(sqlPath);
    homeService.addHome(home);
    model.addAttribute("home",home);
    return "home_show";
}

@RequestMapping("/delete")
    public String delete(Integer id){
    homeService.deleteHomeById(id);
    return "redirect:/home/list";
}

@RequestMapping("/list")
    public ModelAndView list(){
    ModelAndView view = new ModelAndView();
    List<Home> homes = homeService.queryAllHome();
    view.addObject("list",homes);
    view.setViewName("home_list");
    return view;
}

@RequestMapping("/update1")
    public ModelAndView update1(Integer id){
    ModelAndView modelAndView = new ModelAndView();
    Home home = homeService.queryHomeById(id);
    modelAndView.addObject("h",home);
    modelAndView.setViewName("home_update");
    return modelAndView;
}

@RequestMapping("/update2")
    public String update2(Home home) throws IOException {
    String sqlPath=null;
    String localPath="D:\\Hotel\\web\\upload";
    //定义 文件名
    String filename=null;
    if(!home.getFile().isEmpty()){
        //生成uuid作为文件名称
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        //获得文件类型（可以判断如果不是图片，禁止上传）
        String contentType=home.getFile().getContentType();
        //获得文件后缀名
        String suffixName=contentType.substring(contentType.indexOf("/")+1);
        //得到 文件名
        filename=uuid+"."+suffixName;
        System.out.println(filename);
        //文件保存路径
        home.getFile().transferTo(new File(localPath+filename));
    }
    //把图片的相对路径保存至数据库
    sqlPath = "/upload/"+filename;
    System.out.println(sqlPath);
    home.setImg(sqlPath);

    homeService.updateHomeById(home);
    return ("redirect:/home/list");
}

@RequestMapping("/show")
    public ModelAndView show(Integer id){
    ModelAndView view = new ModelAndView();
    Home home = homeService.queryHomeById(id);
    view.addObject("home",home);
    view.setViewName("home_show");
    return view;
}

@RequestMapping("/find")
    public ModelAndView find(int findByNum){
    ModelAndView view = new ModelAndView();
    Home home = homeService.queryHomeByNum(findByNum);
    List<Home> homes = new ArrayList<Home>();
    homes.add(home);
    if (home==null){
        homes = homeService.queryAllHome();
        view.addObject("error","未查询出结果");
    }
    view.addObject("list",homes);
    view.setViewName("home_list");
    return view;
}

@RequestMapping("/type1")
    public String type1(Integer id,Model model){
    Home home = homeService.queryHomeById(id);
    model.addAttribute("h",home);
    return "H_Type_update";
}
 @RequestMapping("/type2")
 public String type2(Home home){
    homeService.updateH_TypeById(home);
    return "redirect:/home/list";
 }
}
