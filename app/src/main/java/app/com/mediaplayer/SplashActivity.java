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
 * 注意:bufferKnife用在8.8.1版本
 * */
public class SplashActivity extends AppCompatActivity {
    private TextView mTvTimer;
    //Activity的生命周期
    private app.com.mediaplayer.FullScreenVideoView fullScreenVideoView;
    private CustomCountDownTimer timer;
    private SplashTimerPresenter timerPresenter;
    private SplashVideoPresenter videoPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);//把自定义的Layout加入到id为content的ViewGroup中（系统布局）
        fullScreenVideoView=(app.com.mediaplayer.FullScreenVideoView) findViewById(R.id.vv_play);
        mTvTimer=(TextView) findViewById(R.id.tv_splash_timer);
        //进行代码的重构
        //由于VideoView会根据视频的文件大小来改变自身大小，所以要自定义view
        //初始化
        initTimerPresenter();
        initListener();
        initVideoPresenter();
        //把Video进行抽离进行抽离
         initVideo();
//        把初始化Timer以及相关内容放在presenter中
    }

    //在activity中持有p层的引用
    private void initTimerPresenter() {
     timerPresenter=new SplashTimerPresenter(this);
     timerPresenter.initTimer();
    }
    //建立到p层的引用
    private void initVideoPresenter(){
        videoPresenter=new SplashVideoPresenter(this);
        videoPresenter.initVideo();
    }

//    private void initTimer() {
//
//    }

    private void initVideo() {
        fullScreenVideoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+ File.separator+R.raw.fight));
    }

    private void initListener() {
        mTvTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_demo=new Intent(SplashActivity.this, MainActivity.class);
                intent_demo.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);//清除之前的任务栈
                startActivity(intent_demo);
            }
        });
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timerPresenter.cancel();
//        timer.cancel();
    }
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    public void setTvTimer(String s) {
        mTvTimer.setText(s);
    }
}
