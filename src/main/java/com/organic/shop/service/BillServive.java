package com.organic.shop.service;

import com.itextpdf.text.DocumentException;
import com.organic.shop.Dtos.BillDto;
import com.organic.shop.Dtos.BillingDto;
import com.organic.shop.Dtos.ElementOnCart;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

@Service
public interface BillServive {
    void order(HashMap<Integer, ElementOnCart> cart, BillingDto billingDto, HttpServletRequest request) throws DocumentException, FileNotFoundException, MessagingException;

    List<BillDto> getAllBill();
}
