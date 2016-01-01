package com.game.part.tmpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.game.part.tmpl.anno.FromXlsxFile;
import com.game.part.tmpl.anno.OneToMany;
import com.game.part.tmpl.anno.OneToOne;
import com.game.part.tmpl.type.AbstractXlsxTmpl;
import com.game.part.tmpl.type.XlsxInt;
import com.game.part.tmpl.type.XlsxStr;

/**
 * 城市配置, 1 个国家里有很多个城市.
 * 现在要查询 "中国" 这个国家中所有类型为 "经济中心" 的城市列表,
 * 我们可以这样做:
 *
 * <code>
 * List<CityTmpl> tmplList = CityTmpl._countryAndTypeMap.get("中国 + 经济中心");
 * </code>
 *
 * @author hjj2017
 * @since 2016/1/1
 *
 */
@FromXlsxFile(fileName = "city.xlsx", sheetIndex = 1, startFromRowIndex = 2)
public class CityTmpl extends AbstractXlsxTmpl {
    /** 城市 Id */
    @OneToOne(groupName = "_Id")
    public XlsxInt _Id = new XlsxInt(false);
    /** 名称 */
    public XlsxStr _name;
    /** 所属国家名称 */
    @OneToMany(groupName = "_country")
    public XlsxStr _countryName;
    /** 城市类型 */
    public XlsxStr _typeName = XlsxStr.createByEnum(false, new String[] { "普通城市", "经济中心", "政治中心" });

    /** "国家 + 类型" 整数数值 */
    private String _countryAndTypeStr = null;

    /** Id 字典*/
    @OneToOne(groupName = "_Id")
    public static Map<Integer, CityTmpl> _IdMap = new HashMap<>();
    /** 国家字典 */
    @OneToMany(groupName = "_country")
    public static Map<String, List<CityTmpl>> _countryMap = new HashMap<>();
    /** "国家 + 类型" 字典 */
    @OneToMany(groupName = "_countryAndType")
    public static Map<String, List<CityTmpl>> _countryAndTypeMap = new HashMap<>();

    /**
     * 获取 "国家 + 类型" 的整数数值,
     * 注意这里, 这个例子主要要说明的是如何使用 2 个以上的字段组成一个 Key?
     * \@OneToOne 和 \@OneToMoney
     * 这两个注解不仅仅可以用在属性上, 也可以用在方法上!
     *
     * @return
     */
    @OneToMany(groupName = "_countryAndType")
    public String getCountryAndTypeInt() {
        if (this._countryAndTypeStr == null) {
            this._countryAndTypeStr = this._countryName.getStrVal() + " + " + this._typeName.getStrVal();
        }

        return this._countryAndTypeStr;
    }
}
