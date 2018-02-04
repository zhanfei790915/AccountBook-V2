<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AfeiBook用户登录</title>
<link rel="stylesheet" type="text/css" href="../res/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../res/css/user.register.css">
<link rel="stylesheet" type="text/css" href="../res/jquery-easyui-1.5.1/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../res/jquery-easyui-1.5.1/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../res/css/public.css">
<style>  
    /*web background*/  
    .container{  
        display:table;  
        height:100%;  
    }  
    .row{  
        display: table-cell;  
        vertical-align: middle;  
    }  
    /* centered columns styles */  
    .row-centered {  
        text-align:center;  
    }  
    .col-centered {  
        display:inline-block;  
        float:none;  
        text-align:left;  
        margin-right:-4px;  
    }  
</style>

</head>
<body>
<div class="container container-fluid">
		<div class="row row-centered clearfix">
			<div class="col-sm-12 col-xs-12 col-centered">
				<ol class="breadcrumb">
					<li class="title-li"><span class="title-text">Afei Book</span></li>
				</ol>
				<form class="form-horizontal" role="form">
					<div class="form-group">
						<label class="col-sm-3 col-xs-3 control-label">帐号/邮箱：</label>
						<div class="col-sm-6 col-xs-6">
							<input class="form-control" id="account" placeholder="帐号/邮箱" maxlength="50">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-3 col-xs-3 control-label">登录密码：</label>
						<div class="col-sm-6 col-xs-6">
							<input class="form-control" type="password" id="password" placeholder="密码"  maxlength="50">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-3 col-xs-3 control-label">验证码：</label>
						<div class="col-sm-3 col-xs-3">
							<input class="form-control" id="checkCode" placeholder="请输入验证码" maxlength="4">
						</div>
						<div class="col-sm-3 col-xs-3">
							<a onclick="refreshCheckCodeButton()" href="#" id="refreshCheckCode"><img src="checkCode.action"></a>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-3 col-xs-3 control-label">记住我：</label>
						<label class="col-sm-1 col-xs-1 checkbox-inline">
							<input class="form-control" type="checkBox" id="rememberMe">
						</label>
					</div>
				    <div class="col-sm-offset-5 col-sm-6 col-xs-6 checkbox">
				        <button class="btn btn-primary btn-large" onclick="login()">登录</button>
				        <a href="showUserRegister.action" class="link1">注册</a>
				    </div>
				</form>
			</div>
		</div>
		
</div>
<div id="qrcode"></div>
</body>
<script type="text/javascript" src="../res/jquery/jquery-2.2.1.min.js"></script>
<script type="text/javascript" src="../res/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../res/layer/layer.js"></script>
<script type="text/javascript" src="../res/jquery/qrcode.js"></script>
<script type="text/javascript" src="../res/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../res/js/user.logout.js"></script>
<script type="text/javascript" src="../res/js/common/common.js"></script>
<script type="text/javascript" src="../res/js/common/validator.js"></script>
<script type="text/javascript" src="../res/js/user.login.js"></script>
<!-- easyui -->
<script type="text/javascript" src="../res/jquery-easyui-1.5.1/jquery.easyui.min.js"></script>
</html>