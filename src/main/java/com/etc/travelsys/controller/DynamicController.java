package com.etc.travelsys.controller;

import com.etc.travelsys.model.entity.DynamicEntity;
import com.etc.travelsys.model.entity.UserEntity;
import com.etc.travelsys.model.vo.FriendDynamicVo;
import com.etc.travelsys.service.IDynamicService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/dynamic")
@Slf4j
public class DynamicController {

    @Autowired
    @Qualifier("dynamicService")
    private IDynamicService dynamicService;

    //我的动态
    @RequestMapping("/queryMyDynamic")
    public String queryMyDynamic(
            @RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,
            HttpSession session, Model model,@RequestParam(required = false) String isCheck){
        if (!StringUtils.isEmpty(isCheck)){
            session.setAttribute("isCheck",isCheck);
        }

        //1.获取请求中的参数
        //2.从session获取userid
        int userid = ((UserEntity)session.getAttribute("USER")).getUserid();
        //3.设置起始页和每页展示的条数
        PageHelper.startPage(pageNum,2);
        //4.访问service中的方法参数: userid返回值:List<DynamicEntity>
        List<DynamicEntity> dynamics = dynamicService.queryMyDynamic(userid);
        //5.PageInfo封装查询结果
        PageInfo<DynamicEntity> pageInfo = new PageInfo<>(dynamics);
        log.info("我的动态，pageInfo={}",pageInfo);
        //6.把数据保存到model中
        model.addAttribute("pageInfo",pageInfo);
        //7.跳转到state.html
        return "/user/state";
    }

    //发表评论
    @RequestMapping("/addDynamic")
    public @ResponseBody boolean addDynamic(String dcontent,HttpSession session){
        //1.获取请求中的参数
        //2.从session中获取userID 和username
        UserEntity user = (UserEntity) session.getAttribute("USER");
        int userid = user.getUserid();
        String username = user.getUserName();

        //3.调用service中的方法  参数：DynamicEntity  返回值：userID
        DynamicEntity dynamic = new DynamicEntity();
        dynamic.setPublishid(userid);
        dynamic.setPublishname(username);
        dynamic.setDcontent(dcontent);
        log.info("评论方法入参，dcontent={}",dcontent);

        boolean res = dynamicService.addDynamic(dynamic);
        //4.响应数据
        return res;
    }

    //删除动态
    @RequestMapping("/delDynamic")
    public @ResponseBody boolean delDynamic(int dynamicid){
        return dynamicService.delDynamic(dynamicid);
    }

    //好友动态
    @RequestMapping("/queryFriendDynamic")
    public String queryFriendDynamic(
            @RequestParam(required = false) String isCheck,
            @RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,
            HttpSession session,Model model){

        if (!StringUtils.isEmpty(isCheck)){
            session.setAttribute("isCheck",isCheck);
        }

        //1.获取请求中的参数
        //2.从session获取userid
        int userid = ((UserEntity)session.getAttribute("USER")).getUserid();
        //3.设置起始页和每页条数
        PageHelper.startPage(pageNum,5);
        //4.调用service中的方法  参数：userid    返回值：List<FriendDynamic>
        List<FriendDynamicVo> friendDynamicVos = dynamicService.queryFriendDynamic(userid);
        //5.封装结果集
        PageInfo<FriendDynamicVo> pageInfo = new PageInfo<>(friendDynamicVos);
        log.info("好友动态,pageInfo={}",pageInfo);
        //6.把数据保存到请求属性中
        model.addAttribute("pageInfo",pageInfo);
        //7.请求转发
        return "/user/state_friend";
    }




}
