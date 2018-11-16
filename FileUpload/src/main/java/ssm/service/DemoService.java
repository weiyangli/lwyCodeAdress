package ssm.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ssm.bean.Hero;
import ssm.bean.RedisKeys;
import ssm.bean.ResultConstant;
import ssm.bean.School;
import ssm.bean.Skin;
import ssm.bean.Student;
import ssm.bean.UserPrizeNum;
import ssm.bean.Zero;
import ssm.handle.PaChong;
import ssm.mapper.DemoMapper;
import ssm.service.serviceInterface.DemoServiceInt;
import ssm.util.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class DemoService implements DemoServiceInt {
    private static Logger logger = LoggerFactory.getLogger(DemoService.class.getName());

    @Autowired
    private DemoMapper demoMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private EsService esService;

    @Autowired
    private PickNumThread pickNumThread;

    @Autowired
    private IdWorker idWorker;

    public List<Student> toHelloPage() {
        School school = redisService.playJedis();
        esService.esAddData(school, school.getId());
        return demoMapper.toHelloPage();
    }

    public void toHelloPages(Student student) {
        student.setStudentId(idWorker.nextId());
        logger.info("待操作数据Id为--->" + student.getStudentId());
        demoMapper.toHelloPages(student);
    }

    /**
     * @param bookJson
     */
    public void saveHero(String bookJson) {
        // 将json 串转对象
        Hero hero = JSONObject.parseObject(bookJson, Hero.class);
        JSONObject zeroMap = hero.getData();
        //
        if (zeroMap != null) {
            Set<String> strings = zeroMap.keySet();
            strings.forEach(s -> {
                Zero zero = new Zero();
                JSONObject jsonObject = (JSONObject) zeroMap.get(s);
                zero.setId((String) jsonObject.get("id"));
                zero.setImage(JSON.toJSONString(jsonObject.get("image")));
                zero.setKey((String) jsonObject.get("key"));
                zero.setName((String) jsonObject.get("name"));
                zero.setTitle((String) jsonObject.get("title"));
                zero.setTags(JSON.toJSONString(jsonObject.get("tags")));
                // 插入缓存
                Long id = idWorker.nextId();
                redisService.insert(jsonObject, id);
                /*// 插入es
                esService.esAddData(zero,id);*/
                // 插入数据库
                demoMapper.insertZero(zero);
                // 下载英雄的图片和详情js文件
                Utils.downloadHttpUrl("http://ossweb-img.qq.com/images/lol/img/champion/" + zero.getId() + ".png",
                        "C:\\Users\\47477\\Desktop\\lwyCodeAdress\\FileUpload\\src\\main\\webapp\\webpage\\page\\img\\", zero.getId() + ".png");
                // 下载详情js
                Utils.downloadHttpUrl("http://lol.qq.com/biz/hero/" + zero.getId() + ".js",
                        "C:\\Users\\47477\\Desktop\\lwyCodeAdress\\FileUpload\\src\\main\\webapp\\webpage\\page\\js\\", zero.getId() + ".js");
            });
        }
    }

    public List<Zero> findZero() {
        return demoMapper.findZero();
    }

    /*
     * 爬取皮肤页面
     * */
    public void insertSkins() throws Exception {
        // 一共访问18个页面
        // 先清除表数据
        demoMapper.deletesSkin();

        for (int i = 1; i <= 18; i++) {
            Document document = Utils.getDocument("http://lol.52pk.com/skinlist_3851_4_" + i + ".shtml");
            Element element = document.getElementsByClass("listBox").first();
            Elements elements = element.getElementsByTag("dl");
            System.out.println(elements.size());
            for (Element element2 : elements) {
                Skin skin = new Skin();
                String pngUrl = element2.getElementsByTag("dt")
                        .first().getElementsByTag("a").first().getElementsByTag("img").attr("src");
                String name = element2.getElementsByTag("dd").first().getElementsByTag("a").first().text();
                String level = element2.getElementsByTag("dd").select("p").first().getElementsByTag("a").first().text();
                String price = element2.getElementsByTag("dd").select("p").get(1).text().replaceAll(" ", "").split("购买")[0].replace("价格：", "");
                skin.setSkinImg(pngUrl).setSkinLevel(level).setSkinName(name).setSkinPrice(price).setId(idWorker.nextId());
                demoMapper.insertSkin(skin);
            }
        }
    }

    /*
     * 用于测试配置spring aop
     * */
    @Override
    public void insertValue() {
        System.out.println("我注入了 aop");
    }

    /*
     * 导出中奖名单
     * */
    public void genPrizesExcel() {
        List<Zero> zeros = findZero();
        List<PaChong.Prize> prizeList = new ArrayList<>();
        for (Zero zero : zeros) {
            PaChong paChong = new PaChong(zero.getName() + " " + zero.getTitle());
            PaChong.Prize prize = paChong.random();
            if (prize != null) {
                prizeList.add(prize);
            }
        }
        // 生成excel
        try {
            Utils.genExcelData(prizeList);
        } catch (Exception e) {
            System.out.println("奖品列表导出异常----->" + e);
        }
    }

    /*
     * 查询英雄皮肤列表(放到redis)
     *
     * */
    public void findZeroSkins() {
        // 查询皮肤数据
        List<Skin> skins = demoMapper.findZeroSkin();
        // 批量插入缓存
        for (Skin skin : skins) {
            redisService.insertSkin(skin, skin.getId());
        }
    }

    /*
     * 查询缓存上的皮肤随机或取 count 条数据
     * */
    public List<Skin> extractSkin(int count) {
        List<Skin> skins = demoMapper.findZeroSkin();
        count = count > skins.size() ? skins.size() : count;
        // 查询redis
        Collections.shuffle(skins);

        skins = skins.subList(0, count);
        return skins;
    }

    public String dealFile(MultipartFile avatar, String targetPath) throws Exception {
        // 获取文件名
        String originalFilename = avatar.getOriginalFilename();
        // 拼接目标文件名
        targetPath = targetPath + originalFilename;
        // 创建新的文件
        File file = new File(targetPath);
        FileUtils.copyInputStreamToFile(avatar.getInputStream(), file);
        return targetPath;
    }

    /**
     * @param openId
     * @return
     */
    public UserPrizeNum getPickPrizeNum(String openId, Long prizeId) {
        // 1. 检验用户是否已经有了抽奖号码(每一个用户只能有一个号码)
        // 2. 生成用户抽奖号码
        // 3. 将号码存于数据库和缓存
        // 4. 将用户号码返回

        UserPrizeNum userPrizeNum = new UserPrizeNum();
        // [1] 检验用户是否已经有了抽奖号码(每一个用户只能有一个号码)
        String json = redisService.getJsonObject(RedisKeys.PICK_NUM + openId + "_" + prizeId);
        if (StringUtils.isNotBlank(json)) {
            userPrizeNum = JSONObject.parseObject(json, UserPrizeNum.class);
            userPrizeNum.initMe(ResultConstant.USER_PICKNUM_EXTRACTED);
        } else {
            // [2] 生成用户抽奖号码(控制线程避免重复号码)
            pickNumThread.setPrizeId(prizeId);
            new Thread(pickNumThread).start();
            // [3] 将号码存于数据库和缓存
            userPrizeNum.setId(idWorker.nextId());
            userPrizeNum.setPrizeId(prizeId);
            userPrizeNum.setOpenId(openId);
            userPrizeNum.setCreateTime(new Date());
            userPrizeNum.setPrizeNum(pickNumThread.getPickNum());
            // 插入缓存
            redisService.insert(userPrizeNum, RedisKeys.PICK_NUM + openId + "_" + prizeId);
            // 插入数据库
            demoMapper.insetPickNum(userPrizeNum);
            // 返回成功
            userPrizeNum.initMe(ResultConstant.SUCCESS);
        }
        return userPrizeNum;
    }

}
