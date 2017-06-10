/**
 * 
 */

if(typeof(innerSearch) != 'function') {
    var innerSearch = function (obj, totalPage, func) {
        var pag = $(obj).closest('div.pagination');
        var si = pag.find('input[name=txtPageSize]');
        var vsi = si.val().replace(/[^\d]*/g, '');
        var ind = pag.find('input[name=txtPageIndex]');
        var vind = ind.val().replace(/[^\d]*/g, '');

        if (vsi && parseInt(vsi) > 0 && vind && parseInt(vind) > 0 && parseInt(vind) <= totalPage) {
            si.val(vsi);
            ind.val(vind);
            if (si.val() == si.data('last-value') && vind == ind.data('last-value')) {
                return;
            } else if (si.val() > si.data('last-value')) {
                vind = 1;
                ind.val(vind);
            }

            si.data('last-value', vsi);
            ind.data('last-value', vind);
        } else {
            si.val(si.data('last-value'));
            ind.val(ind.data('last-value'));
            return;
        }
        eval(func);
    };
}

function getPager(option, func) {
	$.get("resources/js/common/Pagination.html").done(function(data){
		var pageArray = [-2, -1, 0, 1, 2];
		var pager = $(data);
		// 每页大小
		var pageSize = pager.find('input[name=txtPageSize]');
		pageSize.attr('title', '每页 ' + option.pageSize + ' 条记录');
		pageSize.attr('data-last-value', option.pageSize);
		pageSize.attr('value', option.pageSize);
		pager.find('span.pageSize').text('条/共' + option.pageSize + '条');
        
		// 页码
		var pageIndex = pager.find('input[name=txtPageIndex]');
		pageIndex.attr('title', '第 ' + option.pageIndex + ' 页');
		pageIndex.attr('data-last-value', option.pageIndex);
		pageIndex.attr('value', option.pageIndex);
		pager.find('span.totalPages').text('页/共' + option.totalPages + '页');
		
		pager.find('.pagination-confirm input').attr('onclick', "innerSearch(this, option.totalPages, '" + option.pagintionJsFunction + "');");
		
		// 首页
		var first = pager.find('.pagination-button li.first');
		if(option.pageIndex == 1) {
			first.attr('class', 'first disabled');
			first.find('a').attr('onclick', "");
		} else {
			first.attr('class', 'first');
			first.find('a').attr('onclick', "$(this).closest('div.pagination').find('input[name=txtPageIndex]').val($(this).data('value'));" + option.pagintionJsFunction);
		}

		// 上一页
		var prev = pager.find('.pagination-button li.prev');
		if(option.pageIndex == 1) {
			prev.attr('class', 'prev disabled');
			prev.find('a').attr('onclick', "");
		} else {
			prev.attr('class', 'prev');
			prev.find('a').attr('data-value', option.pageIndex - 1);
			prev.find('a').attr('onclick', "$(this).closest('div.pagination').find('input[name=txtPageIndex]').val($(this).data('value'));" + option.pagintionJsFunction);
		}

		// 下一页
		var next = pager.find('.pagination-button li.next');
		if(option.pageIndex == option.totalPages) {
			next.attr('class', 'next disabled');
			prev.find('a').attr('onclick', "");
		} else {
			next.attr('class', 'next');
			next.find('a').attr('data-value', option.pageIndex + 1);
			next.find('a').attr('onclick', "$(this).closest('div.pagination').find('input[name=txtPageIndex]').val($(this).data('value'));" + option.pagintionJsFunction);
		}

		//尾页
		var last = pager.find('.pagination-button li.last');
		if(option.pageIndex == option.totalPages) {
			last.attr('class', 'last disabled');
			prev.find('a').attr('onclick', "");
		} else {
			last.attr('class', 'last');
			last.find('a').attr('data-value', option.totalPages);
			last.find('a').attr('onclick', "$(this).closest('div.pagination').find('input[name=txtPageIndex]').val($(this).data('value'));" + option.pagintionJsFunction);
		}
		
		// 中间页码
		var html = '';
		var li = pager.find('#divLi li');
		for(var i = 0; i < pageArray.length; i++) {
			var p = pageArray[i] + option.pageIndex;
			if(p > 0 && p <= option.totalPages) {
				li.attr('title', '第 ' + p + ' 页');
				var a = li.find('a');
				if(p == option.pageIndex) {
					li.attr('class', 'active');
					a.attr('onclick', "");
				} else {
					li.attr('class', '');
					a.attr('onclick', "$(this).closest('div.pagination').find('input[name=txtPageIndex]').val($(this).data('value'));" + option.pagintionJsFunction);
				}
				a.attr('data-value', p);
				a.text(p);
				
				html += li[0].outerHTML;
			}
		}
		
		pager.find('#divReplace')[0].outerHTML = html;
		
		pager.find('div#divLi').remove();
		
		func(pager[0].outerHTML);
	});
}