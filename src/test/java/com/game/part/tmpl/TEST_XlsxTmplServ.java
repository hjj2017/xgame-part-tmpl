package com.game.part.tmpl;

import java.util.List;

import com.game.part.tmpl.type.AbstractXlsxTmpl;
import org.junit.Test;

/**
 * 模版服务测试类
 *
 * @author hjj2017
 * @since 2015/8/19
 *
 */
public class TEST_XlsxTmplServ {
    @Test
    public void mainTest() {
        // 模版类数组
        Class<?>[] tmplClazzArr = {
            BuildingTmpl.class,
        };

        // 设置 Excel 文件路径
        XlsxTmplServ.OBJ._xlsxFileDir = ClassLoader.getSystemResource("./").getPath() + "/xlsx/val";
        XlsxTmplServ.OBJ._multiLangDir = ClassLoader.getSystemResource("./").getPath() + "/xlsx/i18n/en_US";

        for (Class<?> tmplClazz : tmplClazzArr) {
            // 强制转型
            Class<AbstractXlsxTmpl> c = (Class<AbstractXlsxTmpl>)tmplClazz;
            // 加载 Test 模版
            XlsxTmplServ.OBJ.loadTmplData(c);
            XlsxTmplServ.OBJ.packUp(c);
        }

        // 验证所有数据
        XlsxTmplServ.OBJ.validateAll();

        List<BuildingTmpl> tmplObjList = (List<BuildingTmpl>)XlsxTmplServ.OBJ._objListMap.get(BuildingTmpl.class);

        for (BuildingTmpl tmplObj : tmplObjList) {
            System.out.print(tmplObj._name.getOrigStr());
            System.out.print(" = ");
            System.out.print(tmplObj._name.getLangStr());
            System.out.println();
        }
    }
}
