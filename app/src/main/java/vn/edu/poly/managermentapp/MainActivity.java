package vn.edu.poly.managermentapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.edu.poly.managermentapp.Adapter.MenuAdapter;
import vn.edu.poly.managermentapp.Component.BaseActivity;
<<<<<<< HEAD:app/src/main/java/vn/edu/poly/managermentapp/View/MainActivity.java
import vn.edu.poly.managermentapp.Model.MenuModel;
import vn.edu.poly.managermentapp.R;
import vn.edu.poly.managermentapp.View.Dashboard.Dashboard;
import vn.edu.poly.managermentapp.View.Post.Post;
=======
>>>>>>> e62669240d8b883b7262e41b189cbfdde6db1002:app/src/main/java/vn/edu/poly/managermentapp/MainActivity.java

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private DrawerLayout drawer_layout;
    private NavigationView nav_view;
    private Toolbar toolbar;
    private ListView listview_menu;
    private MenuAdapter menuAdapter;
    private ArrayList<MenuModel> menuModelArrayList;
    private TextView toolbar_title;
    private SharedPreferences dataLogin;
    private SharedPreferences.Editor editor;
    private ImageView img_back_MysiteToobar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEventButton();
        if (savedInstanceState == null) {
            transactionFrangment(new Dashboard(), "Dashboard");
        }
    }

    /*
     * create and register layout
     * */
    private void initView() {
        drawer_layout = findViewById(R.id.drawer_layout);
        nav_view = findViewById(R.id.nav_view);
        toolbar_title = findViewById(R.id.txt_name_MySiteToobar);
        listview_menu = findViewById(R.id.listview_menu);
        img_back_MysiteToobar = findViewById(R.id.img_back_MysiteToobar);
        img_back_MysiteToobar.setImageResource(R.drawable.ic_menu_white);
    }

    /*
     * add event for button
     * list view
     * ...
     * */
    private void initEventButton() {
        listview_menu.setOnItemClickListener(this);
        img_back_MysiteToobar.setOnClickListener(this);
    }

    /*
     * init data for layout
     * */
    private void initData() {
        dataLogin = getSharedPreferences("dataLogin", MODE_PRIVATE);
        editor = dataLogin.edit();
        /*
         * create menu arraylist
         * */
        menuModelArrayList = new ArrayList<>();
        menuModelArrayList.add(new MenuModel("Dashboard"));
        menuModelArrayList.add(new MenuModel("Notifications"));
        menuModelArrayList.add(new MenuModel("Post"));
        menuModelArrayList.add(new MenuModel("Gallery"));
        menuModelArrayList.add(new MenuModel("Contact"));
        /*
         * create menu adapter
         * */
        menuAdapter = new MenuAdapter(this, menuModelArrayList, R.layout.row_menu);
        listview_menu.setAdapter(menuAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
    /*
     * drawer menu open when click ic_menu
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*
     * item menu onclick change fragment
     * */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new Dashboard();
                transactionFrangment(fragment, "Dashboard");
                break;
            case 1:
                break;
            case 2:
                fragment = new Post();
                transactionFrangment(fragment, "Post");
                break;
            case 3:

                break;
            case 5:
                break;
            case 7:
                break;
            default:
                break;
        }
    }

    /*
     * method change fragment
     * */
    public void transactionFrangment(Fragment f, String s) {
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                f).commit();
        toolbar_title.setText(s);
        drawer_layout.closeDrawers();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.img_back_MysiteToobar:
                drawer_layout.openDrawer(GravityCompat.START);
                break;
        }
    }
}
