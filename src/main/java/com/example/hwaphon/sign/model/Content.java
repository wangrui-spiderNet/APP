package com.example.hwaphon.sign.model;

/**
 * Created by hwaphon on 3/18/2016.
 */
public class Content {

    public static final int ROBOT = 0;
    public static final int USER = 1;

    private String content;
    private int flag;

    public Content(String content,int flag){
        this.content = content;
        this.flag = flag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
