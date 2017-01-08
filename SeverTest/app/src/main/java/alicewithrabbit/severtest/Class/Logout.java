package alicewithrabbit.severtest.Class;

import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import alicewithrabbit.severtest.Interface.OnLogoutListener;

/**
 * Created by Alicewithrabbit on 2016/10/3 0003.
 */

public class Logout {
    public OnLogoutListener mListener;
    public void setOnLogoutListener(OnLogoutListener listener){
        mListener = listener;
    }

    private Handler mHandler = new Handler(){
        public void handleMessage(Message msg){
            if (msg.what == 1){
                mListener.onLogout(true);
            }else {
                mListener.onLogout(false);
            }
        }
    };

    public void logout(final String user){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Socket socket = null;
                try {
                    socket = new Socket();
                    socket.connect(new InetSocketAddress("192.168.0.12",9085),5000);
                    PrintWriter writer = new PrintWriter(socket.getOutputStream());
                    writer.println("logout:"+user);
                    writer.flush();

                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));

                    if ("success".equals(reader.readLine())){
                        PConnection.socket = null;
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
