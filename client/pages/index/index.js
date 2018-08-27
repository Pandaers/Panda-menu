// pages/shop/show.js
//import js
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
      1: { id:1 , name: "馒头",price:2 },
      2: { id: 2, name: "包子", price: 5 },
      3: { id: 3, name: "可乐", price: 3 },
      1000: { id: 1000, name: "雪碧", price: 4 }
      },
    info:
      {
        nickname: "小皮-皮皮虾",
        avatar: "http:xxxx",
        mobile: "17785854642",
        address: "裕华路27号河北科技大学",
        selltime: "上午八点-下午九点",
        foodscore: "80",
        severscore: "70",
        avescore:"75",
        /*promotion: [{ pic_url: "", info: "今日特价，满15减10" }],*/
        notice: "今日特价，满15减10",
        menus: [{
          catname: "主食",
          foods: [{
            id: 0,
            avatar: "www.baidu.com",
            name: "馒头",
            virtualsales: 10,
            price: 10,
            goodsNums: 500
           
          },
            {
              id: 1,
              avatar: "www.baidu.com",
              name: "米饭",
              virtualsales: 10,
              price: 2,
              goodsNums: 500

            }
          ]
        },
          {
            catname: "菜品",
            foods: [{
              id: 2,
              avatar: "www.baidu.com",
              name: "红烧肉",
              virtualsales: 10,
              price: 100,
              goodsNums: 500

            },
              {
                id: 3,
                avatar: "www.baidu.com",
                name: "狮子头",
                virtualsales: 10,
                price: 70,
                goodsNums: 500

              }]
      }]}


  },
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
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

  loadData() {
    var that = this
    var id = this.id;
    wx.showNavigationBarLoading()
    wx.login({
      success: function (res) {
        var code = res.code;
        wx.request({
          url: app.requestUrls.menu,
          data: {
            storeid: app.globalData.storeid
          },
          header: {
            'content-type': 'application/json'
          },
          success: function (res) {
            console.log(res.data)
            that.setData({ info: res.data.data})
          },
          fail: function () {
            console.log("bad ")
          }
        })
      }
    })
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
    var {id, sub_id, num} = item
    console.log(item)
    var itemIndex;
    if (sub_id) {
      itemIndex = goods.findIndex(item => {
        return item['id'] == id && item['sub_id'] == sub_id
      })
    } else {
      itemIndex = goods.findIndex(item => {
        return item['id'] == id
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
    var {id, sub_id, num} = item
    var itemIndex;
    if (sub_id) {
      itemIndex = goods.findIndex(item => {
        return item['id'] == id && item['sub_id'] == sub_id
      })
    } else {
      itemIndex = goods.findIndex(item => {
        return item['id'] == id
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
    var { order, info: { goods_map } } = this.data;
    var { goodsId, subId } = e.currentTarget.dataset;
    console.log(goodsId)
    var goods = this.data.goods_map[goodsId];
    console.log(goods)
    var { id, goods_name } = goods
    if (subId) {
      goods = goods.sub_goods_map[subId];
      var {sub_id, sub_name} = goods
    }
    order.totalNum += 1;
    console.log(goods.price)
    order.totalGoodsPrice += goods.price;
    order.totalPrice = order.totalGoodsPrice.toFixed(2);
    order.goods = this.addGoods(order.goods, {
      id: goods.id, name:goods.name,
      price: goods.price,
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
    console.log(e)
    var {goodsId, subId} = e.currentTarget.dataset;
    var goods = this.data.goods_map[goodsId];
    if (subId) {
      goods = goods.sub_goods_map[subId];
    }
    order.totalNum -= 1;
    order.totalGoodsPrice -= goods.price;
    order.totalPackingFee -= goods.packing_fee;
    order.totalPrice = order.totalGoodsPrice.toFixed(2);
    order.goods = this.removeGoods(order.goods, {
      id: goodsId,
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
      let {id, num} = goods[i]
      if (goodsNums[id]) {
        goodsNums[id] += num
      } else {
        goodsNums[id] = num
      }
    }
    return goodsNums
  },
  calcSubNums(goods, goodsId) {
    var subNums = {}
    for (let i = 0, len = goods.length; i < len; i++) {
      let {id, sub_id, num} = goods[i]
      if (id == goodsId) {
        subNums[sub_id] = num
      }
    }
    return subNums
  },
  clearCart(e) {
    this.setData({
      order: {
        totalNum: 0,
        totalPrice: 0,
        totalGoodsPrice: 0,
        totalPackingFee: 0,
        goodsNums: {},
        goods: []
      },
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
    var {id, goods_name, sub_goods} = goods_map[goodsId];
    this.setData({
      showSubGoods: true,
      activeSubGoods: {
        goods_name, id,
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
      storeId = app.globalData.storeId,
      order: {goods},
      loading
    } = this.data
      app.globalData.order = this.data.order
    if (loading) {
      return
    }
    this.setData({
      loading: true
    })
    //temp
    wx.navigateTo({
      url: `/pages/order/quasi`
    })
    wx.login({
      success: function (res) {
        var code = res.code;

        wx.request({
          url: app.requestUrls.login,
          data: {
            storeid: app.globalData.storeid,
            code: code,
            nickname: e.detail.userInfo.nickName,
            avatar: e.detail.userInfo.avatarUrl,
            mobile: '18642316507',
            gender: e.detail.userInfo.gender
          },
          header: {
            'content-type': 'application/json'
          },
          success: function (res) {
            console.log(res.data)
            that.setData({
              loading: false
            })
            app.globalData.userid = res.data.userid
            wx.navigateTo({
              url: `/pages/order/quasi`
            })

          },
          fail: function () {
            console.log("bad ")
          }
        })
      }
    })
  },
  onShareAppMessage() {
    var {info:{seller_id, seller_name}} = this.data
    return {
      title: this.data.nickname,
      path: `/pages/shop/show?storeid=`+app.globalData.storeid
    }
  }
})