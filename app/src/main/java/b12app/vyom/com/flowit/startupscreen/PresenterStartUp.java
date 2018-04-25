package b12app.vyom.com.flowit.startupscreen;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.home.Home;

public class PresenterStartUp implements IPresenterStartUp{

    IViewStartUp iViewStartUp;
    StartUpScreen startUpScreen;

    public PresenterStartUp(StartUpScreen startUpScreen) {
        this.startUpScreen = startUpScreen;
        iViewStartUp = startUpScreen;
    }

    @Override
    public void playVideo() {

    }

    @Override
    public void activityReady(final Context context) {

        iViewStartUp.playVideo(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.start));

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(context,Home.class);
                context.startActivity(intent);
            }
        },65000);
    }

    @Override
    public void showLogin() {

    }


}
