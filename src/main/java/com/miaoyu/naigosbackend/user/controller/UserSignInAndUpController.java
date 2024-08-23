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
        if (loginType.equals("normal") && !password.isEmpty() && !account.isEmpty()) {
            int intUniqueId = 0;
            String dbPwd;
            try {
                intUniqueId = Integer.parseInt(account);
            } catch (NumberFormatException e) {
                if (account.contains("@")) {
                    dbPwd = userSignInAndUpService.isUserInDatabase(1, account);
                }
            }
            dbPwd = userSignInAndUpService.isUserInDatabase(intUniqueId);
            if (!dbPwd.equals(password)) {

            }
            return "";
        }
        return "";
    }
}
