package com.etc.travelsys.controller;

import com.etc.travelsys.model.entity.TogetherEntity;
import com.etc.travelsys.model.entity.UserEntity;
import com.etc.travelsys.service.ITogetherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/together")
@Slf4j
public class TogetherController {

    //注入ITogetherService对象
    @Autowired
    @Qualifier("togetherService")
    private ITogetherService togetherService;

    //搜索并分页查询结伴游列表
    @RequestMapping("/queryTogether")
    public String queryTogether(@RequestParam(required = false,value = "pageNum",defaultValue = "1") int pageNum,
                                @RequestParam(required = false,value = "searchAddress",defaultValue = "no") String searchAddress,
                                Model model){
        //1.接收页面参数---方法入参   pageNum  searchAddress
        log.info("搜索并分页查询结伴游列表，入参========，pageNum{},searchAddress={}",pageNum,searchAddress);
        //2.设置起始页和起始条数
        PageHelper.startPage(pageNum,2);
        //3.调用service中的方法   参数：searchAddress   返回值: List<TogetherEntity>
        List<TogetherEntity> togethers = togetherService.queryTogether(searchAddress);
        //4.封装结果集
        PageInfo<TogetherEntity> pageInfo = new PageInfo<>(togethers);
        //5.把数据保存到model中
        model.addAttribute("pageInfo",pageInfo);
        if (!searchAddress.equals("no")) {
            model.addAttribute("searchAddress", searchAddress);
        }
        log.info("查询到的数据===================={}",pageInfo);
        //6.跳转到/traveller/traveller. html
        return "/traveller/traveller";
    }

    //查询结伴游详情
    @RequestMapping("/queryTogetherDetail")
    public String queryTogetherDetail(int togetherid,Model model){

        //1.接收请求中的参数--入参  togetherid
        //2.调用service中查询结伴游详情的方法   参数:togetherid   返回值: TogetherEntity
        TogetherEntity together = togetherService.queryTogetherDetail(togetherid);
        log.info("查询结伴游详情结果，{}",together);
        //3.把数据保存到model中
        model.addAttribute("t",together);
        //4.跳转到/traveller/traveller_detail.html
        return "/traveller/traveller_detail";
    }

