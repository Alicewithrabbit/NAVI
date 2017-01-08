package alicewithrabbit.severtest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import alicewithrabbit.severtest.R;
import alicewithrabbit.severtest.view.Chara;

/**
 * Created by Administrator on 2016/10/2 0002.
 */

public class ChooseAdapter extends BaseAdapter {

    private Context mContext;
    private List<Chara> mChara_List = new ArrayList<>();
    private LayoutInflater mLayoutInflater;
    private ImageView mAvatar;
    private TextView mName;



    public void findViews(View convertView){
        mAvatar = (ImageView) convertView.findViewById(R.id.avatar1);
        mName = (TextView) convertView.findViewById(R.id.chara_name);
    }


    public ChooseAdapter(Context context, List Chara_List){
        mContext = context;
        mChara_List = Chara_List;
        mLayoutInflater =(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return mChara_List.size();
    }

    @Override
    public Object getItem(int position) {
        return mChara_List.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mLayoutInflater.inflate(R.layout.choose_chara_item,null);
        findViews(convertView);
        switch (mChara_List.get(position).getmCode()){
            case 1:
                mAvatar.setImageResource(R.drawable.joker);
                mName.setText("JOKER");
                break;
            case 2:
                mAvatar.setImageResource(R.drawable.queen);
                mName.setText("QUEEN");
                break;
            case 3:
                mAvatar.setImageResource(R.drawable.panther);
                mName.setText("PANTHER");
                break;
            case 4:
                mAvatar.setImageResource(R.drawable.noir);
                mName.setText("NOIR");
                break;
            case 5:
                mAvatar.setImageResource(R.drawable.navi);
                mName.setText("NAVI");
                break;
            case 6:
                mAvatar.setImageResource(R.drawable.hakuya);
                mName.setText("HAKUYA");
                break;
        }
        return convertView;
    }


}
