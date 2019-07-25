$(document).ready( function() {
   $('#displayInColumnsButton').click( function() {
      $( '#displayCards' ).addClass( 'card-columns' );
      $(this).addClass( 'text-primary' );
      $( '#displayInRowsButton' ).removeClass( 'text-primary' );
   });

   $('#displayInRowsButton').click( function() {
      $( '#displayCards' ).removeClass( 'card-columns' );
      $(this).addClass( 'text-primary' );
      $( '#displayInColumnsButton' ).removeClass( 'text-primary' );
   });
});