const websocket = {
   topicPath: null,
   topicCallback: null,
   onConnect: null,
   reconnectInterval: null,
   stompClient: null,
   timeout: 5,
   currentSecond: 5,
   maxTimeout: 2 * 60,

   init: function( topicPath, topicCallback, onConnect ) {
      this.topicPath = topicPath;
      this.topicCallback = topicCallback;
      this.onConnect = onConnect;
   },

   disconnect: function() {
      if ( this.stompClient ) {
         this.stompClient.disconnect();
      }
   },

   connect: function() {
      const object = this;

      clearInterval(object.reconnectInterval);

      const socket = new SockJS('/elpuente-websocket');

      object.stompClient = Stomp.over(socket);
      object.stompClient.connect({}, function (frame) {
         console.log('Connected: ' + frame);
         object.timeout = 5;
         object.stompClient.subscribe('/topic' + object.topicPath, object.topicCallback );
         if ( object.onConnect && typeof object.onConnect === "function" ) {
            object.onConnect();
         }
         $( '.blockedOnDisconnect' ).unblock();
      }, function() {
         $( '.blockedOnDisconnect' ).block( {
            message: 'Reconnecting in <span id="reconnectCountdown">' + object.currentSecond + '</span>... <a href="" onclick="websocket.connect(); return false;">Try now</a>',
            css: { cursor: 'default', padding: '50px' }
         });

         object.reconnectInterval = setInterval( function() { object.countDown( object ); }, 1000 );
      });
   },

   countDown: function( object ) {
      object.currentSecond--;
      $( '#reconnectCountdown' ).text( object.currentSecond );
      if ( object.currentSecond <= 0 ) {
         object.timeout = Math.ceil( Math.min( object.maxTimeout, object.timeout * 1.5 ) );
         object.currentSecond = object.timeout;
         object.connect();
      }
   },

   send: function( stompPath, request ) {
      const object = this;
      if ( object.stompClient ) {
         object.stompClient.send( "/app" + stompPath, {}, JSON.stringify( request ) )
      }
      else {
         alert( "Hold your horses, the STOMP Client is not connected yet!" );
      }
   }
};