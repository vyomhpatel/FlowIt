package b12app.vyom.com.flowit.startupscreen;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.home.Home;

public class StartUpScreen extends AppCompatActivity implements IViewStartUp {

    private VideoView videoView;
    private IPresenterStartUp iPresenterStartUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up_screen);
        videoView =
                (VideoView) findViewById(R.id.videoView);
//        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.start));
//        videoView.start();
//        new android.os.Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(StartUpScreen.this,Home.class);
//                startActivity(intent);
//            }
//        },65000);

        iPresenterStartUp = new PresenterStartUp(this);
        iPresenterStartUp.activityReady(this);

    }

    @Override
    public void playVideo(Uri uri) {
        videoView.setVideoURI(uri);
        videoView.start();
    }
}
