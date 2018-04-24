package b12app.vyom.com.flowit.home;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import b12app.vyom.com.flowit.R;
import butterknife.BindView;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
//    @BindView(R.id.bottom_tab_layout)
//    TabLayout botNavView;

//    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

//    @BindView(R.id.fab)
//    FloatingActionButton fab;

//    @BindView(R.id.toolbar)
//    Toolbar toolbar;

    private Integer[] bottomTabIcon = {R.drawable.ic_testing};
//    private Integer[] bottomIconPress = {R.mipmap.home_press,R.mipmap.favorite_press,
//            R.mipmap.friends_press,R.mipmap.account_press};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initFloat();
        initDraw(toolbar);
        initBottomNavView();
    }

    private void initBottomNavView() {
        TabLayout botNavView = findViewById(R.id.bottom_tab_layout);
        botNavView.addTab(botNavView.newTab().setIcon(R.drawable.ic_testing).setText("Inbox"));
        botNavView.addTab(botNavView.newTab().setIcon(R.drawable.ic_testing).setText("Project"));
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
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    private void initDraw(Toolbar toolbar) {
        drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_settings) {
            // Handle the camera action
        } else if (id == R.id.nav_help) {

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
