
var main = {
		loadHTML: function(url,contener){
			var ctr = contener;
			if(typeof contener == 'string'){
				ctr = $("#"+contener);
			}
			if(ctr){
				$(ctr).load(url+" div:first",function(response,status,xhr){
					if(status=="success"){
						$(ctr).html("");
						var context = response.substring(response.indexOf("<body"),response.indexOf("</body>")+7);
						$(ctr).html(context);
					}else{
						$(ctr).html("页面加载失败，请联系管理员。");
					}
				});
			}
		}
}