package com.miaoyu.naigos.utils;

import java.util.UUID;

public class GenerateUUID {
    private UUID uuid;

    public GenerateUUID() {
        setUuid();
    }
    public GenerateUUID(String [] args) {
        setUuid(args);
    }

    public void setUuid() {
        this.uuid = UUID.nameUUIDFromBytes(("" + System.currentTimeMillis()).getBytes());
    }

    public void setUuid(String[] args) {
        long timestamp = System.currentTimeMillis();
        if (args.length == 0){
            this.uuid = UUID.nameUUIDFromBytes(("" + timestamp).getBytes());
        } else {
            StringBuilder values = new StringBuilder();
            for (String arg : args) {
                values.append(arg);
            }
            this.uuid = UUID.nameUUIDFromBytes((values.toString() + timestamp).getBytes());
        }
    }

    public UUID getUuid() {
        return uuid;
    }
}
