function init(id, div) {
  // const id = 0;
  // const div = document.getElementById("chartdiv");
  // Create root element
  // https://www.amcharts.com/docs/v5/getting-started/#Root_element

  // 지도 그릴 영역 root 생성
  var root = am5.Root.new("chartdiv");


  // Set themes
  // https://www.amcharts.com/docs/v5/concepts/themes/
  root.setThemes([am5themes_Animated.new(root)]);

  // Create the map chart
  // https://www.amcharts.com/docs/v5/charts/map-chart/
  var chart = root.container.children.push(
    am5map.MapChart.new(root, {
      // panX: "translateX",
      // panY: "translateY",Fgtuy67=\  rotationX: -160,
      projection: am5map.geoEqualEarth(),
    })
  );

  // Create main polygon series for countries
  // https://www.amcharts.com/docs/v5/charts/map-chart/map-polygon-series/
  var polygonSeries = chart.series.push(
    am5map.MapPolygonSeries.new(root, {
      geoJSON: am5geodata_worldLow,
    })
  );

  polygonSeries.mapPolygons.template.setAll({
    tooltipText: "{name}",
    toggleKey: "active",
    interactive: true
    , fill: am5.color("#CDEBFC")
    , templateField: "polygonSettings"
  });



  // 특정 국가 click event 설정
  polygonSeries.mapPolygons.template.events.on(
    "click",

    function (ev, id, div, type) {
      var url = ev.target.dataItem.dataContext.url;
      var country = ev.target.dataItem.get("id");

      if (url) {
        createBar(id, div, country, 2019, type);
        createStackCluster(id, div, country);
        createPie(id, div, country, 2019);
        createString(id, div, country);
      }

      // HTML 요소에 국가 이름 설정
      document.getElementById("countryName").innerHTML = country;
    }
  );

  polygonSeries.mapPolygons.template.states.create("hover", {
    fill: am5.color("#CDEBCC")
  });

  polygonSeries.data.setAll([{
    id: "CN",
    url: "/trade/statShow",
    polygonSettings: {
      fill: am5.color("#0000FF")
    }
  }, {
    id: "US",
    url: "/trade/statShow",
    polygonSettings: {
      fill: am5.color("#008000")
    }
  }, {
    id: "VN",
    url: "/trade/statShow",
    polygonSettings: {
      fill: am5.color("#FF0000")
    }
  }, {
    id: "JP",
    url: "/trade/statShow",
    polygonSettings: {
      fill: am5.color("#FFD700")
    }
  }, {
    id: "HK",
    url: "/trade/statShow",
    polygonSettings: {
      fill: am5.color("#800080")
    }
  }, {
    id: "TW",
    url: "/trade/statShow",
    polygonSettings: {
      fill: am5.color("#008080")
    }
  }, {
    id: "SG",
    url: "/trade/statShow",
    polygonSettings: {
      fill: am5.color("#FFA500")
    }
  }, {
    id: "IN",
    url: "/trade/statShow",
    polygonSettings: {
      fill: am5.color("#FF00FF")
    }
  }, {
    id: "AU",
    url: "/trade/statShow",
    polygonSettings: {
      fill: am5.color("#00FF00")
    }
  }, {
    id: "MX",
    url: "/trade/statShow",
    polygonSettings: {
      fill: am5.color("#00FFFF")
    }
  }
  ]);

  // Add zoom control
  // https://www.amcharts.com/docs/v5/charts/map-chart/map-pan-zoom/#Zoom_control
  var zoomControl = chart.set("zoomControl", am5map.ZoomControl.new(root, {}));
  zoomControl.homeButton.set("visible", true);

  // Set clicking on "water" to zoom out
  chart.chartContainer.get("background").events.on("click", function () {
    chart.goHome();
  });

  // Make stuff animate on load
  chart.appear(1000, 100);
} //end init

// ======= createDiv =======
function createDiv(id, div) {
  var parentContainer = document.createElement("span");
  parentContainer.id = "parentContainer" + id;
  parentContainer.style.width = "370px";
  var parentContainer = document.createElement("span");
  parentContainer.id = "parentContainer" + id;
  parentContainer.style.width = "370px";
  var container = document.createElement("div");
  parentContainer.appendChild(container);
  parentContainer.appendChild(container);
  container.id = "chart" + id;
  container.style.width = "350px";
  container.style.height = "350px";
  // container.style.float = "left";
  container.style.margin = "10px";
  container.style.border = "1px solid #eee";
  console.log("==========", parentContainer);
  return parentContainer;
}

//지우는 함수
function maybeDisposeRoot(divId) {
  // console.log(root.dom.id);
  // console.log(divId);
  am5.array.each(am5.registry.rootElements, function (root) {
    // console.log(root.dom.id);
    if (root && root.dom && root.dom.id === divId) {
      root.dispose();
    }
  });
}

