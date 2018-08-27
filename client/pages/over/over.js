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
  },
  onAddFoods: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    wx.reLaunch({
      url: '../index/index',
    })

  },
  onMyOrders: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    wx.reLaunch({
      url: '../order/list',
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
