/******************************************************************
 *
 *    Java Lib For Android, Powered By Shenzhen Jiuzhou.
 *
 *    Copyright (c) 2001-2014 Digital Telemedia Co.,Ltd
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.atguigu.demo.xiang.qing.service
 *
 *    Filename:    UserService.java
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
 *    Create at:   2017年10月9日 下午7:51:34
 *
 *    Revision:
 *
 *    2017年10月9日 下午7:51:34
 *        - first revision
 *
 *****************************************************************/
package com.atguigu.demo.xiang.qing.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;

import com.atguigu.demo.xiang.qing.entities.User;

public interface UserService {

    int getCountForRegist(String userName);

    void save(User user) throws IOException, SolrServerException ;

    /**
     * @Description (TODO这里用一句话描述这个方法的作用)
     * @param formUser
     * @return
     */
    User getUserForLogin(User formUser);

    /**
     * @Description (TODO这里用一句话描述这个方法的作用)
     * @param user
     */
    void updateUser(User user) throws IOException, SolrServerException;

    /**
     * @Description (TODO这里用一句话描述这个方法的作用)
     * @param userId
     */
    User getUserById(Integer userId);

    /**
     * @Description (TODO这里用一句话描述这个方法的作用)
     * @param keywords
     * @return
     */
    List<Map<String, Object>> getDataFromSolrIndex(String keywords);

}
