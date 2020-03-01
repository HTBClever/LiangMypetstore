var currentLine = 0; // 定义上下键移动需要的参数keyup(function(event)

$("#keyWord").keyup(function(event) {
    if (event.keyCode == 13) {//enter键盘事件
        event.preventDefault();
        enterFunc();
        return;
    }

    if (event.keyCode == 38) {//方向（上）键
        currentLine--;
        changeItem();
        return;
    }

    if (event.keyCode == 40) {//方向（下）键
        event.preventDefault();
        currentLine++;
        changeItem();
        return;
    }

    var key = $("#keyWord").val();//获取输入框的内容
    if (key.replace(/\s+/g, "") == '') { //非空验证
        cleanHtml();
        return;
    }
    var time=new Date().getTime();
    $.ajax({ //ajax查询
        url : "searchProducts",
        type : "get",
        data:{keyWord:key,time:time},
        success : function(data) {
            var res=data.split(",");
            if (res.length <= 0){
                cleanHtml();
                return;
            } //若没有结果，隐藏


            var htm; //遍历结果，拼接html
            htm = "<table id='ret'>";
            for (var i = 0; i < res.length; i++) {
                htm += "<tr id='sel'><td class='line'>" + res[i]
                    + "</td></tr>";
            }
            htm += "</table>";

            $('#showProduct').show(); //显示并赋值
            $('#showProduct').html(htm);

            $('.line:first').addClass('hover');// 表格第一行默认高亮
            $('.line').hover(function() { // 鼠标匹配到的元素高亮
                $('.line').removeClass('hover');
                $(this).addClass('hover');
            }, function() {
                $(this).removeClass('hover');
            });

            $('.line').click(function() { //选中行点击事件
                $('#keyWord').val($(this).text());
                cleanHtml(); //清空操作
            })

        }
    });
});

//enter键盘事件
function enterFunc() {
    if ($(".hover").html() == null) //判断高亮显示的name是否为空
        return;
    $("#keyWord").val($(".hover").html()); //赋值给输入框
    cleanHtml(); //清空操作
}

// 方向盘调用的事件
function changeItem() {
    if (currentLine < 0) { // 此时，第一行高亮，按下了方向（上）键
        currentLine = $("#ret .line").length - 1;
    } else if (currentLine == $("#ret .line").length) { //此时，最后一行高亮，按下了方向（下）键
        currentLine = 0;
    }
    $('.line').removeClass('hover'); // 清除高亮
    $(".line:eq(" + currentLine + ")").addClass('hover'); //添加高亮
}

function cleanHtml() {
    currentLine = 0; //重新赋值
    $('#showProduct').html(""); //清空内容
    $('#showProduct').hide();//隐藏div
}
