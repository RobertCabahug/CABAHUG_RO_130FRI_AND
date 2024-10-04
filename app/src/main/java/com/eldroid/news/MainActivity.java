package com.eldroid.news;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import com.eldroid.news.HeadlineListFragment;  // Assuming HeadlineListFragment is in this package

public class MainActivity extends AppCompatActivity implements HeadlineListFragment.OnHeadlineSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Only add the fragments if this is the first time the activity is created
        if (savedInstanceState == null) {
            // Add the HeadlineListFragment
            HeadlineListFragment headlineFragment = new HeadlineListFragment(); // Assuming it's in the same package
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.headline_fragment_container, headlineFragment)
                    .commit();

            // If in landscape mode, add the NewsContentFragment as well
            if (findViewById(R.id.content_fragment_container) != null) {
                NewsContentFragment contentFragment = new NewsContentFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_fragment_container, contentFragment)
                        .commit();
            }
        }
    }


    @Override
    public void onHeadlineSelected(String newsContent) {
        // Ensure newsContent is not null or empty
        if (newsContent != null && !newsContent.isEmpty()) {
            if (findViewById(R.id.content_fragment_container) != null) {
                // Update the existing content fragment in landscape mode
                NewsContentFragment contentFragment = (NewsContentFragment)
                        getSupportFragmentManager().findFragmentById(R.id.content_fragment_container);
                if (contentFragment != null) {
                    contentFragment.updateContent(newsContent);
                }
            } else {
                // In portrait mode, navigate to the content fragment
                NewsContentFragment contentFragment = NewsContentFragment.newInstance(newsContent);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.headline_fragment_container, contentFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        } else {
            // Handle the case where newsContent is null or empty
            Log.e("MainActivity", "newsContent is null or empty.");
        }
    }
}