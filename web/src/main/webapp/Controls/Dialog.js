window.DialogBox = window.DialogBox || {};
window.DialogBox.ids = window.top.DialogBox.ids || [];
window.DialogBox.windows = window.top.DialogBox.windows || [];
window.DialogBox.callBack = window.top.DialogBox.callBack || [];

window.DialogBox.Show = function(option) {
    window.DialogBox.callBack.push(option.onCallback);
    var id = '__Dialog' + window.DialogBox.ids.length;
    window.DialogBox.ids.push(id);
	var buttonList = ['close', 'confirm', 'cancel'];
	
    var box, html;
	var contentHeight = option.height - 100 > 0 ? option.height - 100 : 0;
	if (option.buttonList) buttonList = option.buttonList;

	html = "<div class='__Dialog' id='" + id + "'>" +
"	<div class='__DialogMask'></div>" +
"	<div class='__DialogContent'>" +
"		<div class='__DialogHeader'><div class='__DialogTitle' title='" + option.title + "'>" + option.title + "</div>" +
"			 <div class='__DialogHeaderButton'><i class='__DialogClose' title='关闭对话框'>×</i><i class='__DialogMin hide' title='最小化'>-</i></div></div>" +
"		<div class='__DialogBody'>";
	if (option.html) {
	    html += option.html;
	} else {
	    html += "<iframe class='__DialogIframe' frameborder='0' scrolling='no'></iframe>";
	}
	html += "</div>" +
"		<div class='__DialogFooter'>" +
"			<a href='javascript:void(0)' class='__DialogFooterBtnConfirm' title='确 定'><i class='fa fa-check'></i>确 定</a>" +
"			<a href='javascript:void(0)' class='__DialogFooterBtnClear hide' title='清 空'>清 空</a>" +
"			<a href='javascript:void(0)' class='__DialogFooterBtnCancel hide' title='取 消'><i class='fa fa-times'></i>取 消</a>" +
"		</div>" +
"	</div>" +
"</div>";

	box = $(html);
	if (option.html) {
	} else {
	    box.find("iframe.__DialogIframe").attr("src", option.url);
	    box.find(".__DialogContent > .__DialogBody > iframe").css("height", contentHeight + 'px');
	}
    
	box.find(".__DialogContent").css({
		"width": option.width + 'px',
		"height": option.height + 'px',
		'margin-left': '-' + parseInt(option.width / 2).toString() + 'px',
		'margin-top': '-' + parseInt(option.height / 2) + 'px'
	});
	box.find(".__DialogContent > .__DialogBody").css("height", contentHeight + 'px');
	if ($.inArray('cancel', buttonList) > -1) {
	    box.find('.__DialogFooter .__DialogFooterBtnCancel').removeClass('hide');
	}
	if($.inArray('clear', buttonList) > -1) {
		box.find('.__DialogFooter .__DialogFooterBtnClear').removeClass('hide');
	}
	if($.inArray('min', buttonList) > -1) {
		box.find('.__DialogHeader .__DialogMin').removeClass('hide');
	}

	init(box.find('.__DialogHeader')[0]);
	
	// 关闭事件
	box.find("i.__DialogClose").click(function() {
		if (typeof option.onDialogClose === "function") {
            option.onDialogClose();
        }
		box.fadeOut();
		setTimeout(function() {
			var box = window.top.document.getElementById(id)
			window.top.document.body.removeChild(box);
		}, 300);
	});
    // 取消事件
	box.find(".__DialogFooter .__DialogFooterBtnCancel").click(function() {
		if (typeof option.onDialogCancel === "function") {
            option.onDialogCancel();
        }
		//box.fadeOut();
		//setTimeout(function() {
		//	var box = window.top.document.getElementById(id)
		//	window.top.document.body.removeChild(box);
	    //}, 300);
		window.DialogBox.Close({ status: true, data: {}, handler: this });
	});
	// 确定事件
	box.find(".__DialogFooter .__DialogFooterBtnConfirm").click(function() {
		var data, func = window.DialogBox.windows[window.DialogBox.windows.length - 1].onDialogOK;
		if(!option.type) {
			if (typeof func === "function") {
				data = func();
				if (typeof data != 'object' || data.status !== true) { return; }
			}
		}
        window.DialogBox.Close({ status: true, data: data ? data.value : {}, handler: this });
	});
	// 清空事件
	box.find(".__DialogFooter .__DialogFooterBtnClear").click(function() {
		if (typeof option.onDialogClear === "function") {
            option.onDialogClear();
        }
		//box.fadeOut();
		//setTimeout(function() {
		//	var box = window.top.document.getElementById(id)
		//	window.top.document.body.removeChild(box);
		//}, 300);
		window.DialogBox.Close({ status: true, data: {}, handler: this });
	});
	// 最小化事件
	box.find(".__DialogFooter .__DialogFooterBtnCancel").click(function() {
		
	});

    window.top.$("body").append(box);
    if (option.url) {
        window.DialogBox.windows.push(window.top.$("#" + id + " .__DialogBody > iframe")[0].contentWindow);
    } else {
        window.DialogBox.windows.push(window);
    }
    window.top.$('#' + window.DialogBox.ids[window.DialogBox.ids.length - 1]).fadeIn();
}

