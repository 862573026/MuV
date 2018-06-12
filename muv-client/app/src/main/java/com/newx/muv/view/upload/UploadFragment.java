package com.newx.muv.view.upload;

import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.newx.base.frameworks.route.facade.annotation.Route;
import com.newx.base.widget.TitleBar;
import com.newx.muv.R;
import com.newx.muv.BR;
import com.newx.base.frameworks.support.mvvm.NxMvvMFragment;
import com.newx.muv.databinding.FragmentUploadBinding;
import com.newx.muv.view.route.FRAGMENT;
import com.newx.muv.vm.UploadVM;


/**
 * Created by newx on 18-5-20.
 * 上传功能的RootFragment
 */
@Route(path = FRAGMENT.UploadFragment)
public class UploadFragment extends NxMvvMFragment<FragmentUploadBinding, UploadVM> {

    @Override
    public int initVariableId() {
        return BR.uploadVM;
    }

    @Override
    public UploadVM initVM() {
        return new UploadVM(context);
    }

    @Override
    public int initContentView() {
        return R.layout.fragment_upload;
    }


    @Override
    public void initToolbar() {
        _mToolbarHelper.getToolbar()
                .setLeftText(R.string.index_text);
        _mToolbarHelper.getToolbar()
                .setTitle("Upload");
        _mToolbarHelper.getToolbar()
                .addAction(new TitleBar.ImageAction(R.drawable.icon_scan_white) {
                    @Override
                    public void performAction(View view) {
                        mViewModel.scanFile();
                    }
                });

    }

    @Override
    public boolean showToolbar() {
        return true;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        mViewModel.mFinishLoadMore.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                mBinding.layoutRefresh.finishLoadMore();
            }
        });
    }
}
