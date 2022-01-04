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
    <title>One Music - Editing Page</title>

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
                    <a href="/loadData" class="nav-brand"><img src="img/core-img/logo.png" alt=""></a>

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
                                <li><a href="/loadData">Home</a></li>
                                <li><a href="/allArtist">Artists</a></li>
                                <c:if test="${sessionScope.acc != null}">
                                    <li><a href="#">Your Playlists</a>
                                        <ul class="dropdown">
                                            <li><a href="/createPlaylistView">Create Playlist</a></li>
                                            <c:forEach items='${requestScope["playList"]}' var="i">
                                                <li><a href="/userPlayListView?pid=${i.pid}">${i.pname}</a></li>
                                            </c:forEach>
                                        </ul>
                                    </li>
                                </c:if>
                                <li><a href="/allSongs">All Songs</a></li>
                                <li><a href="#">Categories</a>
                                    <ul class="dropdown">
                                        <c:forEach items='${requestScope["categories"]}' var="i">
                                            <li><a href="/songByCategory?cid=${i.cid}">${i.cname}</a></li>
                                        </c:forEach>
                                    </ul>
                                </li>
                                <li><a href="contact.jsp">Contact</a></li>
                                <c:if test="${sessionScope.acc.isadmin == 1}">
                                    <li><a href="/addSong">Add new Song</a></li>
                                </c:if>
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
        <p>let's create awesome experiences </p>
        <h2>Editing Song</h2>
    </div>
</section>
<!-- ##### Breadcumb Area End ##### -->

<!-- ##### Contact Area Start ##### -->
<section class="contact-area section-padding-100-0">
    <div class="container">
        <div class="row">

            <div class="col-12">
                <div class="contact-form-area">
                    <form action="/editSong" method="post">
                        <div class="row">
                            <p style="color: #94ee4a">${messSuccess}</p>
                            <input name="songId" value="${song.sid}" style="display: none">
                            <div class="col-12">
                                <div class="form-group">
                                    <input type="text" name="songName"  class="form-control" value="${song.sname}">
                                </div>
                            </div>

                            <div class="col-12">
                                <div class="_1aIEbS">
                                    <div class="X1SONv">
                                        <div class="_1FzaUZ">
                                            <img class="TgSfgo" src="${song.simg}">
                                        </div>
                                        <input type="file" name="songImg" id="imageUpload" class="_2xS5eV" accept="image/*"/>
                                        <label for="imageUpload" class="btn btn--normal">Song : avatar</label>
                                        <input type="file" name="songLink" id="linkUpload" class="_2xS5eV" accept="audio/*"/>
                                        <label for="linkUpload" class="btn btn--normal">Song : link</label>
                                    </div>
                                </div>
                            </div>

                            <div class="col-12">
                                <div class="form-group">
                                    <select class="form-control" name="songOfArtist" style="padding: 0">
                                        <c:forEach items="${listArtists}" var="artist">
                                            <option <c:if test="${song.aid == artist.aid}">selected="selected</c:if> value="${artist.aid}">${artist.aname}</option>
                                        </c:forEach>
                                    </select>
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
