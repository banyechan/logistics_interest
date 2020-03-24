import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import VueAMap from 'vue-amap'


Vue.config.productionTip = false
Vue.prototype.axios = axios

Vue.use(VueAMap)

VueAMap.initAMapApiLoader({
	key: '7b4376e733352648aeabfc84de9c9a73',
	plugin: [
		'AMap.Scale', // 右下角缩略图插件 比例尺
		'AMap.OverView', // 鹰眼控件，
		'AMap.ToolBar', // 工具条控件
		'AMap.MapType', // 类别切换控件
		'AMap.PlaceSearch', 
		'AMap.Geolocation', // 定位控件
		'AMap.Geocoder'
	],
	v: '1.4.4',
	uiVersion: '1.0'

})

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
