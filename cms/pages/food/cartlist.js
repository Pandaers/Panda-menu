
//delecart
function delecart(){
    
}



//转到菜单选项
function toaddcart(){
   
    $("#cartlist").hide();
    $("#addcart").show();
}



//添加菜单
function addfoodcart(e) {
    var cartname = $("#cartname").val();
    var carttips = $("#carttips").val();

    console.log("cartname:" + cartname);
    console.log("carttips:" + carttips);
    
    // $.ajax(url.addcart, {
    //     data: {
           
    //     },
    //     dataType: 'jsonp',
    //     crossDomain: true,
    //     success: function (data) {
    //         if (data && data.resultcode == '200') {
    //             console.log(data.result.today);
    //         }
    //     }
    // });
    window.location.reload();
}


// window.onload() = function(){
//     //加载cartlist数据
// }

