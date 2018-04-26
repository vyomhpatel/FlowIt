package b12app.vyom.com.flowit.MyFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import b12app.vyom.com.flowit.R;

/**
 * @Package b12app.vyom.com.flowit.fragmentbrowse
 * @FileName FragmentDashboard
 * @Date 4/26/18, 12:26 AM
 * @Author Created by fengchengding
 * @Description FlowIt
 */

public class FragmentDashboard extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);
        return v;
    }
}
