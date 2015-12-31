package com.game.part.tmpl;

import java.util.List;
import java.util.Objects;

import com.game.part.tmpl.type.AbstractXlsxTmpl;

public class TEST_XlsxTmplServ {
    // 应用程序入口
    public static void main(String[] argArr) {
        // 设置 Excel 文件的存放目录
        XlsxTmplServ.OBJ._xlsxFileDir = ClassLoader.getSystemResource(".").getPath() + "/xlsx";
        XlsxTmplServ.OBJ._lang = "en_US";

        // 模版类数组
        Class<?>[] tmplClazzArr = {
            CdTmpl.class,
            BuildingTmpl_0.class,
            BuildingTmpl_1.class,
            EquipTmpl.class,
            ShopItemTmpl.class,
            SysLangTmpl.class,
            HeroTmpl.class,
        };

        for (Class<?> tmplClazz : tmplClazzArr) {
            // 强制转型
            Class<AbstractXlsxTmpl> c = (Class<AbstractXlsxTmpl>)tmplClazz;
            // 加载模版类数据并打包
            XlsxTmplServ.OBJ.loadTmplData(c);
            XlsxTmplServ.OBJ.packUp(c);
        }

        // 验证所有模版类
        XlsxTmplServ.OBJ.validateAll();

        // 获取装备模板 11001
        EquipTmpl tmplObj = EquipTmpl._IdMap.get(11001);
        System.out.println(tmplObj._name.getStrVal());

        // 获取所有穿戴位置 = 1 的模板列表
        List<EquipTmpl> tmplObjList = EquipTmpl._wearPosMap.get(1);
        System.out.println(tmplObjList.size());
    }
}
