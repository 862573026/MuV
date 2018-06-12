package com.newx.muv.ui.widget.toolbar;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.newx.base.widget.TitleBar;
import com.newx.muv.ui.widget.toolbar.imp.IToolbar;

/**
 * Created by newx on 18-5-26.
 */

public class ToolbarComponent implements IToolbar {

    public TitleBar mToolbar;

    public View mNavDes;
    public View mCenterView;
    public View mRightView;


    public static class Builder {
        private TitleBar mToolbar;

        private View mNavDes;
        private View mCenterView;
        private View mRightView;

        public Builder setToolbar(TitleBar toolbar) {
            mToolbar = toolbar;
            return this;
        }

        public Builder setNavDes(View navDes) {
            mNavDes = navDes;
            return this;
        }

        public Builder setCenterView(View centerView) {
            mCenterView = centerView;
            return this;
        }

        public Builder setRightView(View rightView) {
            mRightView = rightView;
            return this;
        }

        public ToolbarComponent build() {
            ToolbarComponent toolbarComponent =
                    new ToolbarComponent();
            toolbarComponent.mToolbar = mToolbar;
            toolbarComponent.mCenterView = mCenterView;
            toolbarComponent.mNavDes = mNavDes;
            toolbarComponent.mRightView = mRightView;

            return toolbarComponent;
        }
    }
}
