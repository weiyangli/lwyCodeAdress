export default class myUtils {
    static isNull(str) {
        return str == null || str.length === 0 || str === '';
    }
    static getStr(str) {
        return str+"进来了"
    }
}