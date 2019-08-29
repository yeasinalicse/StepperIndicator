package com.yeasin.stepperindicator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.os.Bundle;
import android.widget.Toast;

import com.badoualy.stepperindicator.StepperIndicator;
import com.yeasin.stepperindicator.fragments.StepOneFragment;
import com.yeasin.stepperindicator.fragments.StepThreeFragment;
import com.yeasin.stepperindicator.fragments.StepTwoFragment;

public class MainActivity extends AppCompatActivity implements StepOneFragment.OnStepOneListener, StepTwoFragment.OnStepTwoListener, StepThreeFragment.OnStepThreeListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private CharSequence[] labels;

    private NonSwipeableViewPager mViewPager;

    private StepperIndicator stepperIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Steper");
        labels = new CharSequence[]{"Setp 1", "Setp 2", "Setp 3"};
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        stepperIndicator = findViewById(R.id.stepperIndicator);


        stepperIndicator.showLabels(true);
        stepperIndicator.setLabels(labels);
        stepperIndicator.setViewPager(mViewPager);
        // or keep last page as "end page"
        stepperIndicator.setViewPager(mViewPager, mViewPager.getAdapter().getCount() - 1); //

        /*// or manual change
        indicator.setStepCount(3);
        indicator.setCurrentStep(2);
*/

    }





    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    return StepOneFragment.newInstance("", "");
                case 1:
                    return StepTwoFragment.newInstance("", "");
                case 2:
                    return StepThreeFragment.newInstance("", "");
            }

            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "First Level";
                case 1:
                    return "Second Level";
                case 2:
                    return "Finish";
            }
            return null;
        }
    }


    @Override
    public void onNextPressed(Fragment fragment) {
        if (fragment instanceof StepOneFragment) {
            mViewPager.setCurrentItem(1, true);
        } else if (fragment instanceof StepTwoFragment) {
            mViewPager.setCurrentItem(2, true);
        } else if (fragment instanceof StepThreeFragment) {
            Toast.makeText(this, "Thanks For Registering", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void onBackPressed(Fragment fragment) {
        if (fragment instanceof StepTwoFragment) {
            mViewPager.setCurrentItem(0, true);
        } else if (fragment instanceof StepThreeFragment) {
            mViewPager.setCurrentItem(1, true);
        }
    }
}
