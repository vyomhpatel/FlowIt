package b12app.vyom.com.flowit.tabfragment.project;

import android.support.v4.app.FragmentActivity;
import android.view.View;

import b12app.vyom.com.flowit.datasource.DataManager;
import b12app.vyom.com.flowit.model.GeneralSubTask;
import b12app.vyom.com.flowit.networkutils.ApiService;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by jinliyu on 4/30/18.
 */

public class SubTaskFragmentPresenter implements SubTaskFragmentContract.IPresenter {

    private SubTaskFragmentContract.IView fragmentView;
    private DataManager mDataManager;
    private Disposable disposable;


    public SubTaskFragmentPresenter(SubTaskFragmentContract.IView fragmentView, DataManager mDataManager) {
        this.fragmentView = fragmentView;
        fragmentView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public Disposable getSubtaskList(ApiService apiService) {
        apiService.listSubTask()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GeneralSubTask.ProjectsubtaskBean>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(GeneralSubTask.ProjectsubtaskBean subtask) {
                        fragmentView.initRecyclerView(subtask);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        return disposable;
    }

    @Override
    public void rvItemClick(View v, int position, GeneralSubTask subtask, FragmentActivity activity) {

    }
}
