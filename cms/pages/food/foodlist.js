function addfood(){
    $("#addfood").hide();
    $("#content").show();
}

function toaddfood(){
    $("#addfood").show();
    $("#content").hide();
}

window.onload = function(){
    
     $.ajax({
        url: url.getmenudata + storeid,
        async: true,
        success: function (res) {
                console.log(res.data)
                var str = ""
               
                for (let index = 0; index < res.data.length; index++) {
                res.data[index].status = res.data[index].status==1?"上架":"未上架"

                    var con =
                       " <tr>"+
                      "<td>"+res.data[index].name+"</td>"+
                      "<td>"+res.data[index].catname+"</td>"+
                      "<td><img src='"+res.data[index].compressimg+
                         "' style='width:60px;height:60px;'></td>"+
                         "<td>"+res.data[index].realsales+"</td>"+
                         "<td>"+res.data[index].detail+"</td>"+
                      "<td> <span class='badge badge-warning' style='background:red;'>"+res.data[index].status+"</span><br></td>"+
                      "<td>"+res.data[index].price+"</td>"+
                     " <td> <button id="+res.data[index].id +"  onclick='editfood()'  type='button' class='btn btn-default btn-sm'><i class='fa fa-edit'></i></button>"+
                       "<button id="+res.data[index].id +" onclick='delefood()'  type='button' class='btn btn-default btn-sm'><i class='fa  fa-trash-o'></i></button>"+
                   "</tr>"
                       
                       
                        str = str + con
                }
                $("#menulist").html(str)


        }
    }
)


}


function editfood(){
    $.ajax({
        url: url.SingleMenu +storeid+"&id="+1000,
        async: true,
        success: function (res) {
                console.log(res.data)
                
        }
        
    });
}