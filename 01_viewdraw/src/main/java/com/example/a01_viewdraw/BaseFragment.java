package com.example.a01_viewdraw;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by pengxiaolve on 17/7/23.
 */

public abstract class BaseFragment extends Fragment {

    protected Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        ViewGroup containerView = (ViewGroup) view.findViewById(R.id.container);
        String className = getChildViewClassName();
        try {
            Class viewClass = Class.forName(className);
            Constructor<View> constructor = viewClass.getDeclaredConstructor(Context.class);
            constructor.setAccessible(true);
            View childView = constructor.newInstance(mContext);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                    .LayoutParams.MATCH_PARENT);
            childView.setLayoutParams(params);
            containerView.addView(childView);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return view;
    }

    public abstract int getLayoutResId();

    public abstract String getChildViewClassName();
}
