$(document).ready( function() {
   $('.displayInColumnsButton').click( function() {
      $(this).addClass( 'text-primary' )
         .closest('.cardViewContainer').each( function() {
         $(this).find('.displayCards').addClass( 'card-columns' );
         $(this).find('.displayInRowsButton').removeClass( 'text-primary' );
      });
   });

   $('.displayInRowsButton').click( function() {
      $(this).addClass( 'text-primary' )
         .closest('.cardViewContainer').each( function() {
         $(this).find('.displayCards').removeClass( 'card-columns' );
         $(this).find('.displayInColumnsButton').removeClass( 'text-primary' );
      });
   });
});