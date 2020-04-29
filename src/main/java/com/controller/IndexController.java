package com.controller;

import com.entity.Role;
import com.entity.User;
import com.service.ScanResultService;
import com.service.TargetService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
public class IndexController {

    private final TargetService targetService;
    private final ScanResultService scanResultService;

    public IndexController(TargetService targetService, ScanResultService scanResultService) {
        this.targetService = targetService;
        this.scanResultService = scanResultService;
    }

    @GetMapping("/index")
    public String showIndexPage(Model model) {
        // For test only
        try {
            String uploadDir= ResourceUtils.getURL("classpath:").getPath();
            System.out.println(uploadDir);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // For test only
        String authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        if (authorities.contains(Role.ADMINISTRATOR.toString())) {
            model.addAttribute("isAdministrator", true);
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.isActive()) {
            model.addAttribute("isActive", false);
            return "set-password";
        }

        model.addAttribute("hasInfo", false);
        model.addAttribute("info", null);
        model.addAttribute("targetList", targetService.getTargetList());
        model.addAttribute("scanResultList", scanResultService.getScanResultList());
        return "index";
    }

    @PostMapping("/delete-target")
    public String deleteTarget(@RequestParam String index) {
        targetService.deleteTarget(index);
        return "redirect:index";
    }

    @PostMapping("/insert-target")
    public String insertTarget(@RequestParam String target, @RequestParam String targetType) {
        targetService.insertTarget(target, targetType);
        return "redirect:index";
    }
}
