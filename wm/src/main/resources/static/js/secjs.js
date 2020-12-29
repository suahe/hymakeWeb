/**
 * 用于在Ajax请求后台数据时主动添加页面令牌（Token）值
 * 38840472@qq.com 2018-03-23
 */
var secjs={
	init:function(){
		var header = $("meta[name='_csrf_header']").attr("content");
		var token = $("meta[name='_csrf']").attr("content");
		$(document).ajaxSend(function(e, xhr, settings) {
		    xhr.setRequestHeader(header, token);
		});
	}
}