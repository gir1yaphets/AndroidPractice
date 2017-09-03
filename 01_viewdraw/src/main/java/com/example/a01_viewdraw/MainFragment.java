package com.example.a01_viewdraw;

import android.content.Context;
import android.os.Bundle;

/**
 * Created by pengxiaolve on 17/7/18.
 */

public class MainFragment extends BaseFragment {

    private static final String TAG = "MainFragment";
    public static final String LAYOUT_RES_ID = "LAYOUT_RES_ID";
    public static final String VIEW_CLASS_NAME = "VIEW_CLASS_NAME";

    public static MainFragment newInstance(Context context, Bundle bundle) {
        MainFragment mainFragment = new MainFragment();
        mainFragment.setArguments(bundle);
        mainFragment.mContext = context;
        return mainFragment;
    }

    @Override
    public int getLayoutResId() {
        return getArguments().getInt(LAYOUT_RES_ID);
    }

    @Override
    public String getChildViewClassName() {
        return getArguments().getString(VIEW_CLASS_NAME);
    }
}
