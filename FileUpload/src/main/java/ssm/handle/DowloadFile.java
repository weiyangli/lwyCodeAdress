package ssm.handle;

import ssm.util.Utils;

public class DowloadFile {
    public static void main(String[] args) {
        String path = "http://ossweb-img.qq.com/images/lol/img/champion/Anivia.png";
        String dir = "C:\\Users\\47477\\Desktop\\lwyCodeAdress\\FileUpload\\src\\main\\webapp\\webpage\\page\\img\\";
        Utils.downloadHttpUrl(path, dir, "Anivia.png");
    }
}
