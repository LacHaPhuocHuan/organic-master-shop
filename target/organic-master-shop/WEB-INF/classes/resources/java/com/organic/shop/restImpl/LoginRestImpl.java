package com.organic.shop.restImpl;

import com.organic.shop.rest.LoginRest;
import com.organic.shop.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class LoginRestImpl extends BaseUserRest implements LoginRest {
    @Autowired
    LoginService loginService;
    @Override
    public ModelAndView goLogin() {
        _ModelAndView.setViewName("/user/login");
        return _ModelAndView;
    }

    @Override
    public ModelAndView login(String username, String password) {
        System.out.println("login username= "+username+" password="+ password);
        try {
            loginService.signIn(username, password);
            _ModelAndView.setView(new RedirectView("/api/u/home"));
            _ModelAndView.addObject("message", "Huan");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return _ModelAndView;
    }

    @Override
    public ModelAndView goHome() {
        System.out.println("Go Home!");
        _ModelAndView.setViewName("/user/index");
        return _ModelAndView;
    }

    @Override
    public ModelAndView logout() {
//        _ModelAndView.setView(new RedirectView("/api/v/auth/login"));
        _ModelAndView.setViewName("/user/login");
        return _ModelAndView;
    }
}
