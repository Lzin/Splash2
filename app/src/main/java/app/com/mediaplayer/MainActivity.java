package app.com.mediaplayer;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * 学习MVP设计模式
 * module viewer presenter
 *
 * 老式的MVC
 * module viewer client
 * */
public class MainActivity extends AppCompatActivity {

    //两个RadioGroup
    private RadioGroup rgMainTop;
    private RadioGroup rgMainBottom;
    private FloatingActionButton facMainHome;
    private FrameLayout flMainButton;
    private RadioButton rbMainShanghai;
    private RadioButton rbMainShenzhen;
    private RadioButton rbMainHangzhou;
    private RadioButton rbMainBeijing;
    //bool 变量
    private boolean isChangeTopOrBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * 使用黄油刀出现问题，自己重新写initView
         * */

        initView();
        changeAnima(rgMainBottom, rgMainTop);//开始情况
    }


    class ClickUtil implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                //进行悬浮
                case R.id.fac_main_home:
                    //android动画
                    isChangeTopOrBottom = !isChangeTopOrBottom;
                    if (isChangeTopOrBottom) {
                        //动画操作
                        changeAnima(rgMainTop, rgMainBottom);
                    } else {
                        changeAnima(rgMainBottom, rgMainTop);
                    }
                    break;
                default:
                    break;
            }
        }


        //安卓动画 :帧动画 补间动画,属性动画

    }

    //通过补间动画实现效果
    private void changeAnima(RadioGroup gone, RadioGroup show) {
        //实现消失动画
        gone.clearAnimation();//清除动画
        Animation animationGone = AnimationUtils.loadAnimation(this, R.anim.main_tab_translate_hide);
        gone.startAnimation(animationGone);
        gone.setVisibility(View.GONE);
        //展示动画
        show.clearAnimation();//清除动画
        Animation animationShow = AnimationUtils.loadAnimation(this, R.anim.main_tab_translate_show);
        show.startAnimation(animationShow);
        show.setVisibility(View.VISIBLE);
    }

    public void initView() {
        //初始化布局(RadioBtn1,2 FloatingActionButton )
        rgMainTop = (RadioGroup) findViewById(R.id.rg_main_top);
        rgMainBottom = (RadioGroup) findViewById(R.id.rg_main_bottom);
        //FloatingActionButton
        facMainHome = (FloatingActionButton) findViewById(R.id.fac_main_home);
        //RadioButton
        rbMainShanghai = (RadioButton) findViewById(R.id.rb_main_shanghai);
        rbMainShenzhen = (RadioButton) findViewById(R.id.rb_main_shenzhen);
        rbMainHangzhou = (RadioButton) findViewById(R.id.rb_main_hangzhou);
        rbMainBeijing = (RadioButton) findViewById(R.id.rb_main_beijing);
        //FrameLayout
        flMainButton = (FrameLayout) findViewById(R.id.fl_main_button);
        //添加点击事件
        facMainHome.setOnClickListener(new ClickUtil());

    }
}
