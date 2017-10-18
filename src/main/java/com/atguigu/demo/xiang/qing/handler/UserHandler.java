/******************************************************************
 *
 *    Java Lib For Android, Powered By Shenzhen Jiuzhou.
 *
 *    Copyright (c) 2001-2014 Digital Telemedia Co.,Ltd
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.atguigu.demo.xiang.qing.handler
 *
 *    Filename:    UserHandler.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    Copyright:   Copyright (c) 2001-2014
 *
 *    Company:     Digital Telemedia Co.,Ltd
 *
 *    @author:     Administrator
 *
 *    @version:    1.0.0
 *
 *    Create at:   2017年10月9日 下午9:06:11
 *
 *    Revision:
 *
 *    2017年10月9日 下午9:06:11
 *        - first revision
 *
 *****************************************************************/
package com.atguigu.demo.xiang.qing.handler;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.solr.client.solrj.SolrServerException;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.atguigu.demo.xiang.qing.entities.User;
import com.atguigu.demo.xiang.qing.service.UserService;

@Controller
public class UserHandler {

    @Autowired
    private UserService userService;
    
    @RequestMapping("/demo/user/search")
    public String search(@RequestParam("keywords")String keywords,Model model){
        List<Map<String, Object>> list = userService.getDataFromSolrIndex(keywords);
        model.addAttribute("list", list);
        return "search_result.jsp";
    }
    
    @RequestMapping("/demo/user/update")
    public String update(User user,@RequestParam("headPicture")MultipartFile headPicture,HttpSession session) throws SolrServerException, IOException, MyException{
       /* System.out.println(user);
        User user2 = (User) session.getAttribute("loginUser");
        System.out.println("user2 = " + user2);*/
        //1.判断headPicture是否为空
        if(!headPicture.isEmpty()){
            //2.调用FastDFS远程服务执行文件上传
            //获取tracker_server.config配置文件的绝对物理路径
            String path = this.getClass().getResource("/tracker_server.conf").getPath();
            //全局初始化 
            ClientGlobal.init(path);
            //创建四个对象
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            
            StorageServer storageServer = null;
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            //执行文件上传并获取返回值
            //[1]file_buff参数:上传的文件的字节数组
            byte[] file_buff = headPicture.getBytes();
            
            //[2]file_ext_name:文件的扩展名
            String originalFilename = headPicture.getOriginalFilename();
            int lastIndexOf = originalFilename.lastIndexOf(".")+1;
            String file_ext_name = originalFilename.substring(lastIndexOf);
            
            //[3]meta_list:文件的原数据
            NameValuePair[] meta_list = null;
            
            //[4]执行文件上传，上传成功后的文件组名与文件名在返回值中
            String[] result = storageClient.upload_appender_file(file_buff, file_ext_name, meta_list);
            
            //[5]获取组名
            String userPicGroup = result[0];
            
            //[6]获取文件名
            String userPicFilename = result[1];
            
            //上传新图片，删除旧的
            String oldGroup = user.getUserPicGroup();
            String oldFilename = user.getUserPicFilename();
            if(oldGroup != null && !"".equals(oldGroup)){
                storageClient.delete_file(oldGroup, oldFilename);
            }
            
            //[7]把组名和文件名设置到User对象中
            user.setUserPicGroup(userPicGroup);
            user.setUserPicFilename(userPicFilename);
        }   
        /*//3.执行user对象的更新操作
        userService.updateUser(user);*/
        try {
            userService.updateUser(user);
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
        
        user = userService.getUserById(user.getUserId());
        session.setAttribute("loginUser", user);
        return "redirect:/index.jsp";
    }
    
    @RequestMapping("demo/user/logout")
    public String loginOut(HttpSession session){
        session.invalidate();
        return "redirect:/index.jsp";
    }
    
    @RequestMapping("/demo/user/login")
    public String login(User formUser,HttpSession session,Map<String,String> map){
        User user = userService.getUserForLogin(formUser);
        if(user != null){
            //用户存在，保存到session域
            session.setAttribute("loginUser", user);
            return "redirect:/index.jsp";
        }else{
            //用户不存在，保存错误信息到map
            map.put("message", "用户名或密码错误，请重新登录");
            return "user_login";
        }
    }
    
    @RequestMapping("/demo/user/regist")
    public String regist(User user,Map<String,String> map) throws SolrServerException, IOException{
        //1.检查用户名是否存在
        String userName = user.getUserName();
        int count = userService.getCountForRegist(userName);
        
        if(count == 0){
            //不存在，注册
            userService.save(user);
            return "user_login";
        }else{
            //存在，注册失败
            map.put("message", "用户名已存在");
            return "user_regist";
        }
    }
    
}
