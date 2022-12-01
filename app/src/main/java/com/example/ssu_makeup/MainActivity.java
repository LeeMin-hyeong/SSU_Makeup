package com.example.ssu_makeup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ssu_makeup.main_fragment.MainHomeFragment;
import com.example.ssu_makeup.main_fragment.MainProfileFragment;
import com.example.ssu_makeup.main_fragment.MainSearchFragment;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends FragmentActivity {
    Fragment home, search, profile;
    double initTime;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        home = new MainHomeFragment();
        search = new MainSearchFragment();
        profile = new MainProfileFragment();

        fragmentManager = getSupportFragmentManager();

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, search).hide(search).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, profile).hide(profile).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, home).commit();

        NavigationBarView navigationBarView = findViewById(R.id.bottom_navigation);
        navigationBarView.getMenu().findItem(R.id.home_button).setChecked(true);

        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);

                int id = item.getItemId();
                if (id == R.id.home_button) {
                    if(search.isVisible())
                        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                    else if(profile.isVisible())
                        fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
                    fragmentTransaction.show(home).hide(search).hide(profile).commit();
                    return true;
                } else if (id == R.id.search_button) {
                    fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
                    fragmentTransaction.hide(home).show(search).hide(profile).commit();
                    return true;
                } else {
                    fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                    fragmentTransaction.hide(home).hide(search).show(profile).commit();
                    return true;
                }
            }
        });

    }


    @Override
    public boolean onKeyDown ( int keyCode, KeyEvent event){
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - initTime > 3000) {
                Toast.makeText(this, "종료하려면 한번 더 누르세요.", Toast.LENGTH_SHORT).show();
                initTime = System.currentTimeMillis();
            } else {
                finish();
            }
        }
        return false;
    }
}

