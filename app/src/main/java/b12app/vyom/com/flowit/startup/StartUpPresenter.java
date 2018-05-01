package b12app.vyom.com.flowit.startup;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.view.View;
import android.widget.VideoView;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.home.HomeActivity;

public class StartUpPresenter implements StartUpContract.IPresenter {
    Context context;
    StartUpContract.IView iView;

    public StartUpPresenter( StartUp startUp) {

        this.iView = startUp;
        this.context = startUp;
    }

    @Override
    public void playVideo(VideoView startUpVideo) {

        startUpVideo.setVideoURI(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.startup));
        iView.displayVideo();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(context,HomeActivity.class);
                context.startActivity(intent);
            }
        },6200);

    }

    @Override
    public void startNextActivity(View view) {
        Intent intent = new Intent(context,HomeActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void start() {

    }
}
