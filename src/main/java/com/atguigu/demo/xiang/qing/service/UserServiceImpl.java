/******************************************************************
 *
 *    Java Lib For Android, Powered By Shenzhen Jiuzhou.
 *
 *    Copyright (c) 2001-2014 Digital Telemedia Co.,Ltd
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.atguigu.demo.xiang.qing.service
 *
 *    Filename:    UserServiceImpl.java
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
 *    Create at:   2017年10月9日 下午7:52:04
 *
 *    Revision:
 *
 *    2017年10月9日 下午7:52:04
 *        - first revision
 *
 *****************************************************************/
package com.atguigu.demo.xiang.qing.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.demo.xiang.qing.entities.User;
import com.atguigu.demo.xiang.qing.mapper.UserMapper;
import com.github.abel533.entity.Example;
import com.github.abel533.entity.Example.Criteria;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    
    @Autowired
    private HttpSolrServer solrServer;

    @Override
    public int getCountForRegist(String userName) {
        Example example = new Example(User.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName", userName);
        return userMapper.selectCountByExample(example);
    }

    @Override
    public void save(User user) throws SolrServerException, IOException {
        //将用户信息保存到数据库
        userMapper.insertSelective(user);
        
        //将用户信息保存到索引库
        //1.创建文档对象
        SolrInputDocument document = new SolrInputDocument();
        //2.添加必要字段
        document.addField("id", user.getUserId());
        document.addField("user_nick", user.getUserNick());
        
        //使用solrServer对象进行文档添加操作
        solrServer.add(document);
        solrServer.commit();
    }

    @Override
    public User getUserForLogin(User formUser) {
        String userName = formUser.getUserName();
        String userPwd = formUser.getUserPwd();
        Example example = new Example(User.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName", userName).andEqualTo("userPwd", userPwd);
        List<User> userList = userMapper.selectByExample(example);
        if(userList == null || userList.size() == 0){
            return null;
        }
        return userList.get(0);
    }

    @Override
    public void updateUser(User user) throws SolrServerException, IOException {
        userMapper.updateByPrimaryKeySelective(user);
        
        //获取当前用户id
        Integer userId = user.getUserId();
        //根据userId作为文档id执行删除
        solrServer.deleteById(userId + "");
        
        SolrInputDocument document = new SolrInputDocument();
        
        document.addField("id", userId);
        document.addField("user_nick", user.getUserNick());
        document.addField("user_gender", user.getUserGender());
        document.addField("user_job", user.getUserJob());
        document.addField("user_hometown", user.getUserHometown());
        document.addField("user_desc", user.getUserDesc());
        document.addField("user_pic_group", user.getUserPicGroup());
        document.addField("user_pic_filename", user.getUserPicFilename());
        
        solrServer.add(document);
        solrServer.commit();
    }

    @Override
    public User getUserById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<Map<String, Object>> getDataFromSolrIndex(String keywords) {
        
        return null;
    }
}
