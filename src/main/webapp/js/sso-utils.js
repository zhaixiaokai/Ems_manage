(function() {
	var ie = !!(window.attachEvent && !window.opera);
	var wk = /webkit\/(\d+)/i.test(navigator.userAgent) && (RegExp.$1 < 525);
	var fn = [];
	var run = function() {
		for ( var i = 0; i < fn.length; i++)
			fn[i]();
	};
	var d = document;
	d.domLoadCallback = false;
	d.ready = function(f) {
		if (!ie && !wk && d.addEventListener)
			return d.addEventListener('DOMContentLoaded', f, false);
		if (fn.push(f) > 1)
			return;
		if (ie)
			(function() {
				try {
					d.documentElement.doScroll('left');
					run();
				} catch (err) {
					if( d.domLoadCallback ) {
						d.domLoadCallback = null;
						delete d.domLoadCallback;
						return;
					}
					setTimeout(arguments.callee, 0);
				}
			})();
		else if (wk)
			var t = setInterval(function() {
				if (/^(loaded|complete)$/.test(d.readyState))
					clearInterval(t), run();
			}, 0);
	};
})();
document.ready(function() {
	document.domLoadCallback = true;
	var iframe;
	try {
		iframe = document.createElement('iframe');
	} catch (ex) {
		iframe = document.createElement('iframe');
	}
	iframe.src = '//sso.apps.sohuno.com/crossdomains';
	iframe.id = 'sso-domain';
	iframe.name = 'sso-domain';
	iframe.width = 0;
	iframe.height = 0;
	iframe.marginHeight = 0;
	iframe.marginWidth = 0;

	document.getElementsByTagName('body')[0].appendChild(iframe);
});
