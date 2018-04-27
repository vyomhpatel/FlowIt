package b12app.vyom.com.flowit.task;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.adapter.ProjectListAdapter;
import b12app.vyom.com.flowit.model.Project;
import b12app.vyom.com.flowit.networkutils.ApiService;
import b12app.vyom.com.flowit.networkutils.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectListFragmentDialog extends DialogFragment {

    private ListView projectList;
    private ApiService apiService;
    private OnCompleteListener mListener;

    public static interface OnCompleteListener {
        public abstract void onComplete(String project_id,String project_name);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.mListener = (OnCompleteListener)context;
        }
        catch (final ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnCompleteListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.project_list_fragment,container,false);
        apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);
        projectList = view.findViewById(R.id.projectList);
        Call<Project> projectCall = apiService.getProjects();
        projectCall.enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, final Response<Project> response) {



                ProjectListAdapter projectListAdapter = new ProjectListAdapter(response.body().getProjects(), getActivity());
                projectList.setAdapter(projectListAdapter);
                projectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String project_name = response.body().getProjects().get(position).getProjectname();
                        String project_id = response.body().getProjects().get(position).getId();
                        mListener.onComplete(project_id,project_name);
                        getDialog().dismiss();

                    }
                });
            }

            @Override
            public void onFailure(Call<Project> call, Throwable t) {

            }
        });


        return view;
    }
}
