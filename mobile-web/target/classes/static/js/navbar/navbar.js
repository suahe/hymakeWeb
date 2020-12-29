var p_s_i = 1;
$(function(){
	//top
	var top = function(){
		$(".searchBtn").click(function(){
			$(".topSearch").stop().fadeIn();
			$(".topSearchBtn").stop().fadeIn();
			$("#top .logo").stop().fadeOut();
			$("#top .stock").stop().fadeOut();
			$("#top .right").stop().fadeOut();
		})
		$(".topSearchClose").click(function(){
			$(".topSearch").stop().fadeOut();
			$(".topSearchBtn").stop().fadeOut();
			$("#top .logo").stop().fadeIn();
			$("#top .stock").stop().fadeIn();
			$("#top .right").stop().fadeIn();
		})
		
		
	}
	
	//nav
	var navT = function(e){
		$(".muen").click(function(){
			let isDisplay = $("#nav").css("display");
			if (isDisplay=="none"){
				$("#nav").fadeOut();
			}else {
				$("#nav").fadeIn();
			}
		})
		/*$(".navClose").click(function(){
			$("#nav").fadeOut();
		})*/
		
		/*$("#nav ul li h2").click(function(){
			$(".navDown").stop().slideUp();
			$(this).find("span").stop().toggleClass("on").parents("li").siblings("li").find("span").removeClass("on");
			$(this).stop().toggleClass("on").parents("li").siblings("li").find("h2").removeClass("on");
			$(this).siblings(".navDown").stop().slideToggle();
		})*/
		$("#nav ul li h2 span").click(function(){
			$(".navDown").stop().slideUp();
			$(this).stop().toggleClass("on").parents("li").siblings("li").find("span").removeClass("on");
			$(this).parents("h2").stop().toggleClass("on").parents("li").siblings("li").find("h2").removeClass("on");
			$(this).parents("h2").siblings(".navDown").stop().slideToggle();
		})
	}
	
	//footer
	var footer = function(){
		$(".footerTop ul li h3").click(function(){
			$(this).stop().toggleClass("on").siblings(".footerDowen").stop().slideToggle().parents("li").siblings("li").find("h3").removeClass("on").siblings(".footerDowen").slideUp();
		})
		
		$(".footerWeixin").click(function(){
			$(".ewmTan").fadeIn();
		})
		
		$(".ewmClose").click(function(){
			$(".ewmTan").fadeOut();
		})
		
	}
	
	
	//5iframe-IOS浏览器
//	$(".iframeTanClose").click(function(){
//		$(this).parents(".iframeTan").fadeOut();
//	})
		$("body,body > *").click(function(){
			$(".iframeTan").fadeOut();
		})

	//下拉
	var selectDown=function(){
		$(".selectW").click(function(event){
			$(this).find(".selectDown").slideToggle().siblings(".selBtn").toggleClass("active");
			$(this).parents(".teacherHWInner").siblings(".teacherHWInner").find(".selectDown").slideUp().siblings(".selBtn").removeClass("active");
			event.stopPropagation();
		})
		$(".selectDown ul li").click(function(){
			$(this).parents(".selectDown").siblings("input").val($(this).text());
		})
		$("body").click(function(){
			$(".selectDown").slideUp();
			$(".selBtn").removeClass("active");
		})

		$(".teacherChoose a").click(function(){
			$(this).addClass("on").siblings("a").removeClass("on");
		})
	}

	//产品
	var proT = function(){
		$(".proTopLabel a").click(function(){
			$(this).addClass("on").siblings("a").removeClass("on");
		})


		$(".proTopB a").click(function(){
			if(p_s_i==1){
				$(this).stop().addClass("on");
				$(this).find("em").text("收起全部类型")
				$(".proTopLabel").css({"height":'auto'});
				p_s_i=0;
			}else if(p_s_i==0){
				$(this).stop().removeClass("on");
				$(this).find("em").text("展开全部类型");
				$(".proTopLabel").css({"height":'1.6rem'});
				p_s_i=1;
			}
		})
	}

	top();      //top
	footer();      //footer
	navT();      //nav
	selectDown();    //下拉
	proT();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
})