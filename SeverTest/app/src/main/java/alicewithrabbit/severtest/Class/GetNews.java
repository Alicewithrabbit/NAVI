package alicewithrabbit.severtest.Class;

import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

import alicewithrabbit.severtest.Interface.GettingNewsBack;

/**
 * Created by Alicewithrabbit on 2016/10/12 0012.
 */

public class GetNews {
    private String mUserID;
    private GettingNewsBack mGetNews;

    public final DatagramSocket mSocket  = new DatagramSocket(8081);

    public GetNews(String mUserID) throws SocketException {
        this.mUserID = mUserID;
    }

    public void setOnGettingListener(GettingNewsBack getNews){

        mGetNews = getNews;

    }

    private Handler mHandler = new Handler(){
        public void handleMessage(Message msg){
            String[] cmds = (String[]) msg.obj;
            mGetNews.onGetting(cmds[0],cmds[1]);
        }
    };

    public void getnews(){





        new Thread(new Runnable() {
            @Override
            public void run() {


                try {

                    DatagramSocket socket  = mSocket;

                    InetAddress severAddress = InetAddress.getByName("192.168.0.12");
                    String str = "share:"+ mUserID;
                    byte data[] = str.getBytes();

                    DatagramPacket packet = new DatagramPacket(data,data.length,severAddress,8081);

                    socket.send(packet);

                    byte data1[] = new byte[1024];

                    DatagramPacket packet1 = new DatagramPacket(data1,data1.length);

                    socket.receive(packet1);

                    String result = new String(packet1.getData());

                    if(result != null&&result.startsWith("back")){
                        String[] cmds = result.split(":");
                         Message msg = mHandler.obtainMessage(1, new String[]{ cmds[1],cmds[2]});
                         msg.sendToTarget();

                    }



                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }


}
