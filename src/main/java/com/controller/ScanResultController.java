package com.controller;

import com.service.ScanResultService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScanResultController {

    private final ScanResultService scanResultService;

    public ScanResultController(ScanResultService scanResultService) {
        this.scanResultService = scanResultService;
    }

    @GetMapping("/history-result")
    public String showHistoryResultPage(Model model) {
        model.addAttribute("historyScanResultList", scanResultService.getHistoryScanResultList());
        return "history-result";
    }
}
