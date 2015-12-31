package com.game.part.tmpl.type;

import com.game.part.tmpl.XlsxTmplError;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

/**
 * 基本类型数值列
 * 
 * @author hjj2019
 * @param <T>
 * 
 */
abstract class BasicTypeCol<T> extends AbstractXlsxCol {
	/** 是否可以为空值 */
	private boolean _nullable = true;
	/** 列值 */
	private T _objVal = null;

	/**
	 * 类默认构造器
	 * 
	 */
	public BasicTypeCol() {
		this(true, null);
	}

	/**
	 * 类参数构造器
	 * 
	 * @param nullable 
	 * 
	 */
	public BasicTypeCol(boolean nullable) {
		this(nullable, null);
	}

	/**
	 * 类参数构造器
	 * 
	 * @param nullable
	 * @param defaultVal 
	 * 
	 */
	public BasicTypeCol(boolean nullable, T defaultVal) {
		this._nullable = nullable;
		this._objVal = defaultVal;
	}

	/**
	 * 类参数构造器
	 * 
	 * @param objVal 
	 * 
	 */
	public BasicTypeCol(T objVal) {
		this(true, objVal);
	}

	/**
	 * 获取对象值
	 * 
	 * @return
	 * 
	 */
	public T getObjVal() {
		return this._objVal;
	}

	/**
	 * 设置对象值
	 * 
	 * @param value 
	 * 
	 */
	void setObjVal(T value) {
		if (value != null) {
			// 如果参数不是空值, 
			// 直接赋值就好了
			this._objVal = value;
		} else {
			// 但如果参数是空值, 
			// 那么看看是否允许为空值?
			// 如果允许, 则赋值
			if (this._nullable) {
				this._objVal = value;
			}
		}
	}

	/**
	 * 获取 int 数值
	 * 
	 * @return 
	 * 
	 */
	public int getIntVal() {
		if (this._objVal == null) {
			return 0;
		} else if (this._objVal instanceof Boolean){
			return ((Boolean)this._objVal).booleanValue() ? 1 : 0;
		} else if (this._objVal instanceof Number) {
			return ((Number)this._objVal).intValue();
		} else if (this._objVal instanceof Temporal) {
			return (int)Instant.from((Temporal)this._objVal).toEpochMilli();
		} else {
			return 0;
		}
	}

	/**
	 * 获取 long 数值
	 * 
	 * @return
	 * 
	 */
	public long getLongVal() {
		if (this._objVal == null) {
			return 0L;
		} else if (this._objVal instanceof Boolean){
			return ((Boolean)this._objVal).booleanValue() ? 1L : 0L;
		} else if (this._objVal instanceof Number) {
			return ((Number)this._objVal).longValue();
		} else if (this._objVal instanceof Temporal) {
			return Instant.from((Temporal)this._objVal).toEpochMilli();
		} else {
			return 0L;
		}
	}

	/**
	 * 获取 short 数值
	 * 
	 * @return
	 * 
	 */
	public short getShortVal() {
		if (this._objVal == null) {
			return 0;
		} else if (this._objVal instanceof Boolean){
			return ((Boolean)this._objVal).booleanValue() ? (short)1 : (short)0;
		} else if (this._objVal instanceof Number) {
			return ((Number)this._objVal).shortValue();
		} else if (this._objVal instanceof Temporal) {
			return (short)Instant.from((Temporal)this._objVal).toEpochMilli();
		} else {
			return 0;
		}
	}

	/**
	 * 获取 float 数值
	 * 
	 * @return 
	 * 
	 */
	public float getFloatVal() {
		if (this._objVal == null) {
			return 0.0f;
		} else if (this._objVal instanceof Boolean){
			return ((Boolean)this._objVal).booleanValue() ? 1.0f : 0.0f;
		} else if (this._objVal instanceof Number) {
			return ((Number)this._objVal).floatValue();
		} else if (this._objVal instanceof Temporal) {
			return (float)Instant.from((Temporal)this._objVal).toEpochMilli();
		} else {
			return 0.0f;
		}
	}

