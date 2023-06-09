package com.organic.shop.controller;

import com.organic.shop.Dtos.UserChangeDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RequestMapping(path = "/admin/account")
public interface AdminAccountController {
    @GetMapping({"/", ""})
    ModelAndView goManagerAccount();

    @PostMapping({"/block/{id}"})
    ModelAndView blockAccount(@PathVariable(name = "id")Long id);

    @PostMapping("/change/{id}")
    ModelAndView changeAccount(
                               @ModelAttribute UserChangeDto user, @PathVariable(name = "id")Long id);

    @PostMapping("/upload-avatar")
    ModelAndView uploadFile(HttpServletRequest request, @RequestParam(name = "file") MultipartFile file);




}
