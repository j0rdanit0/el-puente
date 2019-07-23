$(document).ready( function() {
   if ( $( '.fb-page' ).length > 0 ) {
      let centerInterval = setInterval( function () {
         if ( $( '.fb-page' ).find( 'span:first' ).length > 0 ) {
            $( '.fb-page' ).find( 'span:first' ).addClass( 'mx-auto d-block' );
            clearInterval( centerInterval );
         }
      }, 100 );
   }
});