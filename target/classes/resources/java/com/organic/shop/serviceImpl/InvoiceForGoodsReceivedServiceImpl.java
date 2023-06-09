package com.organic.shop.serviceImpl;

import com.organic.shop.Daos.InvoiceResceivedRepository;
import com.organic.shop.Dtos.NotificationWork;
import com.organic.shop.Dtos.UserDto;
import com.organic.shop.entities.InvoiceForGoodsReceived;
import com.organic.shop.entities.InvoiceForGoodsReceived_Product;
import com.organic.shop.entities.InvoiceForGoodsReceived_Product_Key;
import com.organic.shop.entities.Product;
import com.organic.shop.service.InvoiceForGoodsReceivedService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceForGoodsReceivedServiceImpl implements InvoiceForGoodsReceivedService {
    @Autowired
    InvoiceResceivedRepository invoiceRepository;
//    @Autowired
    private ModelMapper mapper=new ModelMapper();


    @Override
    public List<NotificationWork> getNotifivationList() {
        List<InvoiceForGoodsReceived> invoices=invoiceRepository.findAll();


        List<NotificationWork> list=new ArrayList<>();


        for (InvoiceForGoodsReceived invoice: invoices){
            list.add(
                    new NotificationWork(
                            invoice.getId(),
                    invoice.getLocalDateTime().toString(),
                    "added new product",mapper.map(invoiceRepository.findUserById(invoice.getId()), UserDto.class)
                    )
            )
            ;
        }

        return list;
    }

    @Override
    public void SaveInvoiceReceived(InvoiceForGoodsReceived invoiceForGoodsReceived, Product product) {

        invoiceRepository.save(invoiceForGoodsReceived);
        InvoiceForGoodsReceived_Product_Key key=new InvoiceForGoodsReceived_Product_Key();
        key.setInvoiceForGoodsReceivedId(invoiceForGoodsReceived.getId());
        key.setProductId(product.getId());
        InvoiceForGoodsReceived_Product invoiceDetails=new InvoiceForGoodsReceived_Product();
        invoiceDetails.setInvoiceForGoodsReceived(invoiceForGoodsReceived);
        invoiceDetails.setProduct(product);
        invoiceDetails.setQuantity(product.getUntilInStock());
        invoiceDetails.setActualPrice(product.getPrice()*product.getUntilInStock());
        return;
    }
}
