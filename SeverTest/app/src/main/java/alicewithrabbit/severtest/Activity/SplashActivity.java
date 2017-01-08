package alicewithrabbit.severtest.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import alicewithrabbit.severtest.R;

/**
 * Created by Alicewithrabbit on 2016/10/2 0002.
 */

public class SplashActivity extends Activity {
    private Handler mHandler;
    private int mProgress;
    private TextView mLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == 1) {
                    setContentView(R.layout.activity_splash);
                    mLoading = (TextView) findViewById(R.id.nowloadingtx);
                    mLoading.setText("NOW LOADING..."+(mProgress++)+"%");
                }
                super.handleMessage(msg);
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isProgress()) {
                    try {
                        Thread.sleep(10);
                        Message message = new Message();
                        message.what = 1;
                        mHandler.sendMessage(message);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);

                SplashActivity.this.startActivity(intent);
                SplashActivity.this.finish();
            }
        }).start();



    }



    private boolean isProgress() {
        if(mProgress<99){
            return true;
        }else{
            return false;
        }
    }
}
