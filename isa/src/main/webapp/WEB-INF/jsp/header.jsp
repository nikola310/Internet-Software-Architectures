<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>openForum</title>
<meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
<link rel="apple-touch-icon" href="images/icon.ico" type="image/x-icon" />
<link rel="apple-touch-icon-precomposed" href="images/icon.ico"
	type="image/x-icon" />
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="images/icon.ico" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="../css/style.css" />
</head>
<%@ page import="beans.*"%>
<%@ page import="servlets.*"%>
<body>
	<jsp:useBean id="forum" class="beans.Forum" scope="application"></jsp:useBean>

	<div id="header">
		<div id="header_inner" class="fixed">
			<div id="logo">
				<h1>
					<span>open</span>Forum
				</h1>
				<h2>Be open minded.</h2>
			</div>
			<div id="menu">
				<ul>
					<li><a href="index.jsp" class="active">Home</a></li>
					<li><a href="peoples.jsp" class="active">Peoples</a></li>
					<li><a href="#" class="active">About </a></li>
				</ul>
			</div>
		</div>
	</div>
	<div id="main">
		<div id="main_inner" class="fixed">
			<div id="primaryContent_2columns">
				<div id="columnA_2columns">
					<br class="clear" />