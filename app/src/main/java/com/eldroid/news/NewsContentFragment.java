package com.eldroid.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NewsContentFragment extends Fragment {

    private TextView contentTextView;

    // Single-parameter newInstance method
    public static NewsContentFragment newInstance(String newsContent) {
        NewsContentFragment fragment = new NewsContentFragment();
        Bundle args = new Bundle();
        args.putString("news_content", newsContent);  // Pass news content to the fragment
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        contentTextView = view.findViewById(R.id.news_content);

        // Set content if available
        if (getArguments() != null) {
            String content = getArguments().getString("news_content");
            if (content != null) {
                updateContent(content);
            }
        }
    }

    // Method to update the content dynamically
    public void updateContent(String newsContent) {
        if (contentTextView != null) {
            contentTextView.setText(newsContent);
        }
    }
}
