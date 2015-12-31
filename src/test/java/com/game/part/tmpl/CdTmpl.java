package com.game.part.tmpl;

import java.util.HashMap;
import java.util.Map;

import com.game.part.tmpl.anno.FromXlsxFile;
import com.game.part.tmpl.anno.OneToOne;
import com.game.part.tmpl.type.AbstractXlsxTmpl;
import com.game.part.tmpl.type.XlsxBool;
import com.game.part.tmpl.type.XlsxInt;
import com.game.part.tmpl.type.XlsxLong;
import com.game.part.tmpl.type.XlsxShort;
import com.game.part.tmpl.type.XlsxSkip;
import com.game.part.tmpl.type.XlsxStr;

/**
 * 冷却队列配置
 *
 * @author hjj2017
 * @since 2015/12/31
 *
 */
@FromXlsxFile(fileName = "cd.xlsx")
public class CdTmpl extends AbstractXlsxTmpl {
    /// 不可为空约束,
    /// Id 字段一般都使用这种约束
    /** Id, 不能为空值 */
    @OneToOne(groupName = "_cdId")
    public XlsxInt _Id = new XlsxInt(false);

    /// 可以为空约束,
    /// 这是一种最松散的约束,
    /// 也可以认为是没有约束
    /** 冷却队列名称, 可以为空值 */
    public XlsxStr _name;

    /// 自定义约束,
    /// 需要重载 validate 方法
    /**
     * 冷却队列说明,
     * 可以为空值,
     * 但是字符串长度不能超过 100 个字符
     */
    public XlsxStr _desc = new XlsxStr(true) {
        @Override
        public void validate() {
            if (this.getStrVal() != null &&
                this.getStrVal().length() >= 100) {
                // 如果说明文字太长,
                // 则抛出异常!
                throw new XlsxTmplError(this, "Cd 说明太长了");
            }
        }
    };

    /// 枚举约束
    /** 所属类型 Id */
    public XlsxShort _parentTypeId = XlsxShort.createByEnum(false, new short[] { 1, 2, 3 });

    /// 最小值约束
    /**
     * 冷清队列阈值, 单位时间 = 毫秒.
     * 最小值 = 1000 毫秒,
     * 不能比最小值还小
     */
    public XlsxLong _thresholdVal = XlsxLong.createByMin(false, 1000L);

    /// 跳过一个字段
    public XlsxSkip _skip0;

    /// 可以为空约束,
    /** 是否可以秒杀? */
    public XlsxBool _canBeKilled;

    /** Id 字典 */
    @OneToOne(groupName = "_cdId")
    public static final Map<Integer, CdTmpl> _IdMap = new HashMap<>();
}
