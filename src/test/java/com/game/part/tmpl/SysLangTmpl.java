package com.game.part.tmpl;

import java.util.HashMap;
import java.util.Map;

import com.game.part.tmpl.anno.FromXlsxFile;
import com.game.part.tmpl.anno.OneToOne;
import com.game.part.tmpl.type.AbstractXlsxTmpl;
import com.game.part.tmpl.type.XlsxInt;
import com.game.part.tmpl.type.XlsxStr;

/**
 * 系统提示语言,
 * XlsxTmplServ 会去加载 i18n/${lang} 目录下的文件.
 * ${lang} 变量由 XlsxTmplServ#_lang 指定
 *
 * @author hjj2017
 * @since 2016/1/1
 * @see XlsxTmplServ#_lang
 *
 */
@FromXlsxFile(fileName = "i18n/${lang}/sysLang.xlsx") // <-- 注意这里, 使用了 ${lang} 变量
public class SysLangTmpl extends AbstractXlsxTmpl {
    /** Id */
    @OneToOne(groupName = "_sysLangId")
    public XlsxInt _Id = new XlsxInt(false);
    /** 内容文本 */
    public XlsxStr _text;

    /** Id 字典 */
    @OneToOne(groupName = "_sysLangId")
    public static final Map<Integer, String> _IdMap = new HashMap<>();
}
