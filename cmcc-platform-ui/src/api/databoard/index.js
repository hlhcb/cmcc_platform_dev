;(function(){
    var chartDom = document.getElementById('people');
    var myChart = echarts.init(chartDom);
    var option;

    option = {
            
        tooltip: {
            trigger: 'axis'
        },
        grid: {
            left: '2%',
            right: '5%',
            bottom: '5%',            
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            axisLabel:{
                show:true,
                textStyle:{
                    color:'#ffffff'
                }
            },
            axisLine:{
                lineStyle:{
                    color:'#ffffff',
                    width:1
                }
            },
            data: ['一月', '二月', '三月', '四月', '五月', '六月', '七月']
        },
        yAxis: {
            type: 'value',
            name:'单位：万人',
            nameTextStyle:{
                color:'#ffffff'
            },
            axisLabel:{
                show:true,
                textStyle:{
                    color:'#ffffff'
                }
            },
            axisLine:{
                lineStyle:{
                    color:'#ffffff',
                    width:1
                }
            }
        },
        series: [
            {
                name: '去年返乡人数',
                type: 'line',
                data: [120, 132, 101, 134, 90, 230, 210],
                itemStyle:{
                    normal:{
                        color: '#FFFF00',
                        lineStyle:{
                            color:'#FB8800'
                        }
                    }
                }
            },
            {
                name: '今年返乡人数',
                type: 'line',
                data: [220, 182, 191, 234, 290, 330, 310]
            }
        ]
    };
    myChart.setOption(option);
})()
;(function(){
    var chartDom = document.getElementById('numdata');
    var myChart = echarts.init(chartDom);
    var option;

    option = {
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          right:0,
          top:30,
          textStyle:{
            color:'#ffffff'
          }
        },
        color: ['#FFD3A0', '#FB8800'],
        series: [
          {
            name: '返乡类型',
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            label: {
              normal: {
                position: 'inside',
                formatter: '{c}',
                color: '#ffffff',
                fontSize: 13,
                fontWeight: 'bold'
              },
              show: true,
              position: 'center'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: 20,
                fontWeight: 'bold',
                color: '#ffffff'
              }
            },
            labelLine: {
              show: false
            },
            data: [
              { value: 580, name: '外省返乡' },
              { value: 250, name: '省内返乡' }
            ]
          }
        ]
      };  

    myChart.setOption(option);
})()


