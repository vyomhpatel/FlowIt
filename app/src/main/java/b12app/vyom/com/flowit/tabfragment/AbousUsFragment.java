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
 * @Package b12app.vyom.com.flowit.tabfragment
 * @FileName AbousUsFragment
 * @Date 5/3/18, 10:32 AM
 * @Author Created by fengchengding
 * @Description FlowIt
 */

public class AbousUsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.abous_us,container,false);

        return view;
    }
}
