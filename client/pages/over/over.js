/**
 * @fileOverview 演示会话服务和 WebSocket 信道服务的使用方式
 */
const { $Message } = require('../../dist/base/index');
// 引入 QCloud 小程序增强 SDK
var qcloud = require('../../vendor/wafer2-client-sdk/index');
var app =getApp()
/**
 * 使用 Page 初始化页面，具体可参考微信公众平台上的文档
 */
Page({

  /**
   * 初始数据，我们把服务地址显示在页面上
   */
  data: {
    returnorder:{
      orderid: 'OR180852',
      createtime:'2018-08-23 14:24'
    }
    
  },
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    this.loadData()
    wx.request({
      url: 'https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token=13_Sscd8j8CpBYN3RkXI8KSSHqTA3HDKNLCyr52zq5YYAeQlXO8fYZtdACIMTe8_m457BufsBHkpvb1B76XE8vNIrAEVSrG-8tANjJ6SLxxQB4eIJx3iJqSuBDLz0RbWx2Punc36or1h9zpel1QXNUjAHAAQX',
      data: { 'path': "/pages/SHOP/SHOP?seatid=1", 'width': 430 },
      method: "POST",
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        console.log(res.data)
      },
      fail: function (res) {
        console.log('isFail')
      }
    })
  },
  onAddFoods: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    wx.reLaunch({
      url: '../shop/show',
    })

  },
  loadData() {
    var that = this
    var { id } = this
    var { loading } = this.data
    if (loading) {
      return
    }
    this.setData({
      loading: false,
      goods: app.globalData.order.goods,
      order: app.globalData.order,
    })
  },
});
