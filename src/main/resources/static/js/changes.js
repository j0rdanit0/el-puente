$(function () {
   websocket.init(
      "/changeApprovals",
      function( response ) {
      updateChangeApprovals( JSON.parse( response.body ) );
   }, function() {
      $.get( '/changeApprovals', updateChangeApprovals );
   } );

   if ( getChangeInitials() ) {
      showChanges( getChangeInitials() );
   }
   else {
      enterInitials();
   }
});

function showChanges( initials ) {
   if ( initials && initials.length > 0 ) {
      websocket.connect();
      $( '#initialsContainer' ).hide();
      $( '#changesContainer' ).show();
      $( '#initialsDisplay' ).text( initials );
      saveChangeInitials( initials );
   }
}

function enterInitials() {
   $( '#initials' ).val( '' );
   $( '#changesContainer' ).hide();
   $( '#initialsContainer' ).show();
   websocket.disconnect();
   removeChangeInitials();
}

function updateChangeApprovals( changeApprovals ) {
   let html = "";
   changeApprovals.forEach( function( changeApproval, index ) {
      html += "<div class='boxShadow card border-0'>" +
         "<div class='card-body' style='border-top: solid 3px var(--primary)'>" +
         "<div class='row no-gutters'>";

      html += "<div class='col-md-8 col-lg-9'>" +
         "   <h1 class='text-danger float-left pr-3 m-0'>" +
         "      " + (index + 1) + "." +
         "   </h1>" +
         "   " + changeApproval.changeText +
         "   <hr class='d-md-none'/>" +
         "</div>";

      html += "<div class='col-md-4 col-lg-3 row no-gutters'>";

      const approvals = changeApproval.approvals.join( ", " );
      const numberOfApprovals = changeApproval.approvals.length;

      html += "   <div class='col-6 col-md-12'>" +
         "      <div class='float-none text-center float-md-right text-md-right'>" +
         "         <small class='text-secondary'>" +
         "            Approved by: <strong id='approvedBy-" + index + "'>" + approvals + "</strong>" +
         "         </small>" +
         "         <a class='badge badge-success ml-2' href='' onclick='sendApproval(" + index + "); return false;'>" +
         "            " + numberOfApprovals +
         "            <i class='fas fa-user-check text-white'></i>" +
         "         </a>" +
         "      </div>" +
         "   </div>";

      const denials = changeApproval.denials.join( ", " );
      const numberOfDenials = changeApproval.denials.length;

      html += "   <div class='col-6 col-md-12'>" +
         "      <div class='float-none text-center float-md-right text-md-right'>" +
         "         <small class='text-secondary'>" +
         "            Denied by: <strong id='deniedBy-" + index + "'>" + denials + "</strong>" +
         "         </small>" +
         "         <a class='badge badge-secondary ml-2' href='' onclick='sendDenial(" + index + "); return false;'>" +
         "            " + numberOfDenials +
         "            <i class='fas fa-user-times text-white'></i>" +
         "         </a>" +
         "      </div>" +
         "   </div>";

      html += "</div>";

      if ( changeApproval.denials && changeApproval.denials.length > 0 ) {
         let reasonHtml = "";
         changeApproval.denialReasons.forEach( function( reason, reasonIndex ) {
            reasonHtml += "<li>" +
               "   <small class='bg-secondary text-white px-3 rounded-pill'>" +
               "      <strong>" + changeApproval.denials[reasonIndex] + "</strong> - " + reason +
               "   </small>" +
               "</li>";
         });

         html += "<hr/>" +
            "<ul class='text-secondary' style='list-style-type: none;'>" + reasonHtml + "</ul>";
      }

      html += "</div></div></div>";
   } );

   $('#changesList').html( html );
}

function sendApproval( index ) {
   const initials = getChangeInitials();
   if ( $( '#approvedBy-' + index ).text().includes( initials ) ) {
      websocket.send( "/changeRequest", new ChangeApprovalRequest( true, index, true, initials ) );
   }
   else {
      websocket.send( "/changeRequest", new ChangeApprovalRequest( false, index, true, initials ) );
   }
}

function sendDenial( index ) {
   const initials = getChangeInitials();
   if ( $( '#deniedBy-' + index ).text().includes( initials ) ) {
      websocket.send( "/changeRequest", new ChangeApprovalRequest( true, index, false, initials ) );
   }
   else {
      const denialMessage = prompt("Reason for denying:");
      if ( denialMessage && denialMessage.length > 0 ) {
         websocket.send( "/changeRequest", new ChangeApprovalRequest( false, index, false, initials, denialMessage ) );
      }
   }
}

function ChangeApprovalRequest( undo, index, isApproved, author, denialReason ) {
   return {
      undo: undo,
      changeIndex: index,
      approved: isApproved,
      author: author,
      denialReason: denialReason || "",
   }
}