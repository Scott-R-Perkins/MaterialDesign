package com.scottperkins.materialdesign;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    public void onFabClick(View view) {
        CharSequence text = "Item completed";
        int duration = Snackbar.LENGTH_SHORT;
        Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinator), text, duration);
        snackbar.setAction(("Add"), view1 -> {
            snackbar.dismiss();
            Toast toast = Toast.makeText(MainActivity.this, "Added", Toast.LENGTH_LONG);
            toast.show();
        });
        snackbar.show();
    }

    private static class SectionsPagerAdapter extends FragmentStateAdapter {
        public SectionsPagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Nullable
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new TopFragment();
                case 1:
                    return new AttractionFragment();
                case 2:
                    return new RestaurantFragment();
                case 3:
                    return new HotelFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getItemCount() {
            return 4;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);


        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this);
        ViewPager2 viewPager2 = findViewById(R.id.view_pager);
        viewPager2.setAdapter(sectionsPagerAdapter);


        TabLayout tabLayout = findViewById(R.id.tabs);
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setIcon(R.drawable.baseline_home_24);
                    break;
                case 1:
                    tab.setIcon(R.drawable.baseline_attractions_24);
                    break;
                case 2:
                    tab.setIcon(R.drawable.baseline_food_bank_24);
                    break;
                case 3:
                    tab.setIcon(R.drawable.baseline_hotel_24);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + position);
            }
        }).attach();
    }
}