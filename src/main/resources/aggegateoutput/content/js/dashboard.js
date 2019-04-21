/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
var showControllersOnly = false;
var seriesFilter = "";
var filtersOnlySampleSeries = true;

/*
 * Add header in statistics table to group metrics by category
 * format
 *
 */
function summaryTableHeader(header) {
    var newRow = header.insertRow(-1);
    newRow.className = "tablesorter-no-sort";
    var cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Requests";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 3;
    cell.innerHTML = "Executions";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 6;
    cell.innerHTML = "Response Times (ms)";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Throughput";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 2;
    cell.innerHTML = "Network (KB/sec)";
    newRow.appendChild(cell);
}

/*
 * Populates the table identified by id parameter with the specified data and
 * format
 *
 */
function createTable(table, info, formatter, defaultSorts, seriesIndex, headerCreator) {
    var tableRef = table[0];

    // Create header and populate it with data.titles array
    var header = tableRef.createTHead();

    // Call callback is available
    if(headerCreator) {
        headerCreator(header);
    }

    var newRow = header.insertRow(-1);
    for (var index = 0; index < info.titles.length; index++) {
        var cell = document.createElement('th');
        cell.innerHTML = info.titles[index];
        newRow.appendChild(cell);
    }

    var tBody;

    // Create overall body if defined
    if(info.overall){
        tBody = document.createElement('tbody');
        tBody.className = "tablesorter-no-sort";
        tableRef.appendChild(tBody);
        var newRow = tBody.insertRow(-1);
        var data = info.overall.data;
        for(var index=0;index < data.length; index++){
            var cell = newRow.insertCell(-1);
            cell.innerHTML = formatter ? formatter(index, data[index]): data[index];
        }
    }

    // Create regular body
    tBody = document.createElement('tbody');
    tableRef.appendChild(tBody);

    var regexp;
    if(seriesFilter) {
        regexp = new RegExp(seriesFilter, 'i');
    }
    // Populate body with data.items array
    for(var index=0; index < info.items.length; index++){
        var item = info.items[index];
        if((!regexp || filtersOnlySampleSeries && !info.supportsControllersDiscrimination || regexp.test(item.data[seriesIndex]))
                &&
                (!showControllersOnly || !info.supportsControllersDiscrimination || item.isController)){
            if(item.data.length > 0) {
                var newRow = tBody.insertRow(-1);
                for(var col=0; col < item.data.length; col++){
                    var cell = newRow.insertCell(-1);
                    cell.innerHTML = formatter ? formatter(col, item.data[col]) : item.data[col];
                }
            }
        }
    }

    // Add support of columns sort
    table.tablesorter({sortList : defaultSorts});
}

