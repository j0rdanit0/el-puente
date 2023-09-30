$(document).ready( function() {
   $('.displayInColumnsButton').click( function() {
      $(this).addClass( 'text-primary' ).closest('.cardViewContainer').each( function() {
         $(this).find('.displayCards').addClass( 'card-columns' );
         $(this).find('.displayInRowsButton').removeClass( 'text-primary' );
         $(this).find('.cardImageContainer').removeClass('col-md-4 col-lg-3').addClass('col-12 blendedCardImage');
         $(this).find('.cardInfoContainer').addClass('col-12');
         $(this).find('.cardGiveLivelyContainer').removeClass('col-xl-3 col-lg-4 col-12');
         $(this).find('.blendedCardContainer').addClass('blendedCard');
      });
   });

   $('.displayInRowsButton').click( function() {
      $(this).addClass( 'text-primary' ).closest('.cardViewContainer').each( function() {
         $(this).find('.displayCards').removeClass( 'card-columns' );
         $(this).find('.displayInColumnsButton').removeClass( 'text-primary' );
         $(this).find('.cardImageContainer').addClass('col-md-4 col-lg-3').removeClass('col-12 blendedCardImage');
         $(this).find('.cardInfoContainer').removeClass('col-12');
         $(this).find('.cardGiveLivelyContainer').addClass('col-xl-3 col-lg-4 col-12');
         $(this).find('.blendedCardContainer').removeClass('blendedCard');
      });
   });
});