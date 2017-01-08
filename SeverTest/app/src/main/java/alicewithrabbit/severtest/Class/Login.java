package alicewithrabbit.severtest.Class;

import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import alicewithrabbit.severtest.Interface.LoginCallback;
/**
 * 用户登入消息处理类
 * Created by Alicewithrabbit on 2016/10/3 0003.
 */

public class Login {
    private String mUserID;
    private String mPassword;
    private LoginCallback mCallback;
    public Login(String userID,String password){
        mUserID = userID;
        mPassword = password;
    }


    //设置监听器
    public void setOnLoginListener(LoginCallback callback){
        mCallback = callback;
    }

    private Handler mHandler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    mCallback.onLogin(true);
                    break;
                case 2:
                    mCallback.onLogin(false);
                    break;
            }
        }
    };

    public void login(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Socket socket = null;
                try {
                    socket = new Socket();
                    socket.connect(new InetSocketAddress("192.168.0.12",9085),5000);
                    PrintWriter writer = new PrintWriter(socket.getOutputStream());
                    writer.println("login:"+ mUserID+":"+mPassword);
                    writer.flush();

                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
                    String str_line = reader.readLine();
                    System.out.println(str_line);
                    if ("success".equals(str_line)){
                        PConnection.socket = socket;
                        mHandler.sendEmptyMessage(1);
                    }else {
                        mHandler.sendEmptyMessage(2);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
