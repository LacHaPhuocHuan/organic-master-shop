package com.organic.shop.restImpl;

import com.organic.shop.Dtos.InfoUserCurrentDto;
import com.organic.shop.Dtos.UserChangeDto;
import com.organic.shop.Dtos.UserDto;
import com.organic.shop.entities.User;
import com.organic.shop.rest.AdminAccountRest;
import com.organic.shop.security.MyUserService;
import com.organic.shop.service.UserService;
import com.organic.shop.utils.UploadFile;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class AdminAccountRestImpl extends BaseAdminHomeRest implements AdminAccountRest {
    @Autowired
    UserService userService;
    @Autowired
    MyUserService myUserService;

    private ModelMapper mapper=new ModelMapper();

    @Override
    public ModelAndView goManagerAccount() {
        try {
            List<UserDto> userDtos = userService.getUserAll();
            _ModelAndView.addObject("listUsers", userDtos);
            _ModelAndView.addObject("userDto", new UserChangeDto());
//            _ModelAndView.addObject("userCurrent",mapper.map(myUserService.getUser(), InfoUserCurrentDto.class) );
            User user= userService.findUserById(myUserService.getUser().getId());
            System.out.println(mapper.map(user, InfoUserCurrentDto.class).getAvatar());

            _ModelAndView.addObject("userCurrent",mapper.map(user, InfoUserCurrentDto.class) );

        }catch (Exception e){
            e.printStackTrace();
        }
        _ModelAndView.setViewName("/admin/accounts");
        return _ModelAndView;
    }

    @Override
    public ModelAndView blockAccount(Long id) {
        try{
            userService.blockedUserAccount(id);
            _ModelAndView.setView(new RedirectView("/admin/account?message=true"));
        }catch (Exception e){
            _ModelAndView.setView(new RedirectView("/admin/account?message=false"));
        }
        return _ModelAndView;
    }

    @Override
    public ModelAndView changeAccount( UserChangeDto userDto, Long id) {
        try{

            userService.changeAccount(userDto, id);
            _ModelAndView.setView(new RedirectView("/admin/account"));
        }catch (Exception e){
            _ModelAndView.setView(new RedirectView("/admin/account"));
        }
        return _ModelAndView;
    }

    @Override
    public ModelAndView uploadFile(HttpServletRequest request, MultipartFile file) {
        String nameAva= null;
        System.out.println("UPLOAD FILE");
        try {
            if (file != null)
                nameAva = UploadFile.uploadUserAvatar(file, request);
            userService.changeAvatar(nameAva, myUserService.getUser().getEmail());
        }catch (Exception e){
            e.printStackTrace();
        }
        _ModelAndView.setView(new RedirectView("/admin/account"));
        return _ModelAndView;
    }
}
