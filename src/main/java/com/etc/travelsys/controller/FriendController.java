package com.etc.travelsys.controller;

import com.etc.travelsys.model.entity.UserEntity;
import com.etc.travelsys.model.vo.UserVo;
import com.etc.travelsys.service.IFriendService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import jdk.internal.org.objectweb.asm.Handle;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
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
import java.util.Map;

@Controller
@RequestMapping("/friend")
@Slf4j
public class FriendController {

    @Autowired
    @Qualifier("friendService")
    private IFriendService friendService;

    //分页并搜索 展示好友列表
    @RequestMapping("/queryFriendList")
    public String queryFriendList(@RequestParam(required = false,value = "pageNum",defaultValue = "1") int pageNum,
                                  @RequestParam(required = false,value = "searchName",defaultValue = "no") String searchName,
                                  Model model, HttpSession session,
                                  @RequestParam(required = false) String isCheck){
        log.info("分页并搜索 展示好友列表,pageNum={},searchName={},isCheck={}",pageNum,searchName,isCheck);
        //1.获取请求中的参数: searchName、pageNum、 isCheck
        //2.如果isCheck不为空，就把它保存到session中
        if (!StringUtils.isEmpty(isCheck)){
            session.setAttribute("isCheck",isCheck);
        }
        //3.获取session中的userid
        int userid = ((UserEntity)session.getAttribute("USER")).getUserid();
        //4.设置页码和每页展示的条数
        PageHelper.startPage(pageNum,5);
        //5.调用service中的方法   参数:useridsearchName   返回值:List<UserEntity>
        List<UserEntity> users = friendService.queryFriendList(userid,searchName);
        //6.封装结果集
        PageInfo<UserEntity> pageInfo = new PageInfo<>(users);
        log.info("分页并搜索好友列表，pageInfo={}",pageInfo);
        //7.把数据保存到model中
        model.addAttribute("pageInfo",pageInfo);
        //8.请求转发到user/ friend. html
        return "/user/friend";
    }

    //取消好友关注
    @RequestMapping("/delAttention")
    public @ResponseBody boolean delAttention(int friendId,HttpSession session){
        //从session中获取userid
        int userid = ((UserEntity)session.getAttribute("USER")).getUserid();
        //调用service中的方法参数;userid friendid返回值:boolean
        //响应数据
        return friendService.delAttention(userid,friendId);
    }

    //关注好友--------  查询作者详情
    @RequestMapping("/queryFriendDetail")
    public String queryFriendDetail(int publishid,HttpSession session,Model model){
        //1.获取session中的userid
        int userid = ((UserEntity)session.getAttribute("USER")).getUserid();
        //2.调用service中的方法﹑参数: publishid userid 返回值:UserVo
        UserVo user = friendService.queryFriendDetail(userid,publishid);
        log.info("关注好友=====  查询作者详情，UserVo={}",user);
        //3.把数据保存到model中
        model.addAttribute("user",user);
        //4.跳转页面
        return "/friend/user_basic";
    }

    //关注好友和取消关注
    @RequestMapping("/updateAttention")
    public @ResponseBody boolean updateAttention(int friendid,String isAttention,HttpSession session){
        //1.获取请求中的参数
        //2.从session中获取userid
        int userid = ((UserEntity)session.getAttribute("USER")).getUserid();
        //3.调用service中的方法﹑参数: friendid isAttention 返回值:boolean
        //4.响应数据
        return friendService.updateAttention(friendid,isAttention,userid);
    }


}
