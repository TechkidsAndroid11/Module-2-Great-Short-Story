package techkids.vn.greatstory11.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import techkids.vn.greatstory11.R;
import techkids.vn.greatstory11.adapters.ViewPagerAdapter;

public class ReadingActivity extends AppCompatActivity {
    private ViewPager vpReading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);

        vpReading = (ViewPager) findViewById(R.id.vp_reading);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        vpReading.setAdapter(viewPagerAdapter);
    }
}
