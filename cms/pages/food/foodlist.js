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
        url: url.getmenudata + 1000,
        async: true,
        success: function (res) {
                console.log(res.data)
                var str = ""
                for (let index = 0; index < res.data.length; index++) {
                    var con =
                       " <tr>"+
                      "<td>"+res.data[index].name+"</td>"+
                      "<td>"+res.data[index].catname+"</td>"+
                      "<td><img src='"+res.data[index].compressimg+
                         "' style='width:60px;height:60px;'></td>"+
                         "<td>"+res.data[index].price+"</td>"+
                         "<td>"+res.data[index].price+"</td>"+
                      "<td> <span class='badge badge-warning' style='background:red;'>"+res.data[index].price+"</span><br></td>"+
                      "<td>"+res.data[index].price+"</td>"+
                     " <td> <button type='button' class='btn btn-default btn-sm'><i class='fa fa-edit'></i></button>"+
                       "<button type='button' class='btn btn-default btn-sm'><i class='fa  fa-trash-o'></i></button>"+
                   "</tr>"
                       
                       
                        str = str + con
                }
                $("#menulist").html(str)


        }
    }
)


}