$(document).ready(function() {

    // Customize table sorter default options
    $.extend( $.tablesorter.defaults, {
        theme: 'blue',
        cssInfoBlock: "tablesorter-no-sort",
        widthFixed: true,
        widgets: ['zebra']
    });

    var data = {"OkPercent": 94.34226496125478, "KoPercent": 5.657735038745215};
    var dataset = [
        {
            "label" : "KO",
            "data" : data.KoPercent,
            "color" : "#FF6347"
        },
        {
            "label" : "OK",
            "data" : data.OkPercent,
            "color" : "#9ACD32"
        }];
    $.plot($("#flot-requests-summary"), dataset, {
        series : {
            pie : {
                show : true,
                radius : 1,
                label : {
                    show : true,
                    radius : 3 / 4,
                    formatter : function(label, series) {
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
                            + label
                            + '<br/>'
                            + Math.round10(series.percent, -2)
                            + '%</div>';
                    },
                    background : {
                        opacity : 0.5,
                        color : '#000'
                    }
                }
            }
        },
        legend : {
            show : true
        }
    });

    // Creates APDEX table
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [0.00126038651853235, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [0.0, 500, 1500, "UpdateTask"], "isController": false}, {"data": [0.0, 500, 1500, "CreateUser"], "isController": false}, {"data": [0.0, 500, 1500, "UpdateProjectStatus"], "isController": false}, {"data": [0.0, 500, 1500, "CreateProject"], "isController": false}, {"data": [0.0011123470522803114, 500, 1500, "TaskUsers"], "isController": false}, {"data": [0.0, 500, 1500, "CreateTask"], "isController": false}, {"data": [0.0, 500, 1500, "GetParentTask"], "isController": false}, {"data": [0.0, 500, 1500, "GetTasks"], "isController": false}, {"data": [0.0, 500, 1500, "UpdateProject"], "isController": false}, {"data": [0.0, 500, 1500, "CreateParentTask"], "isController": false}, {"data": [0.0, 500, 1500, "GetAllProjects"], "isController": false}, {"data": [0.002079002079002079, 500, 1500, "UpdateUser"], "isController": false}, {"data": [0.0, 500, 1500, "AllUsers"], "isController": false}, {"data": [0.0, 500, 1500, "GetActiveProjects"], "isController": false}, {"data": [0.011437908496732025, 500, 1500, "UnassignedUsers"], "isController": false}]}, function(index, item){
        switch(index){
            case 0:
                item = item.toFixed(3);
                break;
            case 1:
            case 2:
                item = formatDuration(item);
                break;
        }
        return item;
    }, [[0, 0]], 3);

    // Create statistics table
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 10711, 606, 5.657735038745215, 79185.70002800859, 38, 909786, 212075.40000000014, 278309.7999999998, 790966.759999999, 5.918078456116932, 159.4046298243238, 1.6082451629681669], "isController": false}, "titles": ["Label", "#Samples", "KO", "Error %", "Average", "Min", "Max", "90th pct", "95th pct", "99th pct", "Transactions\/s", "Received", "Sent"], "items": [{"data": ["UpdateTask", 489, 115, 23.517382413087933, 120550.59509202448, 7930, 863257, 242249.0, 458494.0, 597997.9, 0.29070769786850026, 0.19068461755737762, 0.21400748698652042], "isController": false}, {"data": ["CreateUser", 969, 13, 1.3415892672858618, 29788.308565531515, 2815, 858820, 57066.0, 114297.5, 320099.0999999906, 0.535505862379967, 0.11873613743047819, 0.1329217625037027], "isController": false}, {"data": ["UpdateProjectStatus", 727, 78, 10.729023383768913, 105159.37001375515, 3532, 880821, 262638.4, 272910.60000000003, 843630.0800000001, 0.40786466298709534, 0.16146432635849228, 0.0908026588624447], "isController": false}, {"data": ["CreateProject", 832, 17, 2.043269230769231, 51810.89062500001, 3624, 883452, 155992.60000000003, 255585.34999999983, 715324.4699999983, 0.4633053271202901, 0.15826332215117342, 0.13793748454720814], "isController": false}, {"data": ["TaskUsers", 899, 21, 2.335928809788654, 42866.69521690766, 88, 883616, 107776.0, 201814.0, 813282.0, 0.4987453696846032, 21.228678713461633, 0.08133835618879759], "isController": false}, {"data": ["CreateTask", 492, 82, 16.666666666666668, 149742.96341463426, 15124, 811037, 350569.9999999989, 486292.84999999986, 584110.99, 0.28388355004457316, 0.19544548025797628, 0.1716470853612447], "isController": false}, {"data": ["GetParentTask", 478, 12, 2.510460251046025, 72256.51882845191, 7010, 816307, 148623.6, 165269.94999999998, 538643.6399999994, 0.2951952951551535, 0.05588707344125459, 0.053040492241242486], "isController": false}, {"data": ["GetTasks", 474, 7, 1.4767932489451476, 56996.88185654006, 6905, 883758, 120606.0, 139515.25, 546424.75, 0.32179378992296637, 0.3288047861989161, 0.05593415922546815], "isController": false}, {"data": ["UpdateProject", 786, 60, 7.633587786259542, 86261.12213740459, 3791, 909786, 258860.9000000001, 273858.19999999995, 858812.19, 0.43954052915982333, 0.18856962813389322, 0.15063660504431475], "isController": false}, {"data": ["CreateParentTask", 533, 54, 10.131332082551594, 151466.15947467164, 17237, 697790, 275921.6, 292222.6999999996, 571354.5999999997, 0.30329826909441854, 0.17312816812966825, 0.13735422975896608], "isController": false}, {"data": ["GetAllProjects", 606, 45, 7.425742574257426, 165138.24917491738, 10536, 695270, 351000.0, 380388.45, 565368.6599999997, 0.3421007469764165, 43.45607089988586, 0.056459986561537494], "isController": false}, {"data": ["UpdateUser", 962, 40, 4.158004158004158, 50080.761954261965, 1365, 881761, 72240.90000000004, 226895.3499999984, 861369.9, 0.5326190406324103, 0.12478338561480126, 0.15545409493103912], "isController": false}, {"data": ["AllUsers", 879, 15, 1.7064846416382253, 50024.11945392494, 3466, 868465, 144668.0, 256007.0, 737657.8000000007, 0.48777916200760807, 28.932757428019276, 0.07907357509107708], "isController": false}, {"data": ["GetActiveProjects", 667, 33, 4.94752623688156, 143391.8890554723, 6151, 845713, 325023.60000000003, 370384.8, 831948.0800000005, 0.37523811024011866, 47.950274788429276, 0.06082961552720673], "isController": false}, {"data": ["UnassignedUsers", 918, 14, 1.5250544662309369, 33358.16448801743, 38, 906245, 66500.3, 113273.34999999999, 741541.3799999994, 0.5091454841624141, 18.302479495697778, 0.08104561906100928], "isController": false}]}, function(index, item){
        switch(index){
            // Errors pct
            case 3:
                item = item.toFixed(2) + '%';
                break;
            // Mean
            case 4:
            // Mean
            case 7:
            // Percentile 1
            case 8:
            // Percentile 2
            case 9:
            // Percentile 3
            case 10:
            // Throughput
            case 11:
            // Kbytes/s
            case 12:
            // Sent Kbytes/s
                item = item.toFixed(2);
                break;
        }
        return item;
    }, [[0, 0]], 0, summaryTableHeader);

    // Create error table
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": [{"data": ["500", 513, 84.65346534653466, 4.789468770422929], "isController": false}, {"data": ["405", 93, 15.346534653465346, 0.8682662683222855], "isController": false}]}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 10711, 606, "500", 513, "405", 93, null, null, null, null, null, null], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": ["UpdateTask", 489, 115, "405", 81, "500", 34, null, null, null, null, null, null], "isController": false}, {"data": ["CreateUser", 969, 13, "500", 13, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["UpdateProjectStatus", 727, 78, "500", 78, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["CreateProject", 832, 17, "500", 17, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["TaskUsers", 899, 21, "500", 21, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["CreateTask", 492, 82, "500", 82, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["GetParentTask", 478, 12, "500", 12, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["GetTasks", 474, 7, "500", 7, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["UpdateProject", 786, 60, "500", 54, "405", 6, null, null, null, null, null, null], "isController": false}, {"data": ["CreateParentTask", 533, 54, "500", 54, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["GetAllProjects", 606, 45, "500", 45, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["UpdateUser", 962, 40, "500", 34, "405", 6, null, null, null, null, null, null], "isController": false}, {"data": ["AllUsers", 879, 15, "500", 15, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["GetActiveProjects", 667, 33, "500", 33, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["UnassignedUsers", 918, 14, "500", 14, null, null, null, null, null, null, null, null], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
