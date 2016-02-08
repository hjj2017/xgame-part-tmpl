package com.game.part.tmpl.type;

import com.game.part.tmpl.XSSFRowReadStream;

/**
 * 读取帮助者
 * 
 * @author hjj2017
 * @since 2015/2/25
 * 
 */
public interface IReadHelper {
    /**
     * 读取行数据
     *
     * @param toXlsxObj
     * @param fromStream
     *
     */
    public void readImpl(AbstractXlsxTmpl toXlsxObj, XSSFRowReadStream fromStream);
}
