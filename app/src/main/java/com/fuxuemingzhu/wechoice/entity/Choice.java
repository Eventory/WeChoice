package com.fuxuemingzhu.wechoice.entity;

import com.alibaba.fastjson.JSON;

/**
 * 包名：com.fuxuemingzhu.wechoice.entity
 * 类描述：
 * 创建人：fuxuemingzhu
 * 邮箱：fuxuemingzhu@163.com
 * 创建时间：2016/2/21 19:28
 * <p/>
 * 修改人：fuxuemingzhu
 * 修改时间：2016/2/21 19:28
 * 修改备注：
 *
 * @version 1.0
 */
public class Choice {
    private String id;
    private String title;
    private String source;
    private String firstImg;
    private String mark;
    private String url;

    public Choice() {
    }

    public Choice(String id, String title, String source, String firstImg, String mark, String url) {
        this.id = id;
        this.title = title;
        this.source = source;
        this.firstImg = firstImg;
        this.mark = mark;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getFirstImg() {
        return firstImg;
    }

    public void setFirstImg(String firstImg) {
        this.firstImg = firstImg;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
