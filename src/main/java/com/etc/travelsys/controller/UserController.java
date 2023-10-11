package com.etc.travelsys.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.etc.travelsys.model.dto.UserDto;
import com.etc.travelsys.model.entity.UserEntity;
import com.etc.travelsys.service.IUserService;
import com.etc.travelsys.utils.FaceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller //标记控制层，把当前类的对象交给容器管理
//映射路径（访问路径,可以声明在类和方法上），只要访问此类中的资源，一级路径必须是user
@RequestMapping("/user")
@Slf4j //日志框架
public class UserController {
    @Autowired //根据类型自动注入对象
    @Qualifier("userService")  //如果指定注入的对象，使用Qualifier注解
    private IUserService userService;

    //登录
    @RequestMapping("/login")
    public String login(String username, String password, Model model, HttpSession session){
        log.info("login方法入参：username={},password={}",username,password);  //日志信息打印
        //1.接收请求中的参数(view-controller)：方法的入参直接获取(页面输入的username和password)
        //2.处理数据
        //(1)创建UserServiceImpl的对象 --- 成员变量自动注入
        //(2)调用service中的方法  参数：username password  返回值：UserEntity
        UserEntity user =  userService.login(username,password);

        //3.保存数据(controller-view)并跳转页面
        String url = "";
        //(1)判断登录是否成功
        if (user != null){
            log.info("登录成功后的UserEntity数据，{}",user);
            //(2)成功 1.把数据保存到session中  2.跳转页面到index.html
            session.setAttribute("USER",user);

            if (user.getRole() == 1){   //role 1-普通用户  2-管理员用户

                //响应重定向，不会走视图解析器，直接跳转到指定地址
                url = "redirect:/memory/queryIndexMemory";
            }else{
                url = "/admin/strategy_add";
            }

        }else{
            //(3)失败 1.把异常信息保存到model中  2.跳转到login.html
            model.addAttribute("msg","登录名或者密码错误");
            url = "/login/login";
        }

        //经过视图解析器，最终路径解析为 前缀(/templates/)+url+后缀(.html)
        return url;
    }


    //验证用户名是否存在
    @RequestMapping("/checkUserName")
    public @ResponseBody boolean checkUserName(String username){
        System.out.println("================"+username);
        //1.接收页面参数---方法入参  username
        //2.处理数据,调用service中验证用户名是否注册过的方法  参数：userName  返回值：boolean
        boolean res = userService.checkUserName(username);
        //3.处理结果并响应数据
        return res;
    }

    //注册
    @RequestMapping("/register")
    public @ResponseBody boolean register(@Validated UserDto userDto){
        System.out.println("=============="+userDto);
        //1.接收页面参数--方法入参 UserDto
        //2.调用service中注册的方法   参数：UserDto 返回值：boolean
        return userService.register(userDto);
    }

    //修改用户基本信息
    @RequestMapping("/updateUser")
    public @ResponseBody boolean updateUser(UserEntity user,HttpSession session){
        //1.接受请求中的数据-----方法入参   UserEntity
        log.info("修改个人信息的入参UserEntity={}",user);
        //2.调用service中的方法   参数：UserEntity   返回值：Boolean
        boolean res = userService.updateUser(user);
        //重写获取一下修改后的用户信息，保存到session中
        String userName = ((UserEntity)session.getAttribute("USER")).getUserName();
        String password = ((UserEntity)session.getAttribute("USER")).getPassword();
        session.setAttribute("USER",userService.login(userName,password));

        //3.响应数据
        return res;
    }

