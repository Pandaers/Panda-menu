function addfood(e) {
   
    console.log(e)
    $.ajax({
        url: url.addfood,
        async: true,
        data: {
            id: 0,
            storeid: storeid,
            catid:1,
            name: $("#name").val(),
            price: $("#price").val(),
            originalprice: $("#originalprice").val(),
            detail: $("#detail").val(),
            addtime: new Date(),
            status: 0,
            score:$("#score").val(),
            isdelete: 0,
            virtualsales: $("#virtualsales").val(),
            compressimg: $("#compressimg").val(),
            img: $("#img").val(),
            videourl: $("#videourl").val(),
            realsales: $("#realsales").val(),
            goodsnums: $("#goodsnums").val(),
            catname: "test",
        },
        success: function (res) {
            console.log(res)
            $("#addfood").hide();
            $("#content").show();
        }
    });

}

function toaddfood() {
    $("#addfood").show();
    $("#content").hide();
}

window.onload = function () {

    $.ajax({
        url: url.getmenudata + storeid,
        async: true,
        success: function (res) {
            console.log(res.data)
            var str = ""
            for (let index = 0; index < res.data.length; index++) {
                res.data[index].status = res.data[index].status == 1 ? "上架" : "未上架"

                var con =
                    " <tr>" +
                    "<td>" + res.data[index].name + "</td>" +
                    "<td>" + res.data[index].catname + "</td>" +
                    "<td><img src='" + res.data[index].compressimg +
                    "' style='width:60px;height:60px;'></td>" +
                    "<td>" + res.data[index].realsales + "</td>" +
                    "<td>" + res.data[index].detail + "</td>" +
                    "<td> <span class='badge badge-warning' style='background:red;'>" + res.data[index].status + "</span><br></td>" +
                    "<td>" + res.data[index].price + "</td>" +
                    " <td> <button id=" + res.data[index].id + "  onclick='editfood(this)'  type='button' class='btn btn-default btn-sm'><i class='fa fa-edit'></i></button>" +
                    "<button id=" + res.data[index].id + " onclick='delefood(this)'  type='button' class='btn btn-default btn-sm'><i class='fa  fa-trash-o'></i></button>" +
                    "</tr>"


                str = str + con
            }
            $("#menulist").html(str)


        }
    }
    )


}


function editfood(e) {
    console.log(e.id)
    $.ajax({
        url: url.SingleMenu + storeid + "&id=" + e.id,
        async: true,
        success: function (res) {
            res = res.data
            $("#editfood").show();
            $("#content").hide();

            var con = "<div  style='width:80%;margin-left:10%;'>" +
                "<div class='card card-primary'>" +
                "<div class='card-header'>" +
                "<h3 class='card-title'>修改菜单</h3>" +
                "</div>" +
                "<div class='card-body '>" +
                "<div class='form-group'>" +
                "<label>分类名称</label>" +
                "<input type='text' class='form-control' id='catname' value='" + res.catname + "' />" +
                "</div>" +
                "<div class='form-group'>" +
                "<label>菜品名称</label>" +
                "<input type='text' class='form-control' id='name' value='" + res.name + "' />" +
                "</div>" +
                "<div class='form-group'>" +
                "<label>菜品售价</label>" +
                "<input type='number' class='form-control' id='price' value='" + res.price + "' />" +
                "</div>" +
                "<div class='form-group'>" +
                "<label>菜品虚拟售价</label>" +
                "<input type='number' class='form-control' id='originalprice' value='" + res.originalprice + "' />" +
                "</div>" +
                "<div class='form-group'>" +
                "<label>菜品介绍</label>" +
                "<input type='text' class='form-control' id='detail' value='" + res.detail + "' />" +
                "</div>" +
                "<div class='form-group'>" +
                "<label>菜品虚拟销量</label>" +
                "<input type='number' class='form-control' id='realsales' value='" + res.realsales + "' />" +
                "</div>" +
                "<div class='form-group'>" +
                "<label>菜品评分</label>" +
                "<input type='number' class='form-control' id='score' value='" + res.score + "' />" +
                "</div>" +
                "<div class='form-group'>" +
                "<label>菜品图片</label>" +
                "<div class='input-group'>" +
                "<div class=''>" +
                "<input type='file'   class='form-control' id='compressimg' value='" + res.compressimg + "' />" +
                "</div>" +
                "</div>" +
                "</div>" +
                "<div class='form-group'>" +
                "<label>菜品视频</label>" +
                "<input type='text' class='form-control' id='videourl' value='" + res.videourl + "' />" +
                "</div>" +
                "<div class='form-group'>" +
                "<label>菜品数量</label>" +
                "<input type='number' class='form-control' id='goodsnums' value='" + res.goodsnums + "' /> " +
                "</div>" +
                "<div class='form-group'>" +
                "<label>上架操作</label>" +
                "<select class='form-control'  value ='0' id='status'>" +
                "<option>上架</option>" +
                "<option>不上架</option>" +
                "</select>" +
                "</div>" +
                "</div>" +
                "<div class='card-footer'>" +
                "<button  class='btn btn-primary' onclick='addfood()'>确定</button>" +
                "<button  class='btn btn-primary' onclick='addfood()' style='margin-left:30px'>取消</button>" +
                "</div>" +
                "</div>" +
                "</div>";

            $("#editfood").html(con);

        }

    });
}