// ================================== bulletChart ========================================
// =======================================================================================
function createBullet(id, div) {
  var root;

  //title과 그래프를 감싸는 부모div 생성
  var parentContainer = createDiv(id, div);
  var newspace = parentContainer.firstChild;

  //제목 태그 생성
  var title = document.createElement("p");
  title.id = "title" + id;

  //단위 태그 생성
  var smalltitle1 = document.createElement("p");
  smalltitle1.id = "smalltitle1";

  //설명 태그 생성
  var information1 = document.createElement('span');
  information1.id = "information1";

  // 이미지 태그 생성
  var img = document.createElement('img');
  img.src = "/assets/img/information.png";
  img.alt = "설명 이미지";

  // 이미지 크기 조절 (필요한 경우)
  img.style.width = "25px"; // 너비
  img.style.height = "27px"; // 높이

  // span 태그에 이미지 태그 추가
  information1.appendChild(img);

  //이미지 툴팁
  var tooltipSpan = document.createElement('span');
  tooltipSpan.className = 'tooltip';
  tooltipSpan.textContent = "한국의 월별 수출입금액"; // 툴팁에 표시할 텍스트

  information1.appendChild(tooltipSpan);

  img.addEventListener('mouseenter', function () {
    tooltipSpan.classList.add('show-tooltip');
  });
  img.addEventListener('mouseleave', function () {
    tooltipSpan.classList.remove('show-tooltip');
  });

  //밑줄 태그 생성
  var line1 = document.createElement('div');
  line1.id = "line1";

  //넣을 위치 조정
  div.before(parentContainer);
  newspace.before(title, information1, smalltitle1, line1);

  //제목 글 설정
  document.getElementById(`title${id}`).innerText = "수출입 금액";
  smalltitle1.innerText = "단위 : 천$";

  //부모 div css
  parentContainer.style.display = "inline-block";
  parentContainer.style.width = "590px";
  parentContainer.style.height = "430px";
  parentContainer.style.borderRadius = "10px";
  parentContainer.style.border = "1.8px solid #c3c6ce";
  parentContainer.style.transition = "0.5s ease-out";

  parentContainer.addEventListener('mouseenter', function () {
    this.style.boxShadow = " 0 4px 18px 0 rgba(0, 0, 0, 0.5)";
    title.style.fontWeight = "bolder";
    title.style.color = "#008ff0";
    smalltitle1.style.fontWeight = "bolder";
    smalltitle1.style.color = "#008ff0";
    line1.style.borderTop = "2.5px solid #008ff0"
  });
  parentContainer.addEventListener("mouseleave", function () {
    this.style.borderColor = "#c3c6ce";
    this.style.boxShadow = "2px 2px 10px rgba(0, 0, 0, 0.07)";
    title.style.fontWeight = "normal";
    title.style.color = "black";
    smalltitle1.style.fontWeight = "normal";
    smalltitle1.style.color = "black";
    line1.style.borderTop = "1.5px solid black"
  });

  //그래프 css
  newspace.style.width = "570px";
  newspace.style.height = "320px";
  newspace.style.float = "right";
  newspace.style.margin = "10px";
  newspace.style.border = "0px";

  //그 외
  div.before(parentContainer);
  root = am5.Root.new(newspace);

  root.setThemes([am5themes_Animated.new(root)]);

  // Create chart
  // https://www.amcharts.com/docs/v5/charts/xy-chart/
  var chart = root.container.children.push(
    am5xy.XYChart.new(root, {
      panX: true,
      panY: true,
      wheelX: "panX",
      wheelY: "zoomX",
      width: am5.percent(100), //
      height: am5.percent(100), //
      layout: root.verticalLayout, //
    })
  );

  chart.get("colors").set("step", 3);

  // Add cursor
  // https://www.amcharts.com/docs/v5/charts/xy-chart/cursor/
  var cursor = chart.set("cursor", am5xy.XYCursor.new(root, {}));
  cursor.lineY.set("visible", false);
  // chart.options.responsive

  // Create axes
  // https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
  var xAxis = chart.xAxes.push(
    am5xy.DateAxis.new(root, {
      maxDeviation: 0.3,
      baseInterval: {
        timeUnit: "month",
        count: 1,
      },
      renderer: am5xy.AxisRendererX.new(root, {
        minorGridEnabled: true,
      }),
      tooltip: am5.Tooltip.new(root, {}),
    })
  );

  var yAxis = chart.yAxes.push(
    am5xy.ValueAxis.new(root, {
      maxDeviation: 0.3,
      renderer: am5xy.AxisRendererY.new(root, {}),
    })
  );

  // Add series
  // https://www.amcharts.com/docs/v5/charts/xy-chart/series/
  var series = chart.series.push(
    am5xy.LineSeries.new(root, {
      name: "수출",
      xAxis: xAxis,
      yAxis: yAxis,
      valueYField: "value1",
      valueXField: "date",
      tooltip: am5.Tooltip.new(root, {
        labelText: "수출: {valueY}\n수입: {value2}",
      }),
    })
  );

  series.strokes.template.setAll({
    strokeWidth: 2,
  });

  series.get("tooltip").get("background").set("fillOpacity", 0.5);

  var series2 = chart.series.push(
    am5xy.LineSeries.new(root, {
      name: "수입",
      xAxis: xAxis,
      yAxis: yAxis,
      valueYField: "value2",
      valueXField: "date",
    })
  );
  series2.strokes.template.setAll({
    strokeDasharray: [2, 2],
    strokeWidth: 2,
  });

  // Create animating bullet by adding two circles in a bullet container and
  // animating radius and opacity of one of them.
  series.bullets.push(function () {
    var container = am5.Container.new(root, {
      templateField: "bulletSettings",
    });
    var circle1 = container.children.push(
      am5.Circle.new(root, {
        radius: 5,
        fill: am5.color(0xff0000),
      })
    );

    circle1.animate({
      key: "radius",
      to: 20,
      duration: 1000,
      easing: am5.ease.out(am5.ease.cubic),
      loops: Infinity,
    });
    circle1.animate({
      key: "opacity",
      to: 0,
      from: 1,
      duration: 1000,
      easing: am5.ease.out(am5.ease.cubic),
      loops: Infinity,
    });

    return am5.Bullet.new(root, {
      sprite: container,
    });
  });

  // Create animating bullet by adding two circles in a bullet container and
  // animating radius and opacity of one of them.
  series2.bullets.push(function () {
    var container = am5.Container.new(root, {
      templateField: "bulletSettings",
    });
    var circle1 = container.children.push(
      am5.Circle.new(root, {
        radius: 5,
        fill: am5.color(0xff0000),
      })
    );

    circle1.animate({
      key: "radius",
      to: 20,
      duration: 1000,
      easing: am5.ease.out(am5.ease.cubic),
      loops: Infinity,
    });
    circle1.animate({
      key: "opacity",
      to: 0,
      from: 1,
      duration: 1000,
      easing: am5.ease.out(am5.ease.cubic),
      loops: Infinity,
    });

    return am5.Bullet.new(root, {
      sprite: container,
    });
  });

  // Set date fields
  // https://www.amcharts.com/docs/v5/concepts/data/#Parsing_dates
  root.dateFormatter.setAll({
    dateFormat: "yyyy-MM-dd",
    dateFields: ["valueX"],
  });

  // Set data
  var data = [
    {
      date: new Date(2019, 5, 12).getTime(),
      value1: 50,
      value2: 48,
      previousDate: new Date(2019, 5, 5),
      date: new Date(2023, 1, 1).getTime(),
      value1: 46339, // 46339145,
      value2: 59037, // 59037259
      bulletSettings: {
        visible: false,
      },
    },
    {
      date: new Date(2023, 2, 1).getTime(),
      value1: 49994, //49994593
      value2: 55370, //55370498
      bulletSettings: {
        visible: false,
      },
    },
    {
      date: new Date(2023, 3, 1).getTime(),
      value1: 54882, //54882484
      value2: 59635, //59635920
      bulletSettings: {
        visible: false,
      },
    },
    {
      date: new Date(2023, 4, 1).getTime(),
      value1: 49430, //49430879
      value2: 51940, //51940137
      bulletSettings: {
        visible: false,
      },
    },
    {
      date: new Date(2023, 5, 1).getTime(),
      value1: 52054, //52054195
      value2: 54251, //54251194
      bulletSettings: {
        visible: false,
      },
    },
    {
      date: new Date(2023, 6, 1).getTime(),
      value1: 54297, //54297754
      value2: 53055, //53055480
      bulletSettings: {
        visible: false,
      },
    },
    {
      date: new Date(2019, 5, 13).getTime(),
      value1: 53,
      value2: 51,
      previousDate: "2019-05-06",
      date: new Date(2023, 7, 1).getTime(),
      value1: 50457, //50457776
      value2: 48738, //48738114
      bulletSettings: {
        visible: false,
      },
    },
    {
      date: new Date(2019, 5, 14).getTime(),
      value1: 56,
      value2: 58,
      previousDate: "2019-05-07",
      date: new Date(2023, 8, 1).getTime(),
      value1: 51994, //51994074
      value2: 51009, //51009758
      bulletSettings: {
        visible: false,
      },
    },
    {
      date: new Date(2019, 5, 15).getTime(),
      value1: 52,
      value2: 53,
      previousDate: "2019-05-08",
      date: new Date(2023, 9, 1).getTime(),
      value1: 54650, //54650691
      value2: 50972, //50972525
      bulletSettings: {
        visible: false,
      },
    },
    {
      date: new Date(2019, 5, 16).getTime(),
      value1: 48,
      value2: 44,
      previousDate: "2019-05-09",
      date: new Date(2023, 10, 1).getTime(),
      value1: 54989, //54989950
      value2: 53440, //53440582
      bulletSettings: {
        visible: false,
      },
    },
    {
      date: new Date(2019, 5, 17).getTime(),
      value1: 47,
      value2: 42,
      previousDate: "2019-05-10",
      date: new Date(2023, 11, 1).getTime(),
      value1: 55561, //55561090
      value2: 51997, //51997805
      bulletSettings: {
        visible: false,
      },
    },
    {
      date: new Date(2019, 5, 18).getTime(),
      value1: 59,
      value2: 55,
      previousDate: "2019-05-11",
      date: new Date(2023, 12, 1).getTime(),
      value1: 57573, //57573193
      value2: 53122, //53122854
      bulletSettings: {
        visible: true,
      },
    },
  ];

  series.data.setAll(data);
  series2.data.setAll(data);

  series.set("selectedDataItem", series.dataItems[0]);

  var legend = chart.children.push(
    am5.Legend.new(root, {
      width: am5.percent(100),
      centerX: am5.percent(50),
      x: am5.percent(50),
      // layout: root.horizontalLayout
    })
  );
  legend.data.setAll(chart.series.values);

  // Make stuff animate on load
  // https://www.amcharts.com/docs/v5/concepts/animations/
  series.appear(1000);
  series2.appear(1000);
  chart.appear(1000, 100);
} //end createBullet

