package com.newx.camera.vm;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.newx.base.vm.NxBaseVM;
import com.newx.camera.BR;
import com.newx.camera.R;
import com.newx.camera.def.CameraDef;
import com.newx.camera.entity.CameraFilter;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * Created by newx on 18-7-12.
 */

public class CameraFilterVM extends NxBaseVM {

    public ItemBinding<ItemCameraFilterVM> mCameraFilterItem = ItemBinding.of(BR._ItemCameraFilterVM, R.layout.item_camera_filter);

    public ObservableList<ItemCameraFilterVM> mCameraFilterList = new ObservableArrayList<>();


    @Override
    public void onCreate() {
        super.onCreate();

        for (int i = 0; i < CameraDef.EFFECT_CONFIGS.length;i++){
            CameraFilter filter = new CameraFilter();
            filter.setName(CameraDef.EFFECT_CONFIGS[i]);
            filter.setValue(CameraDef.EFFECT_CONFIGS[i]);
            mCameraFilterList.add(new ItemCameraFilterVM(filter));
        }
    }
}
