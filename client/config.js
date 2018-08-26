/**
 * 小程序配置文件
 */

// var host = "https://www.hcyang.top"
var host = "https://www.hcyang.top"
const debug = wx.getStorageSync('debug')
if (debug) {
  host = "https://www.hcyang.top"
}


module.exports = {
    host, 
    qqmapKey: 'FPOBZ-UT2K2-ZFYUC-CX67E-IOOYS-7XFQ6'
}
