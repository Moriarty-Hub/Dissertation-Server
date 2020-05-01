package com.controller;

import com.entity.Target;
import com.mapper.DepartmentMapper;
import com.mapper.JobTitleMapper;
import com.service.TargetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ManageTargetController {

    private final TargetService targetService;
    private final DepartmentMapper departmentMapper;
    private final JobTitleMapper jobTitleMapper;

    public ManageTargetController(TargetService targetService, DepartmentMapper departmentMapper, JobTitleMapper jobTitleMapper) {
        this.targetService = targetService;
        this.departmentMapper = departmentMapper;
        this.jobTitleMapper = jobTitleMapper;
    }

    @GetMapping("/manage-target")
    public String showManageTargetPage(Model model) {
        model.addAttribute("hostTargetList", targetService.getHostTargetList());
        model.addAttribute("urlTargetList", targetService.getUrlTargetList());
        return "manage-target";
    }

    @GetMapping("/edit-target")
    public String showEditTargetPage(@RequestParam String id, Model model) {
        Target target = targetService.getTargetById(id);
        model.addAttribute("target", target);
        model.addAttribute("departmentList", departmentMapper.selectAllDepartmentName());
        model.addAttribute("jobTitleList", jobTitleMapper.selectAllJobTitle());
        return "edit-target";
    }

    @PostMapping("/update-target")
    public String updateTarget(@RequestParam String id, @RequestParam String target, @RequestParam String targetType,
                               @RequestParam String department, @RequestParam String owner) {
        targetService.updateTarget(id, target, targetType, department, owner);

        return "redirect:/edit-target" + "?id=" + id;
    }
}
