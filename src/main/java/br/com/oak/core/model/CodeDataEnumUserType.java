package br.com.oak.core.model;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;


public class CodeDataEnumUserType implements UserType, ParameterizedType {

	private Class<? extends CodeDataEnum> enumClass;
	private int sqlType;

	@Override
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return owner;
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return ((CodeDataEnum) value).getCodigo();
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		boolean equals;
		if (x != null) {
			equals = x.equals(y);
		} else if (y != null) {
			equals = false;
		} else {
			equals = true;
		}
		return equals;
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
        Object retorno = null;
        Serializable codigo = (Serializable) rs.getObject(names[0]);
		/*
		 * Se o codigo do enum for um Number (ex: big decimal), significa que o
		 * codigo deve ser tratado como inteiro, para obter o valor correto do
		 * enum. Se for um string (enum de codigo string), nao é necessario tal
		 * tratamento.
		 */
		if (Number.class.isInstance(codigo)) {
			codigo = ((Number) codigo).intValue();
		}
        if (!rs.wasNull()) {
			for (CodeDataEnum value : returnedClass().getEnumConstants()) {
				if (value.getCodigo().equals(codigo)) {
                    retorno = value;
					break;
                }
            }
        }
		return retorno;
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
		if (value == null) {
			st.setNull(index, sqlType);
		} else {
			st.setObject(index, ((CodeDataEnum) value).getCodigo());
		}
	}

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}

	@Override
	public Class<? extends CodeDataEnum> returnedClass() {
		return enumClass;
	}

	@Override
	public int[] sqlTypes() {
		return new int[] { sqlType };
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setParameterValues(Properties parameters) {
		if (parameters.getProperty("enumClass") != null) {
			try {
				enumClass = (Class<? extends CodeDataEnum>) Class.forName(parameters.getProperty("enumClass"));
				if (enumClass.getMethod("getCodigo").getReturnType().isAssignableFrom(Integer.class)) {
					sqlType = Types.INTEGER;
				} else {
					sqlType = Types.VARCHAR;
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
	}
}
