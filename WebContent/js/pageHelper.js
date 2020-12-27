
// 11页

/**
 * 生成分页按钮的html代码
 * @param curPage       当前页。理应由前端传给后端，但是前端的传参有可能超出实际范围，这就必须交由
 *                      后端来纠正之后，再查询对应页码的数据。然后后端将纠正后的当前页返回给前端，
 *                      以便前端来渲染页码按钮组。如果不传，由后端返回默认值1
 * @param total         总记录数。实际上，后端可以直接返回总页数就可以了，只不过有一定局限性：假如
 *                      前端还需要显示总记录数，凭借总页数和每页记录数，是无法计算出总记录数的。而返
 *                      回总记录数，前端可以自行计算总页数，同时还可以额外显示总记录数
 * @param count         每页显示的记录数，由前端传给后端。如果不传，使用后端定义的默认值
 * @param sideBtnCount  当前页按钮的左边有多少个按钮，不需要传给后端
 * @param urlParamsStr	点击页码切换页面时，携带的条件参数的字符串，拼接在url后面。由后端定义并传给
 *			前端。后端接口并负责接收，按照自己定义的规则进行解析，拆解参数。
			例子：&name=ysq&age=21。前面的&不能少
 */
function pageHelper(API_URL, curPage, total, count, sideBtnCount, urlParamsStr) {
    // 计算总页数
    let pageCount = Math.ceil(total / count);
	
 	let leftPage, rightPage;

	if (pageCount <= 2 * sideBtnCount + 1) {
		leftPage = 1;
		rightPage = pageCount; 
	} else {
		// 计算按钮组最左端和最右端的页码
	    // 将[1, pageCount]分为3个区间：
	    // [1, sideBtnCount]，[sideBtnCount+1, pageCount-sideBtnCount]，[pageCount-sideBtnCount+1, pageCount]
	   	if (curPage > sideBtnCount && curPage <= pageCount - sideBtnCount) {
	        // [sideBtnCount+1, pageCount-sideBtnCount]
	        leftPage = curPage - sideBtnCount;
	        rightPage = curPage + sideBtnCount;
	
	    } else if (curPage <= sideBtnCount) {
	        // [1, sideBtnCount]
	        leftPage = 1;
	        rightPage = 2 * sideBtnCount + 1;
	        // 越界时，修正当前页
	        if (curPage < 1) {
	            curPage = 1;
	        }
	
	    } else if (curPage > pageCount - sideBtnCount) {
	        // [pageCount-sideBtnCount+1, pageCount]
	        leftPage = pageCount - 2 * sideBtnCount;
	        rightPage = pageCount;
	        // 越界时，修正当前页
	        if (curPage > pageCount) {
	            curPage = pageCount;
	        }
	    }
	}

    return "<div class='pagination'>" +
            firstBtn('First') +
            preBtn('Pre') +
            numBtn(leftPage, rightPage) +
            nextBtn('Next') +
            lastBtn('Last') +
            "</div>";

    /**
     * 返回一个可点击的按钮的html代码
     * @param contentHtml   按钮中的内容
     */
    function clickableBtn(contentHtml, num) {
        //return  `<a href='${API_URL}?page=${num}${urlParamsStr}'>${contentHtml}</a>`;
		return  "<a href='" + API_URL + "?page=" + num + urlParamsStr + "'>" + contentHtml + "</a>";
    }

    /**
     * 返回一个当前页按钮的html代码
     * @param contentHtml
     */
    function currentBtn(contentHtml) {
        //return  `<span>${contentHtml}</span>`;
		return  "<span>" + contentHtml + "</span>";
    }

    /**
     * 返回上一页按钮的html代码
     * @param contentHtml
     */
    function preBtn(contentHtml) {
        if (curPage <= 1) {
            return ''; // 我这里直接返回空，你也可以根据你的喜好，返回禁用点击的按钮
        }
        return clickableBtn(contentHtml, curPage - 1);
    }

    /**
     * 返回下一页按钮的html代码
     * @param contentHtml
     */
    function nextBtn(contentHtml) {
        if (curPage >= pageCount) {
            return '';
        }
        return clickableBtn(contentHtml, curPage + 1);
    }

    /**
     * 返回首页按钮的html代码
     * @param contentHtml
     */
    function firstBtn(contentHtml) {
        if (leftPage <= 1) {
            // 如果首页(1)已经显示在了按钮组(>=leftPage)当中，则不需要首页按钮，这里我直接返回空
            return '';
        }
        return clickableBtn(contentHtml, 1);
    }

    /**
     * 返回末页按钮的html代码
     * @param contentHtml
     */
    function lastBtn(contentHtml) {
        if (pageCount <= rightPage) {
            // 如果末页(pageCount)已经显示在了按钮组(<=rightPage)当中，则不需要首页按钮，这里我直接返回空
            return '';
        }
        return clickableBtn(contentHtml, pageCount);
    }

    /**
     * 生成[left, right]区间的按钮的html代码
     * @param left
     * @param right
     */
    function numBtn(left, right) {
        let btnHtml = '';
        for (let i = left; i <= right; i++) {
            if (i === curPage) {  // 当前页
                btnHtml += currentBtn(i);
            } else {
                btnHtml += clickableBtn(i, i);
            }
        }
        return btnHtml;
    }
}

// 获取指定的路径参数，获取不到返回空串
function getUrlParam(key) {
    // ? 后面的
    let searchStr = window.location.search.substring(1);
    console.log(searchStr);

    let paramMap = new Array();

    let paramEntrys = searchStr.split('&');
    for(let i=0; i<paramEntrys.length; i++) {
        let entry = paramEntrys[i].split('=');
        paramMap[ entry[0] ] = entry[1];
    }

    console.log(paramMap);

    return paramMap[key];
}