window.DialogBox._ShowMask = function (option) {
    window.DialogBox.callBack.push(option.onCallback);
    var id = '__Dialog' + window.DialogBox.ids.length;
    window.DialogBox.ids.push(id);
    var buttonList = ['close', 'confirm', 'cancel'];

    var box, html;
    var contentHeight = option.height - 100 > 0 ? option.height - 100 : 0;
    if (option.buttonList) buttonList = option.buttonList;

    html = "<div class='__Dialog' id='" + id + "'>" +
"	<div class='__DialogMask'></div>" +
"	<div class='__DialogContent'>" + option.html +
"	</div>" +
"</div>";

    box = $(html);

    box.find(".__DialogContent").css({
        "width": option.width + 'px',
        "height": option.height + 'px',
        'margin-left': '-' + parseInt(option.width / 2).toString() + 'px',
        'margin-top': '-' + parseInt(option.height / 2) + 'px'
    });

    window.top.$("body").append(box);
    window.DialogBox.windows.push(window);
    window.top.$('#' + window.DialogBox.ids[window.DialogBox.ids.length - 1]).fadeIn();
}

window.DialogBox.Close = function(result) {
    var func = window.top.DialogBox.callBack.pop();
    window.top.DialogBox.windows.pop();
    if (typeof func == 'function' && result.status == true) {
        func(result.data);
    }
    var id = $(result.handler).closest('.__Dialog').attr('id');
    window.top.$('#' + id).fadeOut();
    window.DialogBox.ids.splice($.inArray(id, window.DialogBox.ids), 1);
    
    //id = window.DialogBox.ids.pop();
    setTimeout(function() {
        var box = window.top.document.getElementById(id);
        window.top.document.body.removeChild(box);
    }, 300);
}

window.DialogBox.Alert = function(message, callBack) {
	message = message || '';
	window.DialogBox.Show({
		title: '提示信息',
		html: '<p class="__DialogMessage">' + message + '</p>',
		width:320, 
		height:160,
		type: 1, // 1 alert, 2 confirm, 3 遮罩
		onCallback: callBack,
		buttonList:['confirm']
	});
}

window.DialogBox.Confirm = function(message, confirmCallback, cancelCalback) {
	message = message || '';
	window.DialogBox.Show({
		title: '确认信息',
		html: '<p class="__DialogMessage">' + message + '</p>',
		width:320, 
		height:160,
		type: 2,
		onCallback:function(){ if(typeof confirmCallback == 'function') confirmCallback(); },
		onDialogCancel:function(){ if(typeof cancelCalback == 'function') cancelCalback(); },
		buttonList:['close', 'confirm', 'cancel']
	});
}

window.DialogBox.ShowMask = function () {
    window.DialogBox._ShowMask({
        title: '提示信息',
        html: '<div style="background:rgba(255,255,255,0.5);border-radius:100%;"><img src="/Image/System/loading.gif" /></div>',
        width: 32,
        height: 32,
        type: 3, // 1 alert, 2 confirm, 3 遮罩
        onCallback: function () { },
        buttonList: ['confirm']
    });
}

// 拖动效果
var maskL = 0, maskT = 0, maskX = 0, maskY = 0;
var maskIsOver = false;
var maskZIndex = 10001;
function init(elem) {
	var parentDom = elem.parentNode;
	elem.onmousedown = function(event) {
		var e = event || window.event;
		maskX = e.clientX;
		maskY = e.clientY;
		
		var windowWidth = window.top.document.body.clientWidth + 15;
		var windowHeight = window.top.document.documentElement.clientHeight;
		var contentWidth = parentDom.offsetWidth;
		var contentHeight = parentDom.offsetHeight;
		
		maskL = parseInt(parentDom.offsetLeft);
		maskT = parseInt(parentDom.offsetTop);
		
		maskIsOver = true;
		maskZIndex++;
		parentDom.style.zIndex = maskZIndex;
		window.top.document.onmousemove = function(event) {
		    if (maskIsOver) {
				var e = event || window.event;
				var newLeft = maskL + e.clientX - maskX;
				var newTop = maskT + e.clientY - maskY;
				newLeft = newLeft < 0 ? 0 : newLeft;
				newTop = newTop < 0 ? 0 : newTop;
				
				newLeft = newLeft > windowWidth - contentWidth ? windowWidth - contentWidth : newLeft;
				newTop = newTop > windowHeight - elem.clientHeight ? windowHeight - elem.clientHeight : newTop;
				parentDom.style.left = newLeft + 'px';
				parentDom.style.top = newTop + 'px';
				parentDom.style.marginLeft = '0px';
				parentDom.style.marginTop = '0px';
			}   
		}
		window.top.document.onmouseup = function() {
		    maskIsOver = false;
		}
	}
}