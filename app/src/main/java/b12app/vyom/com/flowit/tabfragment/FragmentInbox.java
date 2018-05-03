package b12app.vyom.com.flowit.tabfragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import javax.inject.Inject;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.adapter.InboxListAdapter;
import b12app.vyom.com.flowit.daggerUtils.AppApplication;
import b12app.vyom.com.flowit.datasource.IDataSource;
import b12app.vyom.com.flowit.home.Global;
import b12app.vyom.com.flowit.model.Employee;
import b12app.vyom.com.flowit.model.InboxModel;
import b12app.vyom.com.utils.FbHelper;
import b12app.vyom.com.utils.SpHelper;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Package b12app.vyom.com.flowit.fragmentbrowse
 * @FileName FragmentInbox
 * @Date 4/26/18, 12:25 AM
 * @Author Created by fengchengding
 * @Description FlowIt
 */

public class FragmentInbox extends Fragment {
    @BindView(R.id.rv_inbox)
    RecyclerView inboxRv;

    @Inject
    SharedPreferences sp;

    private DatabaseReference myRef;

    Unbinder unbinder;

    private static final String TAG = "FragmentInbox";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_inbox, container, false);
        unbinder = ButterKnife.bind(this, v);

        AppApplication.get(getContext()).getAppComponent().inject(this);

        initFireBase();

        return v;
    }

    private void initFireBase() {

        myRef = FbHelper.getInstance().getReference(Global.TABLE_INBOX);
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                initRecyclerView(FbHelper.getInstance().getUserInbox(dataSnapshot, SpHelper.getUserId(sp)));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i(TAG, error.getMessage());
            }
        });
    }

    private void initRecyclerView(List<InboxModel> userInbox) {
        inboxRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        inboxRv.setAdapter(new InboxListAdapter(userInbox, getContext()));
        inboxRv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }


}
