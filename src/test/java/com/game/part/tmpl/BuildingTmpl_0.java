package com.game.part.tmpl;

import com.game.part.tmpl.anno.FromXlsxFile;
import com.game.part.tmpl.type.AbstractXlsxTmpl;
import com.game.part.tmpl.type.XlsxInt;
import com.game.part.tmpl.type.XlsxMultiLang;
import com.game.part.tmpl.type.XlsxStr;

/**
 * 建筑配置 0
 *
 * @author hjj2017
 * @since 2015/8/19
 *
 */
@FromXlsxFile(fileName = "building_0.xlsx")
public class BuildingTmpl_0 extends AbstractXlsxTmpl {
    /** Id */
    public XlsxInt _Id = new XlsxInt(false);
    /** 名称 */
    public XlsxStr _name;

    /**
     * 功能模板,
     * 因为 FuncTmpl 有 3 个字段,
     * 所以将对应 Excel 表中的 C、D、E 这 3 列
     *
     * @see FuncTmpl
     *
     */
    public FuncTmpl _func;
}
