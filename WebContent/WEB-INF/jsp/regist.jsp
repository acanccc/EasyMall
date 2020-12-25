<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>欢迎注册EasyMall</title>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/regist.css"/>
		<script  type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-1.4.2.js"></script>
		<script type="text/javascript">
			/*注册表单的js校验*/
			var formobj={
				"checkForm":function() {
					//1.非空校验				
					var res1=this.checkNull("username", "用户名不能为空！");
					var res2=this.checkNull("password", "密码不能为空！");
					var res3=this.checkNull("password2", "确认密码不能为空！");
					var res4=this.checkNull("nickname", "昵称不能为空！");
					var res5=this.checkNull("email", "邮箱不能为空！");
					var res6=this.checkNull("valistr", "验证码不能为空！");
					var res7=this.checkPassword("password","两次密码输入不一致");
					var res8=this.checkEmail("email","邮箱格式不正确！");
					return res1 && res2 && res3 && res4 && res5 && res6 && res7 && res8;				
				},
				"checkNull":function(name,msg){
					var value=$("input[name='"+name+"']").val();  
					this.setMsg(name,"");
					if(value.trim()==""){
						this.setMsg(name, msg);
						return false;
					}
					return true;
				},
				/* 设置错误提示消息  */
				"setMsg":function(name,msg){
					var $span=$("input[name='"+name+"']").nextAll("span");	
					$span.html(msg);
					$span.css("color","red");
				},
				
				//2.两次密码是否一致校验
				"checkPassword":function(name,msg){
					var pwd=$("input[name='"+name+"2']").val().trim();
					var pwd2=$("input[name='"+name+"']").val().trim();
					
				  	if( pwd!="" && pwd2!=""){
				  		//清空之前的消息。
		//				this.setMsg(name+"2","");
				  		if(pwd!= pwd2){
							this.setMsg(name+"2", msg);
							return false;
						}
				  	}
				  	return true;
				},
				//3.邮箱格式校验
				"checkEmail":function(name,msg){
					var email=$("input[name='"+name+"']").val().trim();
					var regExp=/^\w+@\w+(\.\w+)+$/;
					if(email!=""){
						if(!regExp.test(email)){
							this.setMsg(name, msg);
							return false;
						}
					}
					return true;
				}
			}
			
		
			// 请求发送验证码
			function requestSendEmail(obj) { // 发送邮箱验证码的按钮对象
				var isNotNull = formobj.checkNull("email", "邮箱不能为空！"); // 非空返回true
				var isEmail = formobj.checkEmail("email", "邮箱格式不正确！");
				if (isNotNull && isEmail) {
					// 立马开始倒计时，而不是等到异步请求成功之后才倒计时
					var timer = countDown(obj);	
					var email = $("input[name='email']").val();
					
					$.ajax({
						url: '${pageContext.request.contextPath}/sendEmail',
						type: 'POST',
						data: {
							email: email
						},
						success: function(res) {
							console.log(res);
							if (res.code === 2000) {
								// 发送成功
							} else {
								// 提示错误信息
								$(obj).siblings('span').html(res.msg);
							}
						}
					});
				} else {
					$(obj).attr('disabled',"true");
				}
			}
			
			// 60s倒计时	
			function countDown(obj) {
				// 点击之后立马禁用，防止用户重复多次点击
				$(obj).attr('disabled', true); 
				var seconds = 60;
				$(obj).val(seconds + ' s');
				var timer = setInterval(function () { // 先等1秒，再执行回调函数
					seconds--;
					if (seconds <= 0) {
						clearInterval(timer);
						$(obj).val('发送验证码');
						$(obj).removeAttr("disabled");
					} else {
						$(obj).val(seconds + ' s');
					}
				}, 1000);
				return timer;
			}
		
			/* 点击图片换一张验证码  */
			//浏览器只要发现图片的src地址变化，图片就会变化。
			$(function(){

				
				$("#img").click(function(){
					$(this).attr("src","${ pageContext.request.contextPath }/index/valiImage?time="+new Date().getTime());
				});
				
				//给所有输入框添加失去输入焦点的事件  当失去输入焦点时检查输入是否为空或者两次密码是否一致，或者邮箱格式是否正确。
				$("input[name='username']").blur(function(){
					if(!formobj.checkNull("username", "用户名不能为空！")){
						return false;						
					}else{
						var url="${ pageContext.request.contextPath }/user/checkUser";
						var username=$("input[name='username']").val();
						$.post(url,{"username":username},
							function(data){
								$("#username_msg").html(data);
							}
						);
					}
				});
				
				// 发送验证码的按钮是否正在倒计时
				var isCountDown = false;
				
				$("input[name='password']").blur(function(){
					formobj.checkNull("password", "密码不能为空！");	
					formobj.checkPassword("password","两次密码输入不一致");
				});
				$("input[name='password2']").blur(function(){
					formobj.checkNull("password2", "确认密码不能为空！");		
					formobj.checkPassword("password","两次密码输入不一致");
				});
				$("input[name='nickname']").blur(function(){
					formobj.checkNull("nickname", "昵称不能为空！");
				});
				$("input[name='email']").blur(function(){
					var isNotNull = formobj.checkNull("email", "邮箱不能为空！"); // 非空返回true
					var isEmail = formobj.checkEmail("email", "邮箱格式不正确！");
					if (isNotNull && isEmail) {
						if (!isCountDown) {
							$('#sendEmail').removeAttr('disabled');
						}
					} else {
						$('#sendEmail').attr('disabled',"true");
					}
				});
				$("input[name='valistr']").blur(function(){
					formobj.checkNull("valistr", "验证码不能为空！");
				});
				
			});
			
			
			
			</script>
	</head>
	<body>
	<!-- onsubmit事件在表单提交时触发，该事件会根据返回值决定是否提交表单，  
	          如果onsubmit="return true"会继续提交表单，如果onsubmit="return false" 
	          表单将不会提交 
	     onsubmit="" 引号中报错并不是因为代码有问题，而是MyEclipse工具在检查语法认为这个代码有问题。其实没有错误     
	-->
		<form onsubmit="return formobj.checkForm();" action="${ pageContext.request.contextPath }/user/regist" method="POST">
			<h1>欢迎注册EasyMall</h1>
			<table>
				<tr>
					<td colspan="2" style="color:red;text-align:center;"></span>${ msg }</td>
				</tr>
			
				<tr>
					<td class="tds">用户名：</td>
					<td>
						<input type="text" name="username" value="${ param.username }"/>
						<span id="username_msg"></span>
					</td>
					
				</tr>
				<tr>
					<td class="tds">密码：</td>
					<td>
						<input type="password" name="password"  value="${ param.password }"/>
						<span></span>
					</td>
				</tr>
				<tr>
					<td class="tds">确认密码：</td>
					<td>
						<input type="password" name="password2" value="${ param.password2 }"/>
						<span></span>
					</td>
				</tr>
				<tr>
					<td class="tds">昵称：</td>
					<td>
						<input type="text" name="nickname"  value="${ param.nickname }"/>
						<span></span>
					</td>
				</tr>
				<tr>
					<td class="tds">邮箱：</td>
					<td>
						<input type="text" name="email" value="${ param.email }"/>
						<input type="button" id="sendEmail" disabled value="发送验证码"
							onClick="requestSendEmail(this)" style="width:84px;" />
						<span style="color: red;"></span>
					</td>
				</tr>
				<tr>
					<td class="tds">验证码：</td>
					<td>
						<input  type="text" name="valistr" value=""/>
						<!--  
						<img id="img"  src="${ pageContext.request.contextPath }/index/valiImage"/>
						<span></span>
						-->
					</td>
				</tr>
				<tr>
					<td class="sub_td" colspan="2" class="tds">
						<input type="submit" value="注册用户"/>
					</td>
				</tr>
			</table>
		</form>
		
	</body>
</html>