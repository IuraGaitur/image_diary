package img_diary.paxra.com.imagediary.custom_views;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import img_diary.paxra.com.imagediary.R;


public class InfoDialog extends Dialog {

    public static final String TAG_KILL_AFTER = "kill_activity";

    private Context context;

    @Bind(R.id.txt_timer) TextView mTimerTxtView;

    private boolean killAfter = false;

    public InfoDialog(Context context, boolean killAfter) {
        super(context, R.style.CustomDialogTheme);
        setContentView(R.layout.dialog_info);
        this.killAfter = killAfter;
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ButterKnife.bind(this);
        setCountDown(10000); //kill automatically after 10 seconds
    }


    private void setCountDown(int time) {
        new CountDownTimer(time, 1000) {

            public void onTick(long millisUntilFinished) {
                mTimerTxtView.setText("Auto Destroy In : " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                InfoDialog.this.dismiss();
            }
        }.start();
    }


    @OnClick(R.id.btn_close)
    public void cancelAction() {
        dismiss();
    }

}
