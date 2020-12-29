(function ($) {
	/**
	 * 静态页面分页插件，方便爬虫爬取数据。by daizb@hymake.com
	 */
    var defaults = {
    		obj: new Object(),
    		listNum: 10,	//每页条数
    		pageNum: 4,		//分页栏的页数个数
    		pageBar: 'page_bar'		//分页栏位置
    };
    var updatePage = function (pages) {
    	
    	var _pageNum = defaults.pageNum;
    	var _pagesLen = defaults.pagesLen;
    	
    	nowPage = pages;
		for (var i=0; i<defaults.listLength; i++){
			defaults.obj[i].style.display = "none";
		}
		for (var i=pages*defaults.listNum; i<(pages+1)*defaults.listNum; i++){
			if(defaults.obj[i]){
				defaults.obj[i].style.display = "block";
			}
		}
		
		var strNav = '<nav aria-label="Page navigation example">';
		var strUl = '<ul class="pagination justify-content-center">'
		var strPrevious = '<li class="page-item"><a id="_previous" class="page-link" href="javascript:void(0);" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>';
		
		var PageNum_2 = _pageNum%2 == 0 ? Math.ceil(_pageNum/2)+1 : Math.ceil(_pageNum/2);
		var PageNum_3 = _pageNum%2 == 0 ? Math.ceil(_pageNum/2) : Math.ceil(_pageNum/2)+1;
		var startPage, endPage;
		if (_pageNum >= _pagesLen) {
			startPage = 0;
			endPage = _pagesLen - 1;
		} else if (nowPage<PageNum_2){
			startPage = 0;
			endPage = _pagesLen-1 > _pageNum ? _pageNum : _pagesLen-1;
		} else {
			startPage = nowPage+PageNum_3 >= _pagesLen ? _pagesLen-_pageNum-1 : nowPage-PageNum_2+1;
			var t = startPage +_pageNum;
			endPage = t>_pagesLen ? _pagesLen-1 : t;
		}
		
		var strPage = '';
		for (var i=startPage;i<=endPage;i++){
			
			strPage += '<li class="page-item"><a href="javascript:void(0);" class="page-link _plate" >'+(i+1)+'</a></li>';
		}
		
		var strNext = '<li class="page-item"><a id="_next" class="page-link" href="javascript:void(0);" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>';
		var strUlEnd = '</ul>';
		var strNavEnd = '</nav">';
		
		$('#'+defaults.pageBar)[0].innerHTML = strNav + strUl + strPrevious + strPage + strNext + strUlEnd + strNavEnd;
		
		$('#_previous').on('click', function(){
			updatePage(0);
		});
		
		$('#_next').on('click', function(){
			updatePage(_pagesLen-1);
		});
		
		$('._plate').on('click', function(){
			var _page = Math.ceil($(this).text()-1);
			updatePage(_page);
		});

    }

    $.fn.page = function (options) {

/*        var options = $.extend(defaults, options);*/
        var opt = $.extend({}, defaults, options);

        return this.each(function () {
            
        	var obj = $(this).find('tbody').children('tr');
        	var pagesLen = Math.ceil(obj.length/opt.listNum);	
        	defaults.obj = obj;
        	defaults.listLength = obj.length;
        	defaults.pagesLen = pagesLen;
        	updatePage(0);
        });
    }
})(jQuery);