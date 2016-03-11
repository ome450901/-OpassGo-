package com.willy.smartcityhackathon.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.willy.smartcityhackathon.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Willy on 2016/3/11.
 */
public class BaseAnimationActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.ivLogo)
    ImageView ivLogo;

    private MenuItem menuItem;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        setToolbar();
    }

    private void setToolbar() {
        if(toolbar!=null){
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.mipmap.ic_menu_white);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menuItem = menu.findItem(R.id.action_inbox);
        menuItem.setActionView(R.layout.menu_item_view);
        return true;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public ImageView getIvLogo() {
        return ivLogo;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }
}
