<template>
    <div class="container">
        <div id="amap-container" style="width:100%;height: 500px" />
    </div>
</template>
<script>	
export default {
    data() {
        return {
            map: null,
            maker: null,
            markerList: null
        }
    },
    mounted() {
        this.initMap()
    },
    methods: {
        initMap() {
            this.map = new AMap.Map('amap-container', {
                zoom: 10,
                center: [116.397428,39.90923],

            })
            this.marker = new AMap.Marker({
                position:[116.39, 39.9]
            }) 
            this.markerList.push([
                { position: [121.5273285, 31.21515044] },
                { position: [116.391095, 39.925791] },
                { position: [116.472402, 39.769178] },
                { position: [116.374547, 39.967588] }, 
                { position: [116.406315,39.908775] }
            ])
            this.map.add(this.markerList)
        }
    }
}
</script>
<style scoped>
    .container {
        width: 700px;
        height: 500px;
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate3d(-50%, -50%, 0);
        border: 1px solid #999;
    }

    .search-box {
        position: absolute;
        z-index: 5;
        width: 70%;
        left: 13%;
        top: 10px;
        height: 30px;
    }

    .search-box input {
        float: left;
        width: 80%;
        height: 100%;
        border: 1px solid #30ccc1;
        padding: 0 8px;
        outline: none;
    }

    .search-box button {
        float: left;
        width: 20%;
        height: 100%;
        background: #30ccc1;
        border: 1px solid #30ccc1;
        color: #fff;
        outline: none;
    }

    .tip-box {
        width: 100%;
        max-height: 260px;
        position: absolute;
        top: 30px;
        overflow-y: auto;
        background-color: #fff;
    }
</style>