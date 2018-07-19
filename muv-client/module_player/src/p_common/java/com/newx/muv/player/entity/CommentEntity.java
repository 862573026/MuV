package com.newx.muv.player.entity;


import java.util.List;

/**
 * Created by xuzhijian on 2018/4/19 0019.
 * 评论
 */

public class CommentEntity {

    private String uId; //用户ID

    private String uIcon; // 用户Icon

    private String uName;

    private String commentId; //评论ID

    private String commentTime;

    private String comment;

    private String likeNum;

    private String dislikeNum;

    private String repliesNum;

    private List<CommentReplayEntity> replies;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getuIcon() {
        return uIcon;
    }

    public void setuIcon(String uIcon) {
        this.uIcon = uIcon;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(String likeNum) {
        this.likeNum = likeNum;
    }

    public String getDislikeNum() {
        return dislikeNum;
    }

    public void setDislikeNum(String dislikeNum) {
        this.dislikeNum = dislikeNum;
    }

    public String getRepliesNum() {
        return repliesNum;
    }

    public void setRepliesNum(String repliesNum) {
        this.repliesNum = repliesNum;
    }

    public List<CommentReplayEntity> getReplies() {
        return replies;
    }

    public void setReplies(List<CommentReplayEntity> replies) {
        this.replies = replies;
    }

}
