package com.game.part.tmpl;

import com.game.part.tmpl.anno.ElementNum;
import com.game.part.tmpl.anno.FromXlsxFile;
import com.game.part.tmpl.type.AbstractXlsxTmpl;
import com.game.part.tmpl.type.XlsxArrayList;
import com.game.part.tmpl.type.XlsxInt;
import com.game.part.tmpl.type.XlsxStr;

/**
 * 建筑配置 1
 *
 * @author hjj2017
 * @since 2015/12/31
 *
 */
@FromXlsxFile(fileName = "building_1.xlsx")
public class BuildingTmpl_1 extends AbstractXlsxTmpl {
    /** Id */
    public XlsxInt _Id = new XlsxInt(false);
    /** 名称 */
    public XlsxStr _name;

    /**
     * 功能模板数组,
     * 因为 FuncTmpl 有 3 个字段,
     * 且数组长度 = 3,
     * 所以将对应 Excel 表中的 C、D、E、F、G、H、I、J、K 这 9 列
     *
     * 注意: 建筑的第 1 个功能绝对不能为空,
     * 也就是说 C 列绝对不能为空!
     * D 到 K 这几列可以为空...
     *
     * 这样就可以保证建筑上至少有 1 个功能
     *
     * @see FuncTmpl
     *
     */
    @ElementNum(3)
    public XlsxArrayList<FuncTmpl> _func = new XlsxArrayList<>(
        new FuncTmpl() {
            @Override
            protected void validate() {
                if (this._Id == null ||
                    this._Id.getObjVal() == null ||
                    this._Id.getIntVal() <= 0) {
                    throw new XlsxTmplError(this, "建筑的第 1 个功能为空");
                }
            }
        }
    );
}
