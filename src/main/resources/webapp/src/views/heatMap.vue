<template>
    <div class="map-container">
        <div id="container"  style="width:100%;height:500px"></div>
    </div>
</template>
<script src="https://webapi.amap.com/maps?v=1.4.15&key=7b4376e733352648aeabfc84de9c9a73"></script>
<script src="https://webapi.amap.com/loca?v=1.3.0&key=7b4376e733352648aeabfc84de9c9a73"></script> 
<script>
// import { Loca } from 'https://webapi.amap.com/loca?v=1.3.0&key=7b4376e733352648aeabfc84de9c9a73'
export default {
    data() {
		let self = this
        return {
			heatData: [{
				"lng": 116.191031,
				"lat": 39.988585,
				"count": 10
			}, {
				"lng": 116.389275,
				"lat": 39.925818,
				"count": 11
			}, {
				"lng": 116.287444,
				"lat": 39.810742,
				"count": 12
			}, {
				"lng": 116.481707,
				"lat": 39.940089,
				"count": 13
			}, {
				"lng": 116.410588,
				"lat": 39.880172,
				"count": 14
			}, {
				"lng": 116.394816,
				"lat": 39.91181,
				"count": 15
			}, {
				"lng": 116.416002,
				"lat": 39.952917,
				"count": 16
			}, {
				"lng": 116.39671,
				"lat": 39.924903,
				"count": 17
			}, {
				"lng": 116.180816,
				"lat": 39.957553,
				"count": 18
			}, {
				"lng": 116.382035,
				"lat": 39.874114,
				"count": 19
			}, {
				"lng": 116.316648,
				"lat": 39.914529,
				"count": 20
			}, {
				"lng": 116.395803,
				"lat": 39.908556,
				"count": 21
			}, {
				"lng": 116.74553,
				"lat": 39.875916,
				"count": 22
			}, {
				"lng": 116.352289,
				"lat": 39.916475,
				"count": 23
			}, {
				"lng": 116.441548,
				"lat": 39.878262,
				"count": 24
			}, {
				"lng": 116.318947,
				"lat": 39.942735,
				"count": 25
			}, {
				"lng": 116.382585,
				"lat": 39.941949,
				"count": 26
			}, {
				"lng": 116.42042,
				"lat": 39.884017,
				"count": 27
			}, {
				"lng": 116.31744,
				"lat": 39.892561,
				"count": 28
			}, {
				"lng": 116.407059,
				"lat": 39.912438,
				"count": 29
			}, {
				"lng": 116.412351,
				"lat": 39.888082,
				"count": 30
			}, {
				"lng": 116.444341,
				"lat": 39.915891,
				"count": 31
			}, {
				"lng": 116.335385,
				"lat": 39.741756,
				"count": 32
			}, {
				"lng": 116.3926,
				"lat": 40.008733,
				"count": 33
			}, {
				"lng": 116.389731,
				"lat": 39.92292,
				"count": 34
			}, {
				"lng": 116.413371,
				"lat": 39.874483,
				"count": 35
			}, {
				"lng": 116.199752,
				"lat": 39.911717,
				"count": 36
			}, {
				"lng": 116.278472,
				"lat": 40.254994,
				"count": 37
			}, {
				"lng": 116.464252,
				"lat": 39.925828,
				"count": 38
			}, {
				"lng": 116.479475,
				"lat": 39.937945,
				"count": 39
			}, {
				"lng": 116.415599,
				"lat": 39.956902,
				"count": 40
			}, {
				"lng": 116.355675,
				"lat": 39.870089,
				"count": 41
			}, {
				"lng": 116.295267,
				"lat": 39.987171,
				"count": 42
			}, {
				"lng": 116.323634,
				"lat": 39.911692,
				"count": 43
			}, {
				"lng": 116.692769,
				"lat": 40.173307,
				"count": 44
			}, {
				"lng": 116.287888,
				"lat": 39.928531,
				"count": 45
			}, {
				"lng": 116.386502,
				"lat": 39.922747,
				"count": 46
			}, {
				"lng": 116.236773,
				"lat": 40.218341,
				"count": 47
			}, {
				"lng": 116.490636,
				"lat": 39.804253,
				"count": 48
			}, {
				"lng": 116.391095,
				"lat": 39.925791,
				"count": 49
			}, {
				"lng": 116.472402,
				"lat": 39.769178,
				"count": 50
			}, {
				"lng": 116.38657,
				"lat": 39.956731,
				"count": 51
			}, {
				"lng": 116.427536,
				"lat": 39.943671,
				"count": 52
			}, {
				"lng": 116.374547,
				"lat": 39.967588,
				"count": 53
			}, {
				"lng": 116.380383,
				"lat": 39.871634,
				"count": 54
			}, {
				"lng": 116.376092,
				"lat": 39.965485,
				"count": 55
			}, {
				"lng": 116.352424,
				"lat": 39.91811,
				"count": 56
			}, {
				"lng": 116.020157,
				"lat": 40.348526,
				"count": 57
			}, {
				"lng": 116.416201,
				"lat": 39.951736,
				"count": 58
			}, {
				"lng": 116.405392,
				"lat": 39.908738,
				"count": 59
			}, {
				"lng": 116.49238,
				"lat": 39.926248,
				"count": 60
			}, {
				"lng": 116.389282,
				"lat": 39.988391,
				"count": 61
			}, {
				"lng": 116.396683,
				"lat": 39.923487,
				"count": 62
			}, {
				"lng": 116.41718,
				"lat": 39.905213,
				"count": 63
			}, {
				"lng": 116.321512,
				"lat": 39.913192,
				"count": 64
			}, {
				"lng": 116.260028,
				"lat": 40.03353,
				"count": 65
			}, {
				"lng": 116.394846,
				"lat": 39.911168,
				"count": 66
			}, {
				"lng": 116.374767,
				"lat": 39.96608,
				"count": 67
			}, {
				"lng": 116.6841,
				"lat": 39.909762,
				"count": 68
			}, {
				"lng": 116.3838,
				"lat": 39.95811,
				"count": 69
			}, {
				"lng": 116.39243,
				"lat": 40.01143,
				"count": 70
			}, {
				"lng": 116.661912,
				"lat": 40.121137,
				"count": 71
			}]
        }
    },
    mounted() {
        this.initMap()
    },
    methods: {
        initMap() {
            var map = new AMap.Map('container', {
                features: ['bg', 'road'],
				resizeEnable: true,
				center: [116.397428, 39.90923],
				zoom: 7,
				viewMode: '2D',
				pitch: 50,
				showZoomBar:true
			})
			var layer = new Loca.HeatmapLayer({
				map: map,
			});
			var list = [];
			var i = -1, length = this.heatData.length;
			while (++i < length) {
				var item = this.heatData[i];
				list.push({
					coordinate: [item.lng, item.lat],
					count: item.count
				})
			}

			layer.setData(list, {
				lnglat: 'coordinate',
				value: 'count'
			});

			layer.setOptions({
				style: {
					radius: 16,
					color: {
						0.5: '#2c7bb6',
						0.65: '#abd9e9',
						0.7: '#ffffbf',
						0.9: '#fde468',
						1.0: '#d7191c'
					}
				}
			});

    		layer.render();
        }
    }
}
</script>
<style scoped>

</style>