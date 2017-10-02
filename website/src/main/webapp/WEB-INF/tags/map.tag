<%@tag body-content="empty" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag import="org.appman.photoblog.page.common.pageattributes.PageAttributes" %>

<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=${pageViewDto.googleMapsApiKey}"></script>
<script type="text/javascript" src="/js/markerclusterer.js"></script>
<div class="iframe-container">
    <div id="map"></div>
</div>

<script type="text/javascript">

    var locations = ${pageViewDto.geoLocationsJsonString};

    function getPhotoHtml(locationDetails) {
        return '<a href="' + locationDetails.pageUrl + '" target="_blank">' +
        '<img src="' + locationDetails.photoUrl + '" class="info-img">' +
        '</a>';
    }

    function getInfoHtml(location){
        result = '<div class="info">' +
                             '<div class="info-body">';
        for( i=0; i< location.locationItems.length; i++){
            result += getPhotoHtml(location.locationItems[i]);
        }
        result += '</div>' +'</div>';
        return result;
    }

    function initialize() {

        var map = new google.maps.Map(document.getElementById('map'), {
          mapTypeId: google.maps.MapTypeId.ROADMAP
        });

        var infowindow = new google.maps.InfoWindow();
        var markers = [];
        var marker, i;
        var bounds = new google.maps.LatLngBounds();

        for (i = 0; i < locations.length; i++) {
          marker = new google.maps.Marker({
            position: new google.maps.LatLng(locations[i].latitude, locations[i].longitude),
            map: map
          });

          google.maps.event.addListener(marker, 'click', (function(marker, i) {
            return function() {
              infowindow.setContent(getInfoHtml(locations[i]));
              infowindow.open(map, marker);
            }
          })(marker, i));
          bounds.extend(marker.position);
          markers.push(marker);
        }
        map.fitBounds(bounds);

        var markerCluster = new MarkerClusterer(map, markers, {
            imagePath: '/static/marker/m'
        });
    }
    google.maps.event.addDomListener(window, 'load', initialize);

</script>