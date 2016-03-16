package com.fuxuemingzhu.wechoice.model.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 包名：com.fuxuemingzhu.wechoice.model.bean
 * 类描述：
 * 创建人：fuxuemingzhu
 * 邮箱：fuxuemingzhu@163.com
 * 创建时间：2016/3/16 11:29
 * <p>
 * 修改人：fuxuemingzhu
 * 修改时间：2016/3/16 11:29
 * 修改备注：
 *
 * @version 1.0
 */
public class ChoicePage {
    private List<Choice> contentlist;

    public ChoicePage() {
    }


    public List<Choice> getContentlist() {
        return contentlist;
    }

    public void setContentlist(ArrayList<Choice> contentlist) {
        this.contentlist = contentlist;
    }
}
