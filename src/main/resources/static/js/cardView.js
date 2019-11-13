$(document).ready( function() {
   $('.displayInColumnsButton').click( function() {
      $(this).addClass( 'text-primary' )
         .closest('.cardViewContainer').each( function() {
         $(this).find('.displayCards').addClass( 'card-columns' );
         $(this).find('.displayInRowsButton').removeClass( 'text-primary' );
         $(this).find('.cardImageContainer').removeClass('col-md-4 col-lg-3');
      });
   });

   $('.displayInRowsButton').click( function() {
      $(this).addClass( 'text-primary' )
         .closest('.cardViewContainer').each( function() {
         $(this).find('.displayCards').removeClass( 'card-columns' );
         $(this).find('.displayInColumnsButton').removeClass( 'text-primary' );
         $(this).find('.cardImageContainer').addClass('col-md-4 col-lg-3');
      });
   });
});