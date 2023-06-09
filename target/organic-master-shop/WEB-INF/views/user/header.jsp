<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ogani Template">
    <meta name="keywords" content="Ogani, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Ogani | Template</title>
</head>

<body>
 <!-- Header Section Begin -->
 <header class="header">
    
    <div class="header__top">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6">
                    <div class="header__top__left">
                        <ul>
                            <li><i class="fa fa-envelope"></i> hello@colorlib.com</li>
                            <li>Free Shipping for all Order of $99</li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6">
                    <div class="header__top__right">
                        <div class="header__top__right__social">
                            <a href="#"><i class="fa fa-facebook"></i></a>
                            <a href="#"><i class="fa fa-twitter"></i></a>
                            <a href="#"><i class="fa fa-linkedin"></i></a>
                            <a href="#"><i class="fa fa-pinterest-p"></i></a>
                        </div>
                        <div class="header__top__right__language">
                            <img src="<c:url value='/assets/img/language.png'/>" alt="">
                            <div>English</div>
                            <span class="arrow_carrot-down"></span>
                            <ul>
                                <li><a href="#">Spanis</a></li>
                                <li><a href="#">English</a></li>
                            </ul>
                        </div>
                        <div class="header__top__right__auth">
                            <c:if test="${userCurrent==null}">
                                <a href="<c:url value='/api/v/auth/login/logout'/>"><i class="fa fa-user"></i>Login</a>
                            </c:if>
                            <c:if test="${userCurrent!=null}">
                                <a href="<c:url value='/api/v/auth/login/logout'/>"><i class="fa fa-user"></i>Logout</a>
                            </c:if>
                        </div>
                        <div class="header__top__right__auth">
                            ${userCurrent.getRole().name()}
                            <c:if test="${fn:endsWith(userCurrent.getRole().name(),'ADMIN')}">
                                <a href="<c:url value='/admin/home'/>"><i class="fa fa-user"></i>admin</a>

                            </c:if>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-lg-3">
                <div class="header__logo">
                    <a href="./index.html"><img src="<c:url value='/assets/img/logo.png'/>" alt=""></a>
                </div>
            </div>
            <div class="col-lg-6">
                <nav class="header__menu">
                    <!-- <h1>${submenu.containsKey(item.id)} and ${submenu.size()} </h1> -->
                    <ul>
                        <c:forEach items = "${menu}"  var = "item">
                            <!-- <h3>${submenu.containsKey(item.id)}</h3> -->
                           <li class="active"><a href="<c:url value='${item.link}'/>"> <c:out value = "${item.name}"/><p></a>
                                <c:if test="${submenu.containsKey(item.id)}">
                                    <ul class="header__menu__dropdown">
                                        <c:forEach items = "${submenu.get(item.id)}"  var = "subitem">
                                            <li ><a href="<c:url value='${subitem.link}'/>"> <c:out value = "${subitem.name}"/></a></li>
                                        </c:forEach>
                                    </ul>
                                </c:if>
                             </li>
                        </c:forEach>
                        
                
                    </ul>
                </nav>
            </div>
            <div class="col-lg-3">
                <div class="header__cart">
                    <ul>
                        <li><a href="<c:url value='/api/v/u/cart'/>"><i class="fa fa-shopping-bag" style="font-size:40px;"></i> <span>1</span></a></li>
                    </ul>
                    <!-- <div class="header__cart__price">item: <span>$150.00</span></div> -->
                </div>
            </div>
        </div>
        <div class="humberger__open">
            <i class="fa fa-bars"></i>
        </div>
    </div>
</header>

<!-- Header Section End -->
 <!-- Hero Section Begin -->
 <section class="hero">
    <div class="container">
        <form action="/api/u/search/" method="get">
            <div class="row">
                <div class="col-lg-3">
                    <div class="hero__categories">
                        <div class="hero__categories__all">
                            <i class="fa fa-bars" style="left: 20px;"></i>
                            
                           
                        </div>
                        <select name="category" id="category"  style="height: 100%; width: 100%; background-color: #bce77c;display:flex; text-align: center; ">
                            <ul>
                             <option value="0">All Category </option>
                             <c:forEach items = "${category}"  var = "item">
                                 <option value="${item.id}">${item.name}</option>
                             </c:forEach>
                           </ul>
                        </select>
                    </div>
                </div>
                <div class="col-lg-9">
                    <div class="hero__search">
                        <div class="hero__search__form" style="display: flex;
                        flex-direction: row;
                        flex-wrap: nowrap;
                        justify-content: space-between;">
                                
                                <input type="text" placeholder="What do yo u need?"style="       
                                border-radius: 5px;
                                width: 80%;
                                border-block-end-style: inherit;
                                border-block-end-color: revert;
                            
                                " name="keyWord">
                                <button type="submit" class="site-btn">SEARCH</button>
                        
                        </div>
                        <div class="hero__search__phone">
                            <div class="hero__search__phone__icon">
                                <i class="fa fa-phone"></i>
                            </div>
                            <div class="hero__search__phone__text">
                                <h5>+65 11.188.888</h5>
                                <span>support 24/7 time</span>
                            </div>
                        </div>
                    </div>
                    
                </div>
            </div>
        </form>
    </div>
</section>
<!-- Hero Section End -->
</body>
</html>