// ====================================== pieChart =======================================
// =======================================================================================
// 첫 화면 시작 시 먼저 실행
function createPie(id, div, country, year) {
  $.ajax({
    url: "/trade/pieChart",
    method: "GET",
    async: false,
    data: { country: country },
    success: function (resp) {
      createRealPie(id, div, resp, country, year);
    },
  });
}

function createRealPie(id, div, resp, country, year) {
  console.log("createPie 데이터 : ", resp);
  var space = document.getElementById("chart6");
  var root;

  if (space == null) {
    //제목+그래프 감싸는 부모 div
    var parentContainer = createDiv(id, div);
    var newspace = parentContainer.firstChild;

    //제목 태그 생성
    var title = document.createElement("p");
    title.id = "title" + id;

    //단위 태그 생성
    var smalltitle6 = document.createElement("p");
    smalltitle6.id = "smalltitle6";

    //설명 태그 생성
    var information6 = document.createElement('span');
    information6.id = "information6";

    //이미지 태그 생성
    var img = document.createElement('img');
    img.src = "/assets/img/information.png";
    img.alt = "설명 이미지";

    //이미지 크기 조절
    img.style.width = "25px";
    img.style.height = "27px";

    //span 태그에 이미지 태그 추가
    information6.appendChild(img);

    //이미지 툴팁
    var tooltipSpan6 = document.createElement('span');
    tooltipSpan6.className = 'tooltip6';
    tooltipSpan6.textContent = "국가의 수입시장을 점유한 나라 TOP10";

    information6.appendChild(tooltipSpan6);

    img.addEventListener('mouseenter', function () {
      tooltipSpan6.classList.add('show-tooltip');
    });
    img.addEventListener('mouseleave', function () {
      tooltipSpan6.classList.remove('show-tooltip');
    });

    //밑줄 태그 생성
    var line6 = document.createElement('div');
    line6.id = "line6";

    //위치 선정
    div.before(parentContainer);
    newspace.before(title, information6, smalltitle6, line6);

    //제목 글 설정
    title.innerText = `미국의 수입시장 점유율`;
    smalltitle6.innerText = "단위 : 천$"

    //부모 div css
    parentContainer.style.display = "inline-block";
    parentContainer.style.width = "590px";
    parentContainer.style.height = "430px";
    parentContainer.style.float = "right";
    parentContainer.style.borderRadius = "10px";
    parentContainer.style.border = "1.8px solid #c3c6ce";
    parentContainer.style.transition = "0.5s ease-out";

    parentContainer.addEventListener('mouseenter', function () {
      this.style.boxShadow = " 0 4px 18px 0 rgba(0, 0, 0, 0.5)";
      title.style.fontWeight = "bolder";
      title.style.color = "#008ff0";
      smalltitle6.style.fontWeight = "bolder";
      smalltitle6.style.color = "#008ff0";
      line6.style.borderTop = "2.5px solid #008ff0";
    });
    parentContainer.addEventListener("mouseleave", function () {
      this.style.borderColor = "#c3c6ce";
      this.style.boxShadow = "2px 2px 10px rgba(0, 0, 0, 0.07)";
      title.style.fontWeight = "normal";
      title.style.color = "black";
      smalltitle6.style.fontWeight = "normal";
      smalltitle6.style.color = "black";
      line6.style.borderTop = "1.5px solid black";
    });

    //그래프 css
    newspace.style.width = "570px";
    newspace.style.height = "320px";
    newspace.style.float = "right";
    newspace.style.margin = "10px";
    newspace.style.border = "0px";

    //그 외
    div.after(parentContainer);
    root = am5.Root.new(newspace);

    // 드롭박스 만들어보자
    // 컨테이너 div 생성
    var controlsContainer = document.createElement("div");
    controlsContainer.id = "controlsContainer";
    controlsContainer.style.display = "flex";
    controlsContainer.style.flexDirection = "row";
    controlsContainer.style.justifyContent = "flex-end";

    // 차트 div 가져오기
    var chartDiv = document.getElementById("chart6");

    //새로운 select 요소 생성
    var helpers = {
      buildDropdown: function (result, dropdown, emptyMessage) {
        // Remove current options
        dropdown.html("");

        // Add the empty option with the empty message
        dropdown.append('<option value="">' + emptyMessage + "</option>");

        // Check result isnt empty
        if (result != "") {
          // Loop through each of the results and append the option to the dropdown
          $.each(result, function (k, v) {
            dropdown.append(
              '<option value="' + v.id + '">' + v.name + "</option>"
            );
          });
        }
      },
    }; //end helpers

    // 연도 선택하는 드롭박스
    var dropdownpie = document.createElement("select");
    dropdownpie.id = "dropdownpie"; // 선택 사항: id 설정
    controlsContainer.appendChild(dropdownpie);

    // 조회버튼 만들기
    var input = document.createElement("input");
    input.id = "replyBtnpie";
    input.type = "button";
    input.value = "조회";
    controlsContainer.appendChild(input);

    // 생성된 컨테이너를 차트 div에 추가
    chartDiv.prepend(controlsContainer);

    //데이터 예시와 함께 드롭다운 옵션 생성
    var dropdata =
      '[{"id":2019,"name":"2019"}, {"id":2020,"name":"2020"},{"id":2021,"name":"2021"},{"id":2022,"name":"2022"},{"id":2023,"name":"2023"}]';
    helpers.buildDropdown(
      jQuery.parseJSON(dropdata),
      $("#dropdownpie"),
      "년도"
    );
  } //end if
  else {
    if (country == 'CN') { document.getElementById('title6').innerHTML = `중국의 수입시장 점유율`; }
    else if (country == 'US') { document.getElementById('title6').innerHTML = `미국의 수입시장 점유율`; }
    else if (country == 'JP') { document.getElementById('title6').innerHTML = `일본의 수입시장 점유율`; }
    else if (country == 'AU') { document.getElementById('title6').innerHTML = `호주의 수입시장 점유율`; }
    else if (country == 'TW') { document.getElementById('title6').innerHTML = `대만의 수입시장 점유율`; }

    else if (country == 'HK') { document.getElementById('title6').innerHTML = `홍콩의 수입시장 점유율`; }
    else if (country == 'SG') { document.getElementById('title6').innerHTML = `싱가포르의 수입시장 점유율`; }
    else if (country == 'MX') { document.getElementById('title6').innerHTML = `멕시코의 수입시장 점유율`; }
    else if (country == 'IN') { document.getElementById('title6').innerHTML = `인도의 수입시장 점유율`; }
    else if (country == 'VN') { document.getElementById('title6').innerHTML = `베트남의 수입시장 점유율`; }
    maybeDisposeRoot("chart6");
    root = am5.Root.new(space);
  } //end else

  root.setThemes([am5themes_Animated.new(root)]);

  // data
  var data = [];
  console.log(resp);
  $.each(resp, function (index, item) {
    if (
      item.importMarket != "총계" &&
      item.dateYear == year &&
      item.country == country
    ) {
      // console.log(item);
      var percentile = parseFloat(item.percentile.replace("%", "")); // '%' 문자 제거 후 실수로 변환
      data.push({
        country: item.importMarket,
        percentile: percentile,
      });
    } //end if
  }); //end each
  console.log(data);

  root.setThemes([am5themes_Animated.new(root)]);

  // Create chart
  // https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/
  var chart = root.container.children.push(
    am5percent.PieChart.new(root, {
      endAngle: 270,
    })
  );

  // Create series
  // https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/#Series
  var series = chart.series.push(
    am5percent.PieSeries.new(root, {
      valueField: "percentile",
      categoryField: "country",
      endAngle: 270,
    })
  );

  series.states.create("hidden", {
    endAngle: -90,
  });

  series.labels.template.set("text", "{country}");
  series.data.setAll(data);
  series.appear(1000, 100);
} //end createRealPie

