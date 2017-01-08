package alicewithrabbit.severtest.view;

import java.io.Serializable;

/**
 * 消息存储类
 * Created by Alicewithrabbit on 2016/10/1 0001.
 */

public class Msg implements Serializable {

    private String mMsg;
    private String mAvatar;




    private String mTime;


    private String mCode;//用户编号
    public Msg(String msg,String avatar,String code,String time){
        mMsg = msg;
        mAvatar = avatar;
        mCode = code;
        mTime = time;
    }
    public String getmCode() {return mCode;}
    public String getmAvatar() {
        return mAvatar;
    }
    public String getmMsg() {
        return mMsg;
    }

    public String getmTime() {
        return mTime;
    }
}
