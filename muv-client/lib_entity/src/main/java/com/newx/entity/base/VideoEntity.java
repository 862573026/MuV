package com.newx.entity.base;

/**
 * Created by newx on 18-4-19.
 */

public class VideoEntity implements EntityType {

    private String title;

    private String videoUrl;

    private String coverUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    @Override
    public int getEntityType() {
        return 0;
    }
}
