/* global OpenLayers */

function init_map(){
	map = new OpenLayers.Map("myMap");
    map.addLayer(new OpenLayers.Layer.OSM());

    var lonLat = new OpenLayers.LonLat( 23.727539 ,37.983810 )
          .transform(
            new OpenLayers.Projection("EPSG:4326"), // transform from WGS 1984
            map.getProjectionObject() // to Spherical Mercator Projection
          );
          
    var zoom=5;

     var markers = new OpenLayers.Layer.Markers( "Markers" );
    map.addLayer(markers);
    
    markers.addMarker(new OpenLayers.Marker(lonLat));

    map.setCenter (lonLat, zoom);

	 document.getElementById("Users_address").addEventListener('change',function() {
	 	confirmAddress(map,markers);
	 });

    document.getElementById("Users_city").addEventListener('change', function() {
    	confirmAddress(map,markers);
	});

    document.getElementById("Users_country").addEventListener('change',function() {
    	confirmAddress(map,markers);
	});

    document.getElementById("currentLocationButton").addEventListener('click',function() {
        currentLocation(map,markers);
    });

    document.getElementById("Users_address").addEventListener('input', hideElements);
    document.getElementById("Users_city").addEventListener('input', hideElements);
    document.getElementById("Users_country").addEventListener('input', hideElements);
}


function showMap(){
	document.getElementById('myMap').style.visibility="visible";
}

function hideElements(){
	document.getElementById('myMap').style.visibility="hidden";
	document.getElementById('buttonMap').style.visibility="hidden";
}

function confirmAddress(temp_map,temp_markers){

        var address_confirmation=document.getElementById("address_confirmation");

        var u_country=document.getElementById("Users_country");
        var u_city=document.getElementById("Users_city");
        var u_address=document.getElementById("Users_address");
        var myReq=u_address.value+" "+u_city.value+" "+u_country.value;

        var xhr=new XMLHttpRequest();
        var url="https://nominatim.openstreetmap.org/search/"+encodeURIComponent(myReq)+"?format=json&polygon=0&addressdetails=1";

        xhr.open("GET",url,true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.onreadystatechange=function(){
            if(xhr.readyState===4 && xhr.status===200){
                var resp=JSON.parse(xhr.responseText);
                if(resp===""){
                    address_confirmation.style.color="red";
                    address_confirmation.style.fontSize="medium";
                    address_confirmation.innerHTML="Address doesnt exist";
                    hideElements();
                }else{
                    address_confirmation.style.color="white";
                    address_confirmation.innerHTML="";

                    var lonlat=new OpenLayers.LonLat(resp[0].lon,resp[0].lat).transform(new OpenLayers.Projection("EPSG:4326"),map.getProjectionObject());
                    temp_markers.clearMarkers();
                    temp_markers.addMarker(new OpenLayers.Marker(lonlat));

                    var zoom=17;
                    if(u_address.value==="" && u_city.value===""){
                        zoom=5;
                    }else if(u_address.value==="" && u_city!==""){
                        zoom=10;
                    }
                    map.setCenter(lonlat,zoom);

                    document.getElementById("buttonMap").style.visibility="visible";
                }
            }
        };
        xhr.send();

      }

      function currentLocation(temp_map,temp_markers){
        if(navigator.geolocation!==0){
            navigator.geolocation.getCurrentPosition(function(position){
                showPosition(position,temp_map,temp_markers);
            });
        }else{
            window.alert("Browser does not support geolocation feature!");
            document.getElementById("currentLocationButton").visibility="hidden";
        }
        }

        function showPosition(position,temp_map,temp_markers){
            var address_confirmation=document.getElementById("address_confirmation");

        var xhr=new XMLHttpRequest();
        var url="https://nominatim.openstreetmap.org/reverse?format=json&lat="+position.coords.latitude+"&lon="+position.coords.longitude;

        xhr.open("GET",url,true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.onreadystatechange=function(){
            if(xhr.readyState===4 && xhr.status===200){
                var resp=JSON.parse(xhr.responseText);
                if(resp===""){
                    address_confirmation.style.color="red";
                    address_confirmation.style.fontSize="medium";
                    address_confirmation.innerHTML="Current position doesnt exist";
                    hideElements();
                }else{
                    address_confirmation.style.color="white";
                    address_confirmation.innerHTML="";

                    var lonlat=new OpenLayers.LonLat(position.coords.longitude,position.coords.latitude).transform(new OpenLayers.Projection("EPSG:4326"),map.getProjectionObject());
                    var zoom=15;
                    temp_markers.clearMarkers();
                    temp_markers.addMarker(new OpenLayers.Marker(lonlat));

                    temp_map.setCenter(lonlat,zoom);

                    showMap();

                    document.getElementById("Users_country").value=resp.address.country;
                    document.getElementById("Users_city").value=resp.address.state;
                    if(resp.address.road===undefined){
                        window.alert("GPS cannot find the exact address");
                    }else{
                        document.getElementById("Users_address").value=resp.address.road;
                    }  
                } 
            }
        };
        xhr.send();    

        }
