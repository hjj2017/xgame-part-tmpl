package com.game.part.tmpl.type;

import com.game.part.tmpl.XlsxTmplError;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.game.part.tmpl.XSSFRowReadStream;

import java.text.MessageFormat;

/**
 * Excel 列
 * 
 * @author hjj2017
 * @since 2015/2/21
 * 
 */
public abstract class AbstractXlsxCol {
    /** 所在 Xlsx 文件名称 */
    private String _xlsxFileName = null;
    /** 所在页签名称 */
    private String _sheetName = null;
    /** 行索引 */
    private int _rowIndex = -1;
    /** 列索引 */
    private int _colIndex = -1;

    /**
     * 类默认构造器
     *
     */
    AbstractXlsxCol() {
    }

    /**
     * 获取 Excel 文件名
     *
     * @return
     *
     */
    public String getXlsxFileName() {
        return this._xlsxFileName;
    }

    /**
     * 获取页签名称
     *
     * @return
     *
     */
    public String getSheetName() {
        return this._sheetName;
    }

    /**
     * 获取行索引
     *
     * @return
     *
     */
    public int getRowIndex() {
        return this._rowIndex;
    }

    /**
     * 获取列索引
     *
     * @return
     *
     */
    public int getColIndex() {
        return this._colIndex;
    }

    /**
     * 从 Excel 行数据流中读取数据
     *
     * @param fromStream Excel 行数据流
     *
     */
    public void readFrom(XSSFRowReadStream fromStream) {
        // 断言参数不为空
        assert fromStream != null : "fromStream";

        // 设置 Excel 文件名
        this._xlsxFileName = fromStream.getXlsxFileName();
        // 页签名称
        this._sheetName = fromStream.getSheetName();
        // 设置所在行和列
        this._rowIndex = fromStream.getRowIndex();
        this._colIndex = fromStream.getCurrCellIndex();

        // 调用真实实现
        this.readImpl(fromStream);
        // 读取完成之后进行验证
        this.validate();
    }

    /**
     * 读取行数据, 需要子类进行实现
     *
     * @param fromStream Excel 行数据流
     *
     */
    protected abstract void readImpl(XSSFRowReadStream fromStream);

    /**
     * 验证字段的正确性
     *
     * @return
     *
     */
    protected void validate() {
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    /**
     * 从 Excel 行数据流中读取数据并返回 Xlsx 列对象
     *
     * @param toXlsxColObj
     * @param fromStream
     * @param clazzOfCol
     * @param <TXlsxCol>
     * @return
     *
     */
    public static <TXlsxCol extends AbstractXlsxCol> TXlsxCol readFromAndGet(TXlsxCol toXlsxColObj, XSSFRowReadStream fromStream, Class<TXlsxCol> clazzOfCol) {
        // 断言参数不为空
        assert fromStream != null : "fromStream";

        if (toXlsxColObj == null) {
            // 断言参数不为空
            assert clazzOfCol != null : "clazzOfCol";

            if (clazzOfCol.equals(XlsxSkip.class)) {
                // 如果是 XlsxSkip 类型,
                // 则直接使用单例对象
                toXlsxColObj = (TXlsxCol)XlsxSkip.OBJ;
            } else {
                try {
                    // 如果消息对象为空,
                    // 则直接新建!
                    toXlsxColObj = clazzOfCol.newInstance();
                } catch (Exception ex) {
                    // 包装并抛出异常!
                    throw new XlsxTmplError(ex);
                }
            }
        }

        // 从二进制流中读取数据
        toXlsxColObj.readFrom(fromStream);
        // 返回消息对象
        return toXlsxColObj;
    }
}
