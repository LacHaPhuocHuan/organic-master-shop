package com.organic.shop.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationWork {
     UserDto user;
     String timeAgo;
     String content;
     Long idBill;

    public NotificationWork(Long idBill,String timeAgo, String content) {
        this.timeAgo = timeAgo;
        this.content = content;
        this.idBill = idBill;
    }
    public NotificationWork(Long idBill,String timeAgo, String content, UserDto user) {
        this.timeAgo = timeAgo;
        this.content = content;
        this.idBill = idBill;
        this.user=user;
    }
}