// =============================== xyclusterChart =====================================
// =======================================================================================
var respData = []; // 전역 변수로 resp 데이터를 저장할 배열 선언
var data = []; // 각 연도의 데이터가 담길 것.

//가장 처음으로 실행되는 함수(전체 데이터 받아옴)
function createxycluster(id, div, selectedyear, selectedport) {
  $.ajax({
    method: "GET",
    url: "/trade/xyCluster",
    async: false,
    success: function (resp) {
      respData = resp; // resp 데이터를 전역 변수에 저장
      createRealxycluster(id, div, resp, selectedyear, selectedport);
    },
    error: function (err) {
      console.error("Error fetching data:", err);
    },
  });
}

// 막대차트 그리는 함수
function createRealxycluster(id, div, resp, selectedyear, selectedport) {
  var space = document.getElementById("chart2");
  var root;

  //만약 그래프 영역이 비어있다면 새로 생성
  if (space == null) {
    //제목+그래프 감싸는 부모 div
    var parentContainer = createDiv(id, div);
    var newspace = parentContainer.firstChild;

    //제목 태그 생성
    var title = document.createElement("p");
    title.id = "title" + id;

    //단위 태그 생성
    var smalltitle2 = document.createElement('p');
    smalltitle2.id = "smalltitle2";

    //설명 태그 생성
    var information2 = document.createElement('span');
    information2.id = "information2";

    //설명 태그 생성
    var img = document.createElement('img');
    img.src = "/assets/img/information.png";
    img.alt = "설명 이미지";

    //이미지 크기 조절
    img.style.width = "25px";
    img.style.height = "27px";

    information2.appendChild(img);

    //이미지 툴팁
    var tooltipSpan = document.createElement('span');
    tooltipSpan.className = 'tooltip2';
    tooltipSpan.textContent = "한국이 가장 많이 수출, 수입한 품목 중 TOP5"; // 툴팁에 표시할 텍스트

    information2.appendChild(tooltipSpan);

    img.addEventListener('mouseenter', function () {
      tooltipSpan.classList.add('show-tooltip');
    });
    img.addEventListener('mouseleave', function () {
      tooltipSpan.classList.remove('show-tooltip');
    });

    //밑줄 태그 생성
    var line2 = document.createElement('div');
    line2.id = "line2";

    //위치 선정
    div.before(parentContainer);
    newspace.before(title, information2, smalltitle2, line2);

    //제목 글 설정
    document.getElementById(`title${id}`).innerText = "수출입 품목 TOP5";
    smalltitle2.innerText = "단위 : 천$";

    //부모 div css
    parentContainer.style.display = "inline-block";
    parentContainer.style.width = "590px";
    parentContainer.style.height = "430px";
    parentContainer.style.float = "right";
    parentContainer.style.borderRadius = "10px";
    parentContainer.style.border = "1.8px solid #c3c6ce";
    parentContainer.style.transition = "0.5s ease-out";

    parentContainer.addEventListener('mouseenter', function () {
      this.style.boxShadow = " 0 4px 18px 0 rgba(0, 0, 0, 0.5)";
      title.style.fontWeight = "bolder";
      title.style.color = "#008ff0";
      smalltitle2.style.fontWeight = "bolder";
      smalltitle2.style.color = "#008ff0";
      line2.style.borderTop = "2.5px solid #008ff0"
    });
    parentContainer.addEventListener("mouseleave", function () {
      this.style.borderColor = "#c3c6ce";
      this.style.boxShadow = "2px 2px 10px rgba(0, 0, 0, 0.07)";
      title.style.fontWeight = "normal";
      title.style.color = "black";
      smalltitle2.style.fontWeight = "normal";
      smalltitle2.style.color = "black";
      line2.style.borderTop = "1.5px solid black"
    });

    //그래프 css
    newspace.style.width = "570px";
    newspace.style.height = "320px";
    newspace.style.float = "right";
    newspace.style.margin = "10px";
    newspace.style.border = "0px";

    //그 외
    div.before(parentContainer);
    console.log(newspace.id);
    root = am5.Root.new(newspace);

    // 드롭박스 만들어보자
    // 컨테이너 div 생성
    var controlsContainer = document.createElement("div");
    controlsContainer.id = "controlsContainer";
    controlsContainer.style.display = "flex";
    controlsContainer.style.flexDirection = "row";
    controlsContainer.style.justifyContent = "flex-end";

    // 차트 div 가져오기
    var chartDiv = document.getElementById("chart2");

    // 새로운 select 요소 생성
    var helpers = {
      buildDropdown: function (result, dropdown, emptyMessage) {
        // Remove current options
        dropdown.html("");

        // Add the empty option with the empty message
        dropdown.append('<option value="">' + emptyMessage + "</option>");

        // Check result isnt empty
        if (result != "") {
          // Loop through each of the results and append the option to the dropdown
          $.each(result, function (k, v) {
            dropdown.append(
              '<option value="' + v.id + '">' + v.name + "</option>"
            );
          });
        }
      },
    };

    // 수출/수입 선택하는 드롭박스
    var importdd = document.createElement("select");
    importdd.id = "importdd"; // 선택 사항: id 설정
    controlsContainer.appendChild(importdd);

    // 드롭다운 생성 (연도 선택)
    var dropdown = document.createElement("select");
    dropdown.id = "dropdown"; // 선택 사항: id 설정
    controlsContainer.appendChild(dropdown);

    // 조회버튼 만들기
    var input = document.createElement("input");
    input.id = "replyBtn";
    input.type = "button";
    input.value = "조회";
    controlsContainer.appendChild(input);

    // 생성된 컨테이너를 차트 div에 추가
    chartDiv.prepend(controlsContainer);

    //데이터 예시와 함께 드롭다운 옵션 생성
    var dropdata =
      '[{"id":2019,"name":"2019"}, {"id":2020,"name":"2020"},{"id":2021,"name":"2021"},{"id":2022,"name":"2022"},{"id":2023,"name":"2023"}]';
    helpers.buildDropdown(jQuery.parseJSON(dropdata), $("#dropdown"), "년도");
    var dropdata = '[{"id":"수출","name":"수출"}, {"id":"수입","name":"수입"}]';
    helpers.buildDropdown(
      jQuery.parseJSON(dropdata),
      $("#importdd"),
      "수출/수입"
    );
  } //end if

  //그렇지 않다면 기존 그래프 지우는 함수 사용. 새로 만듦
  else {
    maybeDisposeRoot("chart2");
    //maybeDisposeRoot("chart2");
    root = am5.Root.new(space);
    console.log(newspace);
  } //end else

  root.setThemes([am5themes_Animated.new(root)]);

  //기본 뿌려주는거
  data = [];
  let count = 0;
  $.each(resp, function (index, item) {
    if (item.importexport == selectedport) {
      if (item.dateYear == selectedyear) {
        // alert(item)
        data.push({
          hscode: item.hs4digit,
          product: item.productName,
          price: item.price,
        });
        count++;
      } //end if
    }
    if (count >= 5) {
      // data 길이가 5개 이상이면 반복문 종료
      return false;
    }
  }); //end each
  console.log(data);

  // Create chart
  // https://www.amcharts.com/docs/v5/charts/xy-chart/
  var chart = root.container.children.push(
    am5xy.XYChart.new(root, {
      panX: false,
      panY: false,
      wheelX: "panX",
      wheelY: "zoomX",
      layout: root.verticalLayout,
    })
  );

  // Create axes
  // https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
  var xAxis = chart.xAxes.push(
    am5xy.CategoryAxis.new(root, {
      categoryField: "hscode",
      renderer: am5xy.AxisRendererX.new(root, {
        cellStartLocation: 0.1,
        cellEndLocation: 0.9,
        minGridDistance: 30,
        minorGridEnabled: true,
      }),
      tooltip: am5.Tooltip.new(root, {}),
    })
  );

  xAxis.data.setAll(data);

  var yAxis = chart.yAxes.push(
    am5xy.ValueAxis.new(root, {
      renderer: am5xy.AxisRendererY.new(root, {}),
    })
  );

  // Add series
  // https://www.amcharts.com/docs/v5/charts/xy-chart/series/
  var series = chart.series.push(
    am5xy.ColumnSeries.new(root, {
      //name: name,
      xAxis: xAxis,
      yAxis: yAxis,
      valueYField: "price",
      categoryXField: "hscode",
    })
  );

  series.columns.template.setAll({
    tooltipText: "{hscode} : {product}\n ${valueY}",
    width: am5.percent(90),
    tooltipY: 0,
  });
  series.columns.template.adapters.add("fill", function (fill, target) {
    // return chart.get("colors").getIndex(series.columns.indexOf(target));
    var value = target.dataItem.get("valueY");
    if (value > 60000000) {
      return am5.color("#575AEB"); // 값이 100000 초과일 경우 빨간색
    } else if (value > 35000000) {
      return am5.color("#9974ED");// 값이 50000 초과일 경우 녹색
    } else if (value > 25000000) {
      return am5.color("#A6A7ED"); // 값이 50000 초과일 경우 녹색
    } else if (value > 19000000) {
      return am5.color("#749DED"); // 값이 50000 초과일 경우 녹색
    } else {
      return am5.color("#6AC2F0"); // 그 외의 경우 파란색
    }
  });

  series.columns.template.adapters.add("stroke", function (stroke, target) {
    // return chart.get("colors").getIndex(series.columns.indexOf(target));
    var value = target.dataItem.get("valueY");
    if (value > 60000000) {
      return am5.color("#575AEB"); // 값이 100000 초과일 경우 빨간색
    } else if (value > 35000000) {
      return am5.color("#9974ED"); // 값이 50000 초과일 경우 녹색
    } else if (value > 30000000) {
      return am5.color("#A6A7ED"); // 값이 50000 초과일 경우 녹색
    } else if (value > 20000000) {
      return am5.color("#749DED"); // 값이 50000 초과일 경우 녹색
    } else {
      return am5.color("#6AC2F0"); // 그 외의 경우 파란색
    }
  });
  series.data.setAll(data);
  // Make stuff animate on load
  // https://www.amcharts.com/docs/v5/concepts/animations/
  series.appear();

  series.bullets.push(function () {
    return am5.Bullet.new(root, {
      locationY: 0,
      sprite: am5.Label.new(root, {
        text: "{valueY}",
        fill: root.interfaceColors.get("alternativeText"),
        centerY: 0,
        centerX: am5.p50,
        populateText: true,
      }),
    });
  });

  // Make stuff animate on load
  // https://www.amcharts.com/docs/v5/concepts/animations/
  chart.appear(1000, 100);

  var mapdiv = document.getElementById('parentContainer2');
  var mapcontrolsContainer = document.getElementById('mapcontrolsContainer');
  var maptitle;

  // mapcontrolsContainer가 이미 존재하지 않으면 새로 생성
  if (!mapcontrolsContainer) {
    mapcontrolsContainer = document.createElement('div');
    mapcontrolsContainer.id = 'mapcontrolsContainer';

    // 컨테이너 div 생성 후 맵 div 다음에 추가
    mapdiv.after(mapcontrolsContainer);

    // 지도 위에 제목 태그 생성
    maptitle = document.createElement('p');
    maptitle.id = 'maptitle';
    mapcontrolsContainer.appendChild(maptitle);
  } else {
    // 이미 존재하는 경우, maptitle만 찾아서 사용
    maptitle = document.getElementById('maptitle');
  }

  // 제목 설정
  maptitle.innerText = "국가별 수출입 현황";
}

