package com.game.part.tmpl;

import java.util.HashMap;
import java.util.Map;

import com.game.part.tmpl.anno.FromXlsxFile;
import com.game.part.tmpl.anno.OneToOne;
import com.game.part.tmpl.anno.Validator;
import com.game.part.tmpl.type.AbstractXlsxTmpl;
import com.game.part.tmpl.type.XlsxInt;

/**
 * 商店物品配置
 *
 * @author hjj2017
 * @since 2016/1/1
 * @see Valid_ShopItem
 *
 */
@FromXlsxFile(fileName = "shopItem.xlsx")
@Validator(clazz = Valid_ShopItem.class) // <-- 注意这里, 指明一个验证器
public class ShopItemTmpl extends AbstractXlsxTmpl {
    /** Id */
    @OneToOne(groupName = "_shopItemId")
    public XlsxInt _Id = new XlsxInt(false);
    /** 出售装备 Id */
    public XlsxInt _equipId = new XlsxInt(false);
    /** 价格 */
    public XlsxInt _price = XlsxInt.createByMin(false, 1000);
    /** 所需角色等级 */
    public XlsxInt _requiredHumanLevel = XlsxInt.createByMin(false, 1);

    /** Id 字典 */
    @OneToOne(groupName = "_shopItemId")
    public static final Map<Integer, ShopItemTmpl> _IdMap = new HashMap<>();
}
