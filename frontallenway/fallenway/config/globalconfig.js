var Config = function(){

	//var backendUrlPrefix = "http://localhost:8080/";
	var backendUrlPrefix = "http://fallenway-allenway:8080/";

	this.getBackendUrlPrefix = function(){
		return backendUrlPrefix;
	}
}

module.exports = Config;
