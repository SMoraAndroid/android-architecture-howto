package com.smora.arch.howto.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
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

    private AppBarLayout appBarLayout;
    private BrowseAllFragment browseAllFragment;
    private FavoritesFragment favoritesFragment;
    private SettingsFragment settingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);

        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));

        ((BottomNavigationView) findViewById(R.id.main_bottom_navigation)).setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        showBrowseAll();

    }

    private void showBrowseAll() {
        appBarLayout.setExpanded(true, false);
        if (browseAllFragment == null) {
            browseAllFragment = BrowseAllFragment.newInstance();
        }
        showFragment(browseAllFragment);
    }

    private void showFavorites() {
        appBarLayout.setExpanded(true, false);
        if (favoritesFragment == null) {
            favoritesFragment = FavoritesFragment.newInstance();
        }
        showFragment(favoritesFragment);
    }

    private void showSettings() {
        appBarLayout.setExpanded(true, false);
        if (settingsFragment == null) {
            settingsFragment = SettingsFragment.newInstance();
        }
        showFragment(settingsFragment);
    }

    private  void showFragment(final Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }


}
