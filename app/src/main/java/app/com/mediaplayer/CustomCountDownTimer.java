/**
 * 通过handler实现倒计时功能
 * */
package app.com.mediaplayer;
import android.os.Handler;
import android.widget.TextView;
/**
 * 实现定时器
 * */
public class CustomCountDownTimer implements  Runnable {
    private  int time;
    private final IcountDownHandler countDownHandler;
    private final android.os.Handler handler;
    private boolean isRun;
    private int countDowmTime;
    private TextView tv_show;

    //1.实时去回调这个时候是什么时间 倒计时到几秒 观察者设计模式
    //2.支持动态传入总时间
    //3.每过1s，总秒数-1，回调到activity
    //4.总时间倒计时为0时，回调完成的状态
    public CustomCountDownTimer(int time,IcountDownHandler countDownHandler){
        handler=new Handler();
        this.time=time;
        this.countDowmTime=time;
        this.countDownHandler=countDownHandler;
    }

    //复写Runnable的方法实现具体逻辑
    @Override
    public void run() {
        if(isRun){
            //进行中状态 开始回调
            if(countDownHandler!=null){
                countDownHandler.onTicker(countDowmTime);
            }
            //完成状态 回调停止
            if(countDowmTime==0){
                cancel();
                if(countDownHandler!=null){
                    countDownHandler.onFinish();
                }
            }
            //countDownTime!=0 而且开始回调 因此可以写回调的细节部分（time--）
            else{
                //开始回调
                countDowmTime= time--;//倒计时-1
                //handler 判断的时间
                handler.postDelayed(this,1000);
            }
        }
    }

    //开启倒计时
    public void start(){
        isRun=true;
        handler.post(this);
    }
    //跳出循环终止
    public void cancel(){
        isRun=false;
        handler.removeCallbacks(this);
    }
    //设计模式 观察者模式
    //观察者回调接口
    public interface IcountDownHandler{
        //1.倒计时回调
        void onTicker(int time);
        //2.完成时回调
        void onFinish();
    }
}