// =============================== barChart(국가별 10대 수출입품목)==============================================
// =======================================================================================

// if~else로 사용자가 수출을 눌렀는지 수입을 눌렀는지에 따라 가져오는 데이터 달라짐. 기본은 수출 2019
function createBar(id, div, country, year, type) {
  if (type == "export") {
    $.ajax({
      url: "/trade/Exbarchart",
      method: "GET",
      async: false,
      data: { country: country },
      success: function (resp) {
        createRealBar(id, div, resp, country, year);
      },
    });
  } //end if
  else {
    $.ajax({
      url: "/trade/Ixbarchart",
      method: "GET",
      async: false,
      data: { country: country },
      success: function (resp) {
        createRealBar(id, div, resp, country, year);
      },
    });
  } //end else
} // end createBar

function createRealBar(id, div, resp, country, year) {
  console.log(resp);
  var space = document.getElementById("chart4");
  var root;

  if (space == null) {
    // 제목+그래프 감싸는 div 생성
    var parentContainer = createDiv(id, div);
    var newspace = parentContainer.firstChild;

    //제목 태그 생성
    var title = document.createElement("p");
    title.id = "title" + id;

    //단위 태그 생성
    var smalltitle4 = document.createElement("p");
    smalltitle4.id = "smalltitle4";

    //설명 태그 생성
    var information4 = document.createElement('span');
    information4.id = "information4";

    //이미지 태그 생성
    var img = document.createElement('img');
    img.src = "/assets/img/information.png";
    img.alt = "설명 이미지";

    //이미지 크기 조절
    img.style.width = "25px";
    img.style.height = "27px";

    //span 태그에 이미지 태그 추가
    information4.appendChild(img);

    //이미지 툴팁
    var tooltipSpan4 = document.createElement('span');
    tooltipSpan4.className = 'tooltip4';
    tooltipSpan4.textContent = "국가가 가장 많이 수출, 수입한 TOP5 품목";

    information4.appendChild(tooltipSpan4);

    img.addEventListener('mouseenter', function () {
      tooltipSpan4.classList.add('show-tooltip');
    });
    img.addEventListener('mouseleave', function () {
      tooltipSpan4.classList.remove('show-tooltip');
    });

    //밑줄 태그 생성
    var line4 = document.createElement('div');
    line4.id = "line4";

    //위치 선정
    div.before(parentContainer);
    newspace.before(title, information4, smalltitle4, line4);

    //제목 글 설정
    title.innerText = `미국의 TOP5 수출입품목`;
    smalltitle4.innerText = "단위 : 천$";

    //부모 div css
    parentContainer.style.display = "inline-block";
    parentContainer.style.width = "590px";
    parentContainer.style.height = "430px";
    parentContainer.style.borderRadius = "10px";
    parentContainer.style.border = "1.8px solid #c3c6ce";
    parentContainer.style.transition = "0.5s ease-out";

    parentContainer.addEventListener('mouseenter', function () {
      this.style.boxShadow = " 0 4px 18px 0 rgba(0, 0, 0, 0.5)";
      title.style.fontWeight = "bolder";
      title.style.color = "#008ff0";
      // title.style.borderBottom = "2px solid #008ff0";
      smalltitle4.style.fontWeight = "bolder";
      smalltitle4.style.color = "#008ff0";
      line4.style.borderTop = "2.5px solid  #008ff0";
    });

    parentContainer.addEventListener('mouseleave', function () {
      this.style.borderColor = "#c3c6ce";
      this.style.boxShadow = "2px 2px 10px rgba(0, 0, 0, 0.07)";
      title.style.fontWeight = "normal";
      title.style.color = "black";
      // title.style.borderBottom = "1.7px solid black"
      smalltitle4.style.fontWeight = "normal";
      smalltitle4.style.color = "black";
      // smalltitle4.style.borderBottom = "1.7px solid black"
      line4.style.borderTop = "1.5px solid black";
    });

    //그래프 css
    newspace.style.width = "570px";
    newspace.style.height = "320px";
    newspace.style.float = "right";
    newspace.style.margin = "10px";
    newspace.style.border = "0px";

    //그 외
    div.after(parentContainer);
    root = am5.Root.new(newspace);

    // 드롭박스 만들어보자
    // 컨테이너 div 생성
    var controlsContainer = document.createElement("div");
    controlsContainer.id = "controlsContainer";
    controlsContainer.style.display = "flex";
    controlsContainer.style.flexDirection = "row";
    controlsContainer.style.justifyContent = "flex-end";

    // 차트 div 가져오기
    var chartDiv = document.getElementById("chart4");

    // 새로운 select 요소 생성
    var helpers = {
      buildDropdown: function (result, dropdown, emptyMessage) {
        // Remove current options
        dropdown.html("");

        // Add the empty option with the empty message
        dropdown.append('<option value="">' + emptyMessage + "</option>");

        // Check result isnt empty
        if (result != "") {
          // Loop through each of the results and append the option to the dropdown
          $.each(result, function (k, v) {
            dropdown.append(
              '<option value="' + v.id + '">' + v.name + "</option>"
            );
          });
        }
      },
    }; //end helpers

    // 수출/수입 선택하는 드롭박스
    var importbar = document.createElement("select");
    importbar.id = "importbar"; // 선택 사항: id 설정
    controlsContainer.appendChild(importbar);

    // 연도 선택하는 드롭박스
    var dropdown = document.createElement("select");
    dropdown.id = "dropdownbar"; // 선택 사항: id 설정
    controlsContainer.appendChild(dropdown);

    // 조회버튼 만들기
    var input = document.createElement("input");
    input.id = "replyBtnbar";
    input.type = "button";
    input.value = "조회";
    controlsContainer.appendChild(input);

    //생성된 컨테이너를 차트 div에 추가
    chartDiv.prepend(controlsContainer);

    //데이터 예시와 함께 드롭다운 옵션 생성
    var dropdata =
      '[{"id":2019,"name":"2019"}, {"id":2020,"name":"2020"},{"id":2021,"name":"2021"},{"id":2022,"name":"2022"},{"id":2023,"name":"2023"}]';
    helpers.buildDropdown(
      jQuery.parseJSON(dropdata),
      $("#dropdownbar"),
      "년도"
    );

    var dropdata = '[{"id":"수출","name":"수출"}, {"id":"수입","name":"수입"}]';
    helpers.buildDropdown(
      jQuery.parseJSON(dropdata),
      $("#importbar"),
      "수출/수입"
    );
  } //end if
  else {
    if (country == 'CN') { document.getElementById('title4').innerHTML = `중국의 TOP5 수출입품목`; }
    else if (country == 'US') { document.getElementById('title4').innerHTML = `미국의 TOP5 수출입품목`; }
    else if (country == 'JP') { document.getElementById('title4').innerHTML = `일본의 TOP5 수출입품목`; }
    else if (country == 'AU') { document.getElementById('title4').innerHTML = `호주의 TOP5 수출입품목`; }
    else if (country == 'TW') { document.getElementById('title4').innerHTML = `대만의 TOP5 수출입품목`; }
    else if (country == 'HK') { document.getElementById('title4').innerHTML = `홍콩의 TOP5 수출입품목`; }
    else if (country == 'SG') { document.getElementById('title4').innerHTML = `싱가포르의 TOP5 수출입품목`; }
    else if (country == 'MX') { document.getElementById('title4').innerHTML = `멕시코의 TOP5 수출입품목`; }
    else if (country == 'IN') { document.getElementById('title4').innerHTML = `인도의 TOP5 수출입품목`; }
    else if (country == 'VN') { document.getElementById('title4').innerHTML = `베트남의 TOP5 수출입품목`; }
    maybeDisposeRoot("chart4");
    root = am5.Root.new(space);
  } //end else

  var myTheme = am5.Theme.new(root);

  myTheme.rule("Grid", ["base"]).setAll({
    strokeOpacity: 0.1,
  });
  // Set themes
  // https://www.amcharts.com/docs/v5/concepts/themes/
  root.setThemes([am5themes_Animated.new(root), myTheme]);

  // data
  var data = [];
  $.each(resp, function (index, item) {
    // console.log(item);
    if (item.country == country && item.dateYear == year) {
      // console.log(item);
      data.push({
        hscode: item.hscode,
        country: item.country,
        productName: item.productName,
        price: item.price,
      });
    } //end if
  }); //end each
  console.log("barchartDATA : ", data);

  // Create chart
  // https://www.amcharts.com/docs/v5/charts/xy-chart/
  var chart = root.container.children.push(
    am5xy.XYChart.new(root, {
      panX: false,
      panY: false,
      wheelX: "none",
      wheelY: "none",
      paddingLeft: 0,
    })
  );

  // Create axes
  // https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
  var yRenderer = am5xy.AxisRendererY.new(root, {
    minGridDistance: 30,
    minorGridEnabled: true,
  }); //end yRenderer
  yRenderer.grid.template.set("location", 1);

  var yAxis = chart.yAxes.push(
    am5xy.CategoryAxis.new(root, {
      maxDeviation: 0,
      categoryField: "hscode",
      renderer: yRenderer,
    })
  ); //end yAxis

  var xAxis = chart.xAxes.push(
    am5xy.ValueAxis.new(root, {
      maxDeviation: 0,
      min: 0,
      renderer: am5xy.AxisRendererX.new(root, {
        visible: true,
        strokeOpacity: 0.1,
        minGridDistance: 80,
      }),
    })
  ); //end xAxis

  // Create series
  // https://www.amcharts.com/docs/v5/charts/xy-chart/series/
  var series = chart.series.push(
    am5xy.ColumnSeries.new(root, {
      name: "Series 1",
      xAxis: xAxis,
      yAxis: yAxis,
      valueXField: "price",
      sequencedInterpolation: true,
      categoryYField: "hscode", //여기도 "hscode"라고 바꿔줘야 그래프 그려짐
    })
  ); //end series

  var columnTemplate = series.columns.template;

  columnTemplate.setAll({
    draggable: false, //그래프 드래그하는거 못하게 해놓음
    cursorOverStyle: "pointer",
    tooltipText: "{productName}\n${price}", //productName도 써주기
    cornerRadiusBR: 10,
    cornerRadiusTR: 10,
    strokeOpacity: 0,
  });
  columnTemplate.adapters.add("fill", (fill, target) => {
    return chart.get("colors").getIndex(series.columns.indexOf(target));
  });

  columnTemplate.adapters.add("stroke", (stroke, target) => {
    return chart.get("colors").getIndex(series.columns.indexOf(target));
  });

  columnTemplate.events.on("dragstop", () => {
    sortCategoryAxis();
  });

  yAxis.data.setAll(data);
  // xAxis.data.setAll(data);
  series.data.setAll(data);

  // Make stuff animate on load
  // https://www.amcharts.com/docs/v5/concepts/animations/
  series.appear(1000);
  chart.appear(1000, 100);
} //end createRealBar

