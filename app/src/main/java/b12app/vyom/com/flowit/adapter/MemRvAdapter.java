package b12app.vyom.com.flowit.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.model.Employee;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Package b12app.vyom.com.flowit.adapter
 * @FileName EmployeeRvAdapter
 * @Date 4/27/18, 11:34 AM
 * @Author Created by fengchengding
 * @Description FlowIt
 */

public class MemRvAdapter extends RecyclerView.Adapter<MemRvAdapter.mViewHolder> {
    private Context context;
    private List<Employee.EmployeesBean> dataList;

    public MemRvAdapter(Context context, List<Employee.EmployeesBean> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MemRvAdapter.mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_team_member, parent, false);

        return new mViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MemRvAdapter.mViewHolder holder, final int position) {
        holder.employeeName.setText(dataList.get(position).getEmpfirstname() + " " + dataList.get(position).getEmplastname());
        holder.employeeEmail.setText(dataList.get(position).getEmpemail());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    class mViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_mem_name)
        TextView employeeName;

        @BindView(R.id.tv_mem_email)
        TextView employeeEmail;

        public mViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
