package com.miaoyu.naigos.bot.controller;

import com.miaoyu.naigos.bot.service.BotBindingService;
import com.miaoyu.naigos.utils.NeedTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/bot/binding")
public class BotBindingController {
    @Autowired
    private NeedTokenUtil needTokenUtil;
    @Autowired
    private BotBindingService botBindingService;

    @GetMapping("/get_code")
    public ResponseEntity<Map<String, Object>> botGetBindingCodeControl(
            @RequestHeader("Authorization") String token
    ) {
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
        if ((int) payload.get("code") == 1) {
            return ResponseEntity.ok(payload);
        }
        return ResponseEntity.ok(botBindingService.botGetBindingCode(payload.get("data").toString()));
    }
    @GetMapping("/verify_code")
    public ResponseEntity<Map<String, Object>> botVerifyCodeControl(
            @RequestHeader("Authorization") String token,
            @RequestParam("uuid") String uuid,
            @RequestParam("code") String code
    ) {
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "bot");
        if ((int) payload.get("code") == 1) {
            return ResponseEntity.ok(payload);
        }
        return ResponseEntity.ok(botBindingService.botVerifyCodeService(uuid, code));
    }
}
