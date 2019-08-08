function mapsSelector( button ) {
   const addressUrlParameter = button.getAttribute( 'addressUrlLine' );
   if /* if we're on iOS, open in Apple Maps */
   ((navigator.platform.indexOf("iPhone") != -1) ||
      (navigator.platform.indexOf("iPod") != -1) ||
      (navigator.platform.indexOf("iPad") != -1)) {
      window.open( "http://maps.apple.com/maps?q=El+Puente&daddr=" + addressUrlParameter );
   }
   else { /* else use Google */
      window.open( "https://www.google.com/maps/dir/?api=1&destination=" + addressUrlParameter );
   }
}