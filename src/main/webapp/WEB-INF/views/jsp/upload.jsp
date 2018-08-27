<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Touro User Creation</title>
<style>
body {
    background-color: #0A6EA4;
}

h1 {
    color: white;
    margin-left: 40px;
} 
div#TestMe {
    height: 100px;
    background-color: green;
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
background-color:#0A6EA4;
}
@media all and (max-width:30em){
button{
display:block;
margin:0.4em auto;
}
}
</style>

</head>
<body>

<h1>Touro User Creation TEST</h1>
<form method="POST" action="${pageContext.request.contextPath}/upload" enctype="multipart/form-data">
    <input type="file" name="file" /><br/>
    <!-- <input type="submit" value="Submit" /> -->
    <button type="submit" value="Submit" >Process Users</button>
</form>

</body>
</html>