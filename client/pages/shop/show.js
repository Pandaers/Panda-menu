// pages/shop/show.js
//import js
import {
  makePhoneCall,
  datetimeFormat
} from '../../utils/util'

import {
  getSellerInfo,
  getReviews, addQuasiOrder
} from '../../utils/apis'
//import js end
var app = getApp()
//import app


var initOrder = {
  totalNum: 0,
  totalPrice: 0,
  totalGoodsPrice: 0,
  totalPackingFee: 0,
  goodsNums: {},
  goods: []
}

Page({
  data: {
    tabs: ["商品", "评价", "商家"],
    activeIndex: 0,

    activeMenuIndex: 0,
    showCart: false,
    
    showSubGoods: false,

    order: initOrder,

    review: {
      hasMore: true,
      loading: false,
      page: 0,
    },
    subNums:[100],
    goods_map: {
      100: { goods_id: 100, goods_name: "馒头",price:1000 }},
    info:
      {
        seller_name: "小皮-皮皮虾",
        goods_map: { 100: { goods_id: 100, goods_name: "馒头",price:1000},
          101: { goods_id: 101, goods_name: "tou", price: 1000 },
        },
        pic_url: "http:xxxx",
        min_price: "11",
        reach_time: "5",
        delivery_fee: "3",
        phone: "17785854642",
        address: "裕华路27号河北科技大学",
        sell_time: "上午八点-下午九点",
        quality: "80",
        service: "70",
        promotion: [{ pic_url: "", info: "今日特价，满15减10" }],
        overall: "5",
        distanceFormat: "5",
        notice: "今日特价，满15减10",
        menus: [{
          menu_name: "主食",
          goods2: [{
            goods_id: 100,
            pic_url: "www.baidu.com",
            goods_name: "馒头",
            sales: 10,
            price: 1000,
            sub_goods: [],
            goodsNums: 500
          },
            {
              goods_id: 100,
              pic_url: "www.baidu.com",
              goods_name: "馒头",
              sales: 10,
              price: 1000,
              sub_goods:[],
              goodsNums: 500
            },

            {
              goods_id: 100,
              pic_url: "www.baidu.com",
              goods_name: "馒头",
              sales: 10,
              price: 1000,
              sub_goods:[],
              goodsNums: 500
            },

            {
              goods_id: 100,
              pic_url: "www.baidu.com",
              goods_name: "馒头",
              sales: 10,
              price: 1000,
              sub_goods:[],
              goodsNums: 500
            },

            {
              goods_id: 100,
              pic_url: "www.baidu.com",
              goods_name: "馒头",
              sales: 10,
              price: 1000,
              sub_goods:[],
              goodsNums: 500
            },


          
          
          
          
          
          ]
        },
          {
            menu_name: "食",
            goods2: [{
              goods_id: 101,
              pic_url: "www.baidu.com",
              goods_name: "头",
              sales: 10,
              price: 1000,
              sub_goods:[],
              goodsNums: 500
            },
            {
              goods_id: 100,
              pic_url: "www.baidu.com",
              goods_name: "馒头",
              sales: 10,
              price: 1000,
              sub_goods:[],
              goodsNums: 500
            },

            {
              goods_id: 100,
              pic_url: "www.baidu.com",
              goods_name: "馒头",
              sales: 10,
              price: 1000,
              sub_goods:[],
              goodsNums: 500
            },

            {
              goods_id: 100,
              pic_url: "www.baidu.com",
              goods_name: "馒头",
              sales: 10,
              price: 1000,
              sub_goods:[],
              goodsNums: 500
            },

            {
              goods_id: 100,
              pic_url: "www.baidu.com",
              goods_name: "馒头",
              sales: 10,
              price: 1000,
              sub_goods:[],
              goodsNums: 500
            },







            ]
          }]
      }


  },
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    this.id = options.id || 2
    this.loadData()
    this.loadReview()
    wx.login({
      success: function (res) {
        var code = res.code;
        console.log(code)
        wx.request({
          url: app.requestUrl.login,
          data: {
            storeid: '11223',
            code: code,
            nickname: "110",
            avatar: "111",
            mobile: '18642316507',
            gender: '0'
          },
          header: {
            'content-type': 'application/json'
          },
          success: function (res) {
            console.log(res.data)
          },
          fail: function () {
            console.log("bad ")
          }
        })
      }
    })
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

  loadData() {
    var that = this
    var id = this.id;
    wx.showNavigationBarLoading()

    /*getSellerInfo({
      seller_id: id,
      success(data) {
        data['distanceFormat'] = +(data['distance'] / 1000).toFixed(2)
        that.setData({
          info: data
        })
        wx.setNavigationBarTitle({
          title: data.seller_name
        })
      },
      complete() {
        wx.hideNavigationBarLoading()
      }
    })*/
  },

  loadReview() {
    var that = this;
    var id = this.id
    var {review: {
      page, loading
    }} = this.data
    if (loading) {
      return;
    }

    this.setData({
      'review.loading': true
    })
    getReviews({
      page,
      seller_id: id,
      success(data) {
        var {review: {
          list
        }} = that.data
        var list2 = data.list.map(item => {
          item['timeFormat'] = datetimeFormat(item['time']);
          return item
        })

        that.setData({
          'review.list': list ? list.concat(list2) : list2,
          'review.loading': false,
          'review.page': page + 1,
          'review.hasMore': data.count == 10
        })
      }
    })
  },

  tabClick: function (e) {
    this.setData({
      activeIndex: e.currentTarget.id
    });
  },

  swiperChange(e) {
    var {current} = e.detail
    this.setData({
      activeIndex: current
    })
  },
  menuClick: function (e) {
    this.setData({
      activeMenuIndex: e.currentTarget.id
    })
  },

  addGoods(goods, item) {
    var {goods_id, sub_id, num} = item
    var itemIndex;
    if (sub_id) {
      itemIndex = goods.findIndex(item => {
        return item['goods_id'] == goods_id && item['sub_id'] == sub_id
      })
    } else {
      itemIndex = goods.findIndex(item => {
        return item['goods_id'] == goods_id
      })
    }
    if (itemIndex >= 0) {
      goods[itemIndex]['num'] += num
    } else {
      goods.push(item)
    }
    return goods
  },
  removeGoods(goods, item) {
    var {goods_id, sub_id, num} = item
    var itemIndex;
    if (sub_id) {
      itemIndex = goods.findIndex(item => {
        return item['goods_id'] == goods_id && item['sub_id'] == sub_id
      })
    } else {
      itemIndex = goods.findIndex(item => {
        return item['goods_id'] == goods_id
      })
    }
    if (itemIndex >= 0) {
      item = goods[itemIndex]
      if (item.num > num) {
        item.num -= num
      } else {
        goods.splice(itemIndex, 1)
      }
    }
    return goods
  },

  increase(e) {
    var {order, info: {goods_map}} = this.data;
    var {goodsId, subId} = e.currentTarget.dataset;
    var goods = goods_map[goodsId];
    var {goods_id, goods_name} = goods
    if (subId) {
      goods = goods.sub_goods_map[subId];
      var {sub_id, sub_name} = goods
    }
    order.totalNum += 1;
    console.log(goods.price)
    order.totalGoodsPrice += goods.price;
    order.totalPrice = order.totalGoodsPrice.toFixed(2);
    order.goods = this.addGoods(order.goods, {
      goods_id, goods_name,
      sub_id, sub_name,
      price: goods.price,
      packing_fee: goods.packing_fee,
      num: 1
    })
    order.goodsNums = this.calcGoodsNums(order.goods)

    this.setData({
      order
    })

    if (subId) {
      this.setData({
        activeSubGoods: Object.assign(this.data.activeSubGoods, {
          subNums: this.calcSubNums(order.goods, goodsId)
        })
      })
    }

  },
  decrease(e) {
    var {order, info: {goods_map}} = this.data;
    var {goodsId, subId} = e.currentTarget.dataset;

    var goods = goods_map[goodsId];
    if (subId) {
      goods = goods.sub_goods_map[subId];
    }
    order.totalNum -= 1;
    order.totalGoodsPrice -= goods.price;
    order.totalPackingFee -= goods.packing_fee;
    order.totalPrice = order.totalGoodsPrice.toFixed(2);
    order.goods = this.removeGoods(order.goods, {
      goods_id: goodsId,
      sub_id: subId,
      num: 1
    })
    order.goodsNums = this.calcGoodsNums(order.goods)
    this.setData({
      order
    })

    if (subId) {
      this.setData({
        activeSubGoods: Object.assign(this.data.activeSubGoods, {
          subNums: this.calcSubNums(order.goods, goodsId)
        })
      })
    }

    if (order.totalNum == 0) {
      this.hideCart()
    }
  },
  calcGoodsNums(goods) {
    var goodsNums = {}
    for (let i = 0, len = goods.length; i < len; i++) {
      let {goods_id, num} = goods[i]
      if (goodsNums[goods_id]) {
        goodsNums[goods_id] += num
      } else {
        goodsNums[goods_id] = num
      }
    }
    return goodsNums
  },
  calcSubNums(goods, goodsId) {
    var subNums = {}
    for (let i = 0, len = goods.length; i < len; i++) {
      let {goods_id, sub_id, num} = goods[i]
      if (goods_id == goodsId) {
        subNums[sub_id] = num
      }
    }
    return subNums
  },
  clearCart(e) {
    this.setData({
      order: initOrder,
      showCart: false
    })
  },
  hideCart(e) {
    this.setData({
      showCart: false
    })
  },
  toggleCart(e) {
    var {showCart, order: {totalNum}} = this.data

    if (totalNum <= 0) {
      return;
    }
    this.setData({
      showCart: !showCart
    })
  },

  showSubGoods(e) {
    console.log(e)
    console.log(this.data)
    var {info: {goods_map}, order} = this.data;

    var {goodsId} = e.currentTarget.dataset;
    var {goods_id, goods_name, sub_goods} = goods_map[goodsId];
    this.setData({
      showSubGoods: true,
      activeSubGoods: {
        goods_name, goods_id,
        sub_goods,
        activeIndex: 0,
        subNums: this.calcSubNums(order.goods, goodsId)
      }
    })
  },
  hideSubGoods(e) {
    this.setData({
      showSubGoods: false
    })
  },
  changeSubGoods(e) {
    var {subIndex} = e.currentTarget.dataset;
    var {activeSubGoods} = this.data;
    activeSubGoods['activeIndex'] = subIndex
    this.setData({
      activeSubGoods
    })
  },
  onPhoneTap(e) {
    var {phone} = e.currentTarget.dataset
    makePhoneCall(phone)
  },

  onScrolltolower(e) {
    var {
      hasMore, loading
    } = this.data.review
    if (hasMore && !loading) {
      this.loadReview()
    }
  },
  onAddQuasiOrder(e) {
    var that = this
    var {
      info: {seller_id},
      order: {goods},
      loading
    } = this.data
    if (loading) {
      return
    }

    this.setData({
      loading: true
    })
    getApp().getLoginInfo(loginInfo => {
      if(!loginInfo.is_login) {
        wx.navigateTo({
          url: '/pages/login/login',
        })
        this.setData({
          loading: false
        })
        return
      }
      addQuasiOrder({
        seller_id, goods,
        success(data) {

          that.setData({
            loading: false
          })
          wx.navigateTo({
            url: `/pages/order/quasi?id=${data.quasi_order_id}`
          })
        },
        error() {
          that.setData({
            loading: false
          })
        }
      })
    })
  },
  onShareAppMessage() {
    var {info:{seller_id, seller_name}} = this.data
    return {
      title: seller_name,
      path: `/pages/shop/show?id=${seller_id}`
    }
  }
})