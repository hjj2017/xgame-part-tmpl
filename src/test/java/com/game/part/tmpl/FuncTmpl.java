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

    /**
     * 名称
     * 可以改成 XlsxMultiLang 类型试试 :),
     * 修改之后可以使用:
     * _name.getOrigStr();
     * _name.getLangStr();
     * 这两个方法
     *
     */
    public XlsxStr _name;

    /** 说明 */
    public XlsxStr _desc;
}
