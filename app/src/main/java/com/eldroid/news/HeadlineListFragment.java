package com.eldroid.news;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.eldroid.news.R;

public class HeadlineListFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    // Interface to communicate with the parent activity
    private OnHeadlineSelectedListener callback;

    public interface OnHeadlineSelectedListener {
        void onHeadlineSelected(String newsContent);
    }

    public HeadlineListFragment() {
        // Required empty public constructor
    }

    public static HeadlineListFragment newInstance(String param1, String param2) {
        HeadlineListFragment fragment = new HeadlineListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            callback = (OnHeadlineSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_headline_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Find the ListView in the fragment layout
        ListView listView = view.findViewById(R.id.list);

        // Sample headlines and corresponding full content
        String[] headlines = {"News 1", "News 2", "News 3", "News 4"};
        final String[] newsContents = {
                "Full content of News 1",
                "Full content of News 2",
                "Full content of News 3",
                "Full content of News 4"
        };

        // Use the custom adapter to bind data to the custom layout
        CustomListAdapter adapter = new CustomListAdapter(requireContext(), headlines);

        // Set the adapter on the ListView
        listView.setAdapter(adapter);

        // Handle item clicks
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Notify the parent activity of the selected item
                callback.onHeadlineSelected(newsContents[position]);
            }
        });
    }

    // Custom adapter class to display news headlines in the custom layout
    public class CustomListAdapter extends ArrayAdapter<String> {

        private final Context context;
        private final String[] headlines;

        public CustomListAdapter(Context context, String[] headlines) {
            // Use 0 for the resource since we'll inflate it manually
            super(context, 0, headlines);
            this.context = context;
            this.headlines = headlines;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            // Inflate the custom layout if it's not recycled
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.news_list, parent, false);
            }

            // Get the TextView from the custom layout and set the headline
            TextView headlineTextView = convertView.findViewById(R.id.tvList);
            headlineTextView.setText(headlines[position]);

            return convertView;
        }
    }
}

