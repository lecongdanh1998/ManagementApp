package vn.edu.poly.managermentapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vn.edu.poly.managermentapp.Component.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
