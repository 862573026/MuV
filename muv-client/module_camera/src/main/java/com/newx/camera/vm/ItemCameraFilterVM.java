package com.newx.camera.vm;

import com.newx.base.vm.NxBaseVM;
import com.newx.camera.entity.CameraFilter;

/**
 * Created by newx on 18-7-12.
 */

public class ItemCameraFilterVM extends NxBaseVM {

    public CameraFilter mFilter;

    public ItemCameraFilterVM(CameraFilter filter) {
        mFilter = filter;
    }
}
