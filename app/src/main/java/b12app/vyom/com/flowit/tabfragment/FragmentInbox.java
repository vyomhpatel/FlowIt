package b12app.vyom.com.flowit.tabfragment;

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
 * @FileName FragmentInbox
 * @Date 4/26/18, 12:25 AM
 * @Author Created by fengchengding
 * @Description FlowIt
 */

public class FragmentInbox extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_inbox, container, false);
        return v;
    }
}
