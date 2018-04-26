package b12app.vyom.com.flowit.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionHelper;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RFACLabelItem;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RapidFloatingActionContentLabelList;
import com.wangjie.rapidfloatingactionbutton.util.RFABTextUtil;

import java.util.ArrayList;
import java.util.List;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.projectcreate.ProjectCreateActivity;
import b12app.vyom.com.flowit.task.TaskCreateActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, RapidFloatingActionContentLabelList.OnRapidFloatingActionContentLabelListListener {
    @BindView(R.id.bottom_tab_layout)
    TabLayout botNavView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.layout_float)
    RapidFloatingActionLayout rfaLayout;

    @BindView(R.id.btn_float)
    RapidFloatingActionButton rfaButton;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.nav_view)
    NavigationView leftDrawer;

    private Integer[] bottomTabIcon = {R.drawable.ic_testing};
//    private Integer[] bottomIconPress = {R.mipmap.home_press,R.mipmap.favorite_press,
//            R.mipmap.friends_press,R.mipmap.account_press};

    private RapidFloatingActionHelper rfabHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        initFloat();

        initDraw(toolbar);

        initBottomNavView();
    }

    private void initBottomNavView() {
        botNavView.addTab(botNavView.newTab().setIcon(R.drawable.ic_testing).setText("Inbox"));
        botNavView.addTab(botNavView.newTab().setIcon(R.drawable.ic_testing).setText("Browse"));
        botNavView.addTab(botNavView.newTab().setIcon(R.drawable.ic_testing).setText("Task"));
        botNavView.addTab(botNavView.newTab().setIcon(R.drawable.ic_testing).setText("Timeline"));
        botNavView.addTab(botNavView.newTab().setIcon(R.drawable.ic_testing).setText("Dashboard"));

        botNavView.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {


//                bottmeTab.getTabAt(pos_tab).setIcon(iconPressList.get(pos_tab));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void initFloat() {

        RapidFloatingActionContentLabelList rfaContent = new RapidFloatingActionContentLabelList(this);

        rfaContent.setOnRapidFloatingActionContentLabelListListener(this);
        List<RFACLabelItem> items = new ArrayList<>();

        items.add(new RFACLabelItem<Integer>()
                .setLabel(getString(R.string.new_project))
                .setResId(R.drawable.ic_testing)
                .setIconNormalColor(0xffd84315)
                .setIconPressedColor(0xffbf360c)
                .setWrapper(0)
        );

        items.add(new RFACLabelItem<Integer>()
                .setLabel(getString(R.string.new_task))
                .setResId(R.drawable.ic_testing)
                .setIconNormalColor(0xff056f00)
                .setIconPressedColor(0xff0d5302)
                .setLabelColor(0xff056f00)
                .setWrapper(1)
        );

        rfaContent
                .setItems(items)
                .setIconShadowRadius(RFABTextUtil.dip2px(this, 5))
                .setIconShadowColor(0xff888888)
                .setIconShadowDy(RFABTextUtil.dip2px(this, 5))
        ;

        rfabHelper = new RapidFloatingActionHelper(
                this,
                rfaLayout,
                rfaButton,
                rfaContent
        ).build();
    }

    private void initDraw(Toolbar toolbar) {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        leftDrawer.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                break;
        }

        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_settings:
                break;
            case R.id.nav_help:
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onRFACItemLabelClick(int position, RFACLabelItem item) {
        if (position == 0) {
            startActivity(new Intent(Home.this, ProjectCreateActivity.class));
        } else if (position == 1) {
            startActivity(new Intent(Home.this, TaskCreateActivity.class));

        }
        rfabHelper.toggleContent();
    }

    @Override
    public void onRFACItemIconClick(int position, RFACLabelItem item) {
        if (position == 0) {
            startActivity(new Intent(Home.this, ProjectCreateActivity.class));
        } else if (position == 1) {
            startActivity(new Intent(Home.this, TaskCreateActivity.class));

        }
        rfabHelper.toggleContent();

    }
}
