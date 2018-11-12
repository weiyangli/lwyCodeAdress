/*!
 *@name     jquery.barrager.js
 *@version  1.1
 *@author   yaseng@uauc.net
 *@url      https://github.com/yaseng/jquery.barrager.js
 */
(function ($) {
    // 弹幕背景色
    var colors = ['#ff4d4f', '#ff7a45', '#ffa940', '#bae637', '#faad14',
                  '#73d13d', '#36cfc9', '#40a9ff', '#9254de', '#f759ab'];

    $.fn.barrager = function (barrage) {
        barrage = $.extend({
            close: true,
            top: 0,
            max: 10,
            speed: 6,
            color: '#fff',
            old_ie_color: '#000000'
        }, barrage || {});

        // 计算弹幕位置
        var boxHeight = 42; // 一条弹幕的高度40px, 留margin，则在原基础增加2px
        var barrager_id = 'barrage_' + Date.now();
        var id = '#' + barrager_id;
        var this_height = this.height();
        var this_width = this.width();
        var rowCount = Math.floor(this_height / (boxHeight) ); // 能装几个高度不重叠的弹幕
        var pos = typeof(window.barragerPos) == 'undefined' ? 0 : window.barragerPos + 1; // 当前弹幕的位置，页面刷新后从0开始
        if (pos >= rowCount) {
            pos = 0;
        }
        window.barragerPos = pos;
        var top = barrage.top == 0 ? pos * (boxHeight) : barrage.top;

        // 创建弹幕容器
        var div_barrager = $("<div class='barrage' id='" + barrager_id + "'></div>").appendTo($(this));
        div_barrager.css("top", top + "px");
        div_barrager_box = $("<div class='barrage_box'></div>").appendTo(div_barrager);
        
        if (barrage.close) {
            div_barrager_box.append(" <div class='close'></div>");
        }

        if (barrage.img) {
            div_barrager_box.append("<a class='portrait' href='javascript:;'></a>");
            var img = $("<img src='' >").appendTo(id + " .barrage_box .portrait");
            img.attr('src', barrage.img);
        }
        
        var content = $("<div class='content'></div>").appendTo(id + " .barrage_box");
        content.attr({ 'title': barrage.info });
        content.attr({ 'id': barrage.id }).empty().append(barrage.info);
        if (navigator.userAgent.indexOf("MSIE 6.0") > 0 || navigator.userAgent.indexOf("MSIE 7.0") > 0 || navigator.userAgent.indexOf("MSIE 8.0") > 0) {
            content.css('color', barrage.old_ie_color);
        } else {
            content.css('color', barrage.color);
        }

        div_barrager.css('margin-right', 0);

        // color 
        var colorIndex = Math.floor(Math.random() * colors.length);
        div_barrager_box.css('background-color', colors[colorIndex] + 'CD');

        $(id).animate({
            right: this_width
        }, barrage.speed * 1000, function () {
            $(id).remove();
        });

        div_barrager_box.mouseover(function () {
            $(id).stop(true);
        });

        div_barrager_box.mouseout(function () {
            $(id).animate({
                right: this_width
            }, barrage.speed * 1000, function () {
                $(id).remove();
            });
        });

        $(id + '.barrage .barrage_box .close').click(function () {
            $(id).remove();
        })
    }

    $.fn.barrager.removeAll = function () {
        $('.barrage').remove();
    }

})(jQuery);
