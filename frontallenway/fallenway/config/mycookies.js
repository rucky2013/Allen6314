var MyCookies = function(){

	var cookies = {};

	this.getMyCookies = function(req){
		req.headers.cookie && req.headers.cookie.split(';').forEach(function( cookie ) {
			var parts = cookie.split('='); 
		 	cookies[ parts[ 0 ].trim() ] = ( parts[ 1 ] || '' ).trim(); 
 		});	
		
		return cookies;
	}
}

module.exports = MyCookies;
