String.prototype.toArray = function() {
    try {
        var array = JSON.parse(this);
        return Array.isArray(array) ? array : [];
    } catch (error) {
        console.log(`字符串解析为数组出错: ${this}`);
    }

    return [];
};

var hello = function () {
    alert(Utils.types.jpg)
}