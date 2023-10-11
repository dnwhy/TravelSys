package com.etc.travelsys.controller;

import com.etc.travelsys.mapper.GuideMapper;
import com.etc.travelsys.model.entity.GuideEntity;
import com.etc.travelsys.model.entity.GuideTypeEntity;
import com.etc.travelsys.model.entity.UserEntity;
import com.etc.travelsys.service.IGuideService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/guide")
public class GuideController {

    @Autowired
    @Qualifier("guideService")
    private IGuideService guideService;

    //查询所有旅游攻略类型
    @RequestMapping("/queryAllType")
    public @ResponseBody List<GuideTypeEntity> queryAllType(){
        //1.调用service中的方法   参数：无   返回值 ：List<GuideTypeEntity>
        return guideService.queryAllType();
    }

    //添加旅游记忆攻略 -- 富文本编辑器
    @RequestMapping("/addGuide")
    public @ResponseBody String addGuide(GuideEntity guide, HttpSession session,
                                         @RequestParam("file")MultipartFile file){
        log.info("添加旅游攻略方法入参=======，guide={}",guide);
        //1.上传文件，获取文件路径
        //(1)接收上传的文件@RequestParam ("file")MultipartFile file
        try {
            //(2)根据时间戳+文件名来创建新的文件名，这样即使是第二次上传相同名称的文件,也不会把第一次的文件覆盖
            String fileName = System.currentTimeMillis()+file.getOriginalFilename();
            //(3)图片存储的真实路径，然后拼接文件名
            String destFileName = "D:\\Windows11\\Desktop\\upload\\guide\\"+fileName;
            //(4)第一次运行的时候，这个文件所在的目录是不存在的，这里需要创建一下目录
            File destFile = new File(destFileName);
            destFile.getParentFile().mkdirs(); //创建文件目录
            //(5)把浏览器上传的文件复制到指定位置
            file.transferTo(destFile);

            //2.把图片的存储路径，设置到guide对象的img属性（数据库中存储的路径)
            guide.setImg("/upload/guide/"+fileName);

            //3.获取session中的userid和username
            UserEntity user = (UserEntity) session.getAttribute("USER");
            guide.setPublishid(user.getUserid());
            guide.setPublishname(user.getUserName());

            //4.调用service中的方法参数:GuideEntity返回值: boolean
            boolean res = guideService.addGuide(guide);
            return res == true?"旅游攻略添加成功":"旅游攻略添加失败";
        }catch (IOException e){
            e.printStackTrace();
        }
        return "旅游攻略添加失败";
    }

    //查询所有类型以及类型下的旅游攻略列表
    @RequestMapping("/queryAllGuide")
    public String queryAllGuide(Model model){
        //1.获取请求中的参数
        //2.调用方法
        List<GuideTypeEntity> guideTypes = guideService.queryAllGuide();
        //3.把数据保存到model中
        model.addAttribute("guideTypes",guideTypes);
        log.info("查询所有类型以及类型下的旅游攻略列表结果，guideTypes={}",guideTypes);
        //4.跳转页面
        return "/strategy/strategy";
    }

    //查询旅游攻略详情
    @RequestMapping("/queryGuideDetail")
    public String queryGuideDetail(int gid,Model model){
        //调用service查询旅游攻略详情的方法
        GuideEntity guide = guideService.queryGuideDetail(gid);
        model.addAttribute("guide",guide);
        return "/strategy/strategy_detail";
    }

    //查询所有的旅游攻略
    @RequestMapping("/queryAll")
    public String queryAll(Model model){
        List<GuideEntity> guides = guideService.queryAll();
        model.addAttribute("guides",guides);
        return "/admin/strategy_list";
    }

    //删除攻略
    @RequestMapping("/delGuide")
    public @ResponseBody boolean delGuide(int gid){
        //1.接收页面的参数--方法入参gid
        log.info("========删除旅游攻略的gid={}",gid);
        //2.调用service中删除旅游记忆的方法   参数: mid  返回值: boolean
        //3.响应数据
        return guideService.delGuide(gid);
    }

}
