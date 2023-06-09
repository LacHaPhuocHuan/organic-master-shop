<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vn">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ogani Template">
    <meta name="keywords" content="Ogani, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <style>
       .shopping-cart {
  border: 1px solid #ebebeb;
  border-radius: 8px;
  box-shadow: 0 2px 5px rgba(0,0,0,.1);
  margin: 20px;
  overflow: hidden;
  width: 100%;
}

.shopping-cart table {
  border-collapse: collapse;
  width: 100%;
}

.shopping-cart th, .shopping-cart td {
  padding: 12px;
  text-align: left;
  vertical-align: middle;
}

.shopping-cart thead th {
  background-color: #f5f5f5;
  color: #333;
  font-weight: 700;
  text-transform: uppercase;
}

.shopping-cart tbody tr:nth-child(even) {
  background-color: #f9f9f9;
}

.shopping-cart tbody td {
  color: #333;
}

.shopping-cart tfoot td {
  font-size: 16px;
  font-weight: 700;
  text-align: right;
}

.shopping-cart tfoot tr:first-child td {
  border-top: 2px solid #ebebeb;
}

.shopping-cart tfoot tr:last-child td {
  border-top: none;
}

.shopping-cart .btn {
  background-color: #4caf50;
  border: none;
  border-radius: 4px;
  color: #fff;
  cursor: pointer;
  display: inline-block;
  font-size: 16px;
  margin-top: 20px;
  padding: 10px 20px;
  text-align: center;
  text-decoration: none;
  transition: background-color 0.3s ease;
  width: 100%;
}

.shopping-cart .btn:hover {
  background-color: #3e8e41;
}


    </style>
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
    <!-- Hero Section Begin -->
    <jsp:include page="header.jsp" />

    <!-- Hero Section End -->

    <!-- Breadcrumb Section Begin -->
    <section class="breadcrumb-section set-bg" data-setbg="<c:url value='/assets/img/breadcrumb.jpg'/>">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="breadcrumb__text">
                        <h2>Shopping Cart</h2>
                        <div class="breadcrumb__option">
                            <span>Shopping Cart</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->

    <!-- Shoping Cart Section Begin -->
    <section class="shoping-cart spad">
        <div class="container">

                <div class="row">
                    
                <div class="shopping-cart">

                    <table>
                    <thead>
                        <tr>
                        <th>Product</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Total</th>
                        <th> </th>
                        </tr>
                    </thead>
                    
                    <tbody>
                            <c:forEach items = "${cart}"  var = "item">
                                <tr>
                                <td>${item.getValue().productDto.name}</td>
                                <td>${item.getValue().productDto.price} $</td>
                                <td>
                                    <form action="<c:url value='/api/v/u/cart/update/${item.key}'/>" method="post">
                                        <!-- <input type="hidden" name="itemId" value="${item.key}"> -->
                                        <input type="number" name="quantity" value="${item.value.quantity}" min="1">
                                        <button type="submit">Update</button>
                                    </form>
                                </td>
                                <td>
                                    ${item.getValue().getTotalPrice()}
                                </td>
                                <td>
                                    <form action="<c:url value='/api/v/u/cart/delete/${item.key}'/>" method="post">
                                        <button>delete</button></td>
                                    </form>
                                </tr>
                            </c:forEach>
                    
                    
                    </tbody>
                    </table>
                </div>       
                </div>  
                <div class="row">
                    <div class="col-lg-12">
                        <div class="shoping__cart__btns">
                            <a href="<c:url value='/api/u/shop'/>" class="primary-btn cart-btn">CONTINUE SHOPPING</a>
                            <!-- <button  class="primary-btn cart-btn cart-btn-right"><span class="icon_loading"></span>
                                Upadate Cart</button> -->
                        </div>
                    </div>
                </div>
     
                <div class="row">
                    
                    <div class="col-lg-6">
                        <div class="shoping__continue">
                            <div class="shoping__discount">
                                <h5>Discount Codes</h5>
                                <form action="<c:url value='/api/v/u/cart/discount-code'/>" method="post">
                                    <input type="text" placeholder="Enter your coupon code" name="code">
                                    <button type="submit" class="site-btn">APPLY COUPON</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="shoping__checkout">
                            <h5>Cart Total</h5>
                            <ul>
                                <li>Subtotal <span>${subPrice}</span></li>
                                <li>Total <span>${totalPrice}</span></li>
                            </ul>
                            <a href="<c:url value='/api/v/u/checkout'/>" class="primary-btn">PROCEED TO CHECKOUT</a>
                        </div>
                    </div>
                </div>
        </div>
    </section>
    <!-- Shoping Cart Section End -->
    <jsp:include page="footer.jsp" />


</body>

</html>