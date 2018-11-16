package cn.nela.dialboardview;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class DialView extends RelativeLayout {

    @BindViews({R.id.dialpad_0, R.id.dialpad_1, R.id.dialpad_2, R.id.dialpad_3, R.id.dialpad_4
            , R.id.dialpad_5, R.id.dialpad_6, R.id.dialpad_7, R.id.dialpad_8, R.id.dialpad_9
            , R.id.dialpad_star, R.id.dialpad_pound, R.id.dialpad_delete})
    List<View> mDialpadViews;

    @BindView(R.id.dialpad_delete)
    View mDialpadDelete;
    @BindView(R.id.dialpad_txt)
    AppCompatEditText mDialpadInput;

    public interface DialViewListener {

        /**
         * 输入内容变化
         */
        void inputChange();

        /**
         * 点击拨号按键
         */
        void onCall();

        /**
         * 点击设置按键
         */
        void onSetting();

        /**
         * 长按事件
         */
        void onLongEvent(String number);

    }

    private DialViewListener mDialViewListener;

    public void setDialViewListener(DialViewListener DialViewListener) {
        this.mDialViewListener = DialViewListener;
    }

    public DialView(Context context) {
        this(context, null);
    }

    public DialView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DialView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.dial_view, this);
        ButterKnife.bind(this);
        mDialpadInput.setInputType(InputType.TYPE_NULL);
    }

    /**
     * 获取输入框内容
     *
     * @return
     */
    public String getNumber() {
        return mDialpadInput.getText().toString();
    }

    /**
     * 清空输入框
     */
    public void cleanDialViewInput() {
        mDialpadInput.setText("");
    }

    /**
     * 设置输入框内容
     *
     * @param input
     */
    public void setDialViewInput(String input) {
        Pattern pattern = Pattern.compile("[a-zA-z0-9]{1,15}");
        if (pattern.matcher(input).matches()) {
            mDialpadInput.setText(input);
        } else {
            return;
        }
    }

    @OnClick({R.id.dialpad_0, R.id.dialpad_1, R.id.dialpad_2, R.id.dialpad_3, R.id.dialpad_4
            , R.id.dialpad_5, R.id.dialpad_6, R.id.dialpad_7, R.id.dialpad_8, R.id.dialpad_9})
    void onDialpad(ViewGroup viewGroup) {
        TextView textView = (TextView) viewGroup.getChildAt(0);
        mDialpadInput.append(textView.getText());
        if (mDialViewListener != null) {
            mDialViewListener.inputChange();
        }
    }

    @OnLongClick({R.id.dialpad_0, R.id.dialpad_1, R.id.dialpad_2, R.id.dialpad_3, R.id.dialpad_4
            , R.id.dialpad_5, R.id.dialpad_6, R.id.dialpad_7, R.id.dialpad_8, R.id.dialpad_9})
    boolean onLongDialpad(ViewGroup viewGroup) {
        TextView textView = (TextView) viewGroup.getChildAt(0);
        if (mDialViewListener != null) {
            mDialViewListener.onLongEvent(textView.getText().toString());
        }
        return true;
    }

    @OnClick(R.id.dialpad_delete)
    void onDelete(View view) {
        String content = mDialpadInput.getText().toString();
        if (!TextUtils.isEmpty(content)) {
            mDialpadInput.setText(content.substring(0, content.length() - 1));
        }
        if (mDialViewListener != null) {
            mDialViewListener.inputChange();
        }
    }

    @OnClick(R.id.fab_call)
    void onCall(View view) {
        if (mDialViewListener != null) {
            mDialViewListener.onCall();
        }
    }

    @OnClick(R.id.fab_set)
    void onSetting(View view) {
        if (mDialViewListener != null) {
            mDialViewListener.onSetting();
        }
    }
}
