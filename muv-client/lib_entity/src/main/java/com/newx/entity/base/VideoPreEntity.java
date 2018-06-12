package com.newx.entity.base;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xuzhijian on 2018/4/11 0011.
 * 视频预览
 */

public class VideoPreEntity implements EntityType, Parcelable {

    private String title;

    private String type;

    private String likeNum;

    private String ownerIcon;

    private String ownerName;

    private String previewUrl; //预览图 -> 生成预览图片的时候就考虑下图片太小的情况

    private String videoId;

    public VideoPreEntity() {
    }

    public static final Creator<VideoPreEntity> CREATOR = new Creator<VideoPreEntity>() {
        @Override
        public VideoPreEntity createFromParcel(Parcel in) {
            return new VideoPreEntity(in);
        }

        @Override
        public VideoPreEntity[] newArray(int size) {
            return new VideoPreEntity[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(String likeNum) {
        this.likeNum = likeNum;
    }

    public String getOwnerIcon() {
        return ownerIcon;
    }

    public void setOwnerIcon(String ownerIcon) {
        this.ownerIcon = ownerIcon;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.type);
        dest.writeString(this.likeNum);
        dest.writeString(this.ownerIcon);
        dest.writeString(this.ownerName);
        dest.writeString(this.previewUrl);
        dest.writeString(this.videoId);
    }

    protected VideoPreEntity(Parcel in) {
        this.title = in.readString();
        this.type = in.readString();
        this.likeNum = in.readString();
        this.ownerIcon = in.readString();
        this.ownerName = in.readString();
        this.previewUrl = in.readString();
        this.videoId = in.readString();
    }

    @Override
    public int getEntityType() {
        return VideoPreEntity;
    }
}
