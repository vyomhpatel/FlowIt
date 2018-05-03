package b12app.vyom.com.flowit.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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
import b12app.vyom.com.flowit.fcmutils.FlowItInstanceIdService;
import b12app.vyom.com.flowit.login.LoginActivity;
import b12app.vyom.com.flowit.model.User;
import b12app.vyom.com.flowit.projectcreate.ProjectCreateActivity;
import b12app.vyom.com.flowit.subtaskcreate.SubTaskCreateActivity;
import b12app.vyom.com.flowit.tabfragment.AbousUsFragment;
import b12app.vyom.com.flowit.tabfragment.FragmentInbox;
import b12app.vyom.com.flowit.tabfragment.FragmentProject;
import b12app.vyom.com.flowit.tabfragment.FragmentSubTask;
import b12app.vyom.com.flowit.tabfragment.FragmentTask;
import b12app.vyom.com.flowit.tabfragment.project.ProjectFgtPresenter;
import b12app.vyom.com.flowit.tabfragment.subtask.SubTaskFragmentPresenter;
import b12app.vyom.com.flowit.tabfragment.task.TaskFgtPresenter;
import b12app.vyom.com.flowit.task.TaskCreateActivity;
import b12app.vyom.com.utils.ActivityUtil;
import b12app.vyom.com.utils.SpHelper;
import butterknife.BindView;
import butterknife.ButterKnife;

