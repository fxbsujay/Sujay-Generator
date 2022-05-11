/**
 * <p>Description: 数据源分页查询 API</p>
 * @author sujay
 * @version 15:56 2022/2/24
 */
function isNotBlank( str = '') {
    return str !== undefined && str !== null && str !== '';
}

/**
 * 防反跳。fn函数在最后一次调用时刻的delay毫秒之后执行！
 * @param fn 执行函数
 * @param delay 时间间隔
 * @param isImmediate 为true，debounce会在delay时间间隔的开始时立即调用这个函数
 * @returns {Function}
 */
function debounce(fn, delay, isImmediate) {
    var timer = null;  //初始化timer，作为计时清除依据
    return function() {
        var context = this;  //获取函数所在作用域this
        var args = arguments;  //取得传入参数
        clearTimeout(timer);
        if(isImmediate && timer === null) {
            //时间间隔外立即执行
            fn.apply(context,args);
            timer = 0;
            return;
        }
        timer = setTimeout(function() {
            fn.apply(context,args);
            timer = null;
        }, delay);
    }
}

/**
 * 用途：js中字符串超长作固定长度加省略号（...）处理
 * @param str:需要进行处理的字符串，可含汉字
 * @param len:需要显示多少个汉字，两个英文字母相当于一个汉字
 */
function beautySub(str, len) {
    var reg = /[\u4e00-\u9fa5]/g,
        slice = str.substring(0, len),
        cCharNum = (~~(slice.match(reg) && slice.match(reg).length)),
        realen = slice.length*2 - cCharNum-1;
    return str.substr(0, realen) + (realen < str.length ? "....." : "");
}