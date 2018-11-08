package ssm.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ssm.bean.Hero;
import ssm.bean.School;
import ssm.bean.Skin;
import ssm.bean.Student;
import ssm.bean.Zero;
import ssm.mapper.DemoMapper;
import ssm.service.serviceInterface.DemoServiceInt;
import ssm.util.Utils;

import java.util.List;
import java.util.Set;

@Service
public class DemoService implements DemoServiceInt {
    @Autowired
    private DemoMapper demoMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private EsService esService;

    @Autowired
    private IdWorker idWorker;

    public List<Student> toHelloPage() {
        School school = redisService.playJedis();
        esService.esAddData(school, school.getId());
        return demoMapper.toHelloPage();
    }

    public void toHelloPages(Student student) {
        if (student.getStudentId() == 0) {
            student.setStudentId(idWorker.nextId());
        }
        demoMapper.toHelloPages();
    }

    /**
     *
     * @param bookJson
     */
    public void saveHero(String bookJson){
        // 将json 串转对象
        Hero hero = JSONObject.parseObject(bookJson, Hero.class);
        JSONObject zeroMap = hero.getData();
        //
        if (zeroMap != null) {
            Set<String> strings = zeroMap.keySet();
            strings.forEach(s -> {
                Zero  zero = new Zero();
                JSONObject jsonObject = (JSONObject)zeroMap.get(s);
                zero.setId((String) jsonObject.get("id"));
                zero.setImage(JSON.toJSONString(jsonObject.get("image")));
                zero.setKey((String) jsonObject.get("key"));
                zero.setName((String) jsonObject.get("name"));
                zero.setTitle((String) jsonObject.get("title"));
                zero.setTags(JSON.toJSONString(jsonObject.get("tags")));
                // 插入缓存
                Long id = idWorker.nextId();
                redisService.insert(jsonObject,id);
                /*// 插入es
                esService.esAddData(zero,id);*/
                // 插入数据库
                demoMapper.insertZero(zero);
                // 下载英雄的图片和详情js文件
                Utils.downloadHttpUrl("http://ossweb-img.qq.com/images/lol/img/champion/"+zero.getId()+".png",
                        "C:\\Users\\47477\\Desktop\\lwyCodeAdress\\FileUpload\\src\\main\\webapp\\webpage\\page\\img\\",zero.getId()+".png");
                // 下载详情js
                Utils.downloadHttpUrl("http://lol.qq.com/biz/hero/"+zero.getId()+".js",
                        "C:\\Users\\47477\\Desktop\\lwyCodeAdress\\FileUpload\\src\\main\\webapp\\webpage\\page\\js\\",zero.getId()+".js");
            });
        }
    }

    public List<Zero> findZero() {
        return demoMapper.findZero();
    }

    /*
    * 爬取皮肤页面
    * */
    public void insertSkins() throws Exception{
        // 一共访问18个页面
        // 先清除表数据
        demoMapper.deletesSkin();

        for (int i=1; i<=18; i++) {
            Document document= Utils.getDocument("http://lol.52pk.com/skinlist_3851_4_"+i+".shtml");
            Element element = document.getElementsByClass("listBox").first();
            Elements elements = element.getElementsByTag("dl");
            System.out.println(elements.size());
            for(Element element2:elements) {
                Skin skin = new Skin();
                String pngUrl = element2.getElementsByTag("dt")
                        .first().getElementsByTag("a").first().getElementsByTag("img").attr("src");
                String name = element2.getElementsByTag("dd").first().getElementsByTag("a").first().text();
                String level = element2.getElementsByTag("dd").select("p").first().getElementsByTag("a").first().text();
                String price = element2.getElementsByTag("dd").select("p").get(1).text().replaceAll(" ","").split("购买")[0].replace("价格：","");
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
}
