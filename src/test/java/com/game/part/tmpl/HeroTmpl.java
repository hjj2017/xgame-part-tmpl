package com.game.part.tmpl;

import java.util.HashMap;
import java.util.Map;

import com.game.part.tmpl.anno.FromXlsxFile;
import com.game.part.tmpl.anno.OneToOne;
import com.game.part.tmpl.type.AbstractXlsxTmpl;
import com.game.part.tmpl.type.XlsxInt;
import com.game.part.tmpl.type.XlsxMultiLang;

/**
 * 英雄配置,
 * 注意: 英雄名称和说明这 2 个字段用的是 XlsxMultiLang 类型!
 * XlsxTmplServ 遇到这个类型的字段时, 会进行翻译...
 * 需要执行 XlsxTmplServ.OBJ._lang = "en_US";
 * 另外, 对于 Excel 的文件放置目录也有特殊要求!
 * 大致如下:
 *
 * <pre>
 * xlsx/
 *  |
 *  +-- i18n/
 *  |    |
 *  |    +-- en_US/
 *  |         |
 *  |         +-- hero.xlsx  <-- 英文翻译文件, 必须和数据文件同名
 *  |
 *  +-- hero.xlsx  <-- 数据文件
 * </pre>
 *
 * 这个 en_US/hero.xlsx 文件是英文译文文件,
 * 只有两个字段: "原文" 和 "译文"
 * 如果没有译文时, XlsxMultiLang 字段会默认使用原文
 *
 * @author hjj2017
 * @since 2016/1/1
 *
 * @see XlsxMultiLang
 * @see XlsxMultiLang#getOrigStr()
 * @see XlsxMultiLang#getLangStr()
 *
 */
@FromXlsxFile(fileName = "hero.xlsx")
public class HeroTmpl extends AbstractXlsxTmpl {
    /** 英雄 Id */
    @OneToOne(groupName = "_heroIdMap")
    public XlsxInt _Id = new XlsxInt(false);

    /**
     * 英雄名称,
     * 注意: 这里使用的是多语言字段类型!
     * 可以使用 _name.getOrigStr() 获取多语言原文;
     * 可以使用 _name.getLangStr() 获取多语言译文;
     */
    public XlsxMultiLang _name;

    /**
     * 英雄说明
     * 注意: 这里使用的是多语言字段类型!
     */
    public XlsxMultiLang _desc;

    /** Id 字典 */
    @OneToOne(groupName = "_heroIdMap")
    public static final Map<Integer, HeroTmpl> _IdMap = new HashMap<>();
}
