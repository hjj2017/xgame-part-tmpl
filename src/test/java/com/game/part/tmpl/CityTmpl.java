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
 * 现在要查询 10011 ( 中国 ) 这个国家中所有类型为 2 ( 经济中心 ) 的城市列表,
 * 我们可以这样做:
 *
 * <code>
 * List<CityTmpl> tmplList = CityTmpl._countryAndTypeMap.get(
 *     CityTmpl.getCountryAndTypeInt(10011, 2)
 * );
 * </code>
 *
 * @author hjj2017
 * @since 2016/1/1
 *
 */
@FromXlsxFile(fileName = "city.xlsx", sheetIndex = 1, startFromRowIndex = 2)
public class CityTmpl extends AbstractXlsxTmpl {
    /** 城市 Id */
    @OneToOne(groupName = "_cityId")
    public XlsxInt _Id = new XlsxInt(false);
    /** 名称 */
    public XlsxStr _name;
    /** 所属国家 Id */
    @OneToMany(groupName = "_countryId")
    public XlsxInt _countryId;

    /**
     * 城市类型,
     * 1 = 普通城市, 2 = 经济城市, 4 = 政治中心
     */
    public XlsxInt _typeId = XlsxInt.createByEnum(false, new int[] { 1, 2, 4 });

    /** "国家 + 类型" 整数数值 */
    private int _countryAndTypeInt = -1;

    /** Id 字典*/
    @OneToOne(groupName = "_cityId")
    public static Map<Integer, CityTmpl> _IdMap = new HashMap<>();
    /** 国家字典 */
    @OneToMany(groupName = "_countryId")
    public static Map<Integer, List<CityTmpl>> _countryMap = new HashMap<>();
    /** "国家 + 类型" 字典 */
    @OneToMany(groupName = "_countryAndType")
    public static Map<Integer, List<CityTmpl>> _countryAndTypeMap = new HashMap<>();

    /**
     * 获取 "国家 + 类型" 的整数数值,
     * 注意这里, 这个例子主要要说明的是如何使用 2 个以上的字段组成一个 Key?
     * \@OneToOne 和 \@OneToMoney
     * 这两个注解不仅仅可以用在属性上, 也可以用在方法上!
     *
     * @return
     */
    @OneToMany(groupName = "_countryAndType")
    public int getCountryAndTypeInt() {
        if (this._countryAndTypeInt <= -1) {
            this._countryAndTypeInt = getCountryAndTypeInt(this._countryId, this._typeId);
        }

        return this._countryAndTypeInt;
    }

    /**
     * 获取 "国家 + 类型" 的整数数值
     *
     * @param countryId
     * @param typeId
     * @return
     */
    public static int getCountryAndTypeInt(XlsxInt countryId, XlsxInt typeId) {
        return getCountryAndTypeInt(
            countryId.getIntVal(),
            typeId.getIntVal()
        );
    }

    /**
     * 获取 "国家 + 类型" 的整数数值
     *
     * @param countryId
     * @param typeId
     * @return
     */
    public static int getCountryAndTypeInt(int countryId, int typeId) {
        return countryId * 1000 + typeId;
    }
}
