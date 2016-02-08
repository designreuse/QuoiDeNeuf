var encodeHtmlEntity = function(str) {
	return String(str).replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, '&quot;');
};

var getSavedObject = function(nom){
  return JSON.parse(localStorage.getItem(nom));
};

var saveObject = function(key, obj){
  localStorage.setItem( key, JSON.stringify(obj) );
};