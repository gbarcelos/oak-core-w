package br.com.oak.core.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

public class DataUtil {

	public static final String PATTERN_DATA = "dd/MM/yyyy";

	public static final String PATTERN_DATA_HORA = "dd/MM/yyyy HH:mm:ss";

	public static final String PATTERN_DATA_EXTENSO = "d 'de' MMMM 'de' yyyy";

	public static final String PATTERN_DATA_EXTENSO_FULL = "d 'de' MMMM 'de' yyyy - HH:mm";

	public static final String formataData(final Date data) {
		return formataData(data, PATTERN_DATA);
	}

	public static final String formataData(final Date data,
			final String patternData) {

		String dataFormatada = "";

		if (data != null && StringUtils.isNotBlank(patternData)) {
			final SimpleDateFormat formatter = new SimpleDateFormat(patternData);
			dataFormatada = formatter.format(data);
		}
		return dataFormatada;
	}

	public static boolean isDataValida(final String dataTeste) {

		boolean dataValida = true;

		try {
			final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					PATTERN_DATA, Locale.getDefault());

			simpleDateFormat.setLenient(false);
			simpleDateFormat.parse(dataTeste);

		} catch (Exception e) {
			dataValida = false;
		}
		return dataValida;
	}

	public static Date cloneWith2359(final Date date) {
		Date retorno = null;

		if (date != null) {
			retorno = (Date) date.clone();
			final Calendar cal = Calendar.getInstance();
			cal.setTime(retorno);
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			cal.set(Calendar.MILLISECOND, 999);
			retorno.setTime(cal.getTime().getTime());
		}
		return retorno;
	}

	public static String recuperarMes(final Date data) {
		final SimpleDateFormat sdf = new SimpleDateFormat("MM");
		return sdf.format(data);
	}

	public static String recuperarAno(final Date data) {
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return sdf.format(data);
	}

	public static String recuperarAnoAtual() {
		return recuperarAno(Calendar.getInstance().getTime());
	}
}