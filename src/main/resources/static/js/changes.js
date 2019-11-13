$(function () {
   $('#goLiveButton').click( function() {
      const $goLiveButton = $(this);
      const $loadingIcon = $goLiveButton.find( '.spinner-border' );
      goLiveInProgress( $goLiveButton, $loadingIcon );
      if ( confirm( 'Are you sure you want to Go Live? This cannot be undone.' ) ) {
         $.post( '/changes/goLive', {}, function() {
            goLiveFinished( $goLiveButton, $loadingIcon );
         });
      }
      else {
         goLiveFinished( $goLiveButton, $loadingIcon );
      }
   });
});

function goLiveInProgress( $goLiveButton, $loadingIcon ) {
   $goLiveButton.addClass( 'disabled' );
   $loadingIcon.removeClass( 'd-none' );
}

function goLiveFinished( $goLiveButton, $loadingIcon ) {
   $loadingIcon.addClass( 'd-none' );
   $goLiveButton.removeClass( 'disabled' );
}