;(function(){
    var chartDom = document.getElementById('chart3');
    var myChart = echarts.init(chartDom);
    var option;

    option = {
        color:['#FB8800','#AB0000'],
        tooltip: {
            trigger: 'axis'
        },
        grid:{
          bottom:20
        },
        toolbox: {
            iconStyle:{
                borderColor:'#ffffff'
            },
            emphasis:{
                iconStyle:{
                    borderColor:'#FFE600'
                }
            },
            feature: {
                dataView: { show: true, readOnly: false },
                magicType: { show: true, type: ['line', 'bar'] },
                restore: { show: true },
                saveAsImage: { show: true }
            }
        },
        xAxis: [
            {
                type: 'category',
                data: ['等效新增', '等效宽带新增', '5G合约新增', 'CHN产品新增'],
                axisLabel:{
                    show:true,
                    interval:0,
                    textStyle:{
                        color:'#ffffff'
                    }
                },
                axisLine:{
                    lineStyle:{
                        color:'#ffffff',
                        width:1
                    }
                },
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '当日完成量',
                min: 0,
                max: 500,
                interval: 100,
                axisLabel:{
                    color:'#fff'
                },
                axisLine:{
                    lineStyle:{
                        color:'#fff',
                        width:1
                    }
                }
            },
            {
                type: 'value',
                name: '当月完成量',
                min: 0,
                max: 5000,
                interval: 1000,
                axisLabel:{
                    color:'#fff'
                },
                axisLine:{
                    lineStyle:{
                        color:'#fff',
                        width:1
                    }
                }
                
            }
        ],
        series: [
            {
                name: '当日完成量',
                type: 'bar',
                data: [100, 50, 120, 200]
            },
            {
                name: '当月完成量',
                type: 'line',
                yAxisIndex: 1,
                data: [1700, 500, 1200, 2000]
            }
        ]
    };
    myChart.setOption(option);
})()
;(function(){
    var chartDom = document.getElementById('total');
    var myChart = echarts.init(chartDom);
    var option;

    const pathSymbols = {
    plane:
        'path://M1.112,32.559l2.998,1.205l-2.882,2.268l-2.215-0.012L1.112,32.559z M37.803,23.96 c0.158-0.838,0.5-1.509,0.961-1.904c-0.096-0.037-0.205-0.071-0.344-0.071c-0.777-0.005-2.068-0.009-3.047-0.009 c-0.633,0-1.217,0.066-1.754,0.18l2.199,1.804H37.803z M39.738,23.036c-0.111,0-0.377,0.325-0.537,0.924h1.076 C40.115,23.361,39.854,23.036,39.738,23.036z M39.934,39.867c-0.166,0-0.674,0.705-0.674,1.986s0.506,1.986,0.674,1.986 s0.672-0.705,0.672-1.986S40.102,39.867,39.934,39.867z M38.963,38.889c-0.098-0.038-0.209-0.07-0.348-0.073 c-0.082,0-0.174,0-0.268-0.001l-7.127,4.671c0.879,0.821,2.42,1.417,4.348,1.417c0.979,0,2.27-0.006,3.047-0.01 c0.139,0,0.25-0.034,0.348-0.072c-0.646-0.555-1.07-1.643-1.07-2.967C37.891,40.529,38.316,39.441,38.963,38.889z M32.713,23.96 l-12.37-10.116l-4.693-0.004c0,0,4,8.222,4.827,10.121H32.713z M59.311,32.374c-0.248,2.104-5.305,3.172-8.018,3.172H39.629 l-25.325,16.61L9.607,52.16c0,0,6.687-8.479,7.95-10.207c1.17-1.6,3.019-3.699,3.027-6.407h-2.138 c-5.839,0-13.816-3.789-18.472-5.583c-2.818-1.085-2.396-4.04-0.031-4.04h0.039l-3.299-11.371h3.617c0,0,4.352,5.696,5.846,7.5 c2,2.416,4.503,3.678,8.228,3.87h30.727c2.17,0,4.311,0.417,6.252,1.046c3.49,1.175,5.863,2.7,7.199,4.027 C59.145,31.584,59.352,32.025,59.311,32.374z M22.069,30.408c0-0.815-0.661-1.475-1.469-1.475c-0.812,0-1.471,0.66-1.471,1.475 s0.658,1.475,1.471,1.475C21.408,31.883,22.069,31.224,22.069,30.408z M27.06,30.408c0-0.815-0.656-1.478-1.466-1.478 c-0.812,0-1.471,0.662-1.471,1.478s0.658,1.477,1.471,1.477C26.404,31.885,27.06,31.224,27.06,30.408z M32.055,30.408 c0-0.815-0.66-1.475-1.469-1.475c-0.808,0-1.466,0.66-1.466,1.475s0.658,1.475,1.466,1.475 C31.398,31.883,32.055,31.224,32.055,30.408z M37.049,30.408c0-0.815-0.658-1.478-1.467-1.478c-0.812,0-1.469,0.662-1.469,1.478 s0.656,1.477,1.469,1.477C36.389,31.885,37.049,31.224,37.049,30.408z M42.039,30.408c0-0.815-0.656-1.478-1.465-1.478 c-0.811,0-1.469,0.662-1.469,1.478s0.658,1.477,1.469,1.477C41.383,31.885,42.039,31.224,42.039,30.408z M55.479,30.565 c-0.701-0.436-1.568-0.896-2.627-1.347c-0.613,0.289-1.551,0.476-2.73,0.476c-1.527,0-1.639,2.263,0.164,2.316 C52.389,32.074,54.627,31.373,55.479,30.565z'
    };
    const labelSetting = {
        show: false
    };
    option = {
        tooltip: {
        },
        grid: {
            containLabel: true,
            left: 15,
            bottom:10,
            top:10
        },
        yAxis: {
            data: ['北京', '上海', '天津', '重庆', '江苏', '云南'],
            inverse: true,
            axisLine: { show: false },
            axisTick: { show: false },
            axisLabel: {
                margin: 10,
                fontSize: 15,
                color:'#fff'
            },
            axisPointer: {
                label: {
                    show: true,
                    margin: 0
                }
            }
        },
        xAxis: {
            splitLine: { show: false },
            axisLabel: { show: false },
            axisTick: { show: false },
            axisLine: { show: false }
        },
        series: [
            {
                name: '返乡人数',
                type: 'pictorialBar',
                label: labelSetting,
                symbolRepeat: true,
                symbolSize: ['80%', '60%'],
                barCategoryGap: '40%',
                data: [
                    {
                    value: 367,
                    symbol: pathSymbols.plane
                    },
                    {
                    value: 300,
                    symbol: pathSymbols.plane
                    },
                    {
                    value: 256,
                    symbol: pathSymbols.plane
                    },
                    {
                    value: 208,
                    symbol: pathSymbols.plane
                    },
                    {
                    value: 173,
                    symbol: pathSymbols.plane
                    },
                    {
                    value: 123,
                    symbol: pathSymbols.plane
                    }
                ]
            }
        ]
    };

    myChart.setOption(option);
})()

;(function(){
    var chartDom = document.getElementById('contact');
    var myChart = echarts.init(chartDom);
var option;

const gaugeData = [
  {
    value: 50,
    name: '当月接触率',
    title: {
      offsetCenter: ['0%', '-35%']
    },
    detail: {
      valueAnimation: true,
      offsetCenter: ['0%', '-10%']
    }
  },
  {
    value: 89,
    name: '当日接触率',
    title: {
      offsetCenter: ['0%', '15%']
    },
    detail: {
      valueAnimation: true,
      offsetCenter: ['0%', '40%']
    }
  }
];
option = {
  series: [
    {
      type: 'gauge',
      radius: '90%',
      startAngle: 90,
      endAngle: -270,
      itemStyle:{
        normal:{
          color: function (colors) {
            var colorList = ['#9E0101','#FB8800'];
            return colorList[colors.dataIndex];
          }
        }
      },
      pointer: {
        show: false
      },
      progress: {
        show: true,
        overlap: false,
        roundCap: false,
        clip: false,
        itemStyle: {
          borderWidth: 1,
          borderColor: '#fff'
        }
      },
      axisLine: {
        lineStyle: {
          width: 20
        }
      },
      splitLine: {
        show: false,
        distance: 0,
        length: 10
      },
      axisTick: {
        show: false
      },
      axisLabel: {
        show: false,
        distance: 50
      },
      data: gaugeData,
      title: {
        fontSize: 12,
        color:'#fff'
      },
      detail: {
        width: 40,
        height: 8,
        fontSize: 11,
        color: 'inherit',
        borderColor: 'inherit',
        borderRadius: 20,
        borderWidth: 1,
        formatter: '{value}%'
      }
    }
  ]
};

option && myChart.setOption(option);

})()