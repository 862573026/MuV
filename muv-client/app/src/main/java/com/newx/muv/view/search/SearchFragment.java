package com.newx.muv.view.search;

import com.newx.base.frameworks.route.facade.annotation.Route;
import com.newx.muv.BR;
import com.newx.muv.R;
import com.newx.base.frameworks.support.mvvm.NxMvvMFragment;
import com.newx.muv.databinding.FragmentSearchBinding;
import com.newx.muv.view.route.FRAGMENT;
import com.newx.muv.vm.SearchVM;

/**
 * Created by xuzhijian on 2018/4/13 0013.
 */
@Route(path = FRAGMENT.SearchFragment)
public class SearchFragment extends NxMvvMFragment<FragmentSearchBinding,SearchVM> {

    @Override
    public int initContentView() {
        return R.layout.fragment_search;
    }

    @Override
    public int initVariableId() {
        return BR.searchVM;
    }

    @Override
    public SearchVM initVM() {
        return new SearchVM();
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        _mToolbarHelper.getToolbar().setTitle(R.string.search_text);
    }

    @Override
    public boolean showToolbar() {
        return true;
    }
}
