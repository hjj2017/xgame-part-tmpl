package com.game.part.tmpl;

import java.text.MessageFormat;
import java.util.List;

/**
 * 商店物品验证,
 * 注意: 在所有的模板类都加载完成之后才执行该验证类
 *
 * @author hjj2017
 * @since 2016/1/1
 *
 */
public class Valid_ShopItem implements IXlsxValidator<ShopItemTmpl> {
    @Override
    public void validate(List<ShopItemTmpl> objList) {
        if (objList == null ||
            objList.isEmpty()) {
            return;
        }

        objList.forEach(obj -> {
            // 获取装备配置
            EquipTmpl equipTmpl = EquipTmpl._IdMap.get(obj._equipId.getIntVal());

            if (equipTmpl == null) {
                // 如果装备配置不存在,
                // 则直接抛出异常
                throw new XlsxTmplError(obj._equipId,
                    MessageFormat.format("未找到 Id = {0} 的装备", obj._equipId.getStrVal()
                ));
            }
        });
    }
}
