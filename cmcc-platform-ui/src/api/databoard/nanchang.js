;(function(){
    const key = "55be995c6540470c9fb4816ea5aae9d5";
    let hoveredPolygonId = null;
    /**
     * 初始化地图实例
     */
    const map = new tianjingmapgl.Map({
        container: 'map', // container ID
        style: `http://123.14.199.58:8086/tianjing-server/mapdata-api/services/MapStyleServer/style/a331756fb56441a0b14f740b942edbaa/styleJSON?key=${key}`, // style URL
        center: [115.844372, 28.661611], // starting position [lng, lat]
        zoom: 9, // starting zoom
        pitch: 0,
    });

    map.addControl(new tianjingmapgl.FullscreenControl(), "top-right");

    function gridLoad() {
        if (map.getLayer("state-fills")) {
            map.removeLayer('state-fills');
            map.removeLayer('state-borders');
            map.removeSource('states');
        }
        map.addSource('states', {
            'type': 'geojson',
            'data': 'http://123.14.199.58:8086/tianjing-server/mapdata-api/services/nanchang-grid/FeatureServer/0/query?outFields=*&where=1=1&f=geojson&key=552280e3-8cbd-49a1-9d2a-9010045bc492'
        });
        map.addLayer({
            'id': 'state-fills',
            'type': 'fill',
            'source': 'states',
            'layout': {},
            'paint': {
                'fill-color': '#627BC1',
                'fill-opacity': [
                    'case',
                    ['boolean', ['feature-state', 'hover'], false],
                    1,
                    0.5
                ]
            }
        });
        map.addLayer({
            'id': 'state-borders',
            'type': 'line',
            'source': 'states',
            'layout': {},
            'paint': {
                'line-color': '#627BC1',
                'line-width': 2
            }
        });
    }

    function microgridLoad() {
        if (map.getLayer("state-fills")) {
            map.removeLayer('state-fills');
            map.removeLayer('state-borders');
            map.removeSource('states');
        }
        map.addSource('states', {
            'type': 'geojson',
            'data': 'http://123.14.199.58:8086/tianjing-server/mapdata-api/services/nanchang-microgrid/FeatureServer/0/query?outFields=*&where=1=1&f=geojson&key=552280e3-8cbd-49a1-9d2a-9010045bc492'
        });
        map.addLayer({
            'id': 'state-fills',
            'type': 'fill',
            'source': 'states',
            'layout': {},
            'paint': {
                'fill-color': '#627BC1',
                'fill-opacity': [
                    'case',
                    ['boolean', ['feature-state', 'hover'], false],
                    1,
                    0.5
                ]
            }
        });
        map.addLayer({
            'id': 'state-borders',
            'type': 'line',
            'source': 'states',
            'layout': {},
            'paint': {
                'line-color': '#627BC1',
                'line-width': 2
            }
        });
    }




    map.on('click', "state-fills", (e) => {
        let isOddRow = true;
        let tablebg = false;
        let Popup = "";

        console.log(e)
        let { properties } = e.features[0]
        console.log(properties)
        let objectid = properties.objectid

        Object.keys(properties).forEach((item) => {
            if (
                properties[item] &&
                item != "shape_area" &&
                item != "shape_leng"
            ) {
                Popup += `
        <div class="porp-conter" style="background-color: ${isOddRow ? '#2a569433' : 'transparent'}">
            <div class="porp-conter-name">${item}</div>
            <div class="porp-conter-value">${properties[item]}</div>
        </div>
        `;
                isOddRow = !isOddRow; // 切换到下一行的奇偶性
            }
        });

        map.setPaintProperty("state-fills", "fill-color", [
            "match",
            ["get", "objectid"],
            objectid,
            "#0C3B70",
            '#627BC1'
        ]);

        new tianjingmapgl.Popup()
            .setLngLat(e.lngLat)
            .setHTML(Popup)
            .addTo(map);
    })
})()