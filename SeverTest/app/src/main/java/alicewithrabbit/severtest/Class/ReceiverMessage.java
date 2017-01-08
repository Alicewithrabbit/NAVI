package alicewithrabbit.severtest.Class;

import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import alicewithrabbit.severtest.Interface.OnReceiverListener;

/**
 * 消息接收处理类
 * Created by Alicewithrabbit on 2016/10/3 0003.
 */

public class ReceiverMessage {

    private OnReceiverListener mListener;
    public void setOnReceiverListener(OnReceiverListener listener){
        mListener = listener;
    }

    private Handler mHandler = new Handler(){
        public void handleMessage(Message msg) {
          String[] cmds = (String[]) msg.obj;
          mListener.onReceiver(cmds[0], cmds[1]);
        }
    };

    public void receive(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = PConnection.socket;

                        while (true) {
                            //System.out.println("wait for message...");
                            BufferedReader reader = new BufferedReader(
                                    new InputStreamReader(socket.getInputStream()));
                            String str_line = reader.readLine();

                            if (str_line != null&&str_line.startsWith("rec")) {
                                //rec:从发送方到接收方的消息
                                String[] cmds = str_line.split(":");
                                System.out.println(cmds[0] + ":" + cmds[1] + ":" + cmds[2] + ":" + cmds[3]);
                                Message msg = mHandler.obtainMessage(1, new String[]{cmds[1], cmds[3]});
                                msg.sendToTarget();
                            }
                        }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
