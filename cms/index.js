
window.onload = function () {
    //渲染最新菜单列表
    var data = {
        show: {
            newordernum: 20,
            allordernum: 50,
            allusernum: 200,
            newincome: 123.134,

        },
        orderdata: [
            {
                orderid: 1000,
                ordercontent: "馒头1个，西瓜两个，德州扒鸡一个",
                ordertips: "加婉米饭",
                tablenum: 4,
                orderstate: ["未支付", "未上菜"],
                ordertime: ["2018-2-3 12:00", "未知"]
            },
            {
                orderid: 1000,
                ordercontent: "馒头1个，西瓜两个，德州扒鸡一个",
                ordertips: "加婉米饭",
                tablenum: 4,
                orderstate: ["未支付", "未上菜"],
                ordertime: ["2018-2-3 12:00", "未知"]
            },
            {
                orderid: 1000,
                ordercontent: "馒头1个，西瓜两个，德州扒鸡一个",
                ordertips: "加婉米饭",
                tablenum: 4,
                orderstate: ["未支付", "未上菜"],
                ordertime: ["2018-2-3 12:00", "未知"]
            }
        ]
    }

    for (let index = 0; index < data.orderdata.length; index++) {
        var con = " <tr><td>" + data.orderdata[index].orderid +
            "</td><td style='width: 30%; text-align: justify'>" + data.orderdata[index].ordercontent + "</td><td>" + data.orderdata[index].ordertips + "</td>"
            + "<td>" + data.orderdata[index].tablenum + "</td>"
            + " <td><span class='badge badge-info'>" + data.orderdata[index].orderstate[0] + "</span><br><span class='badge badge-success'>" + data.orderdata[index].orderstate[1] + "</span></td>" +
            " <td><div class='sparkbar'  data.orderdata-color='#00a65a'  data.orderdata-height='20'>" + data.orderdata[index].ordertime[0] + "--" + data.orderdata[index].ordertime[1] + "</div></td></tr>"
        $("#orderlist").append(con)
    }

    //修改四个框的数值
    $("#newordernum").text(data.show.newordernum); 
    $("#allordernum").text(data.show.newordernum); 
    $("#allusernum").text(data.show.newordernum); 
    $("#newincome").text(data.show.newordernum); 


    $.get(url.getindexdata+0,function(data,status){
        console.log(data)
      });


};