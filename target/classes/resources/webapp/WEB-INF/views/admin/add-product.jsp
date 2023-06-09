<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Add Product - Dashboard HTML Template</title>
    <jsp:include page="head.jsp" />

    <!--
	Product Admin CSS Template
	https://templatemo.com/tm-524-product-admin
	-->
  </head>

  <body>
    <jsp:include page="header.jsp" />

    <div class="container tm-mt-big tm-mb-big">
      <div class="row">
        <div class="col-xl-9 col-lg-10 col-md-12 col-sm-12 mx-auto">
          <div class="tm-bg-primary-dark tm-block tm-block-h-auto">
            <div class="row">
              <div class="col-12">
                <h2 class="tm-block-title d-inline-block">Add Product</h2>
              </div>
            </div>
           
            <form action="<c:url value='/admin/product/add'/>"  class="tm-edit-product-form" enctype="multipart/form-data" method="post">
              <div class="row tm-edit-product-row">
                <div class="col-xl-6 col-lg-6 col-md-12">
                      <div class="form-group mb-3">
                          <label for="name">Product Name</label>
                          <input id="name" name="name" type="text" class="form-control validate" required="true"/>
                      </div>
  
                      <div class="form-group mb-3">
                        <label for="category">Category</label>
                        <select id="category" class="custom-select tm-select-accounts" name="idCategory">
                          <c:forEach items="${categoryList}" var="item" varStatus="indexLoop">
                            <option value="${item.getId()}"   >${item.name}</option>
                          </c:forEach>
                        </select>
                      </div>
                      
                      <div class="row">
                          <div class="form-group mb-3 col-xs-12 col-sm-6">
                              <label for="expire_date">Expire Date</label>
                              <input id="expire_date" type="text" name="date" class="form-control validate" data-large-mode="true"/>
                          </div>
                          <div class="form-group mb-3 col-xs-12 col-sm-6">
                              <label for="stock">Units In Stock</label>
                              <input  type="number"  id="stock" name="quantity"  class="form-control validate" required="true"/>
                          </div>
                          
                      </div>
                      <div class="form-group mb-3">
                        <label for="price">Prce</label>
                        <input  type="number"  id="price" name="price"  class="form-control validate" required="true"/>
                      </div>
                  
                    
                </div>
                <div class="col-xl-6 col-lg-6 col-md-12 mx-auto mb-4">
                  <div class="form-group">
                    <label for="photo">Upload New Photo</label>
                    <input type="file" name="file" id="file"/><br/>
                  </div>
                </div>
                <div class="col-12">
                  <button type="submit" class="btn btn-primary btn-block text-uppercase">Add Product Now</button>
                </div>
  
              </div>
              </form>
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

    <jsp:include page="footer.jsp" />

    <script>
      $(function() {
        $("#expire_date").datepicker();
      });
    </script>
  </body>
</html>
