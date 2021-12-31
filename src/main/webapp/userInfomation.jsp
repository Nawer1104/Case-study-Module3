<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title -->
    <title>One Music - Account Overview</title>

    <!-- Favicon -->
    <link rel="icon" href="img/core-img/favicon.ico">

    <!-- Stylesheet -->
    <link rel="stylesheet" href="style.css">

</head>

<body>
<!-- Preloader -->
<div class="preloader d-flex align-items-center justify-content-center">
    <div class="lds-ellipsis">
        <div></div>
        <div></div>
        <div></div>
        <div></div>
    </div>
</div>

<!-- ##### Header Area Start ##### -->
<header class="header-area">
    <!-- Navbar Area -->
    <div class="oneMusic-main-menu">
        <div class="classy-nav-container breakpoint-off">
            <div class="container">
                <!-- Menu -->
                <nav class="classy-navbar justify-content-between" id="oneMusicNav">

                    <!-- Nav brand -->
                    <a href="index.jsp" class="nav-brand"><img src="img/core-img/logo.png" alt=""></a>

                    <!-- Navbar Toggler -->
                    <div class="classy-navbar-toggler">
                        <span class="navbarToggler"><span></span><span></span><span></span></span>
                    </div>

                    <!-- Menu -->
                    <div class="classy-menu">

                        <!-- Close Button -->
                        <div class="classycloseIcon">
                            <div class="cross-wrap"><span class="top"></span><span class="bottom"></span></div>
                        </div>

                        <!-- Nav Start -->
                        <div class="classynav">
                            <ul>
                                <li><a href="index.jsp">Home</a></li>
                                <li><a href="/allArtist">Artists</a></li>
                                <li><a href="#">Pages</a>
                                    <ul class="dropdown">
                                        <li><a href="index.jsp">Home</a></li>
                                        <li><a href="allArtists.jsp">Albums</a></li>
                                        <li><a href="allSongs.jsp">Events</a></li>
                                        <li><a href="blog.jsp">News</a></li>
                                        <li><a href="contact.jsp">Contact</a></li>
                                        <li><a href="elements.jsp">Elements</a></li>
                                        <li><a href="login.jsp">Login</a></li>
                                        <li><a href="#">Dropdown</a>
                                            <ul class="dropdown">
                                                <li><a href="#">Even Dropdown</a></li>
                                                <li><a href="#">Even Dropdown</a></li>
                                                <li><a href="#">Even Dropdown</a></li>
                                                <li><a href="#">Even Dropdown</a>
                                                    <ul class="dropdown">
                                                        <li><a href="#">Deeply Dropdown</a></li>
                                                        <li><a href="#">Deeply Dropdown</a></li>
                                                        <li><a href="#">Deeply Dropdown</a></li>
                                                        <li><a href="#">Deeply Dropdown</a></li>
                                                        <li><a href="#">Deeply Dropdown</a></li>
                                                    </ul>
                                                </li>
                                                <li><a href="#">Even Dropdown</a></li>
                                            </ul>
                                        </li>
                                    </ul>
                                </li>
                                <li><a href="/allSongs">All Songs</a></li>
                                <li><a href="blog.jsp">News</a></li>
                                <li><a href="contact.jsp">Contact</a></li>
                            </ul>

                            <!-- Login/Register & Cart Button -->
                            <div class="login-register-cart-button d-flex align-items-center">
                                <!-- Login/Register -->
                                <c:if test="${sessionScope.acc == null}">
                                    <div class="login-register-btn mr-50">
                                        <a href="login.jsp" id="loginBtn">Login </a>
                                    </div>

                                    <div class="login-register-btn mr-50">
                                        <a href="registration.jsp" id="registerBtn"> Register</a>
                                    </div>
                                </c:if>

                                <c:if test="${sessionScope.acc != null}">
                                    <div class="header__navbar-item header__navbar-user">
                                        <img src="${sessionScope.acc.uimg}" alt="" class="header__navbar-user-img">
                                        <div class="login-register-btn mr-50">
                                            <a href="/info?userAcc=${sessionScope.acc.uid}">${sessionScope.acc.uname}</a>
                                        </div>
                                        <div class="login-register-btn mr-50">
                                            <a href="/logout">Log Out</a>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                        <!-- Nav End -->

                    </div>
                </nav>
            </div>
        </div>
    </div>
