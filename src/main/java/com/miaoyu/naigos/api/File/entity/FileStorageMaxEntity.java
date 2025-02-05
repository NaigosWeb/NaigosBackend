package com.miaoyu.naigos.api.File.entity;

public class FileStorageMaxEntity {
    private String uuid;
    private long max_storage;
    private long temp_storage;
    private long temp_storage_exp;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getMax_storage() {
        return max_storage;
    }

    public void setMax_storage(long max_storage) {
        this.max_storage = max_storage;
    }

    public long getTemp_storage() {
        return temp_storage;
    }

    public void setTemp_storage(long temp_storage) {
        this.temp_storage = temp_storage;
    }

    public long getTemp_storage_exp() {
        return temp_storage_exp;
    }

    public void setTemp_storage_exp(long temp_storage_exp) {
        this.temp_storage_exp = temp_storage_exp;
    }
}
