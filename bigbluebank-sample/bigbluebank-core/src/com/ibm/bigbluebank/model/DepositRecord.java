package com.ibm.bigbluebank.model;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.*;
import com.ibm.etools.marshall.util.*;

/**
 * @generated
 * Generated Class: DepositRecord
 * @type-descriptor.aggregate-instance-td accessor="readWrite" contentSize="41" offset="0" size="41"
 * @type-descriptor.platform-compiler-info language="COBOL" defaultBigEndian="true" defaultCodepage="IBM-1047" defaultExternalDecimalSign="ebcdic" defaultFloatType="ibm390Hex"
 */

public class DepositRecord implements javax.resource.cci.Record,
		javax.resource.cci.Streamable, com.ibm.etools.marshall.RecordBytes {
	/**
	 * @generated
	 */
	private byte[] buffer_ = null;
	/**
	 * @generated
	 */
	private static final int bufferSize_;
	/**
	 * @generated
	 */
	private static final byte[] initializedBuffer_;
	/**
	 * @generated
	 */
	private static java.util.HashMap getterMap_ = null;
	/**
	 * @generated
	 */
	private java.util.HashMap valFieldNameMap_ = null;
	/**
	 * @generated
	 */
	public static final BigDecimal BD_NINE_NINE_NINE_NINE_NINE_DOT_NINE_NINE = new BigDecimal(
			"99999.99");
	/**
	 * @generated
	 */
	public static final BigDecimal BD_NEG_NINE_NINE_NINE_NINE_NINE_DOT_NINE_NINE = new BigDecimal(
			"-99999.99");

	/**
	 * initializer
	 * @generated
	 */
	static {
		bufferSize_ = 41;
		initializedBuffer_ = new byte[bufferSize_];
		String error_CodeInitialValue = " ";
		MarshallStringUtils.marshallFixedLengthStringIntoBuffer(
				error_CodeInitialValue, initializedBuffer_, 36, "IBM-1047", 5,
				MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		String account_NoInitialValue = " ";
		MarshallStringUtils.marshallFixedLengthStringIntoBuffer(
				account_NoInitialValue, initializedBuffer_, 10, "IBM-1047", 11,
				MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		String customer_IdInitialValue = " ";
		MarshallStringUtils.marshallFixedLengthStringIntoBuffer(
				customer_IdInitialValue, initializedBuffer_, 0, "IBM-1047", 10,
				MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		String process_StatusInitialValue = " ";
		MarshallStringUtils.marshallFixedLengthStringIntoBuffer(
				process_StatusInitialValue, initializedBuffer_, 35, "IBM-1047",
				1, MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
	}

	/**
	 * constructor
	 * @generated
	 */
	public DepositRecord() {
		initialize();
	}

	/**
	 * constructor
	 * @generated
	 */
	public DepositRecord(java.util.HashMap valFieldNameMap) {
		valFieldNameMap_ = valFieldNameMap;
		initialize();
	}

	/**
	 * @generated
	 * initialize
	 */
	public void initialize() {
		buffer_ = new byte[bufferSize_];
		System.arraycopy(initializedBuffer_, 0, buffer_, 0, bufferSize_);
	}

	/**
	 * @generated
	 * @see javax.resource.cci.Streamable#read(java.io.InputStream)
	 */
	public void read(java.io.InputStream inputStream)
			throws java.io.IOException {
		byte[] input = new byte[inputStream.available()];
		inputStream.read(input);
		buffer_ = input;
	}

	/**
	 * @generated
	 * @see javax.resource.cci.Streamable#write(java.io.OutputStream)
	 */
	public void write(java.io.OutputStream outputStream)
			throws java.io.IOException {
		outputStream.write(buffer_, 0, getSize());
	}

	/**
	 * @generated
	 * @see javax.resource.cci.Record#getRecordName()
	 */
	public String getRecordName() {
		return (this.getClass().getName());
	}

	/**
	 * @generated
	 * @see javax.resource.cci.Record#setRecordName(String)
	 */
	public void setRecordName(String recordName) {
		return;
	}

	/**
	 * @generated
	 * @see javax.resource.cci.Record#setRecordShortDescription(String)
	 */
	public void setRecordShortDescription(String shortDescription) {
		return;
	}

	/**
	 * @generated
	 * @see javax.resource.cci.Record#getRecordShortDescription()
	 */
	public String getRecordShortDescription() {
		return (this.getClass().getName());
	}

	/**
	 * @generated
	 * @see javax.resource.cci.Record#clone()
	 */
	public Object clone() throws CloneNotSupportedException {
		return (super.clone());
	}

	/**
	 * @generated
	 * @see javax.resource.cci.Record#equals
	 */
	public boolean equals(Object object) {
		return (super.equals(object));
	}

	/**
	 * @generated
	 * @see javax.resource.cci.Record#hashCode
	 */
	public int hashCode() {
		return (super.hashCode());
	}

	/**
	 * @generated
	 * @see com.ibm.etools.marshall.RecordBytes#getBytes
	 */
	public byte[] getBytes() {
		return (buffer_);
	}

	/**
	 * @generated
	 * @see com.ibm.etools.marshall.RecordBytes#setBytes
	 */
	public void setBytes(byte[] bytes) {
		if ((bytes != null) && (bytes.length != 0))
			buffer_ = bytes;
	}

	/**
	 * @generated
	 * @see com.ibm.etools.marshall.RecordBytes#getSize
	 */
	public int getSize() {
		return (41);
	}

	/**
	 * @generated
	 */
	public boolean match(Object obj) {
		if (obj == null)
			return (false);
		if (obj.getClass().isArray()) {
			byte[] currBytes = buffer_;
			try {
				byte[] objByteArray = (byte[]) obj;
				if (objByteArray.length != buffer_.length)
					return (false);
				buffer_ = objByteArray;
			} catch (ClassCastException exc) {
				return (false);
			} finally {
				buffer_ = currBytes;
			}
		} else
			return (false);
		return (true);
	}

	/**
	 * @generated
	 */
	public void populate(Object obj) {
		if (obj.getClass().isArray()) {
			try {
				buffer_ = (byte[]) obj;
			} catch (ClassCastException exc) {
			}
		}
	}

	/**
	 * @generated
	 * @see java.lang.Object#toString
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer(super.toString());
		sb.append("\n");
		ConversionUtils.dumpBytes(sb, buffer_);
		return (sb.toString());
	}

	/**
	 * @generated
	 * wrappedGetNumber
	 */
	public Number wrappedGetNumber(String propertyName) {
		Number result = null;

		if (getterMap_ == null) {
			synchronized (initializedBuffer_) {
				if (getterMap_ == null) {
					java.util.HashMap getterMap = new java.util.HashMap();
					try {
						BeanInfo info = Introspector.getBeanInfo(this
								.getClass());
						PropertyDescriptor[] props = info
								.getPropertyDescriptors();

						for (int i = 0; i < props.length; i++) {
							String propName = props[i].getName();
							getterMap.put(propName, props[i].getReadMethod());
						}
					} catch (IntrospectionException exc) {
					}
					getterMap_ = getterMap;
				}
			}
		}

		Method method = (Method) getterMap_.get(propertyName);
		if (method != null) {
			try {
				result = (Number) method.invoke(this, new Object[0]);
			} catch (Exception exc) {
			}
		}

		return (result);
	}

	/**
	 * @generated
	 * evaluateMap
	 */
	public java.util.HashMap evaluateMap(java.util.HashMap valFieldNameMap) {
		if (valFieldNameMap == null)
			return (null);
		java.util.HashMap returnMap = new java.util.HashMap(
				valFieldNameMap.size());
		java.util.Set aSet = valFieldNameMap.entrySet();

		for (java.util.Iterator cursor = aSet.iterator(); cursor.hasNext();) {
			java.util.Map.Entry element = (java.util.Map.Entry) cursor.next();
			String key = (String) element.getKey();
			String fieldName = (String) element.getValue();
			Number fieldValue = wrappedGetNumber(fieldName);
			if (fieldValue == null)
				fieldValue = new Integer(0);
			returnMap.put(key, fieldValue);
		}

		return (returnMap);
	}

	/**
	 * @generated
	 * Returns the integer value of the formula string for an offset or size.
	 * The formula can be comprised of the following functions:
	 * neg(x)   := -x       // prefix negate
	 * add(x,y) := x+y      // infix add
	 * sub(x,y) := x-y      // infix subtract
	 * mpy(x,y) := x*y      // infix multiply
	 * div(x,y) := x/y      // infix divide
	 * max(x,y) := max(x,y)
	 * min(x,y) := min(x,y)
	 *
	 * mod(x,y) := x mod y
	 *
	 * The mod function is defined as mod(x,y) = r where r is the smallest non-negative integer
	 * such that x-r is evenly divisible by y. So mod(7,4) is 3, but mod(-7,4) is 1. If y is a
	 * power of 2, then mod(x,y) is equal to the bitwise-and of x and y-1.
	 *
	 * val(1, m, n, o,..)
	 *
	 * The val function returns the value of a field in the model. The val function takes one
	 * or more arguments, and the first argument refers to a level-1 field in the type model and must be either:
	 *    - the name of a level-1 field described in the language model
	 *    - the integer 1 (indicating that the level-1 parent of the current structure is meant)
	 * If the first argument to the val function is the integer 1, then and only then are subsequent arguments
	 * permitted. These subsequent arguments are integers that the specify the ordinal number within its
	 * substructure of the subfield that should be dereferenced.
	 *
	 * @return The integer value of the formula string for an offset or size.
	 * @param formula The formula to be evaluated.
	 * @param valFieldNameMap A map of val() formulas to field names.
	 * @throws IllegalArgumentException if the formula is null.
	 */

	public int evaluateFormula(String formula, java.util.HashMap valFieldNameMap)
			throws IllegalArgumentException {
		if (formula == null)
			throw new IllegalArgumentException(MarshallResource.instance()
					.getString(MarshallResource.MARSHRT_FORMULA_NULL));

		int result = 0;

		int index = formula.indexOf("(");

		if (index == -1) // It's a number not an expression
		{
			try {
				result = Integer.parseInt(formula);
			} catch (Exception exc) {
			}

			return (result);
		}

		// Determine the outermost function
		String function = formula.substring(0, index);

		if (function.equalsIgnoreCase("val")) {
			Object field = valFieldNameMap.get(formula);
			if (field == null)
				return (0);

			if (field instanceof String) {
				Number num = wrappedGetNumber((String) field);
				if (num == null) // Element does not exist
					return (0);
				result = num.intValue();
			} else if (field instanceof Number)
				result = ((Number) field).intValue();
			else
				return (0);

			return (result);
		} else if (function.equalsIgnoreCase("neg")) {
			// The new formula is the content between the brackets
			formula = formula.substring(index + 1, formula.length() - 1);
			result = -1 * evaluateFormula(formula, valFieldNameMap);
			return (result);
		} else {
			// Get the contents between the outermost brackets
			formula = formula.substring(index + 1, formula.length() - 1);
			char[] formulaChars = formula.toCharArray();

			// Get the left side and the right side of the operation

			int brackets = 0;
			int i = 0;

			for (; i < formulaChars.length; i++) {
				if (formulaChars[i] == '(')
					brackets++;
				else if (formulaChars[i] == ')')
					brackets--;
				else if (formulaChars[i] == ',') {
					if (brackets == 0)
						break;
				}
			}

			String leftSide = "0";
			String rightSide = "0";

			leftSide = formula.substring(0, i);
			rightSide = formula.substring(i + 1);

			if (function.equalsIgnoreCase("add"))
				result = evaluateFormula(leftSide, valFieldNameMap)
						+ evaluateFormula(rightSide, valFieldNameMap);
			else if (function.equalsIgnoreCase("mpy"))
				result = evaluateFormula(leftSide, valFieldNameMap)
						* evaluateFormula(rightSide, valFieldNameMap);
			else if (function.equalsIgnoreCase("sub"))
				result = evaluateFormula(leftSide, valFieldNameMap)
						- evaluateFormula(rightSide, valFieldNameMap);
			else if (function.equalsIgnoreCase("div"))
				result = evaluateFormula(leftSide, valFieldNameMap)
						/ evaluateFormula(rightSide, valFieldNameMap);
			else if (function.equalsIgnoreCase("max"))
				result = Math.max(evaluateFormula(leftSide, valFieldNameMap),
						evaluateFormula(rightSide, valFieldNameMap));
			else if (function.equalsIgnoreCase("min"))
				result = Math.min(evaluateFormula(leftSide, valFieldNameMap),
						evaluateFormula(rightSide, valFieldNameMap));
			else if (function.equalsIgnoreCase("mod"))
				result = evaluateFormula(leftSide, valFieldNameMap)
						% evaluateFormula(rightSide, valFieldNameMap);
		}

		return (result);
	}

	/**
	 * @generated
	 * @type-descriptor.restriction maxLength="10"
	 * @type-descriptor.initial-value kind="SPACE"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="10" offset="0" size="10"
	 * @type-descriptor.string-td characterSize="1" lengthEncoding="fixedLength" paddingCharacter=" " prefixLength="0"
	 */
	public String getCustomer_Id() {
		String customer_Id = null;
		customer_Id = MarshallStringUtils
				.unmarshallFixedLengthStringFromBuffer(buffer_, 0, "IBM-1047",
						10);
		return (customer_Id);
	}

	/**
	 * @generated
	 */
	public void setCustomer_Id(String customer_Id) {
		if (customer_Id != null) {
			if (customer_Id.length() > 10)
				throw new IllegalArgumentException(MarshallResource.instance()
						.getString(MarshallResource.IWAA0124E, customer_Id,
								"10", "customer_Id"));
			MarshallStringUtils.marshallFixedLengthStringIntoBuffer(
					customer_Id, buffer_, 0, "IBM-1047", 10,
					MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		}
	}

	/**
	 * @generated
	 * @type-descriptor.restriction maxLength="11"
	 * @type-descriptor.initial-value kind="SPACE"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="11" offset="10" size="11"
	 * @type-descriptor.string-td characterSize="1" lengthEncoding="fixedLength" paddingCharacter=" " prefixLength="0"
	 */
	public String getAccount_No() {
		String account_No = null;
		account_No = MarshallStringUtils.unmarshallFixedLengthStringFromBuffer(
				buffer_, 10, "IBM-1047", 11);
		return (account_No);
	}

	/**
	 * @generated
	 */
	public void setAccount_No(String account_No) {
		if (account_No != null) {
			if (account_No.length() > 11)
				throw new IllegalArgumentException(MarshallResource.instance()
						.getString(MarshallResource.IWAA0124E, account_No,
								"11", "account_No"));
			MarshallStringUtils.marshallFixedLengthStringIntoBuffer(account_No,
					buffer_, 10, "IBM-1047", 11,
					MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		}
	}

	/**
	 * @generated
	 * @type-descriptor.restriction lowerBound="-99999.99" upperBound="99999.99"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="7" offset="21" size="7"
	 * @type-descriptor.external-decimal-td virtualDecimalPoint="2" signFormat="trailing"
	 */
	public BigDecimal getAvail_Imm() {
		BigDecimal avail_Imm = null;
		avail_Imm = MarshallExternalDecimalUtils
				.unmarshallBigDecimalFromBuffer(
						buffer_,
						21,
						7,
						true,
						2,
						MarshallExternalDecimalUtils.SIGN_FORMAT_TRAILING,
						MarshallExternalDecimalUtils.EXTERNAL_DECIMAL_SIGN_EBCDIC);
		return (avail_Imm);
	}

	/**
	 * @generated
	 */
	public void setAvail_Imm(BigDecimal avail_Imm) {
		if (avail_Imm != null) {
			if ((avail_Imm
					.compareTo(BD_NEG_NINE_NINE_NINE_NINE_NINE_DOT_NINE_NINE) == -1)
					|| (avail_Imm
							.compareTo(BD_NINE_NINE_NINE_NINE_NINE_DOT_NINE_NINE) == 1))
				throw new IllegalArgumentException(MarshallResource.instance()
						.getString(MarshallResource.IWAA0127E,
								avail_Imm.toString(), "avail_Imm", "-99999.99",
								"99999.99"));
			MarshallExternalDecimalUtils.marshallExternalDecimalIntoBuffer(
					avail_Imm, buffer_, 21, 7, true, 2,
					MarshallExternalDecimalUtils.SIGN_FORMAT_TRAILING,
					MarshallExternalDecimalUtils.EXTERNAL_DECIMAL_SIGN_EBCDIC);
		}
	}

	/**
	 * @generated
	 * @type-descriptor.restriction lowerBound="-99999.99" upperBound="99999.99"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="7" offset="28" size="7"
	 * @type-descriptor.external-decimal-td virtualDecimalPoint="2" signFormat="trailing"
	 */
	public BigDecimal getAmount() {
		BigDecimal amount = null;
		amount = MarshallExternalDecimalUtils.unmarshallBigDecimalFromBuffer(
				buffer_, 28, 7, true, 2,
				MarshallExternalDecimalUtils.SIGN_FORMAT_TRAILING,
				MarshallExternalDecimalUtils.EXTERNAL_DECIMAL_SIGN_EBCDIC);
		return (amount);
	}

	/**
	 * @generated
	 */
	public void setAmount(BigDecimal amount) {
		if (amount != null) {
			if ((amount
					.compareTo(BD_NEG_NINE_NINE_NINE_NINE_NINE_DOT_NINE_NINE) == -1)
					|| (amount
							.compareTo(BD_NINE_NINE_NINE_NINE_NINE_DOT_NINE_NINE) == 1))
				throw new IllegalArgumentException(MarshallResource.instance()
						.getString(MarshallResource.IWAA0127E,
								amount.toString(), "amount", "-99999.99",
								"99999.99"));
			MarshallExternalDecimalUtils.marshallExternalDecimalIntoBuffer(
					amount, buffer_, 28, 7, true, 2,
					MarshallExternalDecimalUtils.SIGN_FORMAT_TRAILING,
					MarshallExternalDecimalUtils.EXTERNAL_DECIMAL_SIGN_EBCDIC);
		}
	}

	/**
	 * @generated
	 * @type-descriptor.restriction maxLength="1"
	 * @type-descriptor.initial-value kind="SPACE"
	 * @type-descriptor.level88 name="Err" value="E, e"
	 * @type-descriptor.level88 name="Cleared" value="C, c"
	 * @type-descriptor.level88 name="Unprocessed" value="U, u"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="1" offset="35" size="1"
	 * @type-descriptor.string-td characterSize="1" lengthEncoding="fixedLength" paddingCharacter=" " prefixLength="0"
	 */
	public String getProcess_Status() {
		String process_Status = null;
		process_Status = MarshallStringUtils
				.unmarshallFixedLengthStringFromBuffer(buffer_, 35, "IBM-1047",
						1);
		return (process_Status);
	}

	/**
	 * @generated
	 */
	public void setProcess_Status(String process_Status) {
		if (process_Status != null) {
			if (process_Status.length() > 1)
				throw new IllegalArgumentException(MarshallResource.instance()
						.getString(MarshallResource.IWAA0124E, process_Status,
								"1", "process_Status"));
			MarshallStringUtils.marshallFixedLengthStringIntoBuffer(
					process_Status, buffer_, 35, "IBM-1047", 1,
					MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		}
	}

	/**
	 * @generated
	 */
	public static String getErr() {
		return ("E");
	}

	/**
	 * @generated
	 */
	public static boolean isErr(String err) {
		return ("E".equals(err) || "e".equals(err));
	}

	/**
	 * @generated
	 */
	public static String getCleared() {
		return ("C");
	}

	/**
	 * @generated
	 */
	public static boolean isCleared(String cleared) {
		return ("C".equals(cleared) || "c".equals(cleared));
	}

	/**
	 * @generated
	 */
	public static String getUnprocessed() {
		return ("U");
	}

	/**
	 * @generated
	 */
	public static boolean isUnprocessed(String unprocessed) {
		return ("U".equals(unprocessed) || "u".equals(unprocessed));
	}

	/**
	 * @generated
	 * @type-descriptor.restriction maxLength="5"
	 * @type-descriptor.initial-value kind="SPACE"
	 * @type-descriptor.simple-instance-td accessor="readWrite" contentSize="5" offset="36" size="5"
	 * @type-descriptor.string-td characterSize="1" lengthEncoding="fixedLength" paddingCharacter=" " prefixLength="0"
	 */
	public String getError_Code() {
		String error_Code = null;
		error_Code = MarshallStringUtils.unmarshallFixedLengthStringFromBuffer(
				buffer_, 36, "IBM-1047", 5);
		return (error_Code);
	}

	/**
	 * @generated
	 */
	public void setError_Code(String error_Code) {
		if (error_Code != null) {
			if (error_Code.length() > 5)
				throw new IllegalArgumentException(MarshallResource.instance()
						.getString(MarshallResource.IWAA0124E, error_Code, "5",
								"error_Code"));
			MarshallStringUtils.marshallFixedLengthStringIntoBuffer(error_Code,
					buffer_, 36, "IBM-1047", 5,
					MarshallStringUtils.STRING_JUSTIFICATION_LEFT, " ");
		}
	}

}