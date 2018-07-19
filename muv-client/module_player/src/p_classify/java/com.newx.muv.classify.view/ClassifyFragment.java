package com.newx.muv.classify.view;


import com.newx.base.route.RP;
import com.newx.common.frameworks.route.facade.annotation.Route;
import com.newx.common.frameworks.support.mvvm.NxBindingFragment;
import com.newx.player.R;
import com.newx.player.databinding.FragmentClassifyBinding;

/**
 * Created by xuzhijian on 2018/4/8 0008.
 */
@Route(path = RP.ClassifyFragment)
public class ClassifyFragment extends NxBindingFragment<FragmentClassifyBinding> {

    @Override
    public int initContentView() {
        return R.layout.fragment_classify;
    }

}
