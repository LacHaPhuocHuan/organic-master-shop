package com.organic.shop.serviceImpl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.organic.shop.Daos.BillDetailsRepository;
import com.organic.shop.Daos.BillRepository;
import com.organic.shop.Daos.CategoryRepository;
import com.organic.shop.Daos.PaymentMethodRepository;
import com.organic.shop.Dtos.BillDto;
import com.organic.shop.Dtos.BillingDto;
import com.organic.shop.Dtos.ElementOnCart;
import com.organic.shop.Dtos.ProductDto;
import com.organic.shop.entities.*;
import com.organic.shop.security.MyUserService;
import com.organic.shop.service.CartService;
import com.organic.shop.service.BillServive;
import com.organic.shop.utils.Email;
import com.organic.shop.utils.NotFoundException;
import com.organic.shop.utils.StatusDelivery;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.itextpdf.text.FontFactory.getFont;

@Service
public class BillServiceImpl implements BillServive {
    private final static  SimpleDateFormat DATE_FORMAT=new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    Email emailService;
    @Autowired
    BillDetailsRepository  billDetailsRepository;
    @Autowired
    BillRepository billRepository;
    @Autowired
    MyUserService service;
    private ModelMapper mapper=new ModelMapper();

    private Long idBill;
    private Date date;
    @Autowired
    PaymentMethodRepository paymentMethodRepository;
    @Autowired
    CartService cartService;
    @Autowired
    CategoryRepository categoryRepository;



    @Override
    public void order(HashMap<Integer, ElementOnCart> cart, BillingDto billingDto, HttpServletRequest request) throws DocumentException, FileNotFoundException, MessagingException {
        System.out.println(5);
        sendBillPDFIntoMail(cart, billingDto,request);
        System.out.println(6);
        save(cart, billingDto);
    }

    @Override
    public List<BillDto> getAllBill() {
        List<Bill> bills=billRepository.findAll();
        //
        List<BillDto> dtos=new ArrayList<>();
        Double totalPriceT=0.0;
        for (Bill  bill: bills){
            Double price=billDetailsRepository.totalPrice(bill.getId());
            //Lấy tiền price ở bill mỗi bill.
            //Dùng Repository cua BillDetails.
            if (!Objects.isNull(price)) totalPriceT=totalPriceT+price;
        }
        Double finalTotalPriceT = totalPriceT;

        return  bills.stream()
                // là một cái-> có java 8.
                .map(b->
                        new BillDto(b.getId(),
                        DATE_FORMAT.format(b.getSaleOfDate()),
                        billRepository.findUserById(b.getId()).getEmail(),
                        finalTotalPriceT,
                        b.getStatusDelivery().name(),
                        b.getAddress(),
                        b.getPhone().toString())
                ).collect(Collectors.toList());
    }

    private void save(HashMap<Integer, ElementOnCart> cart, BillingDto billingDto) {
        Bill bill = new Bill();
        idBill= UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        date=new Date();
        bill.setSaleOfDate(date);
        bill.setUser(service.getUser()); // set the user associated with this bill
        bill.setPhone(billingDto.getPhone());
        bill.setAddress(billingDto.getAddress());
        bill.setStatusDelivery(StatusDelivery.Canceled);
        billRepository.save(bill);
        BillDetails billDetails;
        for (Map.Entry<Integer, ElementOnCart> cartEntry: cart.entrySet()){
            billDetails = new BillDetails();
            BillDetailsKey billDetailsKey=new BillDetailsKey();
            billDetailsKey.setBillId(bill.getId());
            billDetails.setBill(bill);
            ProductDto productDto=cartEntry.getValue().getProductDto();
            System.out.println("ID PRODUCT FOR BILL"+productDto.getId());
            billDetailsKey.setProductId(productDto.getId());
            billDetails.setProduct(mapper.map(productDto, Product.class));
            System.out.println("ID ON PRODUCT MAIN: "+mapper.map(productDto, Product.class).getId());
            Integer quantity=cartEntry.getValue().getQuantity();
            billDetails.setQuantity(quantity);
            billDetails.setActualPrice(quantity*productDto.getPrice());
            billDetails.setId(billDetailsKey);
            billDetailsRepository.save(billDetails);
        }


    }

    private void sendBillPDFIntoMail(HashMap<Integer, ElementOnCart> cart, BillingDto billingDto, HttpServletRequest request) throws FileNotFoundException, DocumentException, MessagingException {

//        PaymentMethod paymentMethod=paymentMethodRepository.findById(billingDto.getPaymentMethod_Id())
//                .orElseThrow(()->new NotFoundException("This payment Method  don't work, please choice one another payment method!"));
        String data = "Name: " + billingDto.getUsername() + "\n" + "contact Number: " + billingDto.getPhone()
                + "\n" + "Email: " + billingDto.getEmail() + "\n " + "Payment Method: " ;

        String uploadRootPath = request.getServletContext().getRealPath("/assets/bill/");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nameBill= dateFormat.format(new Date())+ service.getUser().getEmail();
        File uploadRootDir = new File(uploadRootPath);

        // Tạo thư mục gốc upload nếu nó không tồn tại.
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }
        Document document = new Document();
        String fileAddress=uploadRootPath +"/"+ nameBill+".pdf";
        PdfWriter.getInstance(document, new FileOutputStream(fileAddress));
        document.open();
        setRectangleInPdf(document);

