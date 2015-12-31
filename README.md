#xgame-part-tmpl

在游戏项目中，用于读取 Excel 2007 格式文件，支持简单校验和多语言。该项目是 Xgame 项目的一个子项目！

----

**初始化 XlsxTmplServ**

1、（必选）加载 D 盘 /Temp_Test/xlsx/val 目录下的所有 Excel 文件（2007 格式）；

2、（可选）设置多语言翻译资源目录，多语言翻译资源放置在 D 盘 /Temp_Test/xlsx/i18n/en_US 目录下；

2、（可选）将系统动态生成的 Java 类源码保存到 D 盘 /Temp_Test/debug 目录下；

```
XlsxTmplServ.OBJ._xlsxFileDir = "/D:/Temp_Test/xlsx/val";
// XlsxTmplServ.OBJ._multiLangDir = "/D:/Temp_Test/xlsx/i18n/en_US";
// XlsxTmplServ.OBJ._debugClazzToDir = "/D:/Temp_Test/debug";
```

----

**注册模版类并验证**

1、加载 BuildingTmpl 类、ShopTmpl 类和 SysLangTmpl 类；

2、验证所有模版类；

```
// 模版类数组
Class<?>[] tmplClazzArr = {
    EquipTmpl.class,
    BuildingTmpl.class, 
    ShopTmpl.class,
    SysLangTmpl.class,
};

for (Class<?> tmplClazz : tmplClazzArr) {
    // 强制转型
    Class<AbstractXlsxTmpl> c = (Class<AbstractXlsxTmpl>)tmplClazz;
    
    // 加载模版类数据并打包
    XlsxTmplServ.OBJ.loadTmplData(c);
    XlsxTmplServ.OBJ.packUp(c);
}

// 验证所有模版类
XlsxTmplServ.OBJ.validateAll();
```

----

**EquipTmpl 类**

1、从 equip.xlsx 文件的第 1 个页签开始读数据；

2、\[A 列\] = 装备 Id，不允许为空值；

3、\[B 列\] = 装备名称，可以为空值；

4、\[C 列\] = 装备说明，可以为空值；

5、\[D 列\] = 穿戴位置，不能为空！并且只能是 1、2、3、4 这四个数值中的其中一个！默认值 = 1

6、在类中，定义装备 Id 字典，可以通过装备 Id 数值取得 EquipTmpl 对象；

7、在类中，定义装备穿戴字典，可以通过穿戴位置取得 EquipTmpl 对象列表；

```
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
```
----

**使用 EquipTmpl 类**

0、一定先要确保已经调用过：XlsxTmplServ.OBJ.packUp(...); 这一步；

1、根据装备 Id 获取 EquipTmpl 对象；

2、根据穿戴位置获取 EquipTmpl 对象列表；

```
// 获取装备模板 11001
EquipTmpl tmplObj = EquipTmpl._IdMap.get(11001);
// 获取所有穿戴位置 = 1 的模板列表
tmplObjList = EquipTmpl._wearPosMap.get(1);
```

@see [EquipTmpl.java](./src/test/java/com/game/part/tmpl/EquipTmpl.java)

@see [TEST_MyEquip.java](./src/test/java/com/game/part/tmpl/TEST_MyEquip.java)

@see [equip.xlsx](./src/test/resources/equip.xlsx)