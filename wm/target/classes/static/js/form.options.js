(function($){
	/**
	 * 表单重置插件，解决hidden类型不能reset的问题 by 38840472@qq.com
	 * 重置表单 $('#表单编号').reset();
	 * 获取指定名称的值 $('#表单编号').getValue('字段名');
	 * 弹出选择窗口回写值到文本框， 需要重新触发验证 ：$("#表单编号").reValid('字段名');
	 */
    var defaults = {
    	name: ''
    };
    $.fn.reset = function (options) {
        var opt = $.extend({}, defaults, options);
        return this.each(function () {
			$(this).each(function(){
				this.reset();
				$(this).find("input[type='hidden']").val("");
			});
        });
    };
    $.fn.getValue = function (name) {
		return $(this).find('*[name="' + name + '"]').val();
    };
    $.fn.reValid = function (name) {
    	return $(this).data('bootstrapValidator').updateStatus(name, 'NOT_VALIDATED').validateField(name);
    }
})(jQuery);