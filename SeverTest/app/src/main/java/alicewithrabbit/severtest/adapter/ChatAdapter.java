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

import alicewithrabbit.severtest.R;
import alicewithrabbit.severtest.view.Msg;

/**
 * Created by Alicewithrabbit on 2016/9/29 0029.
 */

public class ChatAdapter extends BaseAdapter {

    private Context mContext;

    private LayoutInflater mLayoutInflater;

    private String[] mChatInfo= {"Welcome to KAITOU Channel!"};
    private TextView mChatTime;
    private TextView mchatTextFrom;
    private ImageView mchatImageFrom;
    private TextView mchatTextTo;
    private ImageView mchatImageTo;
    private List<Msg> mMsg = new ArrayList<>();
    private String mCode;//好友编号
    private String mCode_me;//自己编号

    public ChatAdapter(Context context,List msg) {
        this.mContext = context;
        mMsg = msg;
        mLayoutInflater =(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {

        return mMsg.size();
    }

    @Override
    public Object getItem(int position) {

        return mMsg.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    private void findViews(View convertView){
        //得到所有item
        mchatTextFrom = (TextView)convertView.findViewById(R.id.chatfrom_content);
        mchatImageFrom = (ImageView)convertView.findViewById(R.id.chatfrom_icon);
        mchatTextTo = (TextView)convertView.findViewById(R.id.chatto_content);
        mchatImageTo = (ImageView)convertView.findViewById(R.id.chatto_icon);
        mChatTime = (TextView) convertView.findViewById(R.id.chat_time);

    }

    //选择好友头像
    public void SelectFromAvatar(ImageView imageView,String code){
        switch (code){
            case "1":
                imageView.setImageResource(R.drawable.joker);
                break;
            case "2":
                imageView.setImageResource(R.drawable.queen);
                break;
            case "3":
                imageView.setImageResource(R.drawable.panther);
                break;
            case "4":
                imageView.setImageResource(R.drawable.noir);
                break;
            case "5":
                imageView.setImageResource(R.drawable.navi);
                break;
            case "6":
                imageView.setImageResource(R.drawable.hakuya);
                break;
        }
    }

    //选择自己头像
    public void SelectTAvatar(ImageView imageView,String code){
        switch (code){
            case "1":
                imageView.setImageResource(R.drawable.joker);
                break;
            case "2":
                imageView.setImageResource(R.drawable.queen);
                break;
            case "3":
                imageView.setImageResource(R.drawable.panther);
                break;
            case "4":
                imageView.setImageResource(R.drawable.noir);
                break;
            case "5":
                imageView.setImageResource(R.drawable.navi);
                break;
            case "6":
                imageView.setImageResource(R.drawable.hakuya);
                break;
        }
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //返回一个视图
        convertView = mLayoutInflater.inflate(R.layout.chat_lv_item,null);
        //获取控件
        findViews(convertView);
        //控件与数据绑定
        switch (mMsg.get(position).getmAvatar()){
            case "1"://消息来自服务器
                mchatTextTo.setVisibility(View.GONE);
                mchatImageTo.setVisibility(View.GONE);
                mCode = mMsg.get(position).getmCode();
                SelectFromAvatar(mchatImageFrom,mCode);
                mchatTextFrom.setText(mMsg.get(position).getmMsg());
                mChatTime.setText(mMsg.get(position).getmTime());
                break;
            case "2"://消息来自用户
                mchatTextFrom.setVisibility(View.GONE);
                mchatImageFrom.setVisibility(View.GONE);
                mCode_me = mMsg.get(position).getmCode();
                SelectFromAvatar(mchatImageTo,mCode_me);
                mchatTextTo.setText(mMsg.get(position).getmMsg());
                mChatTime.setText(mMsg.get(position).getmTime());
                break;
        };
        //判断消息来自哪里
        return convertView;
    }



    //刷新视图
    public void refresh(List<Msg> msg){
        mMsg = msg;
        notifyDataSetChanged();
    }
}
