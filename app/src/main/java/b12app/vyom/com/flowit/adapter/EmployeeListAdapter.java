package b12app.vyom.com.flowit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.model.Employee;
import b12app.vyom.com.flowit.model.Project;

public class EmployeeListAdapter extends BaseAdapter {


    private final LayoutInflater layoutInflater;
    private List<Employee.EmployeesBean> employeesBeans;
   private ListView listView;
   private Context context;

    public EmployeeListAdapter(List<Employee.EmployeesBean> employeesBeans, Context context) {
        this.employeesBeans = employeesBeans;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return employeesBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return employeesBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EmployeeListViewHolder employeeListViewHolder;

        if(convertView==null){
            employeeListViewHolder = new EmployeeListViewHolder();
            convertView = layoutInflater.inflate(R.layout.employee_list_item_format,parent,false);
            employeeListViewHolder.emp_name = convertView.findViewById(R.id.emp_name);
            employeeListViewHolder.emp_email = convertView.findViewById(R.id.emp_email);
            employeeListViewHolder.emp_mobile = convertView.findViewById(R.id.emp_mobile);
            employeeListViewHolder.designation_text = convertView.findViewById(R.id.designation_text);
            convertView.setTag(employeeListViewHolder);
        } else {

            employeeListViewHolder = (EmployeeListViewHolder) convertView.getTag();
        }

        employeeListViewHolder.emp_name.setText(employeesBeans.get(position).getEmpfirstname()+" "
                                                +employeesBeans.get(position).getEmplastname());

        employeeListViewHolder.emp_mobile.setText(employeesBeans.get(position).getEmpmobile());
        employeeListViewHolder.emp_email.setText(employeesBeans.get(position).getEmpemail());
        switch (employeesBeans.get(position).getEmpdesignation()){

            case "manager":
                employeeListViewHolder.designation_text.setText("MGR");
                break;
            case "MNGR":
                employeeListViewHolder.designation_text.setText("MGR");
                break;
            case "TL":
                employeeListViewHolder.designation_text.setText("TL");
                break;
            case "DVLPR":
                employeeListViewHolder.designation_text.setText("DEV");
                break;
            case "TSTR":
                employeeListViewHolder.designation_text.setText("QA");
                break;

        }
        return convertView;
    }

    private class EmployeeListViewHolder{

        TextView designation_text,emp_name,emp_mobile,emp_email;

    }
}
