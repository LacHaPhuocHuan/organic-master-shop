<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Product Page - Admin HTML Template</title>
    <jsp:include page="head.jsp" />
    

    <!--
	Product Admin CSS Template
	https://templatemo.com/tm-524-product-admin
	-->
  </head>

  <body id="reportsPage">
    
    <jsp:include page="header.jsp" />
    <div class="container mt-5">
      <div class="row tm-content-row">
        <div class="col-sm-12 col-md-12 col-lg-8 col-xl-8 tm-block-col">
          <form action="<c:url value='/admin/product/delete-products'/>" method="post">

          <div class="tm-bg-primary-dark tm-block tm-block-products">
            <div class="tm-product-table-container">
              <table class="table table-hover tm-table-small tm-product-table">
                <thead>
                  <tr>
                    <th scope="col">&nbsp;</th>
                    <th scope="col">PRODUCT NAME</th>
                    <th scope="col">UNIT SOLD</th>
                    <th scope="col">IN STOCK</th>
                    <th scope="col">EXPIRE DATE</th>
                    <th scope="col">&nbsp;</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach items = "${productList}"  var = "item" varStatus="indexLoop">
                    <tr>
                      <th scope="row"><input type="checkbox" name="selectedId" value="${item.getId()}"  /></th>
                      <td class="tm-product-name">${item.getName()}</td>
                      <td>${item.getUnitSold()}</td>
                      <td>${item.getInStock()}</td>
                      <td>${item.getExpiredDate()}</td>
                      <td>
                        <a href="<c:url value='/admin/product/delete-product/${item.getId()}' />" class="tm-product-delete-link">
                          <i class="far fa-trash-alt tm-product-delete-icon"></i>
                        </a>
                      </td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
            </div>
            <!-- table container -->
            <a
            
              href="<c:url value='/admin/product/page-add-product' />"
              class="btn btn-primary btn-block text-uppercase mb-3">Add new product</a>
            <button class="btn btn-primary btn-block text-uppercase">
              Delete selected products
            </button>

          </div>
        </form>
        </div>
        <div class="col-sm-12 col-md-12 col-lg-4 col-xl-4 tm-block-col">
          <div class="tm-bg-primary-dark tm-block tm-block-product-categories">
            <h2 class="tm-block-title">Product Categories</h2>
            <div class="tm-product-table-container">
              <table class="table tm-table-small tm-product-table">
                <tbody>
                  <c:forEach items = "${categoryList}"  var = "item" varStatus="indexLoop">
                    <tr>
                      <td class="tm-product-name">${item.getName()}</td>
                      <!-- <td class="text-center">
                        <a href="<c:url value='/admin/product/delete-category/${item.getId()}' />" class="tm-product-delete-link">
                          <i class="far fa-trash-alt tm-product-delete-icon"></i>
                        </a>
                      </td> -->
                    </tr>
                  </c:forEach>
                 
                </tbody>
              </table>
            </div>
            <!-- table container -->
            <button class="btn btn-primary btn-block text-uppercase mb-3">
              Add new category
            </button>
          </div>
        </div>
      </div>
    </div>
    <footer class="tm-footer row tm-mt-small">
      <div class="col-12 font-weight-light">
        <p class="text-center text-white mb-0 px-4 small">
          Copyright &copy; <b>2018</b> All rights reserved. 
          
          Design: <a rel="nofollow noopener" href="https://templatemo.com" class="tm-footer-link">Template Mo</a>
        </p>
      </div>
    </footer>

   
    <script>
      $(function() {
        $(".tm-product-name").on("click", function() {
          window.location.href = "edit-product.html";
        });
      });
    </script>
    <jsp:include page="footer.jsp" />
  </body>
</html>