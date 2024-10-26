package com.miaoyu.naigos.userPermi;

import org.springframework.stereotype.Service;

@Service
public class UserPermi {
    private int permissions;

    public void setPermissions(int permissions) {
        this.permissions = permissions;
    }

    public boolean hasPermission(int permission) {
        return (this.permissions & permission) != 0;
    }
    // 打印用户的权限（二进制表示）
    public void printPermissions() {
        StringBuilder binaryString = new StringBuilder(Integer.toBinaryString(this.permissions));
        // 补齐到最高权限位数的长度，例如这里补齐到4位（假设最多4个权限）
        while (binaryString.length() < 4) {
            binaryString.insert(0, "0");
        }
        System.out.println("Permissions: " + binaryString);
    }
}
