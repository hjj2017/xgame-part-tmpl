package com.game.part.tmpl;

import com.game.part.tmpl.type.AbstractXlsxTmpl;
import com.game.part.tmpl.type.XlsxInt;
import com.game.part.tmpl.type.XlsxStr;

/**
 * 功能模板
 *
 * @author hjj2017
 * @since 2015/12/31
 *
 */
public class FuncTmpl extends AbstractXlsxTmpl {
    /** 功能 Id */
    public XlsxInt _Id;
    /** 名称 */
    public XlsxStr _name;
    /** 说明 */
    public XlsxStr _desc;
}
