package com.organic.shop.config;

import com.organic.shop.utils.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class SecurityExceptionHandler extends ResponseEntityExceptionHandler {

    ModelAndView modelAndView =new ModelAndView();
//    @ExceptionHandler({AccessDeniedException.class, NotFoundProuctOnCart.class})
//    public ModelAndView handleAccessDeniedException(Exception ex, WebRequest request){
//        modelAndView.setViewName("/exception/404");
//        return modelAndView;
//    }
//    @ExceptionHandler({BadRequestException.class})
//    public ModelAndView handleBadRequestException(Exception ex, WebRequest request){
//        modelAndView.setViewName("/exception/400");
//        modelAndView.addObject("message", ex.getMessage());
//        return modelAndView;
//    }
//    @ExceptionHandler({InternalServerErrorException.class})
//    public ModelAndView handleInternalServerErrorException(Exception ex, WebRequest request){
//        modelAndView.setViewName("/exception/500");
//        modelAndView.addObject("message", ex.getMessage());
//        return modelAndView;
//    }
//    @ExceptionHandler({ServiceUnavailableException.class})
//    public ModelAndView handleServiceUnavailableException(Exception ex, WebRequest request){
//        modelAndView.setViewName("/exception/503");
//        modelAndView.addObject("message", ex.getMessage());
//        return modelAndView;
//    }
    @ExceptionHandler({NotFoundException.class, Exception.class,AccessDeniedException.class})
    public ModelAndView handleNotFoundException(Exception ex, WebRequest request){
        modelAndView.setViewName("/exception/404");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }
//    @ExceptionHandler(NotAllowAccessIntoLink.class)
//    public ModelAndView handleNotAllowAccessIntoLink(Exception ex, WebRequest request){
//        modelAndView.setViewName("/exception/404");
//        modelAndView.addObject("message", ex.getMessage());
//        return modelAndView;
//    }




}
