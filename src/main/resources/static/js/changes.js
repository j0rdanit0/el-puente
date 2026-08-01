$(function () {
   $('#goLiveButton').click( function() {
      const $goLiveButton = $(this);
      updateInProgress( $goLiveButton );
      if ( confirm( 'Are you sure you want to Go Live? This cannot be undone.' ) ) {
         $.post( '/changes/goLive', {}, function() {
            updateFinished( $goLiveButton );
            window.location.reload();
         });
      }
      else {
         updateFinished( $goLiveButton );
      }
   });

   $('#declineButton').click( function() {
      const $declineButton = $(this);
      updateInProgress( $declineButton );
      if ( confirm( 'Are you sure you want to decline these changes? This cannot be undone. Approval will be available again after further changes are made.' ) ) {
         $.post( '/changes/decline', {}, function() {
            updateFinished( $declineButton );
            window.location.reload();
         });
      }
      else {
         updateFinished( $declineButton );
      }
   });
});

function updateInProgress( $button ) {
   $button.addClass( 'disabled' );
   $button.find( '.spinner-border' ).removeClass( 'd-none' );
}

function updateFinished( $button ) {
   $button.find( '.spinner-border' ).addClass( 'd-none' );
   $button.removeClass( 'disabled' );
}