(function ($) {
	/**
	 * 数据字典封装插件，依赖select2.js。by 38840472@qq.com
	 */
    var defaults = {
    		code: '-'
    		,width: 200
    		,placeholder: "请选择"
    		,value: "-"
    };

    $.fn.btSelect = function (options) {
        var opt = $.extend({}, defaults, options);
        return this.each(function () {
        	$(this).select2({
        		language: "zh-CN"
        		,theme: "bootstrap"
        		,width: opt.width
        		,placeholder: opt.placeholder
        		,allowClear: true
        		,minimumResultsForSearch: -1
        		,ajax: {
        			url: '../btc/getListByCode',
        			type: "GET",
        			dataType: 'json',
        			data: function (params) {
    					return {
    						code: opt.code
    					};
    			    },
        			processResults: function(data){
        				return {
        					results: data
        			    };
        			}
        		}
            });
        });
    };
    $.fn.btSelected = function(options) {
        var options = $.extend(defaults, options);
        return this.each(function () {
        	var selectObject = $(this)
			$.ajax({
                type: 'GET',
                async: true,
                url: '../btc/getListNameByCodeAndValue',
                data: {'code': defaults.code, 'value': defaults.value},
                dataType: 'json',
                async: false,
                success: function(r){
                	var option = new Option(r.name, r.value, true, true);
                	selectObject.append(option).trigger('change');
                }
			});
        });
    };
    $.fn.btSelectInit = function () {
    	$(this).select2("val", " ");
    }
})(jQuery);