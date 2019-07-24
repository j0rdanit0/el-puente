$(document).ready( function() {
   $('#displayInColumnsButton').click( function() {
      $( '#displayCards' ).addClass( 'card-columns' );
      $(this).removeClass( 'btn-outline-secondary' ).addClass( 'btn-secondary' );
      $( '#displayInRowsButton' ).removeClass( 'btn-secondary' ).addClass( 'btn-outline-secondary' );
   });

   $('#displayInRowsButton').click( function() {
      $( '#displayCards' ).removeClass( 'card-columns' );
      $(this).removeClass( 'btn-outline-secondary' ).addClass( 'btn-secondary' );
      $( '#displayInColumnsButton' ).removeClass( 'btn-secondary' ).addClass( 'btn-outline-secondary' );
   });
});