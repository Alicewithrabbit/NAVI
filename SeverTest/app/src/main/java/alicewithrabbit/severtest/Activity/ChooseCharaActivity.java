package alicewithrabbit.severtest.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;




import java.util.ArrayList;
import java.util.List;

import alicewithrabbit.severtest.R;
import alicewithrabbit.severtest.adapter.ChooseAdapter;

import alicewithrabbit.severtest.view.Chara;

/**
 * Created by Administrator on 2016/10/2 0002.
 */

public class ChooseCharaActivity extends Activity {

    private List<Chara> mChara = new ArrayList<>();

    private ListView mListView;


    private ChooseAdapter mChooseAdapter;
    private Intent mIntent;
    private Intent mIntentAccount;
    private String mAccountCode;





    public void findViews(){

        mListView = (ListView) findViewById(R.id.chara_list);




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_chara);




        mIntentAccount = getIntent();
        mAccountCode = mIntentAccount.getStringExtra("code");
        mChara.add(new Chara("JOKER",1));
        mChara.add(new Chara("QUEEN",2));
        mChara.add(new Chara("PANTHER",3));
        mChara.add(new Chara("NOIR",4));
        mChara.add(new Chara("NAVI",5));
        mChara.add(new Chara("HAKUYA",6));
        mChooseAdapter = new ChooseAdapter(ChooseCharaActivity.this,mChara);
        findViews();
        mListView.setAdapter(mChooseAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int chara_code = mChara.get(position).getmCode();
                mIntent = new Intent(ChooseCharaActivity.this,KaitouChatActivity.class);
                switch (chara_code){
                    case 1:
                        linkin("1");
                        break;
                    case 2:
                        linkin("2");
                        break;
                    case 3:
                        linkin("3");
                        break;
                    case 4:
                        linkin("4");
                        break;
                    case 5:
                        linkin("5");
                        break;
                    case 6:
                        linkin("6");
                        break;
                }
            }
        });
    }

    public void linkin(String toID){
        final String toid = toID;




        mIntent.putExtra("acode",mAccountCode);
        mIntent.putExtra("code",toid);
        ChooseCharaActivity.this.startActivity(mIntent);

    }








}
