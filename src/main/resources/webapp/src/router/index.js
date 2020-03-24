import Vue from "vue";
import VueRouter from "vue-router";
import Index from "../views/index.vue"
import IndexSDK from "../views/indexSDK.vue"
import HeatMap from "../views/heatMap.vue"
import SearchPoint from "../views/searchPoint.vue"

Vue.use(VueRouter);

const routes = [
	{
		path: "/",
		// rediect: '/heatMap',
		name: "IndexSDK",
		component: IndexSDK
	},
	{
		path: '/heatMap',
		name: 'heatMap',
		component: HeatMap
	},
	{
		path: '/point',
		name: 'point',
		component: SearchPoint
	}

];

const router = new VueRouter({
 	routes
});

export default router;

