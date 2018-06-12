package com.newx.muv.vm;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;
import android.os.Handler;

import com.newx.base.config.AssetsManager;
import com.newx.base.proxy.ResourceProxy;
import com.newx.base.utils.number.NumberUtil;
import com.newx.entity.CommentsEntity;
import com.newx.entity.VideosEntity;
import com.newx.entity.base.CommentEntity;
import com.newx.entity.base.VideoEntity;
import com.newx.entity.def.DEFAULT;
import com.newx.muv.BR;
import com.newx.muv.R;
import com.newx.muv.base.databinding.command.BindingAction;
import com.newx.muv.base.databinding.command.BindingCommand;
import com.newx.muv.base.page.NSBaseVM;
import com.newx.muv.base.def.Constant;
import com.newx.muv.utils.text.GsonUtil;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

/**
 * Created by newx on 18-4-17.
 * 视频详情 VM
 */

public class VideoDetailVM extends NSBaseVM {

    private int mVideoId = DEFAULT.ID;

    public ObservableList<ItemCommentVM> mHotCommentList = new ObservableArrayList<>();

    public ObservableList<ItemCommentVM> mNewestCommentList = new ObservableArrayList<>();

    public ObservableField<String> mHotComment = new ObservableField<>();

    public ObservableField<String> mNewestComment = new ObservableField<>();

    public VideoEntity mVideoEntity;




    public VideoDetailVM(int videoId) {
        mVideoId = videoId;
    }

    /**
     * 多类型 Item
     */
    public OnItemBind<ItemCommentVM> onItemBind = new OnItemBind<ItemCommentVM>() {
        @Override
        public void onItemBind(ItemBinding itemBinding, int position, ItemCommentVM item) {
            itemBinding.set(BR.commentModel, position == 0 ? R.layout.item_comment_with_follow : R.layout.item_comment);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        requestComments();
    }

    //
    public BindingCommand onPlay = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //播放
        }
    });


    //封装播放的观察者
    public UIChangeObservable uc = new UIChangeObservable();


    private void requestComments() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //模拟数据
                getVideo();
                getComments();
            }
        }, 100);
    }

    public final static int STATUS_PREPARE = 0;
    public final static int STATUS_PLAYING = 2;
    public final static int STATUS_PAUSE = 3;
    public final static int STATUS_STOP = 4;


    public class UIChangeObservable {

        public ObservableInt status = new ObservableInt(-1);

        //播放进度
        public ObservableFloat progress = new ObservableFloat(-1);
    }


    public void getComments() {
        String comments = AssetsManager.getInstance().readFileFromAssets(ResourceProxy.getContext(), Constant.ASSET_TEST_COMMENT);
        CommentsEntity entity = GsonUtil.toEntity(comments, CommentsEntity.class);

        mHotComment.set(entity.hotComment);

        for (CommentEntity comment : entity.hotComments) {
            comment.setLikeNum(NumberUtil.formatNum(comment.getLikeNum(), false).toString());
            comment.setDislikeNum(NumberUtil.formatNum(comment.getDislikeNum(), false).toString());
            mHotCommentList.add(new ItemCommentVM(comment));
        }

        mNewestComment.set(entity.newestComment);

        for (CommentEntity comment : entity.newestComments) {
            comment.setLikeNum(NumberUtil.formatNum(comment.getLikeNum(), false).toString());
            comment.setDislikeNum(NumberUtil.formatNum(comment.getDislikeNum(), false).toString());
            mNewestCommentList.add(new ItemCommentVM(comment));
        }
    }

    private void getVideo() {
        String videos = AssetsManager.getInstance().readFileFromAssets(ResourceProxy.getContext(), Constant.ASSET_TEST_VIDEO);
        VideosEntity entity = GsonUtil.toEntity(videos, VideosEntity.class);

        mVideoEntity = entity.videos.get(mVideoId);
        uc.status.set(STATUS_PREPARE);
    }


}
