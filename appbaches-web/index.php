<!DOCTYPE html >
  <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <title>AppBaches web client - Reportes en el mapa</title>
    <style>
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
      #map {
        height: 100%;
      }
      /* Optional: Makes the sample page fill the window. */
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
    </style>
  </head>

<html>
  <body>
    <div id="map"></div>

    <script>
      var customLabel = {
        restaurant: {
          label: 'R'
        },
        bar: {
          label: 'B'
        }
      };

        function initMap() {
        var map = new google.maps.Map(document.getElementById('map'), {
          center: new google.maps.LatLng(29.0844527, -110.9646707,17),
          zoom: 12
        });
        var infoWindow = new google.maps.InfoWindow;

          // Change this depending on the name of your PHP or XML file
          downloadUrl('http://localhost/appbaches-web/reportes_new.xml', function(data) {
            var xml = data.responseXML;
            var markers = xml.documentElement.getElementsByTagName('marker');
            Array.prototype.forEach.call(markers, function(markerElem) {
              var id = markerElem.getAttribute('id');
              var name = markerElem.getAttribute('fecha');
              var address = markerElem.getAttribute('estatus');
              var type = markerElem.getAttribute('id_user');
              var point = new google.maps.LatLng(
                  parseFloat(markerElem.getAttribute('latitud')),
                  parseFloat(markerElem.getAttribute('longitud')));

              var infowincontent = document.createElement('div');
              var strong = document.createElement('strong');
              strong.textContent = name
              infowincontent.appendChild(strong);
              infowincontent.appendChild(document.createElement('br'));

              var text = document.createElement('text');
              text.textContent = address
              infowincontent.appendChild(text);
              var icon = customLabel[type] || {};
              
              if(address == 1){
                var marker = new google.maps.Marker({
                  map: map,
                  position: point,
                  label: icon.label,
                  icon: {
                    url: "http://localhost/appbaches-web/media/green-dot.png"
                  }
                });
              }

              if(address == 2){
                var marker = new google.maps.Marker({
                  map: map,
                  position: point,
                  label: icon.label,
                  icon: {
                    url: "http://localhost/appbaches-web/media/yellow-dot.png"
                  }
                });
              }

              if(address == 3){
                var marker = new google.maps.Marker({
                  map: map,
                  position: point,
                  label: icon.label,
                  icon: {
                    url: "http://localhost/appbaches-web/media/red-dot.png"
                  }
                });
              }

              marker.addListener('click', function() {
                infoWindow.setContent(infowincontent);
                infoWindow.open(map, marker);
              });
            });
          });
        }



      function downloadUrl(url, callback) {
        var request = window.ActiveXObject ?
            new ActiveXObject('Microsoft.XMLHTTP') :
            new XMLHttpRequest;

        request.onreadystatechange = function() {
          if (request.readyState == 4) {
            request.onreadystatechange = doNothing;
            callback(request, request.status);
          }
        };

        request.open('GET', url, true);
        request.send(null);
      }

      function doNothing() {}
    </script>
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC98pnjZkySWsG3tj98asFpCTbFiAB8X1g&callback=initMap">
    </script>
  </body>
</html>

