package com.zdt.ufo.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Helper class to show a toast. Use these <b>show(...)</b> methods if you just
 * want to show a simple string toast, otherwise, use the {@link Builder} class.
 */
public class ToastHelper {
    private static Toast mToast;

    private ToastHelper() {
    }

    public static void show(Context context, int textResID) {
        show(context, context.getResources().getString(textResID));
    }

    public static void show(Context context, int textResID, int duration) {
        show(context, context.getResources().getString(textResID), duration);
    }

    public static void show(Context context, int textResID, boolean cancelPrevious) {
        show(context, context.getResources().getString(textResID), cancelPrevious);
    }

    public static void show(Context context, int textResID, int duration, boolean cancelPrevious) {
        show(context, context.getResources().getString(textResID), duration, cancelPrevious);
    }

    public static void show(Context context, String msg) {
        show(context, msg, Toast.LENGTH_SHORT, false);
    }

    public static void show(Context context, String msg, int duration) {
        show(context, msg, duration, false);
    }

    public static void show(Context context, String msg, boolean cancelPrevious) {
        show(context, msg, Toast.LENGTH_SHORT, cancelPrevious);
    }

    public static void show(Context context, String msg, int duration, boolean cancelPrevious) {
        if (cancelPrevious && mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(context, msg, duration);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.setText(msg);
        mToast.show();
    }

    public static void cancel() {
        if (mToast != null) {
            mToast.cancel();
        }
    }

    public static final class Builder {
        private Context context;
        private View customeView;
        private boolean isLengthShort;
        private String text;
        private int textResId = -1;
        private float textSize = -1;
        private int textSizeResId = -1;
        private int textColor = -1;
        private int textColorResId = -1;
        private int bgColor = -1;
        private int bgColorResId = -1;
        private int bgDrawableResId = -1;
        private int paddingLeft = Integer.MIN_VALUE;
        private int paddingTop = Integer.MIN_VALUE;
        private int paddingRight = Integer.MIN_VALUE;
        private int paddingBottom = Integer.MIN_VALUE;
        private int gravity = -1;
        private boolean isCancelPrevious;

        public Builder(Context context) {
            if (context == null) {
                throw new NullPointerException("Context cannot be null!");
            }
            this.context = context.getApplicationContext();
        }

        /**
         * Custome toast view. When customeView is set, only gravity and
         * duration is available.
         */
        public Builder customeView(View customeView) {
            this.customeView = customeView;
            return this;
        }

        public Builder lengthShort(boolean isShort) {
            this.isLengthShort = isShort;
            return this;
        }

        /** The toast message, will override {@link #textResId(int)} */
        public Builder text(String msgText) {
            this.text = msgText;
            return this;
        }

        /**
         * The toast message String resource Id, will be override by
         * {@link #text(String)}.
         */
        public Builder textResId(int textResId) {
            this.textResId = textResId;
            return this;
        }

        /** Text size, the unit is sp. */
        public Builder textSize(float textSize) {
            this.textSize = textSize;
            return this;
        }

        /** Text size dimen resource id. */
        public Builder textSizeResId(int textSizeResId) {
            this.textSizeResId = textSizeResId;
            return this;
        }

        /**
         * Whether cancel previous showed toast. Only function when the previous
         * toast is created by {@link ToastHelper} or
         * {@link ToastHelper.Builder} and be showed by {@link Builder#show()}.
         */
        public Builder cancelPrevious(boolean cancelPrevious) {
            isCancelPrevious = cancelPrevious;
            return this;
        }

        /** Text color, this will override {@link #textColorResId(int)} */
        public Builder textColor(int textColor) {
            this.textColor = textColor;
            return this;
        }

        /**
         * Text color resource id, this will be override by
         * {@link #textColor(int)}
         */
        public Builder textColorResId(int textColorResId) {
            this.textColorResId = textColorResId;
            return this;
        }

        /**
         * The toast background color, this will override {@link #bgColorResId}
         * and be override by {@link #bgDrawableResId(int)}.
         */
        public Builder bgColor(int bgColor) {
            this.bgColor = bgColor;
            return this;
        }

        /**
         * The toast background color resource id, this will be override by
         * {@link #bgColor(int)} and {@link #bgDrawableResId(int)}
         */
        public Builder bgColorResId(int bgColorResId) {
            this.bgColorResId = bgColorResId;
            return this;
        }

        /**
         * The toast background drawable resource id, this will override
         * {@link #bgColor(int)} and {@link #bgColorResId(int)}
         */
        public Builder bgDrawableResId(int bgDrawableResId) {
            this.bgDrawableResId = bgDrawableResId;
            return this;
        }

        /** The padding between text message and its container. */
        public Builder padding(int left, int top, int right, int bottom) {
            this.paddingLeft = left;
            this.paddingTop = top;
            this.paddingRight = right;
            this.paddingBottom = bottom;
            return this;
        }

        /** Toast {@link Gravity} */
        public Builder gravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        @SuppressLint("ShowToast") public Toast build() {
            Toast toast;
            if (customeView != null) {
                toast = new Toast(context);
                toast.setView(customeView);
                toast.setDuration(isLengthShort ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
            } else {
                if (TextUtils.isEmpty(text) && textResId != -1) {
                    text = context.getString(textResId);
                }
                toast = Toast.makeText(context, text, isLengthShort ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);

                if (textColor != -1 || textColorResId != -1 || textSize != -1) {
                    TextView msg = (TextView) toast.getView().findViewById(android.R.id.message);
                    msg.setTextColor(textColor != -1 ? textColor : context.getResources().getColor(textColorResId));
                    if (textSize != -1) {
                        msg.setTextSize(textSize);
                    } else if (textSizeResId != -1) {
                        msg.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(textSizeResId));
                    }
                }

                View toastFrame = toast.getView();
                if (bgDrawableResId != -1) {
                    toastFrame.setBackgroundResource(bgDrawableResId);
                } else if (bgColor != -1) {
                    toastFrame.setBackgroundColor(bgColor);
                } else if (bgColorResId != -1) {
                    toastFrame.setBackgroundColor(context.getResources().getColor(bgColorResId));
                }

                if (paddingLeft != Integer.MIN_VALUE
                    || paddingTop != Integer.MIN_VALUE
                    || paddingRight != Integer.MIN_VALUE
                    || paddingBottom != Integer.MIN_VALUE) {
                    toastFrame.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
                }
            }

            if (gravity != -1) {
                toast.setGravity(gravity, 0, 0);
            }

            return toast;
        }

        /**
         * Show the toast, cancel previously showed toast is
         * {@link #isCancelPrevious} is true. Only function when the previous
         * toast is created by {@link ToastHelper} or
         * {@link ToastHelper.Builder} and be showed by {@link Builder#show()}.
         */
        public void show() {
            if (isCancelPrevious && mToast != null) {
                mToast.cancel();
            }
            mToast = build();
            build().show();
        }
    }
}