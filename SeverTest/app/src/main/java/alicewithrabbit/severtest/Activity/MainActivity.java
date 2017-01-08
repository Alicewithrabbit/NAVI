package alicewithrabbit.severtest.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import alicewithrabbit.severtest.Class.Login;
import alicewithrabbit.severtest.Interface.LoginCallback;
import alicewithrabbit.severtest.R;

public class MainActivity extends Activity implements View.OnClickListener {

    private EditText mEnterAccount;
    private EditText mEnterPassword;

    private TextView mtextView;
    private Button mbutton4;
    private Button mbutton5;
    private String mLoginID;
    private String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setListeners();

    }

    //获取输入的账号
    private String getEditID() {
        return mEnterAccount !=null? mEnterAccount.getText().toString(): "";
    }

    //获取输入的密码
    private String getEditPassword() {
        return mEnterPassword !=null? mEnterPassword.getText().toString(): "";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button4:
                System.exit(0);
                break;
            case R.id.button5:
                Intent intent1 = new Intent(MainActivity.this,UserActivity.class);
                mLoginID = getEditID();
                mPassword = getEditPassword();
                IsValid(intent1,mLoginID,mPassword);
                break;
        }
    }

    public void IsValid(Intent intent,String id,String password){
        mEnterAccount.setText("");
        mEnterPassword.setText("");
        login(id,password,intent);
    }

    //登入信息发送到服务器
    public void login(String userId,String passWord,Intent intent){
        final String userid = userId;
        final String password = passWord;
        final Intent nintent = intent;
        Login login = new Login(userid,password);
        login.setOnLoginListener(new LoginCallback() {
            @Override
            public void onLogin(boolean success) {
                if (success){
                    nintent.putExtra("code",userid);
                    MainActivity.this.startActivity(nintent);
                }else{
                    Toast.makeText(MainActivity.this,"Login failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
        login.login();
    }

    private void setListeners() {

        mbutton4.setOnClickListener(this);
        mbutton5.setOnClickListener(this);
        mtextView.setOnClickListener(this);
    }
    private void findViews() {
        mEnterAccount = (EditText) findViewById(R.id.enter_account);
        mtextView = (TextView)findViewById(R.id.textView);
        mbutton4 = (Button)findViewById(R.id.button4);
        mbutton5 = (Button)findViewById(R.id.button5);
        mEnterPassword = (EditText) findViewById(R.id.password_enter_account);
    }
}
