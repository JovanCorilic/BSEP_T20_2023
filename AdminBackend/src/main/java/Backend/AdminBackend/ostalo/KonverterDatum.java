package Backend.AdminBackend.ostalo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class KonverterDatum {
    public static String konvertovanjeUString(LocalDateTime temp) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return temp.format(dateTimeFormatter);
    }

    public static LocalDateTime konvertovanjeUDateTime(String temp) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return LocalDateTime.parse(temp, dateTimeFormatter);
    }

    public static String konvertovanjeSamoDatumUString(LocalDate temp) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return temp.format(dateTimeFormatter);
    }

    public static LocalDate konvertovanjeSamoDatumUDate(String temp) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(temp, dateTimeFormatter);
    }

    public static Date konvertovanjeSamoDatumUDateHitno(String temp) {
        String[]lista = temp.split("-");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(lista[0]));
        cal.set(Calendar.MONTH, Integer.parseInt(lista[1]));
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(lista[2]));

        return cal.getTime();
    }
}
