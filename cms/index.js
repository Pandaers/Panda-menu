
//渲染最新菜单列表

window.onload = function () {
    //获取数据
    $.ajax({
        url: url.getindexdata + 0,
        async: true,
        success: function (res) {
            var data = {
                show: {
                    newordernum: 20,
                    allordernum: 50,
                    allusernum: 200,
                    newincome: 123.134,

                },
                orderdata: [
            
                ]
            }
            data.orderdata = res.data;
            for (let index = 0; index < data.orderdata.length; index++) {
                var con = " <tr><td>" + data.orderdata[index].orderid +
                    "</td><td style='width: 30%; text-align: justify'>" + data.orderdata[index].ordercontent + "</td><td>" + data.orderdata[index].tips + "</td>"
                    + "<td>" + data.orderdata[index].seatid + "</td>"
                    + " <td><span class='badge badge-info'>" + data.orderdata[index].dishstatue + "</span><br><span class='badge badge-success'>" + data.orderdata[index].orderstatue + "</span><br><span class='badge badge-success'>" + data.orderdata[index].payway + "</span></td>" +
                    " <td><div class='sparkbar'  data.orderdata-color='#00a65a'  data.orderdata-height='20'>" + data.orderdata[index].createtime + "--" + data.orderdata[index].endtime + "</div></td></tr>"
                $("#orderlist").append(con)
            }

            //修改四个框的数值
            $("#newordernum").text(data.show.newordernum);
            $("#allordernum").text(data.show.newordernum);
            $("#allusernum").text(data.show.newordernum);
            $("#newincome").text(data.show.newordernum);
        }

    });



};

