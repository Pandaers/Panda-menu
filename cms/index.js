
//渲染最新菜单列表

window.onload = function () {
};


showdatainbord =  function(res){
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
    data.orderdata = JSON.parse(res.data);
    console.log(data.orderdata)
    var str = "";
    for (let index = 0; index < data.orderdata.length; index++) {

        switch( data.orderdata[index].orderstatue){
            case 0 :{
                data.orderdata[index].orderstatue = "待确认"
                break;
            }
            case 1 :{
                data.orderdata[index].orderstatue = "订单进行中"
                break;
            }
            case 2 :{
                data.orderdata[index].orderstatue = "订单取消"
                break;
            }
            case 3 :{
                data.orderdata[index].orderstatue = "订单结束"
                break;
            }
        }

        switch(  data.orderdata[index].payway){
            case 0 :{
                data.orderdata[index].payway = "未支付"
                break;
            }
            case 1 :{
                data.orderdata[index].payway = "支付宝支付"
                break;
            }
            case 2 :{
                data.orderdata[index].payway= "微信支付"
                break;
            }
            case 3 :{
                data.orderdata[index].payway = "现金支付"
                break;
            }
            case 4 :{
                data.orderdata[index].payway = "银行卡/信用卡支付"
                break;
            }
        }

        switch( data.orderdata[index].dishstatue){
            case 0 :{
                data.orderdata[index].dishstatue = "未上菜"
                break;
            }
            case 1 :{
                data.orderdata[index].dishstatue = "已上菜"
                break;
            }
        }

        var json = JSON.parse(data.orderdata[index].ordercontent);

        var food = "";
        for(var i in json ){
            food = food + i+"("+json[i]+"份);"+"\t\t\t"
        }
       
        var con = " <tr><td>" + data.orderdata[index].orderid +
            "</td><td style='width: 30%; text-align: justify'>" + food + "</td><td>" + data.orderdata[index].tips + "</td>"
            + "<td>" + data.orderdata[index].seatid + "</td>"
            + " <td><span class='badge badge-info' style='background:red;'>" + data.orderdata[index].dishstatue + "</span><br><span class='badge badge-success' >" + data.orderdata[index].orderstatue + "</span><br><span class='badge badge-warning' style='background:rgba(4, 161, 251);color:white;'>" + data.orderdata[index].payway + "</span></td>" +
            " <td><div class='sparkbar'  data.orderdata-color='#00a65a'  data.orderdata-height='20'>" + data.orderdata[index].createtime + "--" + data.orderdata[index].endtime + "</div></td></tr>"
            str = str + con
    }
    $("#orderlist").html(str)
    //修改四个框的数值
    $("#newordernum").text(data.show.newordernum);
    $("#allordernum").text(data.show.newordernum);
    $("#allusernum").text(data.show.newordernum);
    $("#newincome").text(data.show.newordernum);
};

var websocket = null;

//判断当前浏览器是否支持WebSocket
if('WebSocket' in window){
    websocket = new WebSocket("ws://localhost:8080/getordering");
}
else{
    alert('Not support websocket')
}

//连接发生错误的回调方法
websocket.onerror = function(){
    setMessageInnerHTML("error");
};

//连接成功建立的回调方法
websocket.onopen = function(event){
    send();
}

//接收到消息的回调方法
websocket.onmessage = function(event){
   console.log(event)
   showdatainbord(event)
}

//连接关闭的回调方法
websocket.onclose = function(){
    setMessageInnerHTML("close");
}

//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function(){
    websocket.close();
}

//将消息显示在网页上
function setMessageInnerHTML(innerHTML){
    document.getElementById('message').innerHTML += innerHTML + '<br/>';
}

//关闭连接
function closeWebSocket(){
    websocket.close();
}

//发送消息
function send(){
    websocket.send(1000);
}

