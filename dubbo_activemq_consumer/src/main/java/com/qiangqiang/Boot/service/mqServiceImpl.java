package com.qiangqiang.Boot.service;



import com.qiangqiang.entity.NewsLibrary;
import com.qiangqiang.service.NewsLibiaryService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/17
 * \* Time: 16:18
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Service(timeout = 60000)
public class mqServiceImpl implements mqService{

    @Reference(timeout = 60000,retries = 1,version = "*",stub = "com.qiangqiang.Boot.Consumer.NewsLibraryServiceImpl1")
    private NewsLibiaryService newsLibiaryService;

    @Override
    public void selectAll() {
        List<NewsLibrary> newsLibraries = newsLibiaryService.selectAll();
        System.out.println(newsLibraries);
    }
}