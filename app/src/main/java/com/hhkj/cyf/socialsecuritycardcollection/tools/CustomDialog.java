package com.hhkj.cyf.socialsecuritycardcollection.tools;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhkj.cyf.socialsecuritycardcollection.R;


/**
 * Created by Administrator on 2016/7/16.
 */
public class CustomDialog extends Dialog {

    public CustomDialog(Context context) {
        super(context);
    }

    public CustomDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Context context;
        private String title;
        private String message;
        private String positiveButtonText;
        private String centerButtonText;
        private String negativeButtonText;

        private OnClickListener positiveButtonClickListener;
        private OnClickListener centerButtonClickListener;
        private OnClickListener negativeButtonClickListener;

        private int color1;
        private int color2;
        private int color3;

        private boolean isCancel = true;
        private String isCenterGone  = "";

        private View contentView;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * Set the Dialog message from resource
         *
         * @param message
         * @return
         */
        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        /**
         * Set the Dialog title from resource
         *
         * @param title
         * @return
         */
        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        /**
         * Set the Dialog title from String
         *
         * @param title
         * @return
         */

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }
        public Builder setIsCenterGone(String isCenterGone) {
            this.isCenterGone = isCenterGone;
            return this;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        public Builder setCancel(boolean isCancel) {
            this.isCancel = isCancel;
            return this;
        }

        /**
         * Set the positive button resource and it's listener
         *
         * @param positiveButtonText
         * @return
         */
        public Builder setPositiveButton(int positiveButtonText,
                                         OnClickListener listener, int color) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            this.color1 = color;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText,
                                         OnClickListener listener, int color) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            this.color1 = color;
            return this;
        }

        public Builder setCenterButton(int centerButtonText,
                                       OnClickListener listener, int color) {
            this.centerButtonText = (String) context
                    .getText(centerButtonText);
            this.centerButtonClickListener = listener;
            this.color2 = color;
            return this;
        }

        public Builder setCenterButton(String centerButtonText,
                                       OnClickListener listener, int color) {
            this.centerButtonText = centerButtonText;
            this.centerButtonClickListener = listener;
            this.color2 = color;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText,
                                         OnClickListener listener, int color) {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            this.color3 = color;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText,
                                         OnClickListener listener, int color) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            this.color3 = color;
            return this;
        }

        public CustomDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final CustomDialog dialog = new CustomDialog(context, R.style.MyDialog);
            View layout = inflater.inflate(R.layout.dialog_normal_layout, null);
            dialog.addContentView(layout, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            // set the dialog title
            ((TextView) layout.findViewById(R.id.title)).setText(title);
            // set the confirm button
            if (positiveButtonText != null) {
                ((Button) layout.findViewById(R.id.positiveButton))
                        .setText(positiveButtonText);
                ((Button) layout.findViewById(R.id.positiveButton))
                        .setBackgroundResource(color1);
                if (positiveButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.positiveButton))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    positiveButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_POSITIVE);
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.positiveButton).setVisibility(
                        View.GONE);
            }
            // set the cancel button
            if (negativeButtonText != null) {
                ((Button) layout.findViewById(R.id.negativeButton))
                        .setText(negativeButtonText);
                ((Button) layout.findViewById(R.id.negativeButton))
                        .setBackgroundResource(color3);
                if (negativeButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.negativeButton))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    negativeButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_NEGATIVE);
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.negativeButton).setVisibility(
                        View.GONE);
            }
            if (View.GONE == ((Button) layout.findViewById(R.id.negativeButton)).getVisibility() && View.GONE == ((Button) layout.findViewById(R.id.positiveButton)).getVisibility()){
                ((LinearLayout) layout.findViewById(R.id.ll_centerButton)).setVisibility(View.GONE);
            }else {
                ((LinearLayout) layout.findViewById(R.id.ll_centerButton)).setVisibility(View.INVISIBLE);
            }

            if (isCenterGone.equals("gone")){
                ((LinearLayout) layout.findViewById(R.id.ll_centerButton)).setVisibility(View.GONE);
            }else if (isCenterGone.equals("invisible")){
                ((LinearLayout) layout.findViewById(R.id.ll_centerButton)).setVisibility(View.INVISIBLE);
            }else {
                if (centerButtonText != null) {
                    ((LinearLayout) layout.findViewById(R.id.ll_centerButton)).setVisibility(View.VISIBLE);
                    ((Button) layout.findViewById(R.id.centerButton))
                            .setText(centerButtonText);
                    ((Button) layout.findViewById(R.id.centerButton))
                            .setBackgroundResource(color2);
                    if (centerButtonClickListener != null) {
                        ((Button) layout.findViewById(R.id.centerButton))
                                .setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        centerButtonClickListener.onClick(dialog,
                                                DialogInterface.BUTTON_NEGATIVE);
                                    }
                                });
                    }
                } else {
                    // if no confirm button just set the visibility to GONE
                    ((LinearLayout) layout.findViewById(R.id.ll_centerButton)).setVisibility(View.INVISIBLE);
                }
            }


            // set the content message
            if (message != null) {
                ((TextView) layout.findViewById(R.id.message)).setText(message);
            } else if (contentView != null) {
                // if no message set
                // add the contentView to the dialog body
                ((LinearLayout) layout.findViewById(R.id.content))
                        .removeAllViews();
                ((LinearLayout) layout.findViewById(R.id.content))
                        .addView(contentView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            }
            dialog.setContentView(layout);
            dialog.setCancelable(isCancel);
            return dialog;
        }
    }
}