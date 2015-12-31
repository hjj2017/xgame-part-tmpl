package com.game.part.tmpl;

import java.text.MessageFormat;
import java.util.List;

import com.game.part.tmpl.type.AbstractXlsxTmpl;
import org.junit.Test;

/**
 * 测试自定义装备
 *
 * @author hjj2017
 * @since 2015/12/31
 *
 */
public class TEST_MyEquip {
    @Test
    public void mainTest() {
        // 先执行初始化
        init();

        // 获取目标对象列表
        List<EquipTmpl> tmplObjList = XlsxTmplServ.OBJ.getTmplObjList(EquipTmpl.class);

        for (EquipTmpl curr : tmplObjList) {
            // 输出日志信息
            System.out.println(tmplObjToStr(curr));
        }

        System.out.println("----");

        // 获取装备模板 11001
        EquipTmpl tmplObj = EquipTmpl._IdMap.get(11001);
        System.out.println(tmplObjToStr(tmplObj));

        System.out.println("----");

        // 获取所有穿戴位置 = 1 的模板列表
        tmplObjList = EquipTmpl._wearPosMap.get(1);

        for (EquipTmpl curr : tmplObjList) {
            // 输出日志信息
            System.out.println(tmplObjToStr(curr));
        }
    }

    /**
     * 初始化
     *
     */
    private static void init() {
        // 模版类数组
        Class<?>[] tmplClazzArr = {
            EquipTmpl.class,
        };

        // 设置 Excel 文件路径
        XlsxTmplServ.OBJ._xlsxFileDir  = getResPath() + "/xlsx";
//      // 设置多语言目录
//      // 这里不需要进行多语言翻译
//      XlsxTmplServ.OBJ._multiLangDir = getResPath() + "/xlsx/i18n/en_US";

        for (Class<?> tmplClazz : tmplClazzArr) {
            // 强制转型
            Class<AbstractXlsxTmpl> c = (Class<AbstractXlsxTmpl>) tmplClazz;
            // 加载 Test 模版
            XlsxTmplServ.OBJ.loadTmplData(c);
            XlsxTmplServ.OBJ.packUp(c);
        }

//      // 验证所有数据
//      // EquipTmpl 类太简单了, 就不需要验证了
//      XlsxTmplServ.OBJ.validateAll();
    }

    /**
     * 获取资源目录
     *
     * @return
     *
     */
    private static String getResPath() {
        return ClassLoader.getSystemResource("./").getPath();
    }

    /**
     * 获取模板对象的字符串
     *
     * @param tmplObj
     * @return
     *
     */
    private static String tmplObjToStr(EquipTmpl tmplObj) {
        if (tmplObj == null) {
            // 如果参数对象为空,
            // 则直接退出!
            return "";
        }

        return MessageFormat.format(
            "Equip Id = {0}, Name = {1}, Desc = {2}, WearPos = {3}",
            tmplObj._Id.getStrVal(), // 注意: 在这里可以直接使用 int 数值的字符串形式
            tmplObj._name.getStrVal(),
            tmplObj._desc.getStrVal(),
            tmplObj._wearPos.getIntVal()
        );
    }
}
