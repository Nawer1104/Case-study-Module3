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
    <title>One Music - All Artists</title>

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
    <section class="breadcumb-area bg-img bg-overlay" style="background-image: url(img/bg-img/breadcumb3.jpg);">
        <div class="bradcumbContent">
            <p>See what’s new</p>
            <h2>All Artists</h2>
        </div>
    </section>
    <!-- ##### Breadcumb Area End ##### -->

    <!-- ##### Album Catagory Area Start ##### -->
    <section class="album-catagory section-padding-100-0">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="browse-by-catagories catagory-menu d-flex flex-wrap align-items-center mb-70">
                        <a href="/allArtist" data-filter="*">Browse All</a>
                        <a href="/searchArtist?name=a" data-filter=".a" class="${likeName.equals("a") ?"active" : ""}">A</a>
                        <a href="/searchArtist?name=b" data-filter=".b" class="${likeName.equals("b") ?"active" : ""}">B</a>
                        <a href="/searchArtist?name=c" data-filter=".c" class="${likeName.equals("c") ?"active" : ""}">C</a>
                        <a href="/searchArtist?name=d" data-filter=".d" class="${likeName.equals("d") ?"active" : ""}">D</a>
                        <a href="/searchArtist?name=e" data-filter=".e" class="${likeName.equals("e") ?"active" : ""}">E</a>
                        <a href="/searchArtist?name=f" data-filter=".f" class="${likeName.equals("f") ?"active" : ""}">F</a>
                        <a href="/searchArtist?name=g" data-filter=".g" class="${likeName.equals("g") ?"active" : ""}">G</a>
                        <a href="/searchArtist?name=h" data-filter=".h" class="${likeName.equals("h") ?"active" : ""}">H</a>
                        <a href="/searchArtist?name=i" data-filter=".i" class="${likeName.equals("i") ?"active" : ""}">I</a>
                        <a href="/searchArtist?name=j" data-filter=".j" class="${likeName.equals("j") ?"active" : ""}">J</a>
                        <a href="/searchArtist?name=k" data-filter=".k" class="${likeName.equals("k") ?"active" : ""}">K</a>
                        <a href="/searchArtist?name=l" data-filter=".l" class="${likeName.equals("l") ?"active" : ""}">L</a>
                        <a href="/searchArtist?name=m" data-filter=".m" class="${likeName.equals("m") ?"active" : ""}">M</a>
                        <a href="/searchArtist?name=n" data-filter=".n" class="${likeName.equals("n") ?"active" : ""}">N</a>
                        <a href="/searchArtist?name=o" data-filter=".o" class="${likeName.equals("o") ?"active" : ""}">O</a>
                        <a href="/searchArtist?name=p" data-filter=".p" class="${likeName.equals("p") ?"active" : ""}">P</a>
                        <a href="/searchArtist?name=q" data-filter=".q" class="${likeName.equals("q") ?"active" : ""}">Q</a>
                        <a href="/searchArtist?name=r" data-filter=".r" class="${likeName.equals("r") ?"active" : ""}">R</a>
                        <a href="/searchArtist?name=s" data-filter=".s" class="${likeName.equals("s") ?"active" : ""}">S</a>
                        <a href="/searchArtist?name=t" data-filter=".t" class="${likeName.equals("t") ?"active" : ""}">T</a>
                        <a href="/searchArtist?name=u" data-filter=".u" class="${likeName.equals("u") ?"active" : ""}">U</a>
                        <a href="/searchArtist?name=v" data-filter=".v" class="${likeName.equals("v") ?"active" : ""}">V</a>
                        <a href="/searchArtist?name=w" data-filter=".w" class="${likeName.equals("w") ?"active" : ""}">W</a>
                        <a href="/searchArtist?name=x" data-filter=".x" class="${likeName.equals("x") ?"active" : ""}">X</a>
                        <a href="/searchArtist?name=y" data-filter=".y" class="${likeName.equals("y") ?"active" : ""}">Y</a>
                        <a href="/searchArtist?name=z" data-filter=".z" class="${likeName.equals("z") ?"active" : ""}">Z</a>
                    </div>
                </div>
            </div>
<%--            oneMusic-albums--%>
            <div class="row" style="position: relative">

                <!-- Single Album -->
                <c:forEach items='${requestScope["listArtists"]}' var="artist">
                    <div class="col-12 col-sm-4 col-md-3 col-lg-2 single-album-item">
                        <div class="single-album">
                            <img src="${artist.apic}" alt="">
                            <div class="album-info">
                                <a href="/songOfArtist?aid=${artist.aid}">
                                    <h5>${artist.aname}</h5>
                                </a>
                                <p>${artist.description}</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>

                <c:if test="${requestScope.Artist != null}">
                    <div class="col-12 col-sm-4 col-md-3 col-lg-2 single-album-item">
                        <div class="single-album">
                            <img src="${Artist.apic}" alt="">
                            <div class="album-info">
                                <a href="/songOfArtist?aid=${Artist.aid}">
                                    <h5>${Artist.aname}</h5>
                                </a>
                                <p>${Artist.description}</p>
                            </div>
                        </div>
                    </div>
                </c:if>

            </div>
        </div>
    </section>
    <!-- ##### Add Area Start ##### -->
    <div class="add-area mb-100">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="adds">
                        <a href="#"><img src="img/bg-img/add3.gif" alt=""></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ##### Add Area End ##### -->

    <!-- ##### Song Area Start ##### -->
    <div class="one-music-songs-area mb-70">
        <div class="container">
            <div class="row">

                <!-- Single Song Area -->
                <c:forEach items='${requestScope["listSongs"]}' var="song" begin="0" varStatus="loop">
                    <div class="col-12">
                        <div class="single-song-area mb-30 d-flex flex-wrap align-items-end">
                            <div class="song-thumbnail">
                                <img src="${song.simg}" alt="">
                            </div>
                            <div class="song-play-area">
                                <div class="song-name">
                                    <p>${loop.count}. ${song.sname}</p>
                                </div>
                                <div class="${sessionScope.acc.ispremium == 0 || sessionScope.acc == null ? "disabled" : ""}">
                                    <audio preload="auto" controls>
                                        <source src="${song.slink}">
                                    </audio>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>

            </div>
        </div>
    </div>
    <!-- ##### Song Area End ##### -->

    <!-- ##### Contact Area Start ##### -->
    <section class="contact-area section-padding-100 bg-img bg-overlay bg-fixed has-bg-img" style="background-image: url(img/bg-img/bg-2.jpg);">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="section-heading white">
                        <p>See what’s new</p>
                        <h2>Get In Touch</h2>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-12">
                    <!-- Contact Form Area -->
                    <div class="contact-form-area">
                        <form action="#" method="post">
                            <div class="row">
                                <div class="col-md-6 col-lg-4">
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="name" placeholder="Name">
                                    </div>
                                </div>
                                <div class="col-md-6 col-lg-4">
                                    <div class="form-group">
                                        <input type="email" class="form-control" id="email" placeholder="E-mail">
                                    </div>
                                </div>
                                <div class="col-lg-4">
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="subject" placeholder="Subject">
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="form-group">
                                        <textarea name="message" class="form-control" id="message" cols="30" rows="10" placeholder="Message"></textarea>
                                    </div>
                                </div>
                                <div class="col-12 text-center">
                                    <button class="btn oneMusic-btn mt-30" type="submit">Send <i class="fa fa-angle-double-right"></i></button>
                                </div>
                            </div>
                        </form>
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