package com.controller;

import com.entity.Role;
import com.entity.User;
import com.service.ScanResultService;
import com.service.TargetService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        model.addAttribute("scanResultList", scanResultService.getLatestScanResult());
        return "index";
    }
}
