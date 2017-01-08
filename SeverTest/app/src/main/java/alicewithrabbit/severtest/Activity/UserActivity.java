package alicewithrabbit.severtest.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;


import java.net.SocketException;

import alicewithrabbit.severtest.Class.GetNews;
import alicewithrabbit.severtest.Class.Logout;
import alicewithrabbit.severtest.Interface.GettingNewsBack;
import alicewithrabbit.severtest.Interface.OnLogoutListener;
import alicewithrabbit.severtest.R;


public class UserActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Intent mIntentAccount;
    private String mAccountCode;
    private TextView mTextView;
    private Handler mHandler;
    private ImageView mImageView;
    private String mNews;
    private String mNewsID;

    GetNews mgetNews;

    private void findViews(){

        mTextView = (TextView) findViewById(R.id.textView6);
        mImageView = (ImageView) findViewById(R.id.imageView2);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK){
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    public class GettingListener implements GettingNewsBack{

        @Override
        public void onGetting(String newsid, String news) {
            mNews = news;
            mNewsID = newsid;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        try {
            mgetNews = new GetNews(mAccountCode);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        mgetNews.setOnGettingListener(new GettingListener());

        mgetNews.getnews();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Message message = new Message();
                        message.what = 1;
                        mHandler.sendMessage(message);
                    }
                }).start();


                Snackbar.make(view, "Linking to the Server", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        mIntentAccount = getIntent();
        mAccountCode = mIntentAccount.getStringExtra("code");

        findViews();

        mTextView.setMovementMethod(new ScrollingMovementMethod());

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==1)
                    mTextView.setText(mNews);
                if(mNewsID!=null) {
                    if (mNewsID.equals("2")) {
                        mImageView.setImageResource(R.drawable.a78a);
                    } else {
                        mImageView.setImageResource(R.drawable.a133);
                    }

                }
            }
        };




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Logout logout = new Logout();
            logout.setOnLogoutListener(new OnLogoutListener() {
                @Override
                public void onLogout(boolean success) {
                    if(success){
                        Toast.makeText(UserActivity.this,"Logout successful",Toast.LENGTH_SHORT).show();
                        mgetNews.mSocket.close();
                        finish();
                    }else{
                        mgetNews.mSocket.close();
                        finish();
                        Toast.makeText(UserActivity.this,"Logout failed",Toast.LENGTH_SHORT).show();
                    }
                }
            });
            logout.logout(mAccountCode);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

            Intent intent = new Intent(UserActivity.this,ChooseCharaActivity.class);
            intent.putExtra("code",mAccountCode);
            UserActivity.this.startActivity(intent);



        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
