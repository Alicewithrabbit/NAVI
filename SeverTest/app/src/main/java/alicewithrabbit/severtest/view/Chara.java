package alicewithrabbit.severtest.view;

/**
 * Created by Alicewithrabbit on 2016/10/2 0002.
 */
//好友选择
public class Chara {
    public int getmCode() {
        return mCode;
    }

    public String getmName() {
        return mName;
    }

    private String mName;
    private int mCode;

    public Chara(String name,int code){
        mName = name;
        mCode = code;
    }
}