    //退出登录
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        //1.让session立即失效
        session.invalidate();
        //2.跳转到登录页面
        return "/login/login";
    }

    //修改头像
    @RequestMapping("/updateImg")
    public @ResponseBody String updateImg(@RequestParam("file")MultipartFile file,HttpSession session){
        //1.获取请求中的参数---file
        //2.判断file是否为null
        if (file.isEmpty()){
            //3.如果为null----直接return
            return "头像已经是最新";
        }else {
            //4.如果不为null---更新数据库中的img地址
            //(1)根据时间戳+文件名来创建新的文件名，这样即使是第二次上传相同名称的文件,也不会把第一次的文件覆盖
            String fileName = System.currentTimeMillis()+file.getOriginalFilename();
            //(2)图片存储的真实路径，然后拼接文件名
            String destFileName = "D:\\Windows11\\Desktop\\upload\\head\\"+fileName;
            //(3)第一次运行的时候，这个文件所在的目录是不存在的，这里需要创建一下目录
            File destFile = new File(destFileName);
            destFile.getParentFile().mkdirs(); //创建文件目录
            //(4)把浏览器上传的文件复制到指定位置
            try {
                file.transferTo(destFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //5.调用service中的方法参数: img userid返回值: boolean
            int userid = ((UserEntity)session.getAttribute("USER")).getUserid();
            boolean res = userService.updateImg("/upload/head/"+fileName,userid);

            if (res){
                //把session中的图片数据修改，并重新保存会session中
                UserEntity u = ((UserEntity) session.getAttribute("USER"));
                u.setHeadimg("/upload/head/"+fileName);
                session.setAttribute("USER",u);
            }

            //6.响应数据
            return res==true?"头像修改成功":"头像修改失败";
        }
    }

    //修改密码
    @RequestMapping("/updatePwd")
    public @ResponseBody String updatePwd(String oldPwd,String newPwd,HttpSession session){
        //1.获取请参数中的参数---原密码、新密码
        log.info("修改密码功能入参，oldPwd={},newPwd={}",oldPwd,newPwd);
        //2.先验证原密码是否正确
        //(1)从session中获取原密码
        UserEntity user = (UserEntity)session.getAttribute("USER");
        String sessionOldPwd = user.getPassword();
        //(2)验证原密码是否正确
        if (!sessionOldPwd.equals(oldPwd)){
            //不正确—-响应结果，提示原始密码不正确
            session.invalidate();
            return "原密码输入错误";
        }else {//正确—-继续
               //3.修改新密码参数:新密码用户id返回值: boolean
            int userid = user.getUserid();
            boolean res = userService.updatePwd(newPwd,userid);

            //修改成功后，让session立即失效(密码修改后要重新登录)
            session.invalidate();
            //4.响应数据
            return res == true?"密码修改成功":"密码修改失败";
        }
    }

    //人脸注册
    @RequestMapping("/faceRegist")
    public @ResponseBody String faceRegist(String img,HttpSession session){
        //1.获取上传的人脸图片
        //2.从session中获取用户信息（哪个用户进行人脸注册>
        UserEntity u = (UserEntity) session.getAttribute("USER");
        //3.调用Faceltil工具类中封装的调用百度AI接口的方法，进行人脸注册
        String resp = FaceUtil.faceRegister(img,u.getUserName(),
                String.valueOf(u.getUserid()),"login_face");
        //4.解析数据
        JSONObject jsonObj = JSONObject.parseObject(resp);
        log.info("调用百度AI人脸接口，响应的json数据,jsonObj={}",jsonObj);
        //5.判断是否注册成功，如果成功，修改用户已进行入脸注册的标识―参数: userid返回值: boolean
        String msg = jsonObj.get("error_msg").toString();
        if ("SUCCESS".equals(msg)){
            userService.updateIsFace(u.getUserid());
            //6.响应数据
            return "SUCCESS";
        }else {
            return "FAIL";
        }
    }

    //人脸登录
    @RequestMapping("/faceLogin")
    public @ResponseBody String faceLogin(String img,HttpSession session){
        //1.获取请求中的参数 ----图片
        //2.调用FaceUtil工具类中封装的百度AI调用的方法
        String resp = FaceUtil.faceLogin(img,"login_face");
        //3.解析结果，判断是否登录成功
        JSONObject jsonObj = JSONObject.parseObject(resp);
        String msg = jsonObj.get("error_msg").toString();
        if ("SUCCESS".equals(msg)){
            //4.如果登录成功，解析json数据中的userid
            JSONObject result = jsonObj.getJSONObject("result");
            JSONArray userList = result.getJSONArray("user_list");
            JSONObject info = userList.getJSONObject(0);
            String userId = info.getString("user_id");
            //根据userid查询用户详细参数: userid返回值: UserEntity
            UserEntity user = userService.queryUserById(Integer.parseInt(userId));
            //5.把查询到的用户信息保存到session中
            session.setAttribute("USER",user);
            return "SUCCESS";
        }
        //6.响应数据
        return "FAIL";
    }

}
