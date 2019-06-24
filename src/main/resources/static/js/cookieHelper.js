$( function () {
   Cookies.defaults = { expires: 365, path: '/' };
});

const CookieNames = {
   changeInitials: 'elpuente_changeInitials',
};

function getChangeInitials() {
   return Cookies.get( CookieNames.changeInitials );
}

function saveChangeInitials( changeInitials ) {
   Cookies.set( CookieNames.changeInitials, changeInitials );
}

function removeChangeInitials() {
   Cookies.set( CookieNames.changeInitials, '' );
}