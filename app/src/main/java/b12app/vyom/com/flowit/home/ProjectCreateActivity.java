package b12app.vyom.com.flowit.home;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;

import com.squareup.picasso.Picasso;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.utils.CircleImageView;
import b12app.vyom.com.utils.MyFlowlayout;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectCreateActivity extends AppCompatActivity {
    @BindView(R.id.layout_flow)
    MyFlowlayout myFlowlayout;

    @BindView(R.id.tb)
    Toolbar toolbar;

    int[] urls = {R.drawable.ic_avatar, R.drawable.ic_avatar, R.drawable.ic_avatar, R.drawable.ic_avatar};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_create);
        ButterKnife.bind(this);

        initToolBar();
        initFlow();
    }

    private void initToolBar() {
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.green2));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.create_project);
    }

    public void initFlow() {
        LayoutInflater inflater = LayoutInflater.from(this);
        for (int url : urls) {
            CircleImageView imageView = (CircleImageView) inflater.inflate(R.layout.item_flowlayout, myFlowlayout, false);
            Picasso.with(this).load(url).fit().into(imageView);
            myFlowlayout.addView(imageView);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_attachment, menu);
        return true;
    }
}
