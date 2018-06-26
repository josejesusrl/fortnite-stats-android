package learning.josejesusrl.fortnitestats;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GameStats {

    // Datos sobre el jugador, este mismo objeto funciona para todos los modos de juego

    private int kills;
    private int matchesPlayed;
    private String lastMatch;
    private int minutesPlayed;
    private int wins;
    private int top10;
    private int top25;
    private int deaths;
    private double kpd;
    private double kpm;
    private double tpm;
    private double score;
    private double winRate;

    public int getKills() {
        return kills;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public String getLastMatch() {
        try {
            String dateSubString = lastMatch.substring(0, 10);
            SimpleDateFormat parser = new SimpleDateFormat("yy-MM-dd");
            SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yy");

            Date date = parser.parse(dateSubString);
            return formater.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getMinutesPlayed() {
        return minutesPlayed;
    }

    public int getWins() {
        return wins;
    }

    public int getTop10() {
        return top10;
    }

    public int getTop25() {
        return top25;
    }

    public int getDeaths() {
        return deaths;
    }

    public double getKpd() {
        return kpd;
    }

    public double getKpm() {
        return kpm;
    }

    public double getTpm() {
        return tpm;
    }

    public double getScore() {
        return score;
    }

    public double getWinRate() {
        return winRate;
    }







}
