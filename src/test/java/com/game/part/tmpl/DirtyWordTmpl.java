package com.game.part.tmpl;

import com.game.part.tmpl.anno.FromXlsxFile;
import com.game.part.tmpl.type.AbstractXlsxTmpl;
import com.game.part.tmpl.type.XlsxStr;

/**
 * 屏蔽字库
 *
 * @author hjj2017
 * @since 2015/12/31
 *
 */
@FromXlsxFile(fileName = "../i18n/${lang}/dirtyWord.xlsx")
public class DirtyWordTmpl extends AbstractXlsxTmpl {
    /** 屏蔽词 */
    public XlsxStr _dirtyWord;
}
