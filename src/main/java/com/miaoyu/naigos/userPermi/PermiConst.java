package com.miaoyu.naigos.userPermi;

import org.springframework.stereotype.Service;

@Service
public class PermiConst {
    public static final int USER = 1; // 0000 0001
    public static final int CREATOR = 1 << 1; // 0000 0010
    public static final int DEVELOPER = 1 << 2; // 0000 0100
    public static final int MANAGER = 1 << 3; // 0000 1000
    public static final int ADMIN = 1 << 4; // 0001 0000
    public String toString(int permission){
        return switch (permission){
            case USER -> "用户";
            case CREATOR -> "创作者";
            case DEVELOPER -> "开发者";
            case MANAGER -> "管理者";
            case ADMIN -> "创始者";
            default -> null;
        };
    }
}
