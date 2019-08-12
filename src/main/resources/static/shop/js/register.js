	  
	function formsumit(){
		    if(spanName()& spanEmail(Email)&spanTel()
		    &spanRepwd()&pwdSpan()&spanAddrss){
		    	return true;
		    }
		        return false;
		}
	 function spanName(){
	 var name=document.getElementById("name");
     var lenname=name.value.trim().length;
    	if(lenname==0){
    		nameSpan.className="spanStyleErr"; 
    		nameSpan.innerHTML="姓名不能为空";
    		return false;
    	}else{
    		nameSpan.className="glyphicon glyphicon-ok";
    		nameSpan.innerHTML="";
    		return true;
    	}
	 }
	 
	  function pwdSpan(){
	  	var name=document.getElementById("password");
	  	var sizes=name.value.trim().length;
	  	var tests=new RegExp("[0-9a-zA-Z]*[a-zA-Z]+[0-9a-zA-Z]+");
	  	//var testsa=new RegExp(([0-9a-z]*[A-Z]+[0-9a-z]*[0-9]+){6,9});
	  	if(sizes==0)
	  	{
	  		pwdErr.className="spanStyleErr";
	  		pwdErr.innerHTML="密码不能为空";
	  		return false;
	  	}
	  	else if(sizes<6&&sizes>0)
	  	{
	  		pwdErr.className="spanStyleErr";
	  		pwdErr.innerHTML="请输入6位以上";
	  		return false;
	  	}else if(!tests.test(name.value.trim())){
	  		pwdErr.className="spanStyleErr";
	  		pwdErr.innerHTML="必须含有字母";
	  		return false;
	  	}else{ 
	  		pwdErr.className="glyphicon glyphicon-ok";
	  		pwdErr.innerHTML="";
	  		return true;
	  	}
	  }
	
    function spanRepwd(){
    	var name=document.getElementById("rePassword");
    	var namePwd=document.getElementById("password");
    	if(name.value.trim()==namePwd.value.trim())
    	{
    		spanRepwds.className="glyphicon glyphicon-ok";
    		spanRepwds.innerHTML="";
    		return true;//返回true对后面提交有用
    	}else{
    		spanRepwds.className="spanStyleErr";
    		spanRepwds.innerHTML="两次密码不一致";
    		return false;
    	}
    }
    
    function spanEmail(){
    	var name=document.getElementById("email");
        var tests=new RegExp("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.(com|cn)");
        if(!tests.test(name.value.trim())){
        	emails.className="spanStyleErr";
        	emails.innerHTML="邮箱不存在";
        	return false;
        }else{
        	emails.className="glyphicon glyphicon-ok";
        	emails.innerHTML="";
            return true;
        }
    }
    function  spanTel(){
    	var name=document.getElementById("phone");
    	var tests=new RegExp("^[0-9]{11}$");
    	 
    	if(!tests.test(name.value.trim())){
    		tels.className="spanStyleErr"; 
    		tels.innerHTML="号码不存在:";
    		return false;
    	}else{
    		tels.className="glyphicon glyphicon-ok";
    		tels.innerHTML="";
    		return true;
    	}
    }
    
    function  spanAddrss(){
    	var name=document.getElementById("addr");
    	var lenname=name.value.trim().length;
    	if(lenname==0){
    		addrSpan.className="spanStyleErr"; 
    		addrSpan.innerHTML="地址不能为空";
    		return false;
    	}else{
    		addrSpan.className="glyphicon glyphicon-ok";
    		addrSpan.innerHTML="";
    		return true;
    	}
    }
 