package com.qiangqiang.service.impl;

import com.github.pagehelper.PageInfo;
import com.qiangqiang.entity.NewsLibrary;
import com.qiangqiang.mapper.NewsLibraryMapper;
import com.qiangqiang.service.NewsLibiaryService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/17
 * \* Time: 11:22
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Service(version = "1.0.0")
public class NewsLibraryServiceImpl implements NewsLibiaryService {

    @Autowired
    private NewsLibraryMapper newsLibraryMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insert(Long id,
                      String newsTitle,
                      String newsContent,
                      Date includeTime) {
        return newsLibraryMapper.insert(id, newsTitle, newsContent, includeTime);
    }

    @Override
    public List<NewsLibrary> selectAll() {
        return null;
    }

    @Override
    public int updateByPrimaryKey(NewsLibrary record) {
        return 0;
    }

    @Override
    public PageInfo<NewsLibrary> selectByPage(int page, int limit) {
        page = page * limit;
        List<NewsLibrary> newsLibraries = newsLibraryMapper.selectByPage(page, limit);
        PageInfo<NewsLibrary> pageInfo = new PageInfo<>(newsLibraries);
        int count = newsLibraryMapper.selectCount();
        pageInfo.setTotal(count);
        return pageInfo;
    }

    @Override
    public int selectCount() {
        return newsLibraryMapper.selectCount();
    }
}