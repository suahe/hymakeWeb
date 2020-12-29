/*
 *
 *   INSPINIA - Responsive Admin Theme
 *   version 2.7.1
 *
 */

$(document).ready(function () {

    // 最小化右侧菜单
    $('.navbar-minimalize').on('click', function (event) {
    	//阻止原按钮或者链接的提交或跳转
        event.preventDefault();
        //切换class
        $("body").toggleClass("mini-navbar");
        //添加延时动画效果
        if ($('body').hasClass('mini-navbar')) {
        	$('#side-menu').removeAttr('style');
        } else {
            $('#side-menu').hide();
            setTimeout(
                function () {
                    $('#side-menu').fadeIn(400);
                }, 
            200);
        }
    });
});