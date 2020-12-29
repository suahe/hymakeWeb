/**
 * 静态页面分页，方便爬虫爬取。
 * daizb@hymake.com
 */
var pagination = {
		obj: null,
		j: 0,
		page: 0,
		nowPage: 0,
		listNum: 3,
		pagesLen: 0,
		pageNum: 4,
		listId: 'news',
		navId: 'pagination_page',
		init: function(){
			obj = document.getElementById(this.listId).getElementsByTagName("tr");
			j = obj.length;
			pagesLen = Math.ceil(j/this.listNum);
			pagination.upPage(0);
		},
		upPage: function(p){
			nowPage = p;
			for (var i=0; i<j; i++){
				obj[i].style.display = "none";
			}
			for (var i=p*this.listNum; i<(p+1)*this.listNum; i++){
				if(obj[i]){
					obj[i].style.display = "block";
				}
			}
			var strNav = '<nav aria-label="Page navigation example">';
			var strUl = '<ul class="pagination justify-content-center">'
			var strPrevious = '<li class="page-item"><a class="page-link" href="javascript:void(0);" onclick="pagination.upPage(0);" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>';
			
			var PageNum_2 = this.pageNum%2 == 0 ? Math.ceil(this.pageNum/2)+1 : Math.ceil(this.pageNum/2);
			var PageNum_3 = this.pageNum%2 == 0 ? Math.ceil(this.pageNum/2) : Math.ceil(this.pageNum/2)+1;
			var startPage, endPage;
			if (this.pageNum >= pagesLen) {
				startPage = 0;
				endPage = pagesLen - 1;
			} else if (nowPage<PageNum_2){
				startPage = 0;
				endPage = pagesLen-1 > this.pageNum ? this.pageNum : pagesLen-1;
			} else {
				startPage = nowPage+PageNum_3 >= pagesLen ? pagesLen-this.pageNum-1 : nowPage-PageNum_2+1;
				var t = startPage + this.pageNum;
				endPage = t>pagesLen ? pagesLen-1 : t;
			}
			
			var strPage = '';
			for (var i=startPage;i<=endPage;i++){
				if (i == nowPage){
					strPage += '<li class="page-item"><a href="javascript:void(0);" class="page-link" onclick="pagination.upPage('+i+')">'+(i+1)+'</a></li>';
				} else {
					strPage += '<li class="page-item"><a href="javascript:void(0);" class="page-link" onclick="pagination.upPage('+i+')">'+(i+1)+'</a></li>';
				}
			}
			
			var strNext = '<li class="page-item"><a class="page-link" href="javascript:void(0);" onclick="pagination.upPage('+(pagesLen-1)+');" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>';
			var strUlEnd = '</ul>';
			var strNavEnd = '</nav">';
			
			document.getElementById(this.navId).innerHTML = strNav + strUl + strPrevious + strPage + strNext + strUlEnd + strNavEnd;
		}
}