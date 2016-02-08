package com.game.part.tmpl.type;

import com.game.part.tmpl.XSSFRowReadStream;

/**
 * 跳过一个列
 *
 * @author hjj2017
 * @since 2015/10/7
 *
 */
public final class XlsxSkip extends AbstractXlsxCol {
    /** 单例对象 */
    public static final XlsxSkip OBJ = new XlsxSkip();

    /**
     * 类默认构造器
     *
     */
    private XlsxSkip() {
    }

    @Override
    protected void readImpl(XSSFRowReadStream fromStream) {
        if (fromStream != null) {
            // 如果参数对象不为空,
            // 则读取一个 Excel 单元格!
            fromStream.readCell();
        }
    }
}
