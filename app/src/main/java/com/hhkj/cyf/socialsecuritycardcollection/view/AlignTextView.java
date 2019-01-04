package com.hhkj.cyf.socialsecuritycardcollection.view;

import android.content.Context;
import android.graphics.Paint;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by caoyingfu on 2018/2/8.
 */

public class AlignTextView extends android.support.v7.widget.AppCompatTextView {

    private boolean mEnabled = true;

    public AlignTextView(Context context) {
        super(context);
    }

    public AlignTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AlignTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置单行展示不下一个单词时是否自动截断
     *
     * @param enable
     */
    public void setAutoSplit(boolean enable) {
        mEnabled = enable;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (getWidth() > 0 && getHeight() > 0 && mEnabled) {
            CharSequence newText = autoSplitText(this);
            if (!TextUtils.isEmpty(newText)) {
                setText(newText);
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 自动折断单词
     * 实现思路：测量TextView宽度时，测量每行Text的宽度，如果一行展示不下某个单词，就将这个单词折断
     *
     * @param tv
     * @return
     */
    private CharSequence autoSplitText(final TextView tv) {
        final CharSequence rawCharSequence = tv.getText();
        final String rawText = rawCharSequence.toString(); //原始文本
        final Paint tvPaint = tv.getPaint(); //paint，包含字体等信息
        final float tvWidth = tv.getWidth() - tv.getPaddingLeft() - tv.getPaddingRight(); //控件可用宽度

        //将原始文本按行拆分
        String[] rawTextLines = rawText.replaceAll("\r", "").split("\n");
        StringBuilder sbNewText = new StringBuilder();
        for (String rawTextLine : rawTextLines) {
            if (tvPaint.measureText(rawTextLine) <= tvWidth) {
                //如果整行宽度在控件可用宽度之内，就不处理了
                sbNewText.append(rawTextLine);
            } else {
                //如果整行宽度超过控件可用宽度，则按字符测量，在超过可用宽度的前一个字符处手动换行
                float lineWidth = 0;
                for (int cnt = 0; cnt != rawTextLine.length(); ++cnt) {
                    char ch = rawTextLine.charAt(cnt);
                    lineWidth += tvPaint.measureText(String.valueOf(ch));
                    if (lineWidth <= tvWidth) {
                        sbNewText.append(ch);
                    } else {
                        if (cnt - 2 >= 0 && rawTextLine.charAt(cnt - 1) >= 'A' && rawTextLine.charAt(cnt - 1) <= 'z' && rawTextLine.charAt(cnt - 2) >= 'A' && rawTextLine.charAt(cnt - 2) <= 'z') {
                            sbNewText.deleteCharAt(sbNewText.length() - 1);
                            sbNewText.append("-\n");
                            lineWidth = 0;
                            cnt -= 2;
                        } else {
                            sbNewText.append("\n");
                            lineWidth = 0;
                            --cnt;
                        }
                    }
                }
            }
            sbNewText.append("\n");
        }

        //把结尾多余的\n去掉
        if (!rawText.endsWith("\n")) {
            sbNewText.deleteCharAt(sbNewText.length() - 1);
        }
        //使用TextView设置的Span格式
        SpannableString sp = new SpannableString(sbNewText.toString());
        if (rawCharSequence instanceof Spanned) {
            TextUtils.copySpansFrom((Spanned) rawCharSequence, 0, rawCharSequence.length(), null, sp, 0);
        }

        return sp;
    }
}
