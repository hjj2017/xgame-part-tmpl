package com.game.part.tmpl;

import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.game.part.tmpl.anno.FromXlsxFile;
import com.game.part.tmpl.type.AbstractXlsxTmpl;
import com.game.part.util.Assert;
import com.game.part.util.Out;
import com.game.part.util.OutInt;
import com.game.part.util.OutStr;

/**
 * Excel 模板服务
 * 
 * @author hjj2017
 * @since 2014/6/6
 * 
 */
public class XlsxTmplServ implements IServ_LoadTmplData, IServ_PackUp, IServ_Validate {
    /** 单例对象 */
    public static final XlsxTmplServ OBJ = new XlsxTmplServ();
    /** Excel 文件所在目录 */
    public String _xlsxFileDir = null;
    /** 多语言资源目录 */
    public String _multiLangDir = null;

    /** 输出类文件到目标目录, 主要用于调试 */
    public String _debugClazzToDir = null;
    /** 对象列表字典 */
    final ConcurrentHashMap<Class<?>, List<?>> _objListMap = new ConcurrentHashMap<>();

    /**
     * 类默认构造器
     *
     */
    private XlsxTmplServ() {
    }

    /**
     * 获取对象列表
     *
     * @param clazz
     * @return
     *
     */
    public <T> List<T> getObjList(Class<T> clazz) {
        // 断言参数不为空
        Assert.notNull(clazz, "clazz");

        // 获取模板列表
        @SuppressWarnings("unchecked")
        List<T> tl = (List<T>)this._objListMap.get(clazz);
        return tl;
    }

    /**
     * 获取 Excel 文件名和页签索引
     *
     * @param byClazz
     * @param outExcelFileName
     * @param outSheetIndex
     * @param outStartFromRowIndex
     * @throws Exception
     *
     */
    void getExcelFileNameAndSheetIndex(
        Class<?> byClazz,
        OutStr outExcelFileName,
        OutInt outSheetIndex,
        OutInt outStartFromRowIndex) throws Exception {
        // 断言参数不为空
        Assert.notNull(byClazz, "byClazz");

        // 获取 Excel 模板注解
        FromXlsxFile annoXlsxTmpl = byClazz.getAnnotation(FromXlsxFile.class);

        if (annoXlsxTmpl == null) {
            // 如果注解对象为空,
            // 则直接退出!
            throw new XlsxTmplError(MessageFormat.format(
                "{0} 类未标注 {1} 注解",
                byClazz.getName(),
                FromXlsxFile.class.getName()
            ));
        }

        // 获取文件名
        String fileName = annoXlsxTmpl.fileName();

        // 获取 Excel 文件名和页签索引
        Out.putVal(outExcelFileName, fileName);
        Out.putVal(outSheetIndex, annoXlsxTmpl.sheetIndex());

        // 从第几行开始读取
        Out.putVal(
            outStartFromRowIndex,
            annoXlsxTmpl.startFromRowIndex()
        );
    }

    /**
     * 获取模板列表
     *
     * @param tmplClazz
     * @param <T>
     * @return
     *
     */
    public<T extends AbstractXlsxTmpl> List<T> getTmplObjList(
        Class<T> tmplClazz) {
        return (List<T>)this._objListMap.get(tmplClazz);
    }
}