import static b12app.vyom.com.flowit.home.Global.FLAG_MANAGER;
import static b12app.vyom.com.flowit.home.Global.MANAGER;
import static b12app.vyom.com.flowit.home.Global.USER;
import static b12app.vyom.com.flowit.home.Global.USER_ID;

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, RapidFloatingActionContentLabelList.OnRapidFloatingActionContentLabelListListener {
    public static final String INBOX = "Inbox";
    public static final String TASK = "Task";
    public static final String SUB_TASK = "SubTask";
    public static final String INBOX_FGT = "inboxFgt";
    public static final String TASK_FGT = "taskFgt";
    public static final String PROJECT_FGT = "projectFgt";
    public static final String TASK_FGT1 = "taskFgt";
    public static final String STATEMENT_WITH_EMPTY_BODY = "StatementWithEmptyBody";
    public static final String PROJECT = "Project";
    public static final String BROWSE_FGT = "browseFgt";
    public static final String DASH_FGT = "dashFgt";

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

    @Inject
    SharedPreferences sharedPreferences;

    private TextView userNameTv, titleTv;

    private User user;

    //presenter
    private ProjectFgtPresenter projectFgtPresenter;
    private TaskFgtPresenter taskFgtPresenter;
    private SubTaskFragmentPresenter subTaskFgtPresenter;

    private RapidFloatingActionHelper rfabHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        //refresh token
        FlowItInstanceIdService.getInstance().onTokenRefresh();

        initDraw(toolbar);

        initBottomNavView();
    }


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        appComponent.inject(this);
    }

    private void initBottomNavView() {

        if (SpHelper.getUserType(sharedPreferences).equals(MANAGER)) {
            botNavView.addTab(botNavView.newTab().setIcon(R.drawable.ic_testing).setText(INBOX));
            botNavView.addTab(botNavView.newTab().setIcon(R.drawable.ic_tab_project).setText(PROJECT));
            botNavView.addTab(botNavView.newTab().setIcon(R.drawable.ic_tab_task).setText(TASK));
            botNavView.addTab(botNavView.newTab().setIcon(R.drawable.ic_tab_subtask).setText(SUB_TASK));

            //default tab selected
            setDefaultTab();

            botNavView.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    switch (tab.getPosition()) {
                        case 0:
                            ActivityUtil.addFragmentToActivity(R.id.fl_float_container, getSupportFragmentManager(), new FragmentInbox(), INBOX_FGT);
                            showMainFloatBtn();
                            break;
                        case 1:
                            //fragment
                            FragmentProject fragmentProject = new FragmentProject();
                            //presenter
                            projectFgtPresenter = new ProjectFgtPresenter(mDataManager, fragmentProject);
                            //add fragment
                            ActivityUtil.addFragmentToActivity(R.id.fl_float_container, getSupportFragmentManager(), fragmentProject, BROWSE_FGT);
                            showMainFloatBtn();
                            break;
                        case 2:
                            //fragment
                            FragmentTask fragmentTask = new FragmentTask();
                            //presenter
                            taskFgtPresenter = new TaskFgtPresenter(mDataManager, fragmentTask);
                            ActivityUtil.addFragmentToActivity(R.id.fl_float_container, getSupportFragmentManager(), fragmentTask, TASK_FGT);
                            showMainFloatBtn();
                            break;
                        case 3:
                            FragmentSubTask fragmentSubTask = new FragmentSubTask();
                            subTaskFgtPresenter = new SubTaskFragmentPresenter(mDataManager, fragmentSubTask);
                            ActivityUtil.addFragmentToActivity(R.id.fl_float_container, getSupportFragmentManager(), fragmentSubTask, "dashFgt");
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

            initFloat();

        } else {
            dismissMainFloatBtn();

            botNavView.addTab(botNavView.newTab().setIcon(R.drawable.ic_testing).setText(INBOX));
            botNavView.addTab(botNavView.newTab().setIcon(R.drawable.ic_tab_task).setText(TASK));
            botNavView.addTab(botNavView.newTab().setIcon(R.drawable.ic_tab_subtask).setText(SUB_TASK));

            //default tab selected
            setDefaultTab();

            botNavView.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {

                    switch (tab.getPosition()) {
                        case 0:
                            ActivityUtil.addFragmentToActivity(R.id.fl_float_container, getSupportFragmentManager(), new FragmentInbox(), INBOX_FGT);
                            break;
                        case 1:
                            //fragment
                            FragmentTask fragmentTask = new FragmentTask();
                            //presenter
                            taskFgtPresenter = new TaskFgtPresenter(mDataManager, fragmentTask);
                            ActivityUtil.addFragmentToActivity(R.id.fl_float_container, getSupportFragmentManager(), fragmentTask, TASK_FGT);
                            break;
                        case 2:
                            FragmentSubTask fragmentSubTask = new FragmentSubTask();
                            subTaskFgtPresenter = new SubTaskFragmentPresenter(mDataManager, fragmentSubTask);
                            ActivityUtil.addFragmentToActivity(R.id.fl_float_container, getSupportFragmentManager(), fragmentSubTask, DASH_FGT);
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

    }

    private void setDefaultTab() {
        if (SpHelper.getUserType(sharedPreferences).equals(MANAGER)) {
            botNavView.getTabAt(1).select();
            //fragment
            FragmentProject fragmentProject = new FragmentProject();
            //presenter
            projectFgtPresenter = new ProjectFgtPresenter(mDataManager, fragmentProject);
            ActivityUtil.addFragmentToActivity(R.id.fl_float_container, getSupportFragmentManager(), fragmentProject, PROJECT_FGT);
        } else {
            botNavView.getTabAt(1).select();
            //fragment
            FragmentTask fragmentTask = new FragmentTask();
            //presenter
            taskFgtPresenter = new TaskFgtPresenter(mDataManager, fragmentTask);
            ActivityUtil.addFragmentToActivity(R.id.fl_float_container, getSupportFragmentManager(), fragmentTask, TASK_FGT1);
        }

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
                .setLabel(Global.NEW_SUB_TASK)
                .setResId(R.drawable.ic_tab_subtask)
                .setIconNormalColor(0xffd84315)
                .setIconPressedColor(0xffbf360c)
                .setWrapper(2)
        );


        rfaContent
                .setItems(items)
                .setIconShadowRadius(RFABTextUtil.dip2px(this, 5))
                .setIconShadowColor(0xff888888)
                .setIconShadowDy(RFABTextUtil.dip2px(this, 5));

        rfabHelper = new RapidFloatingActionHelper(
                this,
                rfaLayout,
                rfaButton,
                rfaContent
        ).build();
    }

    private void initDraw(Toolbar toolbar) {
        user = SpHelper.getUserInfo(sharedPreferences);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // header
        View header = leftDrawer.getHeaderView(0);
        userNameTv = header.findViewById(R.id.header_name);
        titleTv = header.findViewById(R.id.tv_title);

        userNameTv.setText(user.getUserfirstname() + " " + user.getUserlastname());
        if (!user.getUserid().equals(FLAG_MANAGER)) {
            titleTv.setText(USER);
            SpHelper.saveUserType(sharedPreferences, USER);
        } else {
            titleTv.setText(MANAGER);
            SpHelper.saveUserType(sharedPreferences, MANAGER);
        }

        leftDrawer.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            System.exit(0);
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

    @SuppressWarnings(STATEMENT_WITH_EMPTY_BODY)
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_logout:
                SpHelper.clearUserInfo(sharedPreferences);
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();
                break;

            case R.id.nav_about_us:
                AbousUsFragment abousUsFragment = new AbousUsFragment();
                FragmentManager  fragmentManager = getSupportFragmentManager();
                ActivityUtil.addFragmentToActivity(R.id.fl_float_container,fragmentManager,abousUsFragment, "technologyFgt");
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onRFACItemLabelClick(int position, RFACLabelItem item) {
        if (position == 0) {
            startActivity(new Intent(HomeActivity.this, ProjectCreateActivity.class));
            finish();
        } else if (position == 1) {
            startActivity(new Intent(HomeActivity.this, TaskCreateActivity.class));
            finish();

        } else if (position == 2) {
            startActivity(new Intent(HomeActivity.this, SubTaskCreateActivity.class));
            finish();

        }
        rfabHelper.toggleContent();
    }

    @Override
    public void onRFACItemIconClick(int position, RFACLabelItem item) {
        if (position == 0) {
            startActivity(new Intent(HomeActivity.this, ProjectCreateActivity.class));
        } else if (position == 1) {
            startActivity(new Intent(HomeActivity.this, TaskCreateActivity.class));

        } else if (position == 2) {
            startActivity(new Intent(HomeActivity.this, SubTaskCreateActivity.class));
        }
        rfabHelper.toggleContent();
    }

    public void dismissMainFloatBtn() {
        rfaButton.setVisibility(View.GONE);
    }

    public void showMainFloatBtn() {
        rfaButton.setVisibility(View.VISIBLE);
    }


}
