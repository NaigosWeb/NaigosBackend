package com.miaoyu.naigosbackend.user.controller;

import com.miaoyu.naigosbackend.user.service.UserSignInAndUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/sign")
public class UserSignInAndUpController {
    @Autowired
    private UserSignInAndUpService userSignInAndUpService;
    @PostMapping("/in")
    public String userSignIn(
            @RequestParam("account") String account,
            @RequestParam("password") String password,
            @RequestParam("login_type") String loginType
    ) {
        boolean isPasswordConsistent = false;
        if (loginType.equals("normal") && !password.isEmpty() && !account.isEmpty()) {
            int intUniqueId;
            try {
                intUniqueId = Integer.parseInt(account);
                isPasswordConsistent = userSignInAndUpService.isUserAndPwdInDatabase(intUniqueId, password);
            } catch (NumberFormatException e) {
                isPasswordConsistent = userSignInAndUpService.isUserAndPwdInDatabase(1, account, password);
            }
            if (isPasswordConsistent){
                return "密码存在并正确！";
            }
            return "密码不存在或不正确！";
        } else if (loginType.equals("nopwd") && !account.isEmpty()) {
            int intUniqueId;
            try {
                intUniqueId = Integer.parseInt(account);
            } catch (NumberFormatException e) {

            }
        }
        return "登录方式不正确！";
    }
}
