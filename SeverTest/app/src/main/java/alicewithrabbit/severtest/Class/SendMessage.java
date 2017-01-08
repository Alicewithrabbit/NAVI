package alicewithrabbit.severtest.Class;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import alicewithrabbit.severtest.Interface.OnSendListener;

/**
 * 消息发送类
 * Created by Administrator on 2016/10/3 0003.
 */

public class SendMessage {
    private OnSendListener mListener;

    public void setOnSendListener(OnSendListener listener){
        mListener = listener;
    }

    private Handler mHandler = new Handler(){
        public void handleMessage(Message msg){
            mListener.onSend((String[])msg.obj);
        }
    };

    //SEND:FROM ID : TO ID : MESSAGE
    public void send(final String fromid,final String toid,final String msg){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Socket socket = null;
                try {
                    socket = new Socket();
                    socket.connect(new InetSocketAddress("192.168.0.12",9085),5000);
                    PrintWriter writer = new PrintWriter(socket.getOutputStream());
                    writer.println("send:"+fromid+":"+toid+":"+msg);
                    writer.flush();

                    Message message = mHandler.obtainMessage(1,new String[]{toid,msg});
                    message.sendToTarget();

                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }





}
