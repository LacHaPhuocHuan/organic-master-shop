package com.organic.shop.restImpl;

import com.organic.shop.Dtos.BillDto;
import com.organic.shop.Dtos.NotificationWork;
import com.organic.shop.rest.AdminHomeRest;
import com.organic.shop.service.BillServive;
import com.organic.shop.service.InvoiceForGoodsReceivedService;
import com.organic.shop.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
//@Controller
public class AdminHomeRestImpl extends BaseAdminHomeRest implements AdminHomeRest {
    @Autowired
    BillServive billServive;
    @Autowired
    InvoiceForGoodsReceivedService invoiceForGoodsReceivedService;

    @Override
    public ModelAndView goHome() {
        _ModelAndView.setViewName("/admin/index");
        //Mặc định đã cấu hình ( bắt đầu ở thư mục views, kết thúc bới đui jsp)
        //->admin->index.jsp
        try {
            List<BillDto> bills = billServive.getAllBill();
            //
            _ModelAndView.addObject("bills", bills);
            //
            List<NotificationWork> notificationList = invoiceForGoodsReceivedService.getNotifivationList();
            _ModelAndView.addObject("notificationList", notificationList);

        }catch (Exception e){
            e.printStackTrace();
        }
        return _ModelAndView;
    }

}
