package b12app.vyom.com.flowit.startup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import b12app.vyom.com.flowit.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StartUp extends AppCompatActivity implements StartUpContract.IView{

    @BindView(R.id.startUpVideo)
    VideoView startUpVideo;


    private StartUpContract.IPresenter iPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);
        ButterKnife.bind(this);
        iPresenter = new StartUpPresenter(this);
        iPresenter.playVideo(startUpVideo);
        startUpVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresenter.startNextActivity(v);
            }
        });
    }

    @Override
    public void displayVideo() {
        startUpVideo.start();
    }

    @Override
    public void setPresenter(StartUpContract.IPresenter presenter) {

    }
}
