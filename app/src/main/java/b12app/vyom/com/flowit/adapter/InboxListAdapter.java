package b12app.vyom.com.flowit.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.model.InboxModel;
import butterknife.BindView;
import butterknife.ButterKnife;

public class InboxListAdapter extends RecyclerView.Adapter {

    private final LayoutInflater layoutInflater;
    private List<InboxModel> inboxModelList;
    private ListView listView;
    private Context context;


    public InboxListAdapter(List<InboxModel> dataList, Context context) {
        this.inboxModelList = dataList;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == -1) {//if empty

            View v = LayoutInflater.from(context).inflate(R.layout.item_empty, parent, false);

            return new emptyViewHolder(v);

        } else {

            View v = LayoutInflater.from(context).inflate(R.layout.item_inbox, parent, false);

            return new InboxViewHolder(v);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof InboxViewHolder) {
            ((InboxViewHolder) holder).nameTv.setText(inboxModelList.get(position).getTaskName());
            ((InboxViewHolder) holder).descTv.setText(inboxModelList.get(position).getTaskDesc());
            ((InboxViewHolder) holder).titleTv.setText(R.string.new_assignment);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getItemCount() {
        return (inboxModelList != null && inboxModelList.size() > 0) ? inboxModelList.size() : 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (inboxModelList.size() <= 0) {

            return -1;

        }

        return super.getItemViewType(position);
    }


    class InboxViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_inbox_name)
        TextView nameTv;

        @BindView(R.id.tv_inbox_desc)
        TextView descTv;

        @BindView(R.id.tv_inbox_title)
        TextView titleTv;

        public InboxViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class emptyViewHolder extends RecyclerView.ViewHolder {

        public emptyViewHolder(View itemView) {
            super(itemView);
        }
    }
}