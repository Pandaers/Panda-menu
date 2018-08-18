##```微信小程序获取openid代码```

//访问IP 为 测试 

```javascript
var ip = "http://127.0.0.1:8080";

var api = {

  login:{

    adduser:ip+"/user/adduser"

  }

};

module.exports = api;

```






//使用方法

```javascript
onGotUserInfo: function(e) {

    wx.getSetting({

      success: res => {

        if (res.authSetting['scope.userInfo']) {

          console.log(e)

          var data = e.detail.userInfo

          var nickname = data.nickName

          var avatarUrl = data.avatarUrl

          console.log(nickname + "  " + avatarUrl)

          wx.login({

            success: function(res) {

              var code = res.code;

              wx.request({

                url: api.login.adduser,

                data: {

                  code: code,

                  nickname: nickname,

                  avatarUrl: avatarUrl,

                },

                header: {

                  'content-type': 'application/json'

                },

                success: function(res) {

                  console.log(res.data)

                },

                fail: function() {

                  console.log("bad ")

                }

              })

            }

          })

        } else {

          console.log("no")

        }

      }

    })

  }

```

##```java代码 ``` 	 

```java
    /**
     * 获取用户的openid
     * @param code
     * @param nickname
     * @param avatarUrl
     * @return
     */
    @RequestMapping("/adduser")
    public String insertuser(@Param("code")String code,@Param("nickname")String nickname,@Param("avatarUrl")String avatarUrl){

        //基本信息
        String appid = "wx4f4a31c69f79cd6e";//填写appid
        String appsecret = "bd2dcac539bc006fcedbb96d88c3094f";//填写对应appsecret
        String reslut = "none";

//        DefaultHttpClient httpClient = new DefaultHttpClient();
//        HttpGet httpGet = new HttpGet("https://api.weixin.qq.com/sns/jscode2session?appid="+ appid +
//                "&secret="+appsecret+"&js_code="+code+"&grant_type=authorization_code");
//        try{
//            HttpResponse httpResponse =  httpClient.execute(httpGet);
//            HttpEntity httpEntity = httpResponse.getEntity();
//            reslut = EntityUtils.toString(httpEntity);
//        }catch (Exception e){
//            System.out.println(e.getStackTrace());
//        }


        //使用okhttp
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.weixin.qq.com/sns/jscode2session?appid="+ appid +"&secret="+appsecret+"&js_code="+code+"&grant_type=authorization_code")
                    .build();
            Response response = client.newCall(request).execute();
            reslut = response.body().string();
            if (!response.isSuccessful()) {
                reslut = "服务器端错误: " + response;
            }
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return reslut;
    }
```


```需要加入依赖```



```xml
<!-- 引入的是httpclient 已经过时，代码注释了 -->
        <dependency>
            <groupId>net.databinder</groupId>
            <artifactId>dispatch-http_2.11</artifactId>
            <version>0.8.10</version>
        </dependency>
<!-- 引入的是okhttp已经过时，代替httpclient -->
		<dependency>
            <groupId>com.squareup.okhttp</groupId>
            <artifactId>okhttp</artifactId>
            <version>2.5.0</version>
        </dependency>
```

