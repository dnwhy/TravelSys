package com.etc.travelsys.controller;

import com.etc.travelsys.model.entity.CommentEntity;
import com.etc.travelsys.model.entity.MemoryEntity;
import com.etc.travelsys.model.entity.MemoryTypeEntity;
import com.etc.travelsys.model.entity.UserEntity;
import com.etc.travelsys.model.vo.CityNumVo;
import com.etc.travelsys.service.IMemoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

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
@RequestMapping("/memory")
@Slf4j //日志框架
public class MemoryController {

    //注入对象
    @Autowired
    @Qualifier("memoryService")
    private IMemoryService memoryService;

    //查询首页前四条旅游记忆
    @RequestMapping("/queryIndexMemory")
    public String queryIndexMemory(Model model) {

        //1.接收页面参数---无需参数
        //2.处理数据,调用显示主页旅游记忆的数据﹑参数:无﹑返回值: List<MemoryEntity>
        List<MemoryEntity> memories = memoryService.queryIndexMemory();
        log.info("===============查询首页前四条旅游记忆===============,{}", memories);

        //查询旅游攻略的城市景点统计
        List<CityNumVo> cities = memoryService.queryCityNum();
        model.addAttribute("cities",cities);

        //3.处理结果并跳转页面
        // (1)把集合数据保存到model中
        model.addAttribute("memories", memories);
        // (2)跳转到index.html页面.经过视图解析器
        return "index";
    }

    //查询每个类型的前四条旅游记忆
    @RequestMapping("/queryTypeMemory")
    public String queryTypeMemory(Model model){
        //1.接收页面参数---没有参数
        //2.处理数据，调用service中的方法 参数:无   返回值: List<MemoryTypeEntity>
        List<MemoryTypeEntity> memoryTypes = memoryService.queryTypeMemory();
        log.info("===========查询每个类型的前四条旅游记忆===========,{}",memoryTypes);
        //3.处理结果并跳转页面
        //(1)把数据保存到model中
        model.addAttribute("memoryTypes",memoryTypes);
        //(2)跳转页面到memory/memory.html
        return "memory/memory";
    }

    //查询旅游记忆详情
    @RequestMapping("/queryMemoryDetail")
    public String queryMemoryDetail(int mid,Model model){
        //1.接收请求的参数-–方法入参-- mid
        //2.处理数据
        //(1)调用查询旅游记忆详情的方法  参数: mid 返回值:MemoryEntity
        MemoryEntity memory = memoryService.queryMemoryDetail(mid);
        log.info("查询旅游记忆详情======,{}",memory);
        //(2)调用service旅游记忆详情的所有评论 参数: mid 返回值:List<CommentEntity>
        List<CommentEntity> comments = memoryService.queryCommentList(mid);
        log.info("查询此旅游记忆中所有评论======,{}",comments);
        //3.把数据保存到model中
        model.addAttribute("memory",memory);
        model.addAttribute("comments",comments);

        //4.跳转到memory/memory_detail.html
        return "memory/memory_detail";
    }

    //添加评论
    @RequestMapping("/addComment")
    public @ResponseBody boolean addComment(int mid, String content, HttpSession session){

        log.info("添加评论入参======,mid=,{},content=,{}",mid,content);
        //1.接收请求参数--方法入参mid content
        //2.处理数据
        //(1)获取session对象，从session中获取userid和username
        UserEntity user = (UserEntity) (session.getAttribute("USER"));
        int userId = user.getUserid();
        String userName = user.getUserName();

        //(2)调用service中添加评论的方法参数: CommentEntity 返回值: boolean
        CommentEntity comment = new CommentEntity(0,userId,userName,null,mid,content,null);
        boolean res = memoryService.addComment(comment);
        //3.响应数据
        return res;
    }

