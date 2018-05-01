package b12app.vyom.com.flowit.startup;

import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import b12app.vyom.com.flowit.home.BasePresenter;
import b12app.vyom.com.flowit.home.BaseView;

public interface StartUpContract {
    interface IView extends BaseView<StartUpContract.IPresenter> {

       void displayVideo();

    }

    interface IPresenter extends BasePresenter {

        void playVideo(VideoView startUpVideo);
        void startNextActivity(View view);

    }

}
