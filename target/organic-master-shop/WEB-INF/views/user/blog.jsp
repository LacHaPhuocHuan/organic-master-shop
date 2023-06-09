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
			<div class="header__cart__price">
				item: <span>$150.00</span>
			</div>
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
			<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
				class="fa fa-twitter"></i></a> <a href="#"><i class="fa fa-linkedin"></i></a>
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

	

	<!-- Breadcrumb Section Begin -->
	<section class="breadcrumb-section set-bg"
		data-setbg="<c:url value='/assets/img/breadcrumb.jpg'/>">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<div class="breadcrumb__text">
						<h2>Blog</h2>
						<div class="breadcrumb__option">
							 <span>Blog</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Breadcrumb Section End -->

	<!-- Blog Section Begin -->
	<section class="blog spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-4 col-md-5">
					<div class="blog__sidebar">
						<div class="blog__sidebar__search">
							<form action="#">
								<input type="text" placeholder="Search...">
								<button type="submit">
									<span class="icon_search"></span>
								</button>
							</form>
						</div>
						<div class="blog__sidebar__item">
							<h4>Categories</h4>
							<ul>
								<li><a href="#">All</a></li>
								<li><a href="#">Beauty (20)</a></li>
								<li><a href="#">Food (5)</a></li>
								<li><a href="#">Life Style (9)</a></li>
								<li><a href="#">Travel (10)</a></li>
							</ul>
						</div>
						<div class="blog__sidebar__item">
							<h4>Recent News</h4>
							<div class="blog__sidebar__recent">
								
								<c:forEach items = "${newBlogDtos}"  var = "item">
									<a href="<c:url value='/api/u/blog/blog-details/${item.id}'/>" class="blog__sidebar__recent__item">
										<div class="blog__sidebar__recent__item__pic" style="width: 20%;">
											<img src="<c:url value='/assets${item.img}'/>"  alt="">
										</div>
										<div class="blog__sidebar__recent__item__text">
											<h6>
												${item.name}
											</h6>
											<span>${item.createAt}</span>
										</div>
										
									</a> 
								</c:forEach>
								
							</div>
						</div>
						<div class="blog__sidebar__item">
							<h4>Search By</h4>
							<div class="blog__sidebar__item__tags">
								<a href="#">Apple</a> <a href="#">Beauty</a> <a href="#">Vegetables</a>
								<a href="#">Fruit</a> <a href="#">Healthy Food</a> <a href="#">Lifestyle</a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-8 col-md-7">
					<div class="row">
						<c:forEach items = "${blogDtos}"  var = "item">
							<div class="col-lg-6 col-md-6 col-sm-6">
								<div class="blog__item">
									<div class="blog__item__pic">
										<img src="<c:url value='/assets${item.img}'/>" alt="">
									</div>
									<div class="blog__item__text">
										<ul>
											<li><i class="fa fa-calendar-o"></i> ${item.createAt}</li>
											<li><i class="fa fa-comment-o"></i>5</li>
										</ul>
										<h5>
											<a href="<c:url value='/api/u/blog/blog-details/${item.id}'/>">${item.name}</a>
										</h5>
										<p>${item.title}</p>
										<a href="<c:url value='/api/u/blog/blog-details/${item.id}'/>" class="blog__btn">READ MORE <span
											class="arrow_right"></span></a>
									</div>
								</div>
							</div>
						</c:forEach>

						blogDtos
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Blog Section End -->
    <jsp:include page="footer.jsp" />

	

</body>

</html>