<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ogani Template">
    <meta name="keywords" content="Ogani, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Ogani | Template</title>
    <jsp:include page="head.jsp" />

</head>

<body>
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>

    <!-- Humberger Begin -->
    <div class="humberger__menu__overlay"></div>
    <div class="humberger__menu__wrapper">
        <div class="humberger__menu__logo">
            <a href="#"><img src="img/logo.png" alt=""></a>
        </div>
        <div class="humberger__menu__cart">
            <ul>
                <li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a></li>
                <li><a href="#"><i class="fa fa-shopping-bag"></i> <span>3</span></a></li>
            </ul>
            <div class="header__cart__price">item: <span>$150.00</span></div>
        </div>
        <div class="humberger__menu__widget">
            <div class="header__top__right__language">
                <img src="img/language.png" alt="">
                <div>English</div>
                <span class="arrow_carrot-down"></span>
                <ul>
                    <li><a href="#">Spanis</a></li>
                    <li><a href="#">English</a></li>
                </ul>
            </div>
            <div class="header__top__right__auth">
                <a href="#"><i class="fa fa-user"></i> Login</a>
            </div>
        </div>
        <div id="mobile-menu-wrap"></div>
        <div class="header__top__right__social">
            <a href="#"><i class="fa fa-facebook"></i></a>
            <a href="#"><i class="fa fa-twitter"></i></a>
            <a href="#"><i class="fa fa-linkedin"></i></a>
            <a href="#"><i class="fa fa-pinterest-p"></i></a>
        </div>
        <div class="humberger__menu__contact">
            <ul>
                <li><i class="fa fa-envelope"></i> hello@colorlib.com</li>
                <li>Free Shipping for all Order of $99</li>
            </ul>
        </div>
    </div>
    <!-- Humberger End -->

    <jsp:include page="header.jsp" />

    <!-- Header Section End -->

   

    <!-- Checkout Section Begin -->
    <section class="checkout spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h6><span class="icon_tag_alt"></span> Have a coupon? <a href="#">Click here</a> to enter your code
                    </h6>
                </div>
            </div>
            <div class="checkout__form">
                <h4>Billing Details</h4>
                <form:form action="/api/v/u/checkout/order" method="post" modelAttribute="modelBill">
                    <div class="row">
                        <div class="col-lg-8 col-md-6">
                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="form-group">
                                        <form:label path="username">User Name<span>*</span></form:label>
                                        <form:input path="username" cssClass="form-control" />
                                    </div>
                                </div>
                                
                            </div>
                            <div class="form-group">
                                <form:label path="country">Country<span>*</span></form:label>
                                <form:input path="country" cssClass="form-control" />
                            </div>
                            <div class="form-group">
                                <form:label path="address">Address<span>*</span></form:label>
                                <form:input path="address" placeholder="Street Address" cssClass="form-control" />
                            </div>
                            <div class="form-group">
                                <form:label path="townOrCity">Town/City<span>*</span></form:label>
                                <form:input path="townOrCity" cssClass="form-control" />
                            </div>
                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="form-group">
                                        <form:label path="phone">Phone<span>*</span></form:label>
                                        <form:input path="phone" cssClass="form-control" />
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="form-group">
                                        <form:label path="email">Email<span>*</span></form:label>
                                        <form:input path="email" cssClass="form-control" />
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <form:label path="orderNote">Note <span>*</span></form:label>
                                        <form:input path="orderNote" cssClass="form-control" />
                                    </div>
                                </div>
                                
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-6">
                            <div class="checkout__order">
                                <h4>Your Order</h4>
                                <div class="checkout__order__products">Products <span>Total</span></div>
                                <ul>
                                    <c:forEach items = "${cart}"  var = "item">
                                        <li>${item.getValue().productDto.name}
                                             <span>$ ${item.getValue().getSubTotalPrice()}</span>
                                            </li>
                                     </c:forEach>
                                </ul>
                                <div class="checkout__order__subtotal">Subtotal <span>$${subPrice}</span></div>
                                <div class="checkout__order__total">Total <span>$${totalPrice}</span></div>
                            
                                <p>Lorem ipsum dolor sit amet, consectetur adip elit, sed do eiusmod tempor incididunt
                                    ut labore et dolore magna aliqua.</p>
                                <div class="checkout__input__checkbox">
                                    <label for="payment">
                                        Check Payment
                                        <input type="checkbox" id="payment">
                                        <span class="checkmark"></span>
                                    </label>
                                </div>
                                <div class="checkout__input__checkbox">
                                    <label for="paypal">
                                        Paypal
                                        <input type="checkbox" id="paypal">
                                        <span class="checkmark"></span>
                                    </label>
                                </div>
                                <button type="submit" class="site-btn">PLACE ORDER</button>
                            </div>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </section>
    <!-- Checkout Section End -->
    <jsp:include page="footer.jsp" />



</body>

</html>