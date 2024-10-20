package com.miaoyu.naigos.user.controller;

import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.NormalMap;
import com.miaoyu.naigos.model.UserArchiveModel;
import com.miaoyu.naigos.user.service.GetUserArchiveService;
import com.miaoyu.naigos.user.service.UserSignInAndUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/users/sign")
public class UserSignInAndUpController {
    @Autowired
    private UserSignInAndUpService userSignInAndUpService;
    @Autowired
    private GetUserArchiveService getUserArchiveService;

    /**
    * 登录的控制器层
    * @param account 账号
    * @param password 密码
    * @param loginType 登录方式
    * @param accountType 账号类型
    * @return Map->JSON
    * */
    @PostMapping("/in")
    public Map<String, Object> userSignIn(
            @RequestParam("account") String account,
            @RequestParam("password") String password,
            @RequestParam("login_type") String loginType,
            @RequestParam("account_type") String accountType
    ) {
        if (Objects.equals(loginType, "normal")){
            return userSignInAndUpService.isUserAndPwdInDatabase(accountType, account, password);
        } else if (Objects.equals(loginType, "nopwd")){
            return userSignInAndUpService.findArchiveAndCode(accountType, account);
        }
        return new ErrorMap().errorMap("未知的登录方式");
    }

    /**
    * 确认无密码登录（当用户已经获取的验证码后确认点击了登录）
    * @param account 账号
    * @param code 验证码
     * @param accountType 账号类型
    * @return Map->JSON
    * */
    @PostMapping("/nopwdcl")
    public Map<String, Object> nopwdSignInCheckLogin(
            @RequestParam("account") String account,
            @RequestParam("code") String code,
            @RequestParam("account_type") String accountType
    ){
        return userSignInAndUpService.nopwdSignin(accountType, account, code);
    }
}
