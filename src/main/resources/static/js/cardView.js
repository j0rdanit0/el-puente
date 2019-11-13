$(document).ready( function() {
   $('.displayInColumnsButton').click( function() {
      $(this).addClass( 'text-primary' )
         .closest('.cardViewContainer').each( function() {
         $(this).find('.displayCards').addClass( 'card-columns' );
         $(this).find('.displayInRowsButton').removeClass( 'text-primary' );
         $(this).find('.cardImageContainer').removeClass('col-3').addClass('col-12');
      });
   });

   $('.displayInRowsButton').click( function() {
      $(this).addClass( 'text-primary' )
         .closest('.cardViewContainer').each( function() {
         $(this).find('.displayCards').removeClass( 'card-columns' );
         $(this).find('.displayInColumnsButton').removeClass( 'text-primary' );
         $(this).find('.cardImageContainer').removeClass('col-12').addClass('col-3');
      });
   });
});