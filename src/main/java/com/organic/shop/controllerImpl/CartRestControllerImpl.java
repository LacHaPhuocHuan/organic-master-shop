package com.organic.shop.controllerImpl;

import com.organic.shop.Dtos.ElementOnCart;
import com.organic.shop.controller.CartController;
import com.organic.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class CartRestControllerImpl extends BaseUserController implements CartController {
    @Autowired
    CartService cartService;
    @Override
    public ModelAndView goCart(HttpServletRequest request, HttpSession session) {
        _ModelAndView.addObject("userCurrent", myUserService.getUser());
        _ModelAndView.setViewName("/user/shopping-cart");
        try {
            HashMap<Integer, ElementOnCart> cart
                    = generateCart(session);
            _ModelAndView.addObject("carts", cart);
            for (Map.Entry<Integer,ElementOnCart> cartEntry: cart.entrySet()
                 ) {
                System.out.println(cartEntry.getValue().getProductDto().getName());
                System.out.println(cartEntry.getValue().getQuantity());
                System.out.println(cartEntry.getValue().getTotalPrice());
            }
            System.out.println("price"+ cartService.getTotalPrice(cart));
            System.out.println("sixe() :" + cart.size());
            session.setAttribute("cart", cart);
            _ModelAndView.addObject("totalPrice", cartService.getTotalPrice(cart));
            _ModelAndView.addObject("subPrice", cartService.getSubTotalPrice(cart));

        }catch (Exception e){
            e.printStackTrace();
        }
        return _ModelAndView;
    }
    @Override
    public ModelAndView addProductIntoCart(HttpServletRequest request, HttpSession session, Long id) {
        _ModelAndView.setView(new RedirectView("/api/u/home"));
        try {
            HashMap<Integer, ElementOnCart> cart
                    = generateCart(session);
            HashMap<Integer, ElementOnCart> cartNews;
            cartNews = cartService.addOneProduct(id, 1, cart);
            session.setAttribute("cart", cartNews);
//        _ModelAndView.addObject("carts", cartNews);
        }catch (Exception e){
            e.printStackTrace();
        }
        return _ModelAndView;
    }

    @Override
    public ModelAndView addOneProductIntoCart(HttpServletRequest request, HttpSession session, Long id) {

        _ModelAndView.setView(new RedirectView("/api/u/home"));
        HashMap<Integer, ElementOnCart> cart
                = generateCart(session);
        HashMap<Integer, ElementOnCart> cartNews;
        cartNews = cartService.addOneProduct(id, 1, cart);
        session.setAttribute("cart", cartNews);
//        _ModelAndView.addObject("cart", cartNews);
        return _ModelAndView;
    }

    @Override
    public ModelAndView updateCart(HttpServletRequest request, HttpSession session,HashMap<Integer, ElementOnCart> carts) {
        _ModelAndView.setView(new RedirectView("/cart/"));
        if(Objects.isNull(carts))
            return _ModelAndView;
        session.setAttribute("cart", carts);
        return _ModelAndView;
    }

    @Override
    public ModelAndView deleteOneElement(HttpServletRequest request, HttpSession session, Integer id) {
        _ModelAndView.setView(new RedirectView("/api/v/u/cart/"));
        session.setAttribute("cart", cartService.delete(id, generateCart(session)));
        return _ModelAndView;
    }

    @Override
    public ModelAndView deleteVariousElement(HttpServletRequest request, HttpSession session,List<Long> ids) {
        _ModelAndView.setView(new RedirectView("/cart/"));

        session.setAttribute("cart", cartService.deleteVarious(ids, generateCart(session)));

        return _ModelAndView;
    }

    @Override
    public ModelAndView updateById(HttpServletRequest request, HttpSession session, Integer quantity, Integer id) {
        _ModelAndView.setView(new RedirectView("/api/v/u/cart/"));
        HashMap<Integer, ElementOnCart> cart
                = generateCart(session);
        HashMap<Integer, ElementOnCart> cartNew=cartService.updateById(id, quantity, cart);
        session.setAttribute("cart",cartNew);
        return _ModelAndView;
    }

    @Override
    public ModelAndView discount(HttpServletRequest request, HttpSession session, String code) {
        _ModelAndView.setView(new RedirectView("/api/v/u/cart/"));
        HashMap<Integer, ElementOnCart> cart
                = generateCart(session);
        HashMap<Integer, ElementOnCart> cartNew=cartService.discount(code,cart );
        session.setAttribute("cart",cartNew);
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
