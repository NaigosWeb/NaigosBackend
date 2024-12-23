package com.miaoyu.naigos.utils;

public class HumanFileSize {
    public String humanFileSize(long fileSize) {
        if (fileSize < 1024) {
            return fileSize + " B";
        } else if (fileSize < 1048576) {
            return String.format("%.1f KB", (double) fileSize / 1024);
        } else if (fileSize < 1073741824) {
            return String.format("%.1f MB", (double) fileSize / 1048576);
        } else {
            return String.format("%.1f GB", (double) fileSize / 1073741824);
        }
    }
}
