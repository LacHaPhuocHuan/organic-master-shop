package com.organic.shop.restImpl;

import com.organic.shop.service.MenuService;
import com.organic.shop.utils.MenuAccordingRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;

public class BaseAdminHomeRest{
    protected ModelAndView _ModelAndView=new ModelAndView();

    @Autowired
    MenuService menuService;
    @PostConstruct
    public void initAdminHome(){
        _ModelAndView.addObject("menu", menuService.getMenu(MenuAccordingRole.ADMIN));
    }
}
