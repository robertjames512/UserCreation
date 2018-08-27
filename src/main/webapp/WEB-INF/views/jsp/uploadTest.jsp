<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Touro User Creation</title>
<style>
body {
    background-color: #1D97D8;
}

h1 {
    color: maroon;
    margin-left: 40px;
} 
div#TestMe {
    height: 100px;
    background-color: green;
    }
    
.bmenu{
    padding: 0px;
    margin: 0 0 10px 0;
    position: relative;
}
.bmenu li{
	font-family: "Courier New", Monospace;
    font-size: 50px;
    display: block;
}
.bmenu li a{
	color: transparent;
	display: block;
	text-transform: uppercase;
	text-shadow: 0px 0px 5px #fff;
	letter-spacing: 1px;
	-webkit-transition: all 0.3s ease-in-out;
	-moz-transition: all 0.3s ease-in-out;
	-o-transition: all 0.3s ease-in-out;
	-ms-transition: all 0.3s ease-in-out;
	transition: all 0.3s ease-in-out;
}

.bmenu:hover li a{
	text-shadow: 0px 0px 5px #0d1a3a;
}
.bmenu li a:hover{
	color: #fff;
	text-shadow: 0px 0px 1px #fff;
	padding-left: 10px;
}

a.button1{
display:inline-block;
padding:0.35em 1.2em;
border:0.1em solid #FFFFFF;
margin:0 0.3em 0.3em 0;
border-radius:0.12em;
box-sizing: border-box;
text-decoration:none;
font-family:'Roboto',sans-serif;
font-weight:300;
color:#FFFFFF;
text-align:center;
transition: all 0.2s;
}
a.button1:hover{
color:#000000;
background-color:#FFFFFF;
}
@media all and (max-width:30em){
a.button1{
display:block;
margin:0.4em auto;
}
}

button{
display:inline-block;
padding:0.35em 1.2em;
border:0.1em solid #FFFFFF;
margin:0 0.3em 0.3em 0;
border-radius:0.12em;
box-sizing: border-box;
text-decoration:none;
font-family:'Roboto',sans-serif;
font-weight:300;
color:#FFFFFF;
text-align:center;
transition: all 0.2s;
}
button:hover{
color:#000000;
background-color:#0E91D8;
}
@media all and (max-width:30em){
button{
display:block;
margin:0.4em auto;
}
}
.upload-btn-wrapper {
  position: relative;
  overflow: hidden;
  display: inline-block;
}

.upload-btn-wrapper {
  position: relative;
  overflow: hidden;
  display: inline-block;
}

.btn {
  border: 2px solid gray;
  color: gray;
  background-color: white;
  padding: 8px 20px;
  border-radius: 8px;
  font-size: 20px;
  font-weight: bold;
}

.upload-btn-wrapper input[type=file] {
  font-size: 100px;
  position: absolute;
  left: 0;
  top: 0;
  opacity: 0;
}
</style>

</head>
<body>

<h1>Touro User Creation TEST</h1>

<form method="POST" action="${pageContext.request.contextPath}/upload" enctype="multipart/form-data">
    <div class="upload-btn-wrapper">
  <button class="btn">Upload a file</button>
  <input type="file" name="myfile" />
</div>
    <!-- <input type="file" name="file" /><br/> -->
   <!--  <input type="submit" value="Process Users" /> -->
    <button type="submit" value="Submit" >Process Users</button>
</form>

<ul class="bmenu">
	<li><a href="#">About</a></li>
	<li><a href="#">Illustrations</a></li>
	<li><a href="#">Photography</a></li>
	<li><a href="#">Web Design</a></li>
	<li><a href="#">Personal Projects</a></li>
</ul>

</body>
</html>