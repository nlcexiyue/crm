package com.qiangqiang.service;

import com.github.pagehelper.PageInfo;
import com.qiangqiang.entity.ExplainResult;
import com.qiangqiang.entity.NewsLibrary;

import java.util.Date;
import java.util.List;

public interface NewsLibiaryService {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_library
     *
     * @mbggenerated Thu Dec 17 11:11:31 CST 2020
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_library
     *
     * @mbggenerated Thu Dec 17 11:11:31 CST 2020
     */
    int insert(Long id,
               String newsTitle,
               String newsContent,
               Date includeTime);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_library
     *
     * @mbggenerated Thu Dec 17 11:11:31 CST 2020
     */
    List<NewsLibrary> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_library
     *
     * @mbggenerated Thu Dec 17 11:11:31 CST 2020
     */
    int updateByPrimaryKey(NewsLibrary record);

    PageInfo<NewsLibrary> selectByPage(int page, int limit);

    int selectCount();
}
