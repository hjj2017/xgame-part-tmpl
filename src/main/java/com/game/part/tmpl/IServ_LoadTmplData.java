package com.game.part.tmpl;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.game.part.tmpl.anno.FromXlsxFile;
import com.game.part.tmpl.type.AbstractXlsxCol;
import com.game.part.util.Assert;
import com.game.part.util.Out;
import com.game.part.util.OutInt;
import com.game.part.util.OutStr;

/**
 * 加载模板数据
 * 
 * @author hjj2017
 * @since 2015/2/25
 * 
 */
interface IServ_LoadTmplData {
	/**
	 * 加载模板数据
	 * 
	 * @param byClazz
	 * 
	 */
	default void loadTmplData(Class<? extends AbstractXlsxCol> byClazz) {
		// 加载数据前, 先验证类
		ClazzDefValidator.validate(byClazz);

		// 输出参数 : Excel 文件名称, 页签索引
		OutStr outExcelFileName = new OutStr();
		OutInt outSheetIndex = new OutInt();
		// 从第几行开始读取
		OutInt outStartFromRowIndex= new OutInt();

		try {
			// 获取 Excel 文件名和页签索引
			getExcelFileNameAndSheetIndex(
				byClazz, outExcelFileName, outSheetIndex, outStartFromRowIndex
			);
			// 获取工作表单
			XSSFSheet sheet = getWorkSheet(
				outExcelFileName.getVal(), 
				outSheetIndex.getVal()
			);

			// 执行加载逻辑
			List<?> objList = makeObjList(
				byClazz, sheet, outStartFromRowIndex.getVal(), 
				outExcelFileName.getVal()
			);

			// 添加到字典列表
			XlsxTmplServ.OBJ._objListMap.put(byClazz, objList);
		} catch (XlsxTmplError err) {
			// 抛出模板错误
			XlsxTmplLog.LOG.error(null, err);
			throw err;
		} catch (Exception ex) {
			// 抛出运行时异常
			XlsxTmplLog.LOG.error(ex.getMessage(), ex);
			throw new XlsxTmplError(ex);
		}
	}

	/**
	 * 获取 Excel 文件名和页签索引
	 * 
	 * @param byClazz
	 * @param outExcelFileName
	 * @param outSheetIndex
	 * @param outStartFromRowIndex 
	 * @throws Exception 
	 * 
	 */
	static void getExcelFileNameAndSheetIndex(
		Class<?> byClazz, 
		OutStr outExcelFileName, 
		OutInt outSheetIndex, 
		OutInt outStartFromRowIndex) throws Exception {
		// 断言参数不为空
		Assert.notNull(byClazz, "byClazz");

		// 获取 Excel 模板注解
		FromXlsxFile annoXlsxTmpl = byClazz.getAnnotation(FromXlsxFile.class);

		if (annoXlsxTmpl == null) {
			// 如果注解对象为空, 
			// 则直接退出!
			throw new XlsxTmplError(MessageFormat.format(
				"{0} 类未标注 {1} 注解", 
				byClazz.getName(), 
				FromXlsxFile.class.getName()
			));
		}

		// 获取文件名
		String fileName = annoXlsxTmpl.fileName();
		// 获取属性字典
		Map<String, String> propMap = XlsxTmplServ.OBJ._propMap;

		if (propMap != null &&
			propMap.isEmpty() == false) {
			// 如果属性字典不为空,
			// 则尝试替换文件名中的变量!
			// 注意: 变量必须是以 '$' 字符开头的
			for (Map.Entry<String, String> entry : propMap.entrySet()) {
				// 替换 $var 字符串
				fileName = fileName.replace(
					"$" + entry.getKey(),
					entry.getValue()
				);

				// 替换 ${var} 字符串
				fileName = fileName.replace(
					"${" + entry.getKey() + "}",
					entry.getValue()
				);
			}
		}

		// 获取 Excel 文件名和页签索引
		Out.putVal(outExcelFileName, fileName);
		Out.putVal(outSheetIndex, annoXlsxTmpl.sheetIndex());

		// 从第几行开始读取
		Out.putVal(
			outStartFromRowIndex, 
			annoXlsxTmpl.startFromRowIndex()
		);
	}

	/**
	 * 获取页签
	 * 
	 * @param excelFileName 
	 * @param sheetIndex 
	 * @return 
	 * @throws Exception 
	 * 
	 */
	static XSSFSheet getWorkSheet(String excelFileName, int sheetIndex) throws Exception {
		// 断言文件名
		Assert.notNullOrEmpty(
			excelFileName, "excelFileName"
		);
		// 断言页签索引
		Assert.isTrue(
			sheetIndex >= 0, "sheetIndex"
		);

		// 获取绝对文件名
		final String absFileName = XlsxTmplServ.OBJ._xlsxFileDir + "/" + excelFileName;
		// 记录日志信息
		XlsxTmplLog.LOG.info("打开文件 " + absFileName);

		// 创建输入流
		InputStream is = new BufferedInputStream(new FileInputStream(absFileName));
		// 创建工作簿
		XSSFWorkbook wb = new XSSFWorkbook(is);
		// 获取并页签
		return wb.getSheetAt(sheetIndex);
	}

	/**
	 * 构建对象列表
	 * 
	 * @param byClazz
	 * @param fromSheet
	 * @param startFromRowIndex 
	 * @param xlsxFileName 
	 * @return 
	 * @throws Exception 
	 * 
	 */
	static<T extends AbstractXlsxCol> List<T> makeObjList(
		Class<T> byClazz, 
		XSSFSheet fromSheet, 
		int startFromRowIndex, 
		String xlsxFileName) throws Exception {
		// 断言参数不为空
		Assert.notNull(byClazz, "byClazz");
		Assert.notNull(fromSheet, "fromSheet");
		Assert.notNull(startFromRowIndex, "startRowIndex");

		// 获取最后行数
		final int rowCount = fromSheet.getLastRowNum();

		if (rowCount <= 0) {
			// 如果行数为 0, 
			// 则直接退出!
			XlsxTmplLog.LOG.error(MessageFormat.format(
				"{0} 页签为空数据 ...", 
				fromSheet.getSheetName()
			));
			return Collections.emptyList();
		}

		// 创建列表对象
		List<T> objList = new ArrayList<>(rowCount);

		for (int i = startFromRowIndex; i <= rowCount; i++) {
			// 获取行数据
			XSSFRow row = fromSheet.getRow(i);

			if (row == null) {
				// 如果行数据为空, 
				// 则直接跳过!
				continue;
			}

			// 创建模板对象
			T newObj = byClazz.newInstance();
			// 读取行数据
			newObj.readXSSFRow(new XSSFRowReadStream(row, xlsxFileName));
			// 添加对象到列表
			objList.add(newObj);
		}

		return objList;
	}
}
