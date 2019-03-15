package app.com.mediaplayer;

public class SplashTimerPresenter {
    /**
     * 在p层中得到view层的引用
     *
     * 好处:可以让之后的类很快的实现相同的逻辑
     * */
    private CustomCountDownTimer timer;
    private SplashActivity mActivity;

    //在p层中同时介有activity的引用
    public SplashTimerPresenter (SplashActivity activity){
            this.mActivity=activity;
    }
    //在p层中没有view层的引用
    public void initTimer() {
        timer=new CustomCountDownTimer(5, new CustomCountDownTimer.IcountDownHandler() {
            @Override
            public void onTicker(int time) {
                mActivity.setTvTimer(time+"秒");
//                mTvTimer.setText(time+"秒");
            }
            @Override
            public void onFinish() {
                mActivity.setTvTimer("跳过");
//                mTvTimer.setText("跳过");
            }
        });
        timer.start();
    }

    public void cancel() {
        timer.cancel();
    }
}
