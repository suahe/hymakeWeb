/**
 * Bootstrap Table Chinese translation
 * Author: Zhixin Wen<wenzhixin2010@gmail.com>
 */
(function ($) {
    'use strict';

    $.fn.bootstrapTable.locales['zh-CN'] = {
        formatLoadingMessage: function () {
            return '加载中……';
        },
        formatRecordsPerPage: function (pageNumber) {
        	return ' 每页 ' + pageNumber + ' 条';
//            return '每页 显示 ' + pageNumber + ' 条记录';
            //return ' ' + pageNumber + '';
        },
        formatShowingRows: function (pageFrom, pageTo, totalRows) {
        	return '' + pageFrom + ' 到 ' + pageTo + '  共 ' + totalRows + ' 条';
           // return '显示第 ' + pageFrom + ' 到第 ' + pageTo + ' 条记录，总共 ' + totalRows + ' 条记录';
            //return '';
        },
        formatSearch: function () {
            return '搜索';
        },
        formatNoMatches: function () {
            return ' 查无此记录，请重新搜索。';
        },
        formatPaginationSwitch: function () {
            return '隐藏/显示分页';
        },
        formatRefresh: function () {
            return '刷新';
        },
        formatToggle: function () {
            return '切换';
        },
        formatColumns: function () {
            return '列';
        },
        formatExport: function () {
            return '导出数据';
        },
        formatClearFilters: function () {
            return '清空过滤';
        }
    };

    $.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales['zh-CN']);

})(jQuery);
