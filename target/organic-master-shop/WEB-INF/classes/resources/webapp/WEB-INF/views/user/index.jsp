<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
        <!-- <nav class="humberger__menu__nav mobile-menu">
            <ul>
                <li class="active"><a href="./index.html">Home</a></li>
                <li><a href="./shop-grid.html">Shop</a></li>
                <li><a href="#">Pages</a>
                    <ul class="header__menu__dropdown">
                        <li><a href="./shop-details.html">Shop Details</a></li>
                        <li><a href="./shoping-cart.html">Shoping Cart</a></li>
                        <li><a href="./checkout.html">Check Out</a></li>
                        <li><a href="./blog-details.html">Blog Details</a></li>
                    </ul>
                </li>
                <li><a href="./blog.html">Blog</a></li>
                <li><a href="./contact.html">Contact</a></li>
            </ul>
        </nav>
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
        </div> -->
    </div>
    <!-- Humberger End -->
    <jsp:include page="header.jsp" />
    
   
    <section class="hero">
        <div class="container">
                <div class="row">
                    <!-- <div class="col-lg-3" -->
                        <div class="hero__item set-bg" style="width: 100%;" 
                        data-setbg="<c:url value='/assets/img/hero/banner.jpg'/>"
                        >
                            <div class="hero__text">
                                <span>FRUIT FRESH</span>
                                <h2>Vegetable <br />100% Organic</h2>
                                <p>Free Pickup and Delivery Available</p>
                                <a href="#" class="primary-btn">SHOP NOW</a>
                            </div>
                        </div>
                    <!-- </div> -->
                </div>
        </div>
     </section>

  
    

    <!-- Categories Section Begin -->
    <section class="categories">
        
        <div class="container">
            <div class="row">
                <div class="categories__slider owl-carousel" style="    display: flex;
                flex-direction: row;">
                    <c:forEach items = "${featuredCategory}"  var = "item">
                        <div class="col-lg-3">
                            <div class="categories__item set-bg" data-setbg="<c:url value='/assets${item.img}'/>">
                                <h5><a href="<c:url value='/api/u/shop/product-cartegory/${item.id}'/>">${item.name}</a></h5>
                            </div>
                        </div>                    
                    </c:forEach>
                    
                </div>
            </div>
        </div>
    </section>
    <!-- Categories Section End -->

    <!-- Featured Section Begin -->
    <section class="featured spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title">
                        <h2>Featured Product</h2>
                    </div>
                    <div class="featured__controls">
                        <ul>
                            <li class="active" data-filter="*">All</li>
                           <li data-filter=".oranges">Oranges</li>
                            <li data-filter=".fresh-meat">Fresh Meat</li>
                            <li data-filter=".vegetables">Vegetables</li>
                            <li data-filter=".fastfood">Fastfood</li>
                            
                        </ul>
                    </div>
                </div>
            </div>
            <div class="row featured__filter">
                <c:forEach items = "${featuredProduct}"  var = "item">
                 <c:if test="${item.idCategory>0 && item.idCategory<=3}">
                            <div class="col-lg-3 col-md-4 col-sm-6 mix oranges ">
                </c:if>
                <c:if test="${item.idCategory >3 && item.idCategory<=6}">
                            <div class="col-lg-3 col-md-4 col-sm-6 mix vegetables ">
                </c:if>
                <c:if test="${item.idCategory>6 && item.idCategory<=9}">
                    <div class="col-lg-3 col-md-4 col-sm-6 mix  fresh-meat">
                </c:if>
                <c:if test="${item.idCategory>9 && item.idCategory<=12}">
                    <div class="col-lg-3 col-md-4 col-sm-6 mix  fastfood ">
                 </c:if>
                        <div class="featured__item">
                            <div class="featured__item__pic set-bg" data-setbg="/assets${item.img}">
                                <ul class="featured__item__pic__hover">
                                    <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                    <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                    <li><a href="<c:url value='/api/v/u/cart/add-product/${item.id}'/>"><i class="fa fa-shopping-cart"></i></a></li>
                                </ul>
                            </div>
                            <div class="featured__item__text">
                                <h6><a href="<c:url value='/api/u/shop/product-details/${item.id}'/>">${item.name}</a></h6>
                                <h5>$ ${item.price}</h5>
                            </div>
                        </div>
                        
                    </div>               
                </c:forEach>
            </div>
        </div>
    </section>
    <!-- Featured Section End -->

    <!-- Banner Begin -->
    <div class="banner">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6">
                    <div class="banner__pic">
                        <img src="img/banner/banner-1.jpg" alt="">
                    </div>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-6">
                    <div class="banner__pic">
                        <img src="img/banner/banner-2.jpg" alt="">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Banner End -->

    <!-- Latest Product Section Begin -->
    <section class="latest-product spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-6">
                    <div class="latest-product__text">
                        <h4>Latest Products</h4>
                        <div 
                        class="latest-product__slider owl-carousel owl-loaded owl-drag">
                            <div class="owl-stage-outer">
                             <div class=" owl-stage" style="   
                            transform: translate3d(-10px, 0px, 0px);
                            transition: all 1.2s ease 0s;
                            width: 2160px;">
                                <div class="  owl-item active " style="width: 360px;">
                                <div class="latest-prdouct__slider__item  " >
                                    <c:forEach items = "${latestProducts}"  var = "item" varStatus="indexLoop">
                                        <a href="<c:url value='/api/u/shop/product-details/${item.id}'/>"  class="latest-product__item">
                                            <div class="latest-product__item__pic">
                                                <img style="width: 100%;" src="<c:url value='/assets${item.img}'/>" alt="">
                                            </div>
                                            <div class="latest-product__item__text">
                                                <h6>${item.name}</h6>
                                                <span>${item.price}</span>
                                            </div>
                                        </a>
                                        <c:if test="${(indexLoop.index +1)%3 ==0 || latestProducts.size()==(indexLoop.index +1)}">
                                            </div></div>
                                            <c:if test="${latestProducts.size() > (indexLoop.index +1)}">
                                                <div class="  owl-item active " style="width: 360px;">
                                                <div class="latest-prdouct__slider__item">
                                            </c:if>
                                        </c:if>
                                    </c:forEach>
                            </div>
                            </div>
                             <!-- <div class="owl-nav"><button type="button" role="presentation" class="owl-prev"><span class="fa fa-angle-left"><span></span></span></button><button type="button" role="presentation" class="owl-next"><span class="fa fa-angle-right"><span></span></span></button></div>    -->
                             <div class="owl-dots disabled"></div>
                        </div>
                    </div>
                </div>
            
                <!-- --------------------- -->
                <div class="col-lg-4 col-md-6">
                    <div class="latest-product__text">
                        <h4>Top Rated Products</h4>
                        <div 
                        class="latest-product__slider owl-carousel owl-loaded owl-drag">
                            <div class="owl-stage-outer">
                             <div class=" owl-stage" style="   
                            transform: translate3d(-10px, 0px, 0px);
                            transition: all 1.2s ease 0s;
                            width: 2160px;">
                                <div class="  owl-item active " style="width: 360px;">
                                <div class="latest-prdouct__slider__item  " >
                                    <c:forEach items = "${latestProducts}"  var = "item" varStatus="indexLoop">
                                        <a href="<c:url value='/api/u/shop/product-details/${item.id}'/>" class="latest-product__item">
                                            <div class="latest-product__item__pic">
                                                <img src="<c:url value='/assets${item.img}'/>" alt="">
                                            </div>
                                            <div class="latest-product__item__text">
                                                <h6>${item.name}</h6>
                                                <span>${item.price}</span>
                                            </div>
                                        </a>
                                        <c:if test="${(indexLoop.index +1)%3 ==0 || latestProducts.size()==(indexLoop.index +1)}">
                                            </div></div>
                                            <c:if test="${latestProducts.size() > (indexLoop.index +1)}">
                                                <div class="  owl-item active " style="width: 360px;">
                                                <div class="latest-prdouct__slider__item">
                                            </c:if>
                                        </c:if>
                                    </c:forEach>
                            </div>
                            </div>
                             <div class="owl-nav"><button type="button" role="presentation" class="owl-prev"><span class="fa fa-angle-left"><span></span></span></button><button type="button" role="presentation" class="owl-next"><span class="fa fa-angle-right"><span></span></span></button></div>   
                             <div class="owl-dots disabled"></div>
                        </div>
                    </div>
                </div>
                <!-- --------------------- -->
                <div class="col-lg-4 col-md-6">
                    <div class="latest-product__text">
                        <h4>Review Products</h4>
                        <div 
                        class="latest-product__slider owl-carousel owl-loaded owl-drag">
                            <div class="owl-stage-outer">
                             <div class=" owl-stage" style="   
                            transform: translate3d(-10px, 0px, 0px);
                            transition: all 1.2s ease 0s;
                            width: 2160px;">
                                <div class="  owl-item active " style="width: 360px;">
                                <div class="latest-prdouct__slider__item  " >
                                    <c:forEach items = "${latestProducts}"  var = "item" varStatus="indexLoop">
                                        <a href="<c:url value='/api/u/shop/product-details/${item.id}'/>" class="latest-product__item">
                                            <div class="latest-product__item__pic">
                                                <img src="<c:url value='/assets${item.img}'/>" alt="">
                                            </div>
                                            <div class="latest-product__item__text">
                                                <h6>${item.name}</h6>
                                                <span>${item.price}</span>
                                            </div>
                                        </a>
                                        <c:if test="${(indexLoop.index +1)%3 ==0 || latestProducts.size()==(indexLoop.index +1)}">
                                            </div></div>
                                            <c:if test="${latestProducts.size() > (indexLoop.index +1)}">
                                                <div class="  owl-item active " style="width: 360px;">
                                                <div class="latest-prdouct__slider__item">
                                            </c:if>
                                        </c:if>
                                    </c:forEach>
                            </div>
                            </div>
                             <div class="owl-nav"><button type="button" role="presentation" class="owl-prev"><span class="fa fa-angle-left"><span></span></span></button><button type="button" role="presentation" class="owl-next"><span class="fa fa-angle-right"><span></span></span></button></div>   
                             <div class="owl-dots disabled"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Latest Product Section End -->

    <!-- Blog Section Begin -->
    <section class="from-blog spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title from-blog__title">
                        <h2>From The Blog</h2>
                    </div>
                </div>
            </div>
            <div class="row">
                <c:forEach items = "${blogs}"  var = "item" varStatus="indexLoop">
                <div class="col-lg-4 col-md-4 col-sm-6">
                   
                        <div class="blog__item">
                            <div class="blog__item__pic">
                                <img src="/assets${item.img}" alt="">
                            </div>
                            <div class="blog__item__text">
                                <ul>
                                    <li><i class="fa fa-calendar-o"></i>${item.createAt}</li>
                                    <li><i class="fa fa-comment-o"></i> 5</li>
                                </ul>
                                <h5><a href="<c:url value='/api/u/blog/blog-details/${item.id}'/>" >${item.name}</a></h5>
                                <p>${item.title} </p>
                            </div>
                        </div>
                    
                

                    
                </div>
            </c:forEach>
            </div>
        </div>
    </section>
    <!-- Blog Section End -->

    <jsp:include page="footer.jsp" />



</body>

</html>