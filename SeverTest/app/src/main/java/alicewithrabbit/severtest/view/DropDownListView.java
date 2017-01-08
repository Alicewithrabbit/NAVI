package alicewithrabbit.severtest.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import alicewithrabbit.severtest.R;

/**
 * Created by Administrator on 2016/9/29 0029.
 */

public class DropDownListView extends ListView implements AbsListView.OnScrollListener{

    private static final String TAG = "listview";

    private final static int RELEASE_To_REFRESH = 0;
    private final static int PULL_To_REFRESH = 1;
    private final static int REFRESHING = 2;
    private final static int DONE = 3;
    private final static int LOADING = 4;

    // 实际的padding的距离与界面上偏移距离的比例
    private final static int RATIO = 3;

    private LayoutInflater inflater;
    private FrameLayout fl;
    private LinearLayout headView;

    //	private View line;
    private ProgressBar progressBar;

//	private RotateAnimation animation;
//	private RotateAnimation reverseAnimation;

    // 用于保证startY的值在一个完整的touch事件中只被记录一次
    private boolean isRecored;

    private int headContentWidth;
    private int headContentHeight;

    private int startY;
    private int firstItemIndex;

    private int state;

    private boolean isBack;

    private SwipeRefreshLayout.OnRefreshListener refreshListenerHeader;

    private boolean isRefreshableHeader;

    public DropDownListView(Context context) {
        super(context);
        init(context);
    }

    public DropDownListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DropDownListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DropDownListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }


    private void init(Context context) {
        setCacheColorHint(context.getResources().getColor(R.color.transparent));
        inflater = LayoutInflater.from(context);

        fl = (FrameLayout)inflater.inflate(R.layout.dropdown_lv_head, null);
        headView = (LinearLayout) fl.findViewById(R.id.drop_down_head);

        progressBar = (ProgressBar) fl.findViewById(R.id.loading);
        measureView(headView);


        headContentHeight = headView.getMeasuredHeight();
        headContentWidth = headView.getMeasuredWidth();

        headView.setPadding(0, -1 * headContentHeight, 0, 0);
        headView.invalidate();

        Log.v("size", "width:" + headContentWidth + " height:"
                + headContentHeight);

        addHeaderView(fl, null, false);
//		addHeaderView(headView, null, false);
        setOnScrollListener(this);


        state = DONE;
        isRefreshableHeader = false;
    }




    private void measureView(View child) {
        ViewGroup.LayoutParams p = child.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
                    MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0,
                    MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }


}
