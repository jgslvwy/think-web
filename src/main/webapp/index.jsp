<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<h2>Hello World!</h2>
<head>
<script src="common/jquery-3.3.1.js"></script>
<script src="RSA/Barrett.js"></script>
<script src="RSA/BigInt.js"></script>
<script src="RSA/RSA.js"></script>
</head>
<body>
	<form method="post" id="log">
		<table border="1">
			<tr>
				<td colspan="2">用户登陆</td>
			</tr>
			<tr>
				<td>登陆用户名：</td>
				<td><input type="text" name="name" id="name1"></td>
			</tr>
			<tr>
				<td>登陆密码：</td>
				<td><input type="password" name="password" id="passwd"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="登陆"
					onclick="login();"> <input type="reset" value="重置">
				</td>
			</tr>
		</table>
	</form>
</body>
<script type="text/javascript">
 function login(){
	 var webPath ='http://localhost:8081/think-web';
	 var userCode = document.getElementById("name1").value;
	 var password = document.getElementById("passwd").value;
	 
	 if(""==userCode||""==password){
		 alert("帐号和密码不能为空");
	  }else{
		 url= webPath+'/user/getkey';
		 $.ajax({
		      type: "post",
		      url: url,
              dataType: "json",
		      success: function (data) {
		    	  if(data!=null){
		    		     alert(data);
		    			 setMaxDigits(130);
		    			 var key = new RSAKeyPair("10001","",data);
		    			 var result = encryptedString(key, encodeURIComponent(password));
		    			 url=webPath+'/user/login'+'?name='+userCode+'&password='+result+'&publicKey='+data;
		    			 var str= "a=" + Math.random() * 1000;
		    			 url=url+"&"+str;
		    			 $.ajax({
		    			      type: "post",
		    			      url: url,
		    			      success: function (data) {
		    			           alert("success: " + data);
		    			      }
		    			 });	  
		      }
		      }
		 });
	 }
 }
</script>
</html>
