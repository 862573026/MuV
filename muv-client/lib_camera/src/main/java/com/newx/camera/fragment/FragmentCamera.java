package com.newx.camera.fragment;


import com.newx.base.frameworks.route.facade.annotation.Route;
import com.newx.base.frameworks.support.fragment.NxFragment;
import com.newx.base.frameworks.support.mvvm.NxBindingFragment;
import com.newx.camera.R;
import com.newx.camera.databinding.FragmentCameraBinding;
import com.newx.muv.view.route.FRAGMENT;

/**
 * Created by xuzhijian on 2018/6/5 0005.
 */
@Route(path = FRAGMENT.FragmentCamera)
public class FragmentCamera extends NxBindingFragment<FragmentCameraBinding> {

    @Override
    public int initContentView() {
        return R.layout.fragment_camera;
    }


}