	/**
	 * 获取 double 数值
	 * 
	 * @return 
	 * 
	 */
	public double getDoubleVal() {
		if (this._objVal == null) {
			return 0.0;
		} else if (this._objVal instanceof Boolean){
			return ((Boolean)this._objVal).booleanValue() ? 1.0 : 0.0;
		} else if (this._objVal instanceof Number) {
			return ((Number)this._objVal).doubleValue();
		} else if (this._objVal instanceof Temporal) {
			return Instant.from((Temporal)this._objVal).toEpochMilli();
		} else {
			return 0.0;
		}
	}

	/**
	 * 获取字符串值
	 * 
	 * @return
	 * 
	 */
	public String getStrVal() {
		if (this._objVal == null) {
			return null;
		} else {
			return String.valueOf(this._objVal);
		}
	}

	/**
	 * 获取 bool 值
	 * 
	 * @return 
	 * 
	 */
	public boolean getBoolVal() {
		if (this._objVal == null) {
			return false;
		} else if (this._objVal instanceof Boolean) {
			return ((Boolean)this._objVal).booleanValue();
		} else if (this._objVal instanceof Number) {
			return this.getIntVal() == 1;
		} else if (this._objVal instanceof Temporal) {
			return true;
		} else {
			String strVal = this.getStrVal();
			return strVal.equalsIgnoreCase("true") || strVal.equalsIgnoreCase("yes") || strVal.equalsIgnoreCase("y");
		}
	}

	/**
	 * 获取 date 值, 
	 * 注意 : 如果原始类型是字符串类型, 
	 * 字符串值必须是格式 = "2015-01-01", 
	 * 才能转换为日期时间型!
	 * 
	 * @return
	 * 
	 */
	public LocalDate getDateVal() {
		if (this._objVal == null) {
			return null;
		} else if (this._objVal instanceof Number) {
			return Instant.ofEpochMilli(this.getLongVal()).atZone(ZoneId.systemDefault()).toLocalDate();
		} else if (this._objVal instanceof Temporal) {
			return Instant.from((Temporal)this._objVal).atZone(ZoneId.systemDefault()).toLocalDate();
		} else if (this._objVal instanceof String) {
			return LocalDate.parse(this.getStrVal());
		} else {
			return null;
		}
	}

	/**
	 * 获取 time 值, 
	 * 注意 : 如果原始类型是字符串类型, 
	 * 字符串值必须是格式 = "10:00:00", 
	 * 才能转换为日期时间型!
	 * 
	 * @return
	 * 
	 */
	public LocalTime getTimeVal() {
		if (this._objVal == null) {
			return null;
		} else if (this._objVal instanceof Number) {
			return Instant.ofEpochMilli(this.getLongVal()).atZone(ZoneId.systemDefault()).toLocalTime();
		} else if (this._objVal instanceof Temporal) {
			return Instant.from((Temporal)this._objVal).atZone(ZoneId.systemDefault()).toLocalTime();
		} else if (this._objVal instanceof String) {
			return LocalTime.parse(this.getStrVal());
		} else {
			return null;
		}
	}

	/**
	 * 获取 time 值, 
	 * 注意 : 如果原始类型是字符串类型, 
	 * 字符串值必须是格式 = "2015-01-01 10:00:00", 
	 * 才能转换为日期时间型!
	 * 
	 * @return
	 * 
	 */
	public LocalDateTime getDateTimeVal() {
		if (this._objVal == null) {
			return null;
		} else if (this._objVal instanceof Number) {
			return Instant.ofEpochMilli(this.getLongVal()).atZone(ZoneId.systemDefault()).toLocalDateTime();
		} else if (this._objVal instanceof Temporal) {
			return Instant.from((Temporal)this._objVal).atZone(ZoneId.systemDefault()).toLocalDateTime();
		} else if (this._objVal instanceof String) {
			return LocalDateTime.parse(this.getStrVal(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		} else {
			return null;
		}
	}

	@Override
	public void validate() {
		if (this._nullable == false && 
			this._objVal == null) {
			// 如果不能为空值, 
			// 但对象值它就是空值, 
			// 则抛出异常!
			throw new XlsxTmplError(this, "对象值为空");
		}
	}
}
