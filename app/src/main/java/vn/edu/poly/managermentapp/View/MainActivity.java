package vn.edu.poly.managermentapp.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vn.edu.poly.managermentapp.Component.BaseActivity;
import vn.edu.poly.managermentapp.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