// =============================== StringChart ==============================================
// ==========================================================================================\
function createString(id, div, country) {
  $.ajax({
    url: "/trade/stringChart",
    method: "GET",
    async: false,
    data: { country: country },
    success: function (resp) {
      createRealString(id, div, resp, country);
    },
  });
}

function createRealString(id, div, resp, country) {
  var space = document.getElementById("chart5");
  var root;

  //만약 그래프 영역이 비어있다면 새로 생성
  if (space == null) {
    //제목+그래프 감싸는 부모div생성
    var parentContainer = createDiv(id, div);
    var newspace = parentContainer.firstChild;

    //제목 태그 생성
    var title = document.createElement("p");
    title.id = "title" + id;

    //단위 태그 생성
    var smalltitle5 = document.createElement("p");
    smalltitle5.id = "smalltitle5";

    //설명 태그 생성
    var information5 = document.createElement('span');
    information5.id = "information5";

    //이미지 태그 생성
    var img = document.createElement('img');
    img.src = "/assets/img/information.png";
    img.alt = "설명 이미지";

    //이미지 크기 조절
    img.style.width = "25px";
    img.style.height = "27px";

    //span 태그에 이미지 태그 추가
    information5.appendChild(img);

    //이미지 툴팁
    var tooltipSpan5 = document.createElement('span');
    tooltipSpan5.className = 'tooltip5';
    tooltipSpan5.textContent = "국가의 수입시장을 점유한 나라 TOP10";

    information5.appendChild(tooltipSpan5);

    img.addEventListener('mouseenter', function () {
      tooltipSpan5.classList.add('show-tooltip');
    });
    img.addEventListener('mouseleave', function () {
      tooltipSpan5.classList.remove('show-tooltip');
    });

    //밑줄 태그 생성
    var line5 = document.createElement('div');
    line5.id = "line5";

    //위치 선정
    div.before(parentContainer);
    newspace.before(title, information5, smalltitle5, line5);

    //제목 글 선정
    title.innerText = `미국의 수출입 금액`;
    smalltitle5.innerText = "단위 : 백만$"

    //부모 div css
    parentContainer.style.display = "inline-block";
    parentContainer.style.width = "590px";
    parentContainer.style.height = "430px";
    parentContainer.style.float = "right";
    parentContainer.style.borderRadius = "10px";
    parentContainer.style.border = "1.8px solid #c3c6ce";
    parentContainer.style.transition = "0.5s ease-out";

    parentContainer.addEventListener('mouseenter', function () {
      this.style.boxShadow = " 0 4px 18px 0 rgba(0, 0, 0, 0.5)";
      title.style.fontWeight = "bolder";
      title.style.color = "#008ff0";
      smalltitle5.style.fontWeight = "bolder";
      smalltitle5.style.color = "#008ff0";
      line5.style.borderTop = "2.5px solid #008ff0";
    });
    parentContainer.addEventListener("mouseleave", function () {
      this.style.borderColor = "#c3c6ce";
      this.style.boxShadow = "2px 2px 10px rgba(0, 0, 0, 0.07)";
      title.style.fontWeight = "normal";
      title.style.color = "black";
      smalltitle5.style.fontWeight = "normal";
      smalltitle5.style.color = "black";
      line5.style.borderTop = "1.5px solid black"
    });

    //그래프 css
    newspace.style.width = "570px";
    newspace.style.height = "320px";
    newspace.style.float = "right";
    newspace.style.margin = "10px";
    newspace.style.border = "0px";

    //그 외
    div.after(parentContainer);
    root = am5.Root.new(newspace);
  } //end if
  else {
    if (country == 'CN') { document.getElementById('title5').innerHTML = `중국의 수출입 금액`; }
    else if (country == 'US') { document.getElementById('title5').innerHTML = `미국의 수출입 금액`; }
    else if (country == 'JP') { document.getElementById('title5').innerHTML = `일본의 수출입 금액`; }
    else if (country == 'AU') { document.getElementById('title5').innerHTML = `호주의 수출입 금액`; }
    else if (country == 'TW') { document.getElementById('title5').innerHTML = `대만의 수출입 금액`; }
    else if (country == 'HK') { document.getElementById('title5').innerHTML = `홍콩의 수출입 금액`; }
    else if (country == 'SG') { document.getElementById('title5').innerHTML = `싱가포르의 수출입 금액`; }
    else if (country == 'MX') { document.getElementById('title5').innerHTML = `멕시코의 수출입 금액`; }
    else if (country == 'IN') { document.getElementById('title5').innerHTML = `인도의 수출입 금액`; }
    else if (country == 'VN') { document.getElementById('title5').innerHTML = `베트남의 수출입 금액`; }
    maybeDisposeRoot("chart5");
    root = am5.Root.new(space);
  } //end else

  root.setThemes([am5themes_Animated.new(root)]);

  console.log(resp);
  var data = [];
  $.each(resp, function (index, item) {
    data.push({
      date: new Date(item.dateYear, item.dateMonth, 12).getTime(),
      value1: item.exportPrice,
      value2: item.importPrice,
      previousDate: new Date(2019, 5, 5),
    });
  });
  //end each
  console.log(data);

  // Create chart
  // https://www.amcharts.com/docs/v5/charts/xy-chart/
  var chart = root.container.children.push(
    am5xy.XYChart.new(root, {
      panX: true,
      panY: true,
      wheelX: "panX",
      wheelY: "zoomX",
      layout: root.verticalLayout,
    })
  );

  chart.get("colors").set("step", 3);

  // Add cursor
  // https://www.amcharts.com/docs/v5/charts/xy-chart/cursor/
  var cursor = chart.set("cursor", am5xy.XYCursor.new(root, {}));
  cursor.lineY.set("visible", false);

  // Create axes
  // https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
  var xAxis = chart.xAxes.push(
    am5xy.DateAxis.new(root, {
      maxDeviation: 0.3,
      baseInterval: {
        timeUnit: "month",
        count: 1,
      },
      renderer: am5xy.AxisRendererX.new(root, {
        minorGridEnabled: true,
      }),
      tooltip: am5.Tooltip.new(root, {}),
    })
  );

  var yAxis = chart.yAxes.push(
    am5xy.ValueAxis.new(root, {
      maxDeviation: 0.3,
      renderer: am5xy.AxisRendererY.new(root, {}),
    })
  );

  // Add series
  // https://www.amcharts.com/docs/v5/charts/xy-chart/series/
  var series = chart.series.push(
    am5xy.LineSeries.new(root, {
      name: "수출액",
      xAxis: xAxis,
      yAxis: yAxis,
      valueYField: "value1",
      valueXField: "date",
      tooltip: am5.Tooltip.new(root, {
        labelText: "{valueX}: {valueY}\n{previousDate}: {value2}",
      }),
    })
  );

  series.strokes.template.setAll({
    strokeWidth: 2,
  });

  series.get("tooltip").get("background").set("fillOpacity", 0.5);

  var series2 = chart.series.push(
    am5xy.LineSeries.new(root, {
      name: "수출액",
      xAxis: xAxis,
      yAxis: yAxis,
      valueYField: "value2",
      valueXField: "date",
    })
  );
  series2.strokes.template.setAll({
    strokeDasharray: [2, 2],
    strokeWidth: 2,
  });

  // Set date fields
  // https://www.amcharts.com/docs/v5/concepts/data/#Parsing_dates
  root.dateFormatter.setAll({
    dateFormat: "yyyy-MM-dd",
    dateFields: ["valueX"],
  });

  series.data.setAll(data);
  series2.data.setAll(data);

  series.set("selectedDataItem", series.dataItems[0]);

  // Add legend
  // var legend = chart.children.push(am5.Legend.new(root, {
  //   // nameField: "categoryX",
  //   centerX: am5.percent(50),
  //   x: am5.percent(50)
  // }));

  // legend.data.setAll(chart.series.values);

  var legend = chart.children.push(
    am5.Legend.new(root, {
      width: am5.percent(100),
      centerX: am5.percent(50),
      x: am5.percent(50),
      // , layout: root.verticalLayout
    })
  );
  legend.data.setAll(chart.series.values);

  // Make stuff animate on load
  // https://www.amcharts.com/docs/v5/concepts/animations/
  series.appear(1000);
  series2.appear(1000);
  chart.appear(1000, 100);
} //end StringChart

