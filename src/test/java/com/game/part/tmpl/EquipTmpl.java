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
 * 建筑配置
 *
 * @author hjj2017
 * @since 2015/8/19
 *
 */
@FromXlsxFile(fileName = "equip.xlsx")
public class EquipTmpl extends AbstractXlsxTmpl {
    /** 装备 Id */
    @OneToOne(groupName = "_equipId")
    public XlsxInt _Id = new XlsxInt(false);
    /** 名称 */
    public XlsxStr _name;
    /** 说明 */
    public XlsxStr _desc;
    /** 穿戴位置 */
    @OneToMany(groupName = "_wearPos")
    public XlsxInt _wearPos = XlsxInt.createByEnum(false, 1, new int[] { 1, 2, 3, 4 });

    /** Id 字典 */
    @OneToOne(groupName = "_equipId")
    public static Map<Integer, EquipTmpl> _IdMap = new HashMap<>();
    /** 穿戴位置字典 */
    @OneToMany(groupName = "_wearPos")
    public static Map<Integer, List<EquipTmpl>> _wearPosMap = new HashMap<>();
}