</header>
<!-- ##### Header Area End ##### -->

<!-- ##### Breadcumb Area Start ##### -->
<section class="breadcumb-area bg-img bg-overlay" style="background-image: url(img/bg-img/breadcumb.jpg);">
    <div class="bradcumbContent">
        <p>Profile</p>
        <h2>Account overview</h2>
    </div>
</section>
<!-- ##### Breadcumb Area End ##### -->

<!-- ##### Contact Area Start ##### -->
<section class="contact-area section-padding-100-0">
    <div class="container">
        <div class="row">

            <div class="col-12">
                <div class="contact-form-area">
                    <form action="/editUser" method="post">
                        <div class="row">
                            <input name="userId" value="${user.uid}" style="display: none">
                            <div class="col-md-6 col-lg-4">
                                <div class="form-group">
                                    <input name="userName" type="userName" class="form-control" value="${user.uname}">
                                </div>
                            </div>
                            <div class="col-md-6 col-lg-4">
                                <div class="form-group">
                                    <input type="email" class="form-control" id="email" value="${user.uaccount}" readonly>
                                </div>
                            </div>
                            <div class="col-lg-4">
                                <div class="form-group">
                                    <input name="userPass" type="password" class="form-control" value="${user.upassword}" >
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="message" placeholder="${user.ispremium == 1 ? "Your Plan is :One Sound Premium" : "Your Plan is :One Sound Free"}" readonly>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="form-group">
                                    <input type="text" name="code" class="form-control" placeholder="Type secret code here to update your plan to Premium">
                                </div>
                            </div>
                            <div class="_1aIEbS">
                                <div class="X1SONv">
                                    <div class="_1FzaUZ">
                                        <img class="TgSfgo" src="/${user.uimg}">
                                    </div>
                                    <input type="file" name="userImg" id="imageUpload" class="_2xS5eV"/>
                                    <label for="imageUpload" class="btn btn--normal">Select profile Picture</label>
                                </div>
                            </div>
                            <div class="col-12 text-center">
                                <button class="btn oneMusic-btn mt-30" type="submit">Confirm<i class="fa fa-angle-double-right"></i></button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

        </div>
    </div>
</section>
<!-- ##### Contact Area End ##### -->

<!-- ##### Contact Area Start ##### -->
<section class="contact-area section-padding-0-100">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="section-heading">
                </div>
            </div>
        </div>
    </div>
</section>
<!-- ##### Contact Area End ##### -->

<!-- ##### Footer Area Start ##### -->
<footer class="footer-area">
    <div class="container">
        <div class="row d-flex flex-wrap align-items-center">
            <div class="col-12 col-md-6">
                <a href="#"><img src="img/core-img/logo.png" alt=""></a>
                <p class="copywrite-text"><a href="#"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                    Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
                    <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></a></p>
            </div>

            <div class="col-12 col-md-6">
                <div class="footer-nav">
                    <ul>
                        <li><a href="#">Home</a></li>
                        <li><a href="#">Albums</a></li>
                        <li><a href="#">Events</a></li>
                        <li><a href="#">News</a></li>
                        <li><a href="#">Contact</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</footer>
<!-- ##### Footer Area Start ##### -->

<!-- ##### All Javascript Script ##### -->
<!-- jQuery-2.2.4 js -->
<script src="js/jquery/jquery-2.2.4.min.js"></script>
<!-- Popper js -->
<script src="js/bootstrap/popper.min.js"></script>
<!-- Bootstrap js -->
<script src="js/bootstrap/bootstrap.min.js"></script>
<!-- All Plugins js -->
<script src="js/plugins/plugins.js"></script>
<!-- Active js -->
<script src="js/active.js"></script>
</body>

</html>