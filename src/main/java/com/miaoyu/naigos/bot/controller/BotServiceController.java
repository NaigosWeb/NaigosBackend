package com.miaoyu.naigos.bot.controller;

import com.miaoyu.naigos.bot.service.BotServiceService;
import com.miaoyu.naigos.utils.NeedTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/bot/service")
public class BotServiceController {
    @Autowired
    private NeedTokenUtil needTokenUtil;
    @Autowired
    private BotServiceService botServiceService;

    @GetMapping("/register")
    public ResponseEntity<Map<String, Object>> botServiceRegisterControl(
            @RequestHeader("Authorization") String token,
            @RequestParam("uuid") String uuid,
            @RequestParam("qq_id") long qqId
    ) {
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "bot");
        if ((int) payload.get("code") == 1) {
            return ResponseEntity.ok(payload);
        }
        return ResponseEntity.ok(botServiceService.botServiceRegisterService(uuid, qqId));
    }

    @GetMapping("/check_in")
    public ResponseEntity<Map<String, Object>> botServiceCheckInControl(
            @RequestHeader("Authorization") String token,
            @RequestParam("uuid") String uuid
    ) {
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "bot");
        if ((int) payload.get("code") == 1) {
            return ResponseEntity.ok(payload);
        }
        return ResponseEntity.ok(botServiceService.botServiceCheckInService(uuid));
    }
    @GetMapping("/current")
    public ResponseEntity<Map<String, Object>> botServiceCurrentControl(
            @RequestHeader("Authorization") String token,
            @RequestParam("uuid") String uuid
    ) {
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "bot");
        if ((int) payload.get("code") == 1) {
            return ResponseEntity.ok(payload);
        }
        return ResponseEntity.ok(botServiceService.botServiceCurrentService(uuid));
    }
}
