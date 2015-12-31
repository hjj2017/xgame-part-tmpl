package com.game.part.tmpl;

import com.game.part.tmpl.anno.FromXlsxFile;
import com.game.part.tmpl.type.AbstractXlsxTmpl;
import com.game.part.tmpl.type.XlsxInt;
import com.game.part.tmpl.type.XlsxMultiLang;

/**
 * 建筑配置
 *
 * @author hjj2017
 * @since 2015/8/19
 *
 */
@FromXlsxFile(fileName = "building.xlsx")
public class BuildingTmpl extends AbstractXlsxTmpl {
    /** Id */
    public XlsxInt _Id = new XlsxInt(false);
    /** 名称 */
    public XlsxMultiLang _name = new XlsxMultiLang();
    /** 说明 */
    public XlsxMultiLang _desc = new XlsxMultiLang();
}
