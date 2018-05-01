package b12app.vyom.com.flowit.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import b12app.vyom.com.flowit.daggerUtils.AppApplication;
import b12app.vyom.com.flowit.daggerUtils.AppComponent;

/**
 * @Package b12app.vyom.com.flowit.home
 * @FileName BaseActivity
 * @Date 4/30/18, 11:19 AM
 * @Author Created by fengchengding
 * @Description FlowIt
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent(AppApplication.get(this).getAppComponent());
    }

    protected abstract void setupActivityComponent(AppComponent appComponent);

}
