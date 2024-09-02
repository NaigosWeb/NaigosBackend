package com.miaoyu.naigosbackend.user.controller;

import com.miaoyu.naigosbackend.constantsMap.ErrorMap;
import com.miaoyu.naigosbackend.constantsMap.NormalMap;
import com.miaoyu.naigosbackend.model.UserArchiveModel;
import com.miaoyu.naigosbackend.user.service.GetUserArchiveService;
import com.miaoyu.naigosbackend.user.service.UserSignInAndUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/users/sign")
public class UserSignInAndUpController {
    @Autowired
    private UserSignInAndUpService userSignInAndUpService;
    @Autowired
    private GetUserArchiveService getUserArchiveService;
    @PostMapping("/in")
    public Map<String, Object> userSignIn(
            @RequestParam("account") String account,
            @RequestParam("password") String password,
            @RequestParam("login_type") String loginType
    ) {
        String accountType = "unknown";
        if (account.matches("\\d+")) {
            accountType = "id";
        } else if (account.matches(".*@.*")) {
            accountType = "email";
        }
        if (loginType.equals("normal")){
            Map<String, Object> userAndPwdInDatabase = userSignInAndUpService.isUserAndPwdInDatabase(accountType, account, password);
            if (userAndPwdInDatabase != null) {
                return userAndPwdInDatabase;
            } else {
                return new ErrorMap().errorMap("ID或密码不正确！");
            }
        } else if (loginType.equals("nopwd")) {
            String archiveAndCode = userSignInAndUpService.findArchiveAndCode(accountType, account);
            if (archiveAndCode != null) {
                return new NormalMap().normalSuccessMap(archiveAndCode);
            } else {
                return new ErrorMap().errorMap("ID可能有问题！");
            }
        }
        return new ErrorMap().errorMap("登录方式不正确！");
    }

    @PostMapping("/nopwdcl")
    public Map<String, Object> nopwdSignInCheckLogin(
            @RequestParam("account") String account,
            @RequestParam("code") String code
    ){
        UserArchiveModel userArchive = null;
        if (account.matches("\\d+")) {
            userArchive = getUserArchiveService.getUserArchive(Integer.valueOf(account));
        } else if (account.matches(".*@.*")) {
            userArchive = getUserArchiveService.getUserArchive(1, account);
        }
        if (userArchive != null) {
            return userSignInAndUpService.nopwdSignin(userArchive, code);
        }
        return new ErrorMap().errorMap("找不到档案");
    }
}
