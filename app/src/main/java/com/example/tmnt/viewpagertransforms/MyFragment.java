package com.example.tmnt.viewpagertransforms;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by tmnt on 2016/3/26.
 */
public class MyFragment extends Fragment {
    private static final String EXTRA_POSITION = "EXTRA_POSITION";
    @InjectView(R.id.text)
    TextView text;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int[] color = new int[]{0xFF33B5E5, 0xFFAA66CC, 0xFF99CC00, 0xFFFFBB33, 0xFFFF4444};
        View view = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.fragment_lay, container, false);
        ButterKnife.inject(this, view);
        int position = getArguments().getInt(EXTRA_POSITION);
        text.setText(Integer.toString(position));
        text.setBackgroundColor(color[position]);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    public static Fragment getFragment(int position) {
        MyFragment myFragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_POSITION, position);
        myFragment.setArguments(bundle);
        return myFragment;
    }
}