    //查询详细旅游列表
    @RequestMapping("queryMoreList")
    //@RequestParam:  请求参数绑定    value = "tid" :获取请求中的tid的值赋值给形式参数tid
    // required = false: 不是必须的   defaultValue = "1" :  默认值
    public String queryMoreList(@RequestParam(value = "tid",required = false,defaultValue = "1") int tid,
                                @RequestParam(value = "pageNum",required = false,defaultValue = "1") int pageNum,
                                Model model){
        log.info("查询更多，方法入参======，tid={}，pageNum={}",tid,pageNum);
        //1.接收页面中的参数---tid、当前页
        //2.调用service中查询所有类型的方法  参数:无   返回值: List<MemoryTypeEntity>
        List<MemoryTypeEntity> typeLists = memoryService.queryMoreList();
        //3.设置分页的起始条数和每页展示的条数PageHelper.start(1,3)
          //每页展示的条数，pageNum: 要查询的页码
        int pageSize = 2;
        PageHelper.startPage(pageNum,pageSize);
        //4.调用查询类型对应的分页数据     参数:tid   返回值: List<MemoryEntity>
        List<MemoryEntity> typePages = memoryService.queryTypePage(tid);
        //5.封装分页结果集
        PageInfo<MemoryEntity> page = new PageInfo<>(typePages);
        log.info("总记录数：{}",page.getTotal());
        log.info("总每页数据详情：{}",page.getList());
        log.info("总页数：{}",page.getPages());
        log.info("当前页：{}",page.getPageNum());
        log.info("上一页：{}",page.getPrePage());
        log.info("下一页：{}",page.getNextPage());
        //6.把数据保存到model中
        model.addAttribute("typeLists",typeLists);
        model.addAttribute("page",page);
        model.addAttribute("tid",tid);
        //7.请求转发到memory/memory_list.html
        return "/memory/memory_list";
    }

    //查询所有旅游记忆类型
    @RequestMapping("/queryAllType")
    public @ResponseBody List<MemoryTypeEntity> queryAllType(){
        List<MemoryTypeEntity> typeLists = memoryService.queryMoreList();
        return typeLists;
    }