// ====================================== StackCluster =======================================
// ===========================================================================================
// // 첫 화면에 시작되는
function createStackCluster(id, div, country) {
  $.ajax({
    url: "/trade/StackCluster",
    method: "GET",
    data: { country: country },
    success: function (resp) {
      createRealStackCluster(id, div, resp, country);
    },
  });
}

function createRealStackCluster(id, div, resp, country) {
  var space = document.getElementById("chart3");
  var root;

  //만약 첫 화면이라면
  if (space == null) {
    //제목+그래프 감싸는 부모 div
    var parentContainer = createDiv(id, div);
    var newspace = parentContainer.firstChild;

    //제목 태그 생성
    var title = document.createElement("p");
    title.id = "title" + id;

    //단위 제목 태그 생성
    var smalltitle3 = document.createElement("p");
    smalltitle3.id = "smalltitle3";

    //설명 태그 생성
    var information3 = document.createElement('span');
    information3.id = "information3";

    //이미지 태그 생성
    var img = document.createElement('img');
    img.src = "/assets/img/information.png";
    img.alt = "설명 이미지";

    //이미지 크기 조절
    img.style.width = "25px";
    img.style.height = "27px";

    //span 태그에 이미지 태그 추가
    information3.appendChild(img);

    //이미지 툴팁
    var tooltipSpan3 = document.createElement('span');
    tooltipSpan3.className = 'tooltip3';
    tooltipSpan3.textContent = "수출금액 높은 기준 TOP5품목의 최근 월별 수출수입 금액 증감율";

    information3.appendChild(tooltipSpan3);

    img.addEventListener('mouseenter', function () {
      tooltipSpan3.classList.add('show-tooltip');
    });
    img.addEventListener('mouseleave', function () {
      tooltipSpan3.classList.remove('show-tooltip');
    });

    //밑줄 태그 생성
    var line3 = document.createElement('div');
    line3.id = "line3";

    //위치 선정
    div.before(parentContainer);
    newspace.before(title, information3, smalltitle3, line3);

    //제목 내용 선정
    title.innerText = `미국의 수출입 품목 증감율`;
    smalltitle3.innerText = "단위 : 천$"

    //css
    parentContainer.style.display = "inline-block";
    parentContainer.style.width = "590px";
    parentContainer.style.height = "430px";
    parentContainer.style.borderRadius = "10px";
    parentContainer.style.background = "#ffff";
    parentContainer.style.border = "1.8px solid #c3c6ce";
    parentContainer.style.transition = "0.5s ease-out";
    parentContainer.style.marginBottom = "30px";

    parentContainer.addEventListener('mouseenter', function () {
      this.style.boxShadow = " 0 4px 18px 0 rgba(0, 0, 0, 0.5)";
      title.style.fontWeight = "bolder";
      title.style.color = "#008ff0";
      smalltitle3.style.fontWeight = "bolder";
      smalltitle3.style.color = "#008ff0";
      line3.style.borderTop = "2.5px solid #008ff0";
    });

    parentContainer.addEventListener('mouseleave', function () {
      this.style.borderColor = "#c3c6ce";
      this.style.boxShadow = "2px 2px 10px rgba(0, 0, 0, 0.07)";
      title.style.fontWeight = "normal";
      title.style.color = "black";
      smalltitle3.style.fontWeight = "normal";
      smalltitle3.style.color = "black";
      line3.style.borderTop = "1.5px solid black";
    });

    newspace.style.width = "570px";
    newspace.style.height = "340px";
    newspace.style.margin = "10px";
    newspace.style.border = "0px";

    //그 외
    div.after(parentContainer);
    root = am5.Root.new(newspace);
  } //end if

  //그렇지 않다면 기존 그래프 지우는 함수 사용. 새로 만듦
  else {
    if (country == 'CN') { document.getElementById('title3').innerHTML = `중국의 수출입품목 증감율`; }
    else if (country == 'US') { document.getElementById('title3').innerHTML = `미국의 수출입품목 증감율`; }
    else if (country == 'JP') { document.getElementById('title3').innerHTML = `일본의 수출입품목 증감율`; }
    else if (country == 'AU') { document.getElementById('title3').innerHTML = `호주의 수출입품목 증감율`; }
    else if (country == 'TW') { document.getElementById('title3').innerHTML = `대만의 수출입품목 증감율`; }
    else if (country == 'HK') { document.getElementById('title3').innerHTML = `홍콩의 수출입품목 증감율`; }
    else if (country == 'SG') { document.getElementById('title3').innerHTML = `싱가포르의 수출입품목 증감율`; }
    else if (country == 'MX') { document.getElementById('title3').innerHTML = `멕시코의 수출입품목 증감율`; }
    else if (country == 'IN') { document.getElementById('title3').innerHTML = `인도의 수출입품목 증감율`; }
    else if (country == 'VN') { document.getElementById('title3').innerHTML = `베트남의 수출입품목 증감율`; }
    maybeDisposeRoot("chart3");
    root = am5.Root.new(space);
  } //end else

  root.setThemes([am5themes_Animated.new(root)]);

  //기본 뿌려주는거
  var data = [];
  //let count = 0;
  $.each(resp, function (index, item) {
    if (item.country == country) {
      data.push({
        hscode: item.hscode,
        country: item.country,
        productName: item.productName,
        importrate: item.importrate,
        exportrate: item.exportrate,
        //count ++;
      });
    } //end if
  }); //end each
  console.log(data);

  // Create chart
  // https://www.amcharts.com/docs/v5/charts/xy-chart/
  // 마우스로 줌인아웃 하는거 설정
  var chart = root.container.children.push(
    am5xy.XYChart.new(root, {
      panX: false,
      panY: false,
      wheelX: "panX",
      wheelY: "zoomX",
      layout: root.verticalLayout,
    })
  );

  // Add legend
  // https://www.amcharts.com/docs/v5/charts/xy-chart/legend-xy-series/
  var legend = chart.children.push(
    am5.Legend.new(root, {
      centerX: am5.p50,
      x: am5.p50,
    })
  );

  // Create axes
  // https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
  var xAxis = chart.xAxes.push(
    am5xy.CategoryAxis.new(root, {
      categoryField: "hscode",
      renderer: am5xy.AxisRendererX.new(root, {
        cellStartLocation: 0.1,
        cellEndLocation: 0.9,
        minGridDistance: 30,
        minorGridEnabled: false,
      }),
      tooltip: am5.Tooltip.new(root, {}),
    })
  );

  xAxis.data.setAll(data);

  var yAxis = chart.yAxes.push(
    am5xy.ValueAxis.new(root, {
      baseValue: 0,
      min: -5,
      numberFormat: "#.0'%'",
      renderer: am5xy.AxisRendererY.new(root, {}),
    })
  );

  function makeSeries(name, fieldName, stacked, port) {
    var series = chart.series.push(
      am5xy.ColumnSeries.new(root, {
        stacked: stacked,
        name: port,
        xAxis: xAxis,
        yAxis: yAxis,
        valueYField: fieldName,
        categoryXField: "hscode",
      })
    );

    series.columns.template.setAll({
      tooltipText: "{productName}",
      width: am5.percent(90),
      tooltipY: am5.percent(10),
    });

    series.data.setAll(data);

    // Make stuff animate on load
    // https://www.amcharts.com/docs/v5/concepts/animations/
    series.appear();

    series.bullets.push(function () {
      return am5.Bullet.new(root, {
        locationY: 1,
        sprite: am5.Label.new(root, {
          text: "{valueY}%",
          fill: am5.color(0x000000),
          centerY: am5.percent(50),
          centerX: am5.percent(50),
          populateText: true,
          fontSize: 15,
          // location: 0.5
        }),
      });
    });

    legend.data.push(series);
  } //end makeSeries

  for (let i = 0; i < 1; i++) {
    makeSeries(data[i].productName + " 수출", "exportrate", false, "수출");
    makeSeries(data[i].productName + " 수입", "importrate", false, "수입");
  }

  // Make stuff animate on load
  // https://www.amcharts.com/docs/v5/concepts/animations/
  chart.appear(1000, 100);
} //end StackCluster
