	$.ajaxPrefilter(function( options, originalOptions, jqXHR){
		if(YAHOO.util.Cookie.get("token")){
			if(!options.headers){
				options.headers = {};
			}
			if(!options.headers['Authorization']){
				options.headers['Authorization'] = 'AuthToken ' + YAHOO.util.Cookie.get("token");
			}
		}
	});
	$(document).ajaxError(function(event, jqXHR, ajaxSettings, thrownError){
		if(401 === jqXHR.status){
			location.href="index.html";
		}
	});