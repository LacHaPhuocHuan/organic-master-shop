<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Product Page - Admin HTML Template</title>
    <jsp:include page="head.jsp" />
    <style>
      label[for="photo"] {
  font-size: 18px;
  font-weight: bold;
  color: #333;
  margin-right: 10px;
}

input[type="file"] {
  display: block;
  margin-top: 10px;
  border: 1px solid #ccc;
  padding: 5px;
  border-radius: 5px;
  background-color: #fff;
  color: #333;
  font-size: 16px;
  font-family: Arial, sans-serif;
}

input[type="file"]:hover,
input[type="file"]:focus {
  outline: none;
  border-color: #666;
}
table {
      margin: 0 auto;
      border-collapse: collapse;
      width: 80%;
      max-width: 800px;
      box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
    }
    
    th, td {
      text-align: left;
      padding: 12px;
      border-bottom: 1px solid #ddd;
    }
    
    th {
      background-color: #f2f2f2;
      font-weight: bold;
      color: #333;
    }
    
    tr:hover {
      background-color: #f5f5f5;
    }
    
    td:last-child {
      text-align: center;
    }
    
    img {
      max-width: 100%;
     
    }

    </style>
    <!--
	Product Admin CSS Template
	https://templatemo.com/tm-524-product-admin
	-->
  </head>

  <body id="reportsPage">
    <div class="" id="home">
      <jsp:include page="header.jsp" />

      <div class="container mt-5">
        <div class="row tm-content-row">
          <div class="col-12 tm-block-col">
            <div class="tm-bg-primary-dark tm-block tm-block-h-auto">
              <h2 class="tm-block-title" style="text-align: center;">List of Accounts</h2>
              <table>
                <thead>
                  <tr>
                    <th>Avatar</th>
                    <th>ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Role</th>
                    <th>Address</th>
                    <th>Is Non-Blocked</th>
                    <th>Created At</th>
                    <th>Date of Birth</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach items = "${listUsers}"  var = "item" varStatus="indexLoop">
                    <tr>
                      <td><img src="<c:url value='/assets/img/avatar/${item.getAvatar()}'/>" alt="Avatar"></td>
                      <td>${item.getId()}</td>
                      <td>${item.getFirstname()}</td>
                      <td>${item.getLastname()}</td>
                      <td>${item.getEmail()}</td>
                      <td>${item.getPhone()}</td>
                      <td>${item.getRole().name()}</td>
                      <td>${item.getAddress()}</td>
                      <td>${item.isNonBlocked()}</td>
                      <td>${item.getCreateAt()}</td>
                      <td>${item.getDateofBirth()}</td>
                    </tr>
                  </c:forEach>
                  <!-- Add more rows as needed -->
                </tbody>
              </table>
            </div>
          </div>
        </div>
        <!-- row -->
        <div class="row tm-content-row">
          <div class="tm-block-col tm-col-avatar">
            <div class="tm-bg-primary-dark tm-block tm-block-avatar">
              <h2 class="tm-block-title">Change Avatar</h2>
              <form action="<c:url value='/admin/account/upload-avatar'/>" method="post" enctype="multipart/form-data">
                <div class="tm-avatar-container">
                  <img
                    src="<c:url value='/assets/img/avatar/${userCurrent.getAvatar()}'/>"
                    alt="Avatar"
                    class="tm-avatar img-fluid mb-4"
                  />
                  <a href="#" class="tm-avatar-delete-link">
                    <i class="far fa-trash-alt tm-product-delete-icon"></i>
                  </a>
                </div>
                <div class="form-group">
                  <label for="photo">Upload New Photo</label>
                  <input type="file" id="file" name="file" class="form-control-file" style="    background-color: #ff000000;
                  ">
                </div>
                <button type="submit" class="btn btn-primary btn-block text-uppercase">
                  Save
                </button>
              </form>
              
        
            </div>
          </div>
          <div class="tm-block-col tm-col-account-settings">
            <div class="tm-bg-primary-dark tm-block tm-block-settings">
              <h2 class="tm-block-title">Account Settings</h2>
                <form:form action="/admin/account/change/${userCurrent.getId()}" method="post" modelAttribute="userDto" class="tm-signup-form row" >
                  <div class="form-group col-lg-6">
                    <label for="firstname">First Name</label>
                    <form:input path="firstname" id="firstname" cssClass="form-control validate" value="${userCurrent.getFirstname()}"/>
                  </div>
                  <div class="form-group col-lg-6">
                    <label for="lastname">Last Name</label>
                    <form:input path="lastname" id="lastname" cssClass="form-control validate" value="${userCurrent.getLastname()}"/>
                  </div>
                  <div class="form-group col-lg-6">
                    <label for="email">Account Email</label>
                    <form:input path="email" id="email" cssClass="form-control validate" value="${userCurrent.getEmail()}"/>
                  </div>
                  <div class="form-group col-lg-6">
                    <label for="password">Password</label>
                    <form:password path="password" id="password" cssClass="form-control validate" value="*****"/>
                  </div>
                  <div class="form-group col-lg-6">
                    <label for="rePassword">Re-enter Password</label>
                    <form:password path="rePassword" id="rePassword" cssClass="form-control validate" value=""/>
                  </div>
                  <div class="form-group col-lg-6">
                    <label for="phone">Phone</label>
                    <form:input path="phone" id="phone" cssClass="form-control validate" value="${userCurrent.getPhone()}"/>
                  </div>
                  <div class="form-group col-lg-6">
                    <label class="tm-hide-sm">&nbsp;</label>
                    <button type="submit" class="btn btn-primary btn-block text-uppercase">
                      Update Your Profile
                    </button>
                  </div>
                  <div class="col-12">
                    <button type="submit" class="btn btn-primary btn-block text-uppercase">
                      Delete Your Account
                    </button>
                  </div>
                </form:form>

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
    </div>

    <jsp:include page="footer.jsp" />

  </body>
</html>
