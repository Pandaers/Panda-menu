// pages/order/list.js
import { ORDER_STATES } from './constant'
var app = getApp()
var initData = {
  page: 0,
  hasMore: true,
  loading: false,
  list: [{ nickname: "少掌柜", storeid: '1000', state: 1, orderid: 'OR18018', orderprice: 548, createtime: '2018-07-06 14：26', avatar:'http://i4.fuimg.com/655782/adf1accea2bd8a82.jpg'}],
  loginInfo:{is_login:true}
}

Page({
  data: {
    ORDER_STATES,
  },
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    console.log('onLoad')
    var that = this
    that.setData(initData)
    that.loadData()
  },
  onReady: function () {
    // 页面渲染完成
  },
  onShow: function () {
    // 页面显示
    var that = this
  },
  onHide: function () {
    // 页面隐藏
  },
  onUnload: function () {
    // 页面关闭
  },
  initData(cb) {
    this.setData(initData)
    //this.loadData(cb)
  },
  loadData() {
    var that = this
    wx.login({
      success: function (res) {
        var code = res.code;
        wx.request({
          url: app.requestUrls.myorders,
          data: {
            userid: app.globalData.userid
          },
          header: {
            'content-type': 'application/json'
          },
          success: function (res) {
            console.log(res.data)
            that.that.setData({list:res.data})
          },
          fail: function () {
            console.log("bad ")
          }
        })
      }
    })
  },
  onPayTap(e) {
    var {id} = e.currentTarget
    var that = this
    var {list, loading} = this.data
    if (loading) {
      return;
    }

    this.setData({
      loading: true
    })
    var {order_id} = list[id]
    getPayment({
      order_id,
      success(data) {
        requestPayment({
          data,
          success(data) {
            that.initData()
          },
          complete() {
            that.setData({
              loading: false
            })
          }
        })
      },
      error() {
        that.setData({
          loading: false
        })
      }
    })
  },
  onReachBottom(e) {
    that.loadData()
    /*
    var {
      loginInfo: {is_login},
      hasMore, loading
    } = this.data
    if (is_login && hasMore && !loading) {
      this.loadData()
    }*/
  },
  onPullDownRefresh() {
    that.loadData()
    /*var {loginInfo: {is_login}} = this.data
    if (is_login) {
      wx.showNavigationBarLoading()
      this.initData(() => {
        wx.hideNavigationBarLoading()
        wx.stopPullDownRefresh()
      })
    } else {
      wx.stopPullDownRefresh()
    }*/
  },

  callback(loginInfo) {
    if (this.data.list) {
      this.onLoad()
    }
  },
  onShareAppMessage() {
    return {
      title: '我的订单',
      path: '/pages/order/list'
    }
  }
})