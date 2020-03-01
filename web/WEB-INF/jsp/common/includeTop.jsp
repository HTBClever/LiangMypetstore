<%--
  Created by IntelliJ IDEA.
  User: samsung
  Date: 2019-10-03
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <link rel="StyleSheet" href="css/jpetstore.css" type="text/css"
          media="screen"/>

    <meta name="generator"
          content="HTML Tidy for Linux/x86 (vers 1st November 2002), see www.w3.org"/>
    <title>JPetStore Demo</title>
    <meta content="text/html; charset=windows-1252"
          http-equiv="Content-Type"/>
    <meta http-equiv="Cache-Control" content="max-age=0"/>
    <meta http-equiv="Cache-Control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta http-equiv="Expires" content="Tue, 01 Jan 1980 1:00:00 GMT"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta charset="UTF-8">
    <style>
        *{margin: 0px;padding: 0px;list-style: none;text-decoration: none;}
        .box{margin: 0px 0px; padding-top: 80px;background-color: white;}
        .box:after{content: "";display: block;clear: both; }
        #rightBox{float: right}
        #leftBox{float: left;width: 75%;}
        #rightBoxDiv{float: right}
        /*#rightBox li {}*/
        #leftBox .positive{display: none;}
        #leftBox .active{display: block;}
        #rightBox .active{background: yellow;color: #000;}
        #leftH .active{display: block;}
        #leftH .positive{display: none;}
    </style>
    <style type="text/css">

        #suspensionPicCss{
            position: absolute;
            left: 700px;
            top: 200px;
        }

        .hideWin{
            visibility: hidden;
            display: none;
        }


    </style>
    <script src="JavaScript/jquery.js"></script>
</head>

<body>

<div id="Header">

    <div id="Logo">
        <div id="LogoContent">
            <a href="main"><img src="images/logo-topbar.gif"/></a>
        </div>
    </div>

    <div id="Menu">
        <div id="MenuContent">

            <c:if test="${sessionScope.account==null}">
                <a href="viewSignOn"><img align="middle" name="img_cart" src="images/cart.gif"/></a>
            </c:if>
            <c:if test="${sessionScope.account!=null}">
                <a href="viewCart?account=${sessionScope.account}"><img align="middle" name="img_cart"
                                                                        src="images/cart.gif"/></a>
            </c:if>
            <img align="middle" src="images/separator.gif"/>
            <c:if
                    test="${sessionScope.account == null}">
                <a href="viewSignOn">Sign In</a>
            </c:if>
            <c:if test="${sessionScope.account != null}">
                <a href="signOut">Sign Out</a>
                <img align="middle" src="images/separator.gif"/>
                <a href="viewEditAccount">My Account</a>
            </c:if>
            <img align="middle" src="images/separator.gif"/>
            <a href="help.html">?</a>
        </div>
    </div>

    <div id="Search">
        <div id="SearchContent">
            <form action="viewSearch" method="post">
                <input type="text" name="keyWord" id="keyWord" size="14" class="inputKeyName" autocomplete="off"/>
                <input type="submit" name="searchProducts" value="Search" />
                <div id="showProduct" class=" "></div>
            </form>

        </div>
    </div>

    <div id="QuickLinks">
        <a href="viewCategory?categoryId=FISH"><img
                src="images/sm_fish.gif"/></a> <img src="images/separator.gif"/>
        <a href="viewCategory?categoryId=DOGS"><img
                src="images/sm_dogs.gif"/></a> <img src="images/separator.gif"/>
        <a href="viewCategory?categoryId=REPTILES"><img
                src="images/sm_reptiles.gif"/></a> <img
            src="images/separator.gif"/>
        <a href="viewCategory?categoryId=CATS"><img
                src="images/sm_cats.gif"/></a> <img src="images/separator.gif"/>
        <a href="viewCategory?categoryId=BIRDS"><img
                src="images/sm_birds.gif"/></a>
    </div>


    <div id="UserLog">
        <a href="viewuserlog">Return to UserLog</a>
    </div>
</div>

<div id="Content">