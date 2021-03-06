package b12app.vyom.com.flowit.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionHelper;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RFACLabelItem;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RapidFloatingActionContentLabelList;
import com.wangjie.rapidfloatingactionbutton.util.RFABTextUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import b12app.vyom.com.flowit.R;
import b12app.vyom.com.flowit.daggerUtils.AppComponent;
import b12app.vyom.com.flowit.datasource.DataManager;
import b12app.vyom.com.flowit.projectcreate.ProjectCreateActivity;
import b12app.vyom.com.flowit.tabfragment.FragmentDashboard;
import b12app.vyom.com.flowit.tabfragment.FragmentInbox;
import b12app.vyom.com.flowit.tabfragment.FragmentProject;
import b12app.vyom.com.flowit.tabfragment.FragmentTask;
import b12app.vyom.com.flowit.tabfragment.project.ProjectFgtPresenter;
import b12app.vyom.com.flowit.tabfragment.task.TaskFgtPresenter;
import b12app.vyom.com.flowit.task.TaskCreateActivity;
import b12app.vyom.com.utils.ActivityUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, RapidFloatingActionContentLabelList.OnRapidFloatingActionContentLabelListListener {
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

    @Inject
    DataManager mDataManager;


    //presenter
    private ProjectFgtPresenter projectFgtPresenter;
    private TaskFgtPresenter taskFgtPresenter;

    private Integer[] bottomTabIcon = {R.drawable.ic_testing};
    private Integer[] bottomIconPress = {R.drawable.ic_testing, R.drawable.ic_testing,
            R.drawable.ic_testing, R.drawable.ic_testing};

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

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        appComponent.inject(this);
    }

    private void initBottomNavView() {
        botNavView.addTab(botNavView.newTab().setIcon(R.drawable.ic_testing).setText("Inbox"));
        botNavView.addTab(botNavView.newTab().setIcon(R.drawable.ic_tab_project).setText("Project"));
        botNavView.addTab(botNavView.newTab().setIcon(R.drawable.ic_tab_task).setText("Task"));
//        botNavView.addTab(botNavView.newTab().setIcon(R.drawable.ic_testing).setText("Timeline"));
        botNavView.addTab(botNavView.newTab().setIcon(R.drawable.ic_tab_subtask).setText("SubTask"));

        //default tab selected
        setDefaultTab();

        botNavView.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        ActivityUtil.addFragmentToActivity(R.id.fl_float_container, getSupportFragmentManager(), new FragmentInbox(), "inboxFgt");
                        showMainFloatBtn();
                        break;
                    case 1:
                        //fragment
                        FragmentProject fragmentProject = new FragmentProject();
                        //presenter
                        projectFgtPresenter = new ProjectFgtPresenter(mDataManager, fragmentProject);
                        //add fragment
                        ActivityUtil.addFragmentToActivity(R.id.fl_float_container, getSupportFragmentManager(), fragmentProject, "browseFgt");
                        showMainFloatBtn();
                        break;
                    case 2:
                        //fragment
                        FragmentTask fragmentTask = new FragmentTask();
                        //presenter
                        taskFgtPresenter = new TaskFgtPresenter(mDataManager, fragmentTask);
                        ActivityUtil.addFragmentToActivity(R.id.fl_float_container, getSupportFragmentManager(), fragmentTask, "taskFgt");
                        showMainFloatBtn();
                        break;
                    case 3:
                        ActivityUtil.addFragmentToActivity(R.id.fl_float_container, getSupportFragmentManager(), new FragmentDashboard(), "dashFgt");
                        showMainFloatBtn();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void setDefaultTab() {
        botNavView.getTabAt(1).select();
        //fragment
        FragmentProject fragmentProject = new FragmentProject();
        //presenter
        projectFgtPresenter = new ProjectFgtPresenter(mDataManager, fragmentProject);
        ActivityUtil.addFragmentToActivity(R.id.fl_float_container, getSupportFragmentManager(),fragmentProject, "browseFgt");
    }

    private void initFloat() {

        RapidFloatingActionContentLabelList rfaContent = new RapidFloatingActionContentLabelList(this);

        rfaContent.setOnRapidFloatingActionContentLabelListListener(this);
        List<RFACLabelItem> items = new ArrayList<>();

        items.add(new RFACLabelItem<Integer>()
                .setLabel(getString(R.string.new_project))
                .setResId(R.drawable.ic_tab_project)
                .setIconNormalColor(0xffd84315)
                .setIconPressedColor(0xffbf360c)
                .setWrapper(0)
        );

        items.add(new RFACLabelItem<Integer>()
                .setLabel(getString(R.string.new_task))
                .setResId(R.drawable.ic_tab_task)
                .setIconNormalColor(0xff056f00)
                .setIconPressedColor(0xff0d5302)
                .setLabelColor(0xff056f00)
                .setWrapper(1)
        );

        items.add(new RFACLabelItem<Integer>()
                .setLabel("New Sub Task")
                .setResId(R.drawable.ic_tab_subtask)
                .setIconNormalColor(0xffd84315)
                .setIconPressedColor(0xffbf360c)
                .setWrapper(2)
        );

        items.add(new RFACLabelItem<Integer>()
                .setLabel("New Team")
                .setResId(R.drawable.ic_testing)
                .setIconNormalColor(0xff056f00)
                .setIconPressedColor(0xff0d5302)
                .setLabelColor(0xff056f00)
                .setWrapper(3)
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
            startActivity(new Intent(HomeActivity.this, ProjectCreateActivity.class));
        } else if (position == 1) {
            startActivity(new Intent(HomeActivity.this, TaskCreateActivity.class));

        }
        rfabHelper.toggleContent();
    }

    @Override
    public void onRFACItemIconClick(int position, RFACLabelItem item) {
        if (position == 0) {
            startActivity(new Intent(HomeActivity.this, ProjectCreateActivity.class));
        } else if (position == 1) {
            startActivity(new Intent(HomeActivity.this, TaskCreateActivity.class));

        }
        rfabHelper.toggleContent();
    }

    public void dismissMainFloatBtn(){
        rfaButton.setVisibility(View.GONE);
    }

    public void showMainFloatBtn(){
        rfaButton.setVisibility(View.VISIBLE);
    }
}
