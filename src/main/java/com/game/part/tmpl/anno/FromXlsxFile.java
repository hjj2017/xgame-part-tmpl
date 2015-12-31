package com.game.part.tmpl.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 从指定的 Excel 文件中读取数据, 基本用法:
 *
 * <code><pre>
 * &#64;FromXlsxFile(fileName = "building.xlsx")
 * public class BuildingTmpl extends AbstractXlsxTmpl { ... }
 * </pre></code>
 * 读取 building.xlsx 文件,
 * 这个文件所在目录是由 XlsxTmplServ.OBJ._xlsxFileDir 指定的!
 *
 * @see com.game.part.tmpl.XlsxTmplServ
 * @see com.game.part.tmpl.XlsxTmplServ#_xlsxFileDir
 * @see #fileName()
 *
 * @author hjj2017
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FromXlsxFile {
    /** Excel 文件名称 */
    String fileName();
    /** 页签索引 */
    int sheetIndex() default 0;
    /** 起始行 */
    int startFromRowIndex() default 1;
}
