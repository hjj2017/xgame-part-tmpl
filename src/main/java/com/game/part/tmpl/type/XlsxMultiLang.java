package com.game.part.tmpl.type;

import com.game.part.tmpl.XSSFRowReadStream;

/**
 * 多语言类型字段
 *
 * @author hjj2017
 * @since 2015/12/30
 *
 */
public class XlsxMultiLang extends AbstractXlsxCol {
    /** 是否可以为空值 */
    private boolean _nullable = true;
    /** 原文 */
    private String _origStr = null;
    /** 多语言字符串 */
    private String _langStr = null;

    /**
     * 类默认构造器
     *
     */
    public XlsxMultiLang() {
        this(true);
    }

    /**
     * 类参数构造器
     *
     * @param nullable 是否可以为空
     *
     */
    public XlsxMultiLang(boolean nullable) {
        this._nullable = nullable;
        this._origStr = null;
        this._langStr = null;
    }

    @Override
    protected void readImpl(XSSFRowReadStream stream) {
        if (stream != null) {
            this._origStr = stream.readStr();
        }
    }
}
