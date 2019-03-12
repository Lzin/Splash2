package app.com.mediaplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.File;

/**
 * 通过自定义Activity style实现fullScreen()（去除沉浸栏，标题栏）
 * 由于videoview不能实现全局进行视频展示，所以自定义控件进行实现效果
 * */
public class SplashActivity extends AppCompatActivity {
    private TextView mTvTimer;
    //Activity的生命周期
    private app.com.mediaplayer.FullScreenVideoView fullScreenVideoView;
    private CustomCountDownTimer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);//把自定义的Layout加入到id为content的ViewGroup中（系统布局）
        //由于VideoView会根据视频的文件大小来改变自身大小，所以要自定义view
        mTvTimer=(TextView) findViewById(R.id.tv_splash_timer);
        fullScreenVideoView=(app.com.mediaplayer.FullScreenVideoView) findViewById(R.id.vv_play);
        fullScreenVideoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+ File.separator+R.raw.fight));
        //播放器是否准备好了
        //观察者:界面 被观察者:程序 ioc
        fullScreenVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });
        //实现联系播放
        fullScreenVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });
         timer=new CustomCountDownTimer(5, new CustomCountDownTimer.IcountDownHandler() {
            @Override
            public void onTicker(int time) {
                mTvTimer.setText(time+"秒");
            }

            @Override
            public void onFinish() {
                mTvTimer.setText("跳过");
            }
        });
        timer.start();
        mTvTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_demo=new Intent(SplashActivity.this, MainActivity.class);
                intent_demo.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);//清除之前的任务栈
                startActivity(intent_demo);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