    //添加结伴游
    @PostMapping("/addTogether")
    public @ResponseBody boolean addTogether(TogetherEntity together,
                                             @RequestParam("file")MultipartFile file, HttpSession session){
        log.info("添加结伴游方法的入参，together={},file={}",together,file);
        boolean res = false;
        //1.上传文件，获取文件路径
        //(1)接收上传的文件@RequestParam ("file"")MultipartFile file
        try {
            //(2)根据时间戳+文件名来创建新的文件名，这样即使是第二次上传相同名称的文件,也不会把第一次的文件覆盖
            String fileName = System.currentTimeMillis()+file.getOriginalFilename();
            //(3)图片存储的真实路径，然后拼接文件名
            String destFileName = "D:\\Windows11\\Desktop\\upload\\together\\"+fileName;
            //(4)第一次运行的时候，这个文件所在的目录是不存在的，这里需要创建一下目录
            File destFile = new File(destFileName);
            destFile.getParentFile().mkdirs(); //创建文件目录
            //(5)把浏览器上传的文件复制到指定位置
            file.transferTo(destFile);

            //2.把图片的存储路径，设置到memory对象的img属性（数据库中存储的路径)
            together.setImg("/upload/together/"+fileName);

            //3.获取session中的userid和username
            UserEntity user = (UserEntity) session.getAttribute("USER");
            together.setPublishid(user.getUserid());
            together.setPublishname(user.getUserName());

            //4.调用service中的方法参数:TogetherEntity返回值: boolean
            res = togetherService.addTogether(together);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    //分页查询我发布的结伴游
    @RequestMapping("queryMyTogether")
    public String queryMyTogether(@RequestParam(value = "pageNum",required = false,defaultValue = "1") int pageNum,
                                  HttpSession session,Model model,@RequestParam(required = false) String isCheck){
        if (!StringUtils.isEmpty(isCheck)){
            session.setAttribute("isCheck",isCheck);
        }
        //1.获取请求中的参数---入参pageNum
        //2.获取session中的userid
        int userid = ((UserEntity)(session.getAttribute("USER"))).getUserid();
        //3.设置起始页码和每页展示的条数
        PageHelper.startPage(pageNum,2);
        //4.调用service中的方法参数:userid返回值:List<Together>
        List<TogetherEntity> togethers = togetherService.queryMyTogether(userid);
        //5.分装结果集
        PageInfo<TogetherEntity> pageInfo = new PageInfo<>(togethers);
        //6.把数据保存到model中
        model.addAttribute("pageInfo",pageInfo);
        log.info("分页查询我发布的结伴游，pageInfo={}",pageInfo);
        //7.跳转页面到/user/travel_list.html
        return "/user/travel_list";
    }

    //删除结伴游
    @RequestMapping("/delTogether")
    public @ResponseBody boolean delTogether(int togetherid){
        //1.获取请求中的参数: togetherid
        log.info("删除结伴游方法入参，togetherid = {}",togetherid);
        //2.调用service中的方法参数:together返回值: boolean
        boolean res = togetherService.delTogether(togetherid);
        //3.响应数据
        return res;
    }

    //修改结伴游 -  - 查询结伴游详情
    @RequestMapping("/queryTogetherById")
    public String queryTogetherById(int togetherid,Model model){
        //1.接收请求中的参数 -- 入参  together
        //2.调用service中查询结伴游详情的方法 参数：togetherid 返回值：TogetherEntity
        TogetherEntity together = togetherService.queryTogetherDetail(togetherid);
        log.info("查询结伴游详情结果，{}",together);
        //3.把数据保存到model中
        model.addAttribute("t",together);
        return "/user/travel_update";
    }

    //修改结伴游
    @RequestMapping("updateTogether")
    public @ResponseBody boolean updateTogether(TogetherEntity together,@RequestParam("file") MultipartFile file) {
        log.info("修改结伴游方法的入参，together={}，file={}", together, file);
        boolean res = false;
        //1.接收请求中的参数---方法入参file TogetherEntity
        //2.判断是否有文件上传有文件-执行上传操作﹑没有文件上传-使用之前的图片路径
        if (!file.isEmpty()) {
            //3.上传文件，获取文件路径
            try {
                //(1)根据时间戳+文件名来创建新的文件名，这样即使是第二次上传相同名称的文件,也不会把第一次的文件覆盖
                String fileName = System.currentTimeMillis()+file.getOriginalFilename();
                //图片存储的真实路径，然后拼接文件名
                String destFileName = "D:\\Windows11\\Desktop\\upload\\together\\"+fileName;
                //第一次运行的时候，这个文件所在的目录是不存在的，这里需要创建一下目录
                File destFile = new File(destFileName);
                destFile.getParentFile().mkdirs(); //创建文件目录
                //(2)把浏览器上传的文件复制到指定位置
                file.transferTo(destFile);
                //(3)把图片的存储路径，设置到memory对象的img属性（数据库中存储的路径)
                together.setImg("/upload/together/"+fileName);
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        //4.调用service中的方法参数:TogetherEntity返回值: boolean
        res = togetherService.updateTogether(together);
        return res;
    }

    //指定作者id查询他发布的结伴游
    @RequestMapping("/queryAuthorTogether")
    public String queryAuthorTogether(Model model,int publishid){
        //调用service中查询我的结伴游的方法参数: publishid返回值:List<TogetherEntity>
        List<TogetherEntity> togethers = togetherService.queryMyTogether(publishid);
        //把数据保存到model中
        model.addAttribute("togethers",togethers);
        //跳转页面
        return "/friend/user_partner";
    }


}