        Paragraph paragraph = new Paragraph("Organic Management System", getFont("Header"));
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);
        Paragraph paragraph1 = new Paragraph(data + "\n \n", getFont("Data"));
        document.add(paragraph1);
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        addTableHeader(table);
        for (Map.Entry<Integer, ElementOnCart> cartEntry: cart.entrySet()){
            System.out.println("SEND MAIL PDF---------------------------------------------------");
            table.addCell(cartEntry.getValue().getProductDto().getName());
            Category category=categoryRepository.findById(cartEntry.getValue().getProductDto().getIdCategory())
                    .orElseThrow(()->new NotFoundException("Not found category"));
            table.addCell(category.getName());
            Integer quantity=cartEntry.getValue().getQuantity();
            table.addCell(quantity.toString());
            Double price=cartEntry.getValue().getProductDto().getPrice();
            Double priceTotal=quantity*price;
            table.addCell(price.toString());
            table.addCell(priceTotal.toString());

        }
        document.add(table);
        Paragraph paragraph2 = new Paragraph("\n Total: " + cartService.getTotalPrice(cart) + "\nThanh you!", getFont("Data"));
        document.add(paragraph2);
        document.close();
        sendMail(fileAddress);
//        return null;

    }

    private void sendMail(String fileAddress) throws MessagingException {
        User user= service.getUser();
        String paragraph="<tbody>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td align=\"center\" valign=\"top\"\n" +
                "\t\t\t\tstyle=\"padding-right: 10px; padding-left: 10px\"\n" +
                "\t\t\t\tid=\"m_-4637072519812125909bodyCell\"><table border=\"0\"\n" +
                "\t\t\t\t\tcellpadding=\"0\" cellspacing=\"0\" style=\"max-width: 600px\"\n" +
                "\t\t\t\t\twidth=\"100%\">\n" +
                "\t\t\t\t\t<tbody>\n" +
                "\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t<td align=\"center\" valign=\"top\"></td>\n" +
                "\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t</tbody>\n" +
                "\t\t\t\t</table>\n" +
                "\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "\t\t\t\t\tstyle=\"max-width: 600px\" width=\"100%\">\n" +
                "\t\t\t\t\t<tbody>\n" +
                "\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t<td align=\"center\" valign=\"top\"><table border=\"0\"\n" +
                "\t\t\t\t\t\t\t\t\tcellpadding=\"0\" cellspacing=\"0\"\n" +
                "\t\t\t\t\t\t\t\t\tstyle=\"background-color: #ffffff; border-color: #e5e5e5; border-style: solid; border-width: 0 1px 1px 1px\"\n" +
                "\t\t\t\t\t\t\t\t\twidth=\"100%\">\n" +
                "\t\t\t\t\t\t\t\t\t<tbody>\n" +
                "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td height=\"3\"\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\tstyle=\"clear: both; height: 5px; background: url('https://ci6.googleusercontent.com/proxy/gAQR6G-dK2Y5cvGEGQEUkeuvvuKZSrrmyEjpSzf4fWhsJVSXsxlXWbZo_svd-PzyEhlOk0qSSM3exPcSSYDb8Ltm=s0-d-e1-ft#https://sendy.colorlib.com/img/top-pattern2.gif') repeat-x 0 0; background-size: 46px\">&nbsp;</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td align=\"center\" valign=\"top\" style=\"padding-bottom: 10px\"><a\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\thref=\"#m_-4637072519812125909_\"\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\tstyle=\"text-decoration: none\"><img\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\tsrc=\"https://ci6.googleusercontent.com/proxy/7L0aqUPBmj_uw4Beyz1bwQQcOvsBiMCmkULSBmqT2snrysU2APnyxEp5_2xrnJjPfMv14CIpPDyk2QDICJXaXR9puHhv9b98JcfCtRxA-EhZSBWAbrI=s0-d-e1-ft#https://sendy.colorlib.com/img/email-notifications/almost-there.gif\"\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\twidth=\"150\" alt=\"\" border=\"0\"\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\tstyle=\"width: 100%; max-width: 150px; height: auto; display: block\"\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\tclass=\"CToWUd\" data-bit=\"iit\"></a></td>\n" +
                "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td align=\"center\" valign=\"top\"\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\tstyle=\"padding-bottom: 5px; padding-left: 20px; padding-right: 20px\"><h2\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\tstyle=\"color: #000000; font-family: Helvetica, Arial, sans-serif; font-size: 28px; font-weight: 500; font-style: normal; letter-spacing: normal; line-height: 36px; text-transform: none; text-align: center; padding: 0; margin: 0\">You're\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\talmost there!</h2></td>\n" +
                "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td align=\"center\" valign=\"top\"\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\tstyle=\"padding-bottom: 30px; padding-left: 20px; padding-right: 20px\"><h4\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\tstyle=\"color: #848484; font-family: Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 500; font-style: normal; letter-spacing: normal; line-height: 24px; text-transform: none; text-align: center; padding: 0; margin: 0\">Please\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\tconfirm your subscription by clicking the link below</h4></td>\n" +
                "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td align=\"center\" valign=\"top\"\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\tstyle=\"padding-left: 20px; padding-right: 20px\"><table\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\tborder=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\tstyle=\"margin-bottom: 20px\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<tbody>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td align=\"left\" valign=\"top\"\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tstyle=\"padding: 15px; background: #f8f9fc\"><p\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tstyle=\"color: #666666; font-family: 'Open Sans', Helvetica, Arial, sans-serif; font-size: 14px; font-weight: 400; font-style: normal; letter-spacing: normal; line-height: 22px; text-transform: none; text-align: left; padding: 0; margin: 0\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\ttarget=\"_blank\"\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tdata-saferedirecturl=\"https://www.google.com/url?q=https://sendy.colorlib.com/confirm?e%3DxCHcgT7sEBEW7OkTXmCf3g%26l%3DI5sajNONQFFffS0E0wRLYA&amp;source=gmail&amp;ust=1682643241589000&amp;usg=AOvVaw322t42c539DvAQZ-isR55Z\">https://sendy.colorlib.com/<wbr>confirm?e=<wbr>xCHcgT7sEBEW7OkTXmCf3g&amp;l=<wbr>I5sajNONQFFffS0E0wRLYA\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</a><br>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</p></td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t</tbody>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\twidth=\"100%\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<tbody>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td align=\"center\" valign=\"top\"\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tstyle=\"padding-top: 20px; padding-bottom: 20px\"><table\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\talign=\"center\" border=\"0\" cellpadding=\"0\"\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tcellspacing=\"0\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tbody>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td align=\"center\"\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tstyle=\"background-color: #000000; padding-top: 12px; padding-bottom: 12px; padding-left: 35px; padding-right: 35px; border-radius: 50px\"><a\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\thref=\"https://sendy.colorlib.com/confirm?e=xCHcgT7sEBEW7OkTXmCf3g&amp;l=I5sajNONQFFffS0E0wRLYA\"\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tstyle=\"color: #ffffff; font-family: Helvetica, Arial, sans-serif; font-size: 13px; font-weight: 600; font-style: normal; letter-spacing: 1px; line-height: 20px; text-transform: uppercase; text-decoration: none; display: block\"\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\ttarget=\"_blank\"\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tdata-saferedirecturl=\"https://www.google.com/url?q=https://sendy.colorlib.com/confirm?e%3DxCHcgT7sEBEW7OkTXmCf3g%26l%3DI5sajNONQFFffS0E0wRLYA&amp;source=gmail&amp;ust=1682643241590000&amp;usg=AOvVaw3R36coG83JRNy4AxpSEN3P\">Confirm\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tyour subscription</a></td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tbody>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</table></td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t</tbody>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</table></td>\n" +
                "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td height=\"20\" style=\"font-size: 1px; line-height: 1px\">&nbsp;</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t</tbody>\n" +
                "\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "\t\t\t\t\t\t\t\t\t<tbody>\n" +
                "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td height=\"30\" style=\"font-size: 1px; line-height: 1px\">&nbsp;</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t</tbody>\n" +
                "\t\t\t\t\t\t\t\t</table></td>\n" +
                "\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t</tbody>\n" +
                "\t\t\t\t</table></td>\n" +
                "\t\t</tr>\n" +
                "\t</tbody>";
        emailService.sentMailWithAttachment(user.getEmail(), "Thank you for purchura on orginic-master!",paragraph, fileAddress);
    }

    private void addRow(PdfPTable table, Map<String, Object> mapFormJson) {
        table.addCell((String) mapFormJson.get("name"));
        table.addCell((String) mapFormJson.get("category"));
        table.addCell((String) mapFormJson.get("quantity"));
        table.addCell(Double.toString((Double)mapFormJson.get("price")));
        table.addCell((Double.toString((Double) mapFormJson.get("total"))));

    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Name", "Category", "Quantity", "Price", "Sub Total")
                .forEach(columnTitle-> {
                    PdfPCell header=new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    header.setBackgroundColor(BaseColor.YELLOW);
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(header);

                });
    }

    private void setRectangleInPdf(Document document) throws DocumentException {
        Rectangle rectangle=new Rectangle(557,825,18,15);
        rectangle.enableBorderSide(1);
        rectangle.enableBorderSide(2);
        rectangle.enableBorderSide(4);
        rectangle.enableBorderSide(8);
        rectangle.setBackgroundColor(BaseColor.WHITE);
        document.add(rectangle);
    }




    private Font getFont(String type){

        switch (type){
            case "Header":
                Font headerFont=FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
                headerFont
                        .setStyle(Font.BOLD);
                return headerFont
                        ;
            case "Data":
                Font font=FontFactory.getFont(FontFactory.TIMES_ROMAN);
                font
                        .setStyle(Font.BOLD);
                return font; default: return new Font();

        }
    }


}
