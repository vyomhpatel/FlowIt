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
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
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

public class EmployeeRvAdapter extends RecyclerView.Adapter<EmployeeRvAdapter.mViewHolder> {
    private Context context;
    private List<Employee.EmployeesBean> dataList;
    private List<Employee.EmployeesBean> memberList;

    private SparseBooleanArray mSelectedPos;
    private SparseBooleanArray selectedEmlopyee;

    public EmployeeRvAdapter(Context context, List<Employee.EmployeesBean> employees, List<Employee.EmployeesBean> memberList) {
        this.context = context;
        this.dataList = employees;
        this.memberList = memberList;
        mSelectedPos = new SparseBooleanArray(employees.size());
        selectedEmlopyee = new SparseBooleanArray(employees.size());

        //find out team member
        if (memberList != null){
            for (int i = 0; i < employees.size(); i ++){
                for (int j = 0 ; j < memberList.size(); j++){
                    if (memberList.get(j).getEmpid().equals(employees.get(i).getEmpid())){
                        mSelectedPos.put(i, true);
                        selectedEmlopyee.put(i, true);
                    }
                }
            }
        }
    }

    @NonNull
    @Override
    public EmployeeRvAdapter.mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_employee_rv, parent, false);

        return new mViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final EmployeeRvAdapter.mViewHolder holder, final int position) {
        holder.employeeId.setText(dataList.get(position).getEmpid());
        holder.employeeName.setText(dataList.get(position).getEmpfirstname() + " " + dataList.get(position).getEmplastname());

        holder.employeeCbx.setTag(position);

        holder.employeeCbx.setChecked(mSelectedPos.get(position, false));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    class mViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_employ_id)
        TextView employeeId;

        @BindView(R.id.tv_employ_name)
        TextView employeeName;

        @BindView(R.id.cbx_employ)
        CheckBox employeeCbx;

        public mViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            employeeCbx.setOnClickListener(this);
        }

        //we cannot use onCheckChange listener because when you scroll down and your item will get recycler,
        // this callback will automatically invoke
        @Override
        public void onClick(View v) {
            int pos = (int)v.getTag();
            //if it is not checked
            if (!mSelectedPos.get(pos)){
                //mark as check
                mSelectedPos.put(pos, true);
                // Since pos is layoutPosition(which we can visual see)
                // we need use adapter position, to find the real employee we picked
                selectedEmlopyee.put(getAdapterPosition(), true);
                Log.i("选中", getAdapterPosition() + "");
            }else {
                //mark as un check
                mSelectedPos.delete(pos);
                selectedEmlopyee.put(getAdapterPosition(), false);
                Log.i("选中, un", getAdapterPosition() + "");
            }
        }
    }

    public SparseBooleanArray getPickedEmployee(){
        return selectedEmlopyee;
    }

    private boolean isItemChecked(int pos){
        return mSelectedPos.get(pos);
    }

    private void setItemChecked(int position, boolean isChecked) {
        mSelectedPos.put(position, isChecked);
    }


}
