window.onscroll = function() {scrollFunction()};

function scrollFunction() {
   if (document.body.scrollTop > 25 || document.documentElement.scrollTop > 25) {
      document.getElementById("navLogo").classList.add( 'smallNavLogo' );
   } else {
      document.getElementById("navLogo").classList.remove( 'smallNavLogo' );
   }
}