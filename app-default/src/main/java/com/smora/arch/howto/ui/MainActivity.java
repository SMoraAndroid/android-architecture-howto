package com.smora.arch.howto.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.smora.arch.howto.R;
import com.smora.arch.howto.ui.fragment.BrowseAllFragment;
import com.smora.arch.howto.ui.fragment.FavoritesFragment;
import com.smora.arch.howto.ui.fragment.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private final BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_browse_all:
                    showBrowseAll();
                    break;
                case R.id.action_favorites:
                    showFavorites();
                    break;
                case R.id.action_settings:
                    showSettings();
                    break;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));

        ((BottomNavigationView) findViewById(R.id.main_bottom_navigation)).setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        showBrowseAll();

    }

    private void showBrowseAll() {
        showFragment(BrowseAllFragment.newInstance());
    }

    private void showFavorites() {
        showFragment(FavoritesFragment.newInstance());
    }

    private void showSettings() {
        showFragment(SettingsFragment.newInstance());
    }

    private  void showFragment(final Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }


}
