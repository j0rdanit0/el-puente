$(function () {
   $('#goLiveButton').click( function() {
      const $goLiveButton = $(this);
      $goLiveButton.addClass( 'disabled' );
      const $loadingIcon = $goLiveButton.find( '.spinner-border' );
      $loadingIcon.removeClass( 'd-none' );
      $.post( '/changes/goLive', {}, function() {
         $loadingIcon.addClass( 'd-none' );
         $goLiveButton.removeClass( 'disabled' );
      });
   });
});