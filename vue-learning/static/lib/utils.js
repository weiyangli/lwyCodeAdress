/**
 * 关于 vue 中对于 js 文件的引用，其实非常的简单一直不愿意用心看，
 * 若果 js 文件使用 export default 定义方法块，该方法块中的方法可以直接在其他 js 页面中 使用 import 导入 文件就可以使用了，
 * export default 是 es6 版本引入的语法。 而在这之前声明js类 都需要在 要使用该类的html页面中使用
 * <script src="../../../static/lib/url.js"></script> 这样的语法导入。
 * String.prototype.format 这样的声明方式是将当期类中的方法进行扩展，就是在源码的基础上写自己使用的方法，
 * 和java中声明java bean 自己定义getset类似。  简单的一批这么久才整明白。
 * 批评自己: 每次学新的东西都学个大概就觉得差不多了，或者就觉得够了，其实别人都学的比你深。
 */
function Utils() {

}
Utils.getTimeStr = function (time) {
    var self = app,
    dateT = new Date(time),
    str;
    var hours = dateT.getHours() < 10 ? '0' + dateT.getHours() : dateT.getHours();
    var mins = dateT.getMinutes() < 10 ? '0' + dateT.getMinutes() : dateT.getMinutes();
    str = hours + ':' + mins;
    return str;
}

Utils.getStr = function(str) {
    return str+"进来了!!!!!@!"
}


