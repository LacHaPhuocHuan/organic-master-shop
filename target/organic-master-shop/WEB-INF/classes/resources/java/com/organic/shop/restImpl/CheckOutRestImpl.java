package com.organic.shop.restImpl;

import com.itextpdf.text.DocumentException;
import com.organic.shop.Dtos.BillingDto;
import com.organic.shop.Dtos.ElementOnCart;
import com.organic.shop.rest.CheckOutRest;
import com.organic.shop.service.CartService;
import com.organic.shop.service.BillServive;
import com.organic.shop.utils.InternalServerErrorException;
import com.organic.shop.utils.NotFoundProuctOnCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Objects;

@RestController
public class CheckOutRestImpl extends BaseUserRest implements CheckOutRest {

    @Autowired
    BillServive checkOutService;
    @Autowired
    CartService cartService;
    @Override
    public ModelAndView goCheckOut(HttpSession session) {
        _ModelAndView.addObject("userCurrent", myUserService.getUser());
        HashMap<Integer, ElementOnCart> cart
                = (HashMap<Integer, ElementOnCart>) session.getAttribute("cart");
        if (cart==null){
            throw  new NotFoundProuctOnCart("Cart don't existed any product. Let's back and shopping! (^_^)");
        }
        _ModelAndView.setViewName("/user/checkout");
//        _ModelAndView.addObject("cart", cart);
        _ModelAndView.addObject("totalPrice", cartService.getTotalPrice(cart));
        _ModelAndView.addObject("subPrice", cartService.getSubTotalPrice(cart));
        _ModelAndView.addObject("modelBill", new BillingDto());
        return _ModelAndView;
    }

    @Override
    public ModelAndView order(BillingDto billingDto, HttpSession session, HttpServletRequest request) throws Exception {
        try {
            System.out.println(1);
            HashMap<Integer, ElementOnCart> cart
                    = generateCart(session);
            System.out.println(2);
            if (cart == null) {
                throw new NotFoundProuctOnCart("Cart don't existed any product. Let's back and shopping! (^_^)");
            }
            System.out.println(3);
            checkOutService.order(cart, billingDto, request);
            System.out.println(4);
            _ModelAndView.setView(new RedirectView("/api/u/home"));
        }catch (Exception e){
           throw new Exception();
        }
        return _ModelAndView;
    }
    HashMap<Integer, ElementOnCart> generateCart(HttpSession session){
        HashMap<Integer, ElementOnCart> cart
                = (HashMap<Integer, ElementOnCart>) session.getAttribute("cart");
        if(Objects.isNull(cart)) {
            cart = new HashMap<>();
        }
        return cart;
    }




}
