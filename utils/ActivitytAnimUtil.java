import android.app.Activity;
import android.content.Intent;
import com.hna.tarmac.frontlinestaff.R;

/**
 * 启动Activity的动画特效集合
 * @author Vincent
 */
public class ActivitytAnimUtil {

  /**
   * 启动activity，带左右滑动效果
   *
   * @isFinishIntent true则关闭当前activity
   */
  public static void startActivityWithSlide(Activity context, Intent intent,
      boolean isFinishIntent) {
    Activity parentActivity = context.getParent();
    context.startActivity(intent);

    if (parentActivity != null) {
      parentActivity.overridePendingTransition(R.anim.util_intent_slide_in_left,
          R.anim.util_intent_slide_out_left);
    } else {
      context.overridePendingTransition(R.anim.util_intent_slide_in_left,
          R.anim.util_intent_slide_out_left);
    }
    if (isFinishIntent) {
      context.finish();
    }
  }

  /**
   * 启动activityForResult，带左右滑动效果
   *
   * @isFinishIntent true则关闭当前activity
   */
  public static void startActivityForResultWithSlide(Activity context, Intent intent,
      boolean isFinishIntent, int requestCode) {
    Activity parentActivity = context.getParent();
    context.startActivityForResult(intent, requestCode);

    if (parentActivity != null) {
      parentActivity.overridePendingTransition(R.anim.util_intent_slide_in_left,
          R.anim.util_intent_slide_out_left);
    } else {
      context.overridePendingTransition(R.anim.util_intent_slide_in_left,
          R.anim.util_intent_slide_out_left);
    }
    if (isFinishIntent) {
      context.finish();
    }
  }

  /**
   * 返回上一个activity （之前的intent未关闭）,携带ResultCode,带左右滑动效果
   */
  public static void finishWithResultCode(Activity context, int resultCode) {
    Activity parentActivity = context.getParent();
    context.setResult(resultCode);
    context.finish();
    if (parentActivity != null) {
      parentActivity.overridePendingTransition(0, android.R.anim.slide_out_right);
    } else {
      context.overridePendingTransition(0, android.R.anim.slide_out_right);
    }
  }

  /**
   * 返回上一个activity ,返回 RESULT_OK,并关闭
   */
  public static void finishWithIntent(Activity context, Intent intent) {
    Activity parentActivity = context.getParent();
    context.setResult(Activity.RESULT_OK, intent);
    context.finish();
    if (parentActivity != null) {
      parentActivity.overridePendingTransition(0, android.R.anim.slide_out_right);
    } else {
      context.overridePendingTransition(0, android.R.anim.slide_out_right);
    }
  }

  /**
   * 返回上一个activity （之前的intent未关闭）,不返回OK
   */
  public static void finishWithoutResult(Activity context) {
    Activity parentActivity = context.getParent();
    context.finish();
    if (parentActivity != null) {
      parentActivity.overridePendingTransition(0, android.R.anim.slide_out_right);
    } else {
      context.overridePendingTransition(0, android.R.anim.slide_out_right);
    }
  }
}
