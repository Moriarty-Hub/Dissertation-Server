package com.controller;

import com.entity.User;
import com.service.ModifyPasswordService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Optional;

@Controller
public class ModifyPasswordController {

    private final ModifyPasswordService modifyPasswordService;

    public ModifyPasswordController(ModifyPasswordService modifyPasswordService) {
        this.modifyPasswordService = modifyPasswordService;
    }

    @GetMapping("/modify-password")
    public String showModifyPasswordPage() {
        return "modify-password";
    }

    @PostMapping("/modify-password")
    public String modifyPassword(@RequestParam String oldPassword, @RequestParam String newPassword, @RequestParam String confirmNewPassword, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String id = user.getId();
        Map<Boolean, Optional<String>> returnValue = modifyPasswordService.modifyPassword(id, oldPassword, newPassword, confirmNewPassword);
        if (returnValue.containsKey(true)) {
            model.addAttribute("hasInfo", true);
            model.addAttribute("info", "Your password has updated.");
            return "redirect:index";
        } else {
            returnValue.get(false).ifPresent(info -> model.addAttribute("info", info));
            return "modify-password";
        }
    }

    @PostMapping("/set-password")
    public String setPassword(@RequestParam String password, @RequestParam String confirmPassword, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String id = user.getId();
        Map<Boolean, Optional<String>> returnValue = modifyPasswordService.setPassword(id, password, confirmPassword);
        if (returnValue.containsKey(true)) {
            return "redirect:index";
        } else {
            returnValue.get(false).ifPresent(info -> model.addAttribute("info", info));
            return "set-password";
        }
    }
}
