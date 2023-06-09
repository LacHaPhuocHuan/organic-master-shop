package com.organic.shop.controllerImpl;

import com.organic.shop.controller.ContacController;
import com.organic.shop.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class ContactControllerImpl extends BaseUserController implements ContacController {
    @Autowired
    ContactService contactService;
    @Override
    public ModelAndView goContact(String message) {
        _ModelAndView.addObject("userCurrent", myUserService.getUser());
        _ModelAndView.setViewName("/user/contact");
        _ModelAndView.addObject("massage", message);
        return _ModelAndView;
    }

    @Override
    public ModelAndView sendMessage(String username, String email, String message) {
        String messageSuccess="We feeling peace when see that you send response for our. I will change last to work for social and you ";
        String messageFail="We feeling peace when see that you send response for our. But, we sorry,my server on one status as the importance problem . We will result your mater";
        try {
            contactService.resultMessageAndSendMailResponse(username,email,messageSuccess);
            _ModelAndView.setView(new RedirectView("/api/u/contact?message="+true));

        }catch (Exception  e){
            _ModelAndView.setView(new RedirectView("/api/u/contact?message="+false));
        }
        return _ModelAndView;
    }
}
