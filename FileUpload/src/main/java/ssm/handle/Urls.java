package ssm.handle;

public interface Urls {
    String PAGE_DEMO_REST = "/api/demo/test/{Id}";
    String PAGE_DEMO_CREATE = "/api/demo/test";
    String HERO_LOL_JSON = "/api/lol/hero";
    String HERO_PICK_SKIN = "/api/lol/pick/skin";

    /*
    * 用于微信小程序
    * */
    String UPLOAD_FILE = "/api/upload/file"; // 文件上传
    String IMAGE_PREVIEW = "/api/image/preview"; // 文件上传
    String GET_PICK_PRIZE_NUM = "/api/get/pick_prize/num/{openId}"; // 获取抽奖号码

    /*
    * 网页端奖品
    * */
    String PRIZES_LIST = "/api/prizes";// 奖品列表
    String ADD_PRIZE = "/api/add/prize"; // 添加奖品


    String EXAM_IMPORT = "/api/import/exam";  // 导入线下试卷
}
