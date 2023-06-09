package com.organic.shop.service;

import com.organic.shop.Dtos.NotificationWork;
import com.organic.shop.entities.InvoiceForGoodsReceived;
import com.organic.shop.entities.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InvoiceForGoodsReceivedService {
    List<NotificationWork> getNotifivationList();
    void SaveInvoiceReceived(InvoiceForGoodsReceived  invoiceForGoodsReceived, Product product);
}