    //添加旅游记忆，上传图片
    @PostMapping("/addMemory")
    public String addMemory(MemoryEntity memory,
                                           @RequestParam("file")MultipartFile file,
                                           HttpSession session){
        //1.上传文件，获取文件路径
        //(1).接收上传的文件@RequestParam("file")MultipartFile file
        try {
            //(2).根据时间戳+文件名来创建新的文件名，这样即使是第二次上传相同名称的文件,也不会把第一次的文件覆盖
            String fileName = System.currentTimeMillis()+file.getOriginalFilename();
            //(3).图片存储的真实路径，然后拼接文件名
            String destFileName = "D:\\Windows11\\Desktop\\upload\\memory\\"+fileName;
            //(4).第一次运行的时候，这个文件所在的目录是不存在的，这里需要创建一下目录
            File destFile = new File(destFileName);
            destFile.getParentFile().mkdirs(); //创建文件目录
            //(5).把浏览器上传的文件复制到指定位置
            file.transferTo(destFile);

            //2.把图片的存储路径，设置到memory对象的img属性（数据库中存储的路径)
            memory.setImg("/upload/memory/"+fileName);

            //3.获取session中的userid和username
            UserEntity user = (UserEntity) session.getAttribute("USER");
            memory.setPublishid(user.getUserid());
            memory.setPublishname(user.getUserName());

            //4.调用service中的方法参数:MemoryEntity返回值: boolean
            memoryService.addMemory(memory);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/memory/queryAllMemory";
    }

    //分页查询我发布的所有旅游记忆列表
    @RequestMapping("/queryAllMemory")
    public String queryAllMemory(@RequestParam(value = "pageNum",
            required = false,defaultValue = "1") int pageNum,Model model,
                                 HttpSession session,
                                 @RequestParam(required = false) String isCheck){

        if (!StringUtils.isEmpty(isCheck)){
            session.setAttribute("isCheck",isCheck);
        }
        //1.接收请求参数---方法入参 pageNum
        //从session中获取userid
        int userid = ((UserEntity)(session.getAttribute("USER"))).getUserid();
        //2.设置分页的起始条数和每页展示的条数
        PageHelper.startPage(pageNum,3);
        //3.调用查询所有旅游记忆的分页数据   参数:userid  返回值: List<MemoryEntity>
        List<MemoryEntity> memories = memoryService.queryAllMemory(userid);
        //4.封装分页结果集
        PageInfo<MemoryEntity> pageInfo = new PageInfo<>(memories);
        //5.把数据保存至model中
        model.addAttribute("pageInfo",pageInfo);
        //6.跳转到/user/memory_list.html页面
        return "/user/memory_list";
    }

    //删除旅游记忆
    @RequestMapping("/delMemory")
    public @ResponseBody boolean delMemory(int mid){
        //1.接收页面的参数--方法入参mid
        log.info("========删除旅游记忆的mid={}",mid);
        //2.调用service中删除旅游记忆的方法   参数: mid  返回值: boolean
        boolean res = memoryService.delMemory(mid);
        //3.响应数据
        return res;
    }

    //修改页面，查询记忆详情
    @RequestMapping("/queryMemoryByMid")
    public String queryMemoryByMid(int mid,Model model){
        //1.接收请求中的参数--方法入参mid
        //2.调用service，查询旅游记忆详情参数: mid返回值:MemoryEntity
        MemoryEntity memory = memoryService.queryMemoryDetail(mid);
        //3．调用service，查询旅游类型类别---之前写过﹑参数;无﹑返回值: List<MemoryTypeEntity>
        List<MemoryTypeEntity> lists = memoryService.queryMoreList();
        //4.把数据保存至model中
        model.addAttribute("memory",memory);
        model.addAttribute("lists",lists);
        //5.跳转到/user/memory_update.html
        return "/user/memory_update";
    }

    //修改旅游记忆详情
    @PostMapping("/updateMemory")
    public String updateMemory(MemoryEntity memory,
                               @RequestParam("file")MultipartFile file){
        //1.接受请求参数，MemoryEntity memory
        //2.判断是否有文件上传有文件-执行上传操作没有文件上传-使用之前的图片路径
        if (!file.isEmpty()){
            //3.有文件上传
            //(1).根据时间戳+文件名来创建新的文件名，这样即使是第二次上传相同名称的文件,也不会把第一次的文件覆盖
            String fileName = System.currentTimeMillis()+file.getOriginalFilename();
            //(2).图片存储的真实路径，然后拼接文件名
            String destFileName = "D:\\Windows11\\Desktop\\upload\\memory\\"+fileName;
            //(3).第一次运行的时候，这个文件所在的目录是不存在的，这里需要创建一下目录
            File destFile = new File(destFileName);
            destFile.getParentFile().mkdirs(); //创建文件目录
            try {
                //(4).把浏览器上传的文件复制到指定位置
                file.transferTo(destFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //5.把图片的存储路径，设置到memory对象的img属性（数据库中存储的路径)
            memory.setImg("/upload/memory/"+fileName);
        }
        log.info("修改旅游记忆详情的MemoryEntity,{}",memory);
        //6.调用service中的方法参数:MemoryEntity返回值:无
        memoryService.updateMemory(memory);
        //7.跳转到查询所有旅游记忆列表的方法
        return "redirect:/memory/queryAllMemory";
    }

    //分页查询指定作者id他发布的旅游记忆
    @RequestMapping("/queryAuthorMemory")
    public String queryAuthorMemory(@RequestParam(value = "publishid", required = false,defaultValue = "1") int publishid,
                                    @RequestParam(value = "pageNum", required = false,defaultValue = "1") int pageNum,
                                    Model model){

        //设置分页的起始条数和每页展示的条数
        PageHelper.startPage(pageNum,4);
        //1.调用service中的方法   参数：publishID     返回值：List<MemoryEntity>
        List<MemoryEntity> memories = memoryService.queryAllMemory(publishid);
        //封装分页结果集
        PageInfo<MemoryEntity> pageInfo = new PageInfo<>(memories);
        //2.把数据保存到model中
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("publishid",publishid);
        //3.跳转页面
        return "/friend/user_memory";
    }

    //查询旅游攻略的城市景点统计





}
