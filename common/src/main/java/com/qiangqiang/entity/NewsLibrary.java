package com.qiangqiang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsLibrary implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column news_library.id
     *
     * @mbggenerated Thu Dec 17 11:34:52 CST 2020
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column news_library.news_title
     *
     * @mbggenerated Thu Dec 17 11:34:52 CST 2020
     */
    private String newsTitle;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column news_library.news_content
     *
     * @mbggenerated Thu Dec 17 11:34:52 CST 2020
     */
    private String newsContent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column news_library.include_time
     *
     * @mbggenerated Thu Dec 17 11:34:52 CST 2020
     */
    private Date includeTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column news_library.id
     *
     * @return the value of news_library.id
     *
     * @mbggenerated Thu Dec 17 11:34:52 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column news_library.id
     *
     * @param id the value for news_library.id
     *
     * @mbggenerated Thu Dec 17 11:34:52 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column news_library.news_title
     *
     * @return the value of news_library.news_title
     *
     * @mbggenerated Thu Dec 17 11:34:52 CST 2020
     */
    public String getNewsTitle() {
        return newsTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column news_library.news_title
     *
     * @param newsTitle the value for news_library.news_title
     *
     * @mbggenerated Thu Dec 17 11:34:52 CST 2020
     */
    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column news_library.news_content
     *
     * @return the value of news_library.news_content
     *
     * @mbggenerated Thu Dec 17 11:34:52 CST 2020
     */
    public String getNewsContent() {
        return newsContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column news_library.news_content
     *
     * @param newsContent the value for news_library.news_content
     *
     * @mbggenerated Thu Dec 17 11:34:52 CST 2020
     */
    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column news_library.include_time
     *
     * @return the value of news_library.include_time
     *
     * @mbggenerated Thu Dec 17 11:34:52 CST 2020
     */
    public Date getIncludeTime() {
        return includeTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column news_library.include_time
     *
     * @param includeTime the value for news_library.include_time
     *
     * @mbggenerated Thu Dec 17 11:34:52 CST 2020
     */
    public void setIncludeTime(Date includeTime) {
        this.includeTime = includeTime;
    }
}