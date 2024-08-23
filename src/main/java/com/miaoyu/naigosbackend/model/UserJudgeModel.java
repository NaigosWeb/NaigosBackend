package com.miaoyu.naigosbackend.model;

public class UserJudgeModel {
    private String qq_id;
    private String score;
    private String favorite;
    private boolean transfer_archive;

    public String getQq_id() {
        return qq_id;
    }

    public void setQq_id(String qq_id) {
        this.qq_id = qq_id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public boolean isTransfer_archive() {
        return transfer_archive;
    }

    public void setTransfer_archive(boolean transfer_archive) {
        this.transfer_archive = transfer_archive;
    }
}
