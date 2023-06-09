package com.organic.shop.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class UploadFile {
    public static String uploadProductImg(MultipartFile file, HttpServletRequest request){
        return doUpload(request,file, "/WEB-INF/views/assets/img/product");
    }
    public static String uploadUserAvatar(MultipartFile file, HttpServletRequest request){
        return doUpload(request,file, "/WEB-INF/views/assets/img/avatar/");
    }
    public static String uploadCategoryImg(MultipartFile file, HttpServletRequest request){
        return doUpload(request,file, "/WEB-INF/views/assets/img/category/");
    }
    private static String doUpload(HttpServletRequest request, MultipartFile image, String directory) {
        String name = null;
        // Thư mục gốc upload file.
        String uploadRootPath = request.getServletContext().getRealPath(directory);
//		String uploadRootPath = new File("src/main/resource/assets/images/").getAbsolutePath();
//        System.out.println("uploadRootPath=" + uploadRootPath);

        File uploadRootDir = new File(uploadRootPath);

        // Tạo thư mục gốc upload nếu nó không tồn tại.
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }

        try {
            // Lấy tên file gốc.
            String fileName = image.getOriginalFilename();
            System.out.println("Client File Name = " + fileName);

            if (fileName != null && fileName.length() > 0) {
                // Tạo file tại Server.
                File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + fileName);

                // Ghi file vào folder trên server.
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(image.getBytes());
                stream.close();
                System.out.println("Write file: " + serverFile);
                name = fileName;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return name;
    }
}
