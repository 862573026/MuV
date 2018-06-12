package com.newx.camera.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newx.base.frameworks.route.facade.annotation.Route;
import com.newx.base.ui.fragmentation.SupportFragment;
import com.newx.camera.R;
import com.newx.muv.view.route.FRAGMENT;

/**
 * Created by xuzhijian on 2018/6/5 0005.
 */
@Route(path = FRAGMENT.FragmentCamera)
public class FragmentCamera extends SupportFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera, container, false);
        return view;
    }
}
