// pages/order/quasi.js
var app=getApp()

import {
  alert,
  requestPayment, getCurrentPage
} from '../../utils/util'
Page({
  data: {
    content: ''
  },
  onTipsChange:function(e){
    console.log(e)
    this.data.finalorder.tips = e.detail.detail.value
  },
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    this.id = options.id || '2908'
    this.loadData()

  },
  onReady: function () {
    // 页面渲染完成
  },
  onShow: function () {
    // 页面显示
  },
  onHide: function () {
    // 页面隐藏
  },
  onUnload: function () {
    // 页面关闭
  },
  onNumberChange: function (e) {
    this.data.finalorder.customernum = e.detail.value
  },
  loadData() {
    var that = this
    var {id} = this
    var {loading} = this.data
    if(loading) {
      return
    }
    this.setData({
      loading: false,
      goods:app.globalData.order.goods,
      order: app.globalData.order,
      finalorder: {
        paywaystring: "现金支付",
        payway: 3,
        seatid: app.globalData.seatid,
        tips: '',
        customernum:4,
        orderstatue:0,
        storeid: app.globalData.storeid,
        userid: app.globalData.userid,
        ordercontent: app.globalData.order.goodsNums,
        orderprice: app.globalData.order.totalPrice
      }
    })
    wx.showNavigationBarLoading()
  },
  callbackAddress(addr_id) {
    var that = this
    var {id} = this
    var {loading} = this.data
    if (loading) {
      return
    }
    this.setData({
      loading: true
    })
    wx.showNavigationBarLoading()
    updateOrderAddr({
      quasi_order_id: id,
      addr_id,
      success(data) {
        data['cut_money_total'] = +data.cut_money + +data.coupon_money
        that.setData({
          info: data,
          loading: false
        })
        wx.hideNavigationBarLoading()
      },
      error() {
        that.setData({
          loading: false
        })
        wx.hideNavigationBarLoading()
      }
    })
  },
  callbackCoupon(user_coupon_id) {
    var that = this
    var {id} = this
    var {loading} = this.data
    if (loading) {
      return
    }
    this.setData({
      loading: true
    })
    wx.showNavigationBarLoading()
    updateOrderCoupon({
      quasi_order_id: id,
      user_coupon_id,
      success(data) {
        data['cut_money_total'] = +data.cut_money + +data.coupon_money
        that.setData({
          info: data,
          loading: false
        })
        wx.hideNavigationBarLoading()
      },
      error() {
        that.setData({
          loading: false
        })
        wx.hideNavigationBarLoading()
      }
    })
  },
  callbackContent(content) {
    this.setData({
      content
    })
  },
  onAddOrder(e) {
    var that = this
    this.setData({
      loading: true
    })
    //temp
    wx.navigateTo({
      url: `/pages/over/over`
    })
    wx.login({
      success: function (res) {
        var code = res.code;
        console.log(code)
        wx.request({
          url: app.requestUrls.login,
          data: {
            order:that.data.finalorder,
            code: code
          },
          header: {
            'content-type': 'application/json'
          },
          success: function (res) {
            console.log(res.data)
            app.globalData.orderid = res.data.orderid
          },
          fail: function () {
            console.log("bad ")
          }
        })
      }
    })

  }
})