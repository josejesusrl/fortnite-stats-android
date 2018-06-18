package learning.josejesusrl.fortnitestats;

public class GameStats {

    // Todos los datos que puede contener sobre el jugador, ya sea en SOLO, DUO o SQUAD

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

    public GameStats(int kills, int matchesPlayed, String lastMatch, int minutesPlayed, int wins, int top10, int top25, int deaths, double kpd, double kpm, double tpm, double score, double winRate) {
        this.kills = kills;
        this.matchesPlayed = matchesPlayed;
        this.lastMatch = lastMatch;
        this.minutesPlayed = minutesPlayed;
        this.wins = wins;
        this.top10 = top10;
        this.top25 = top25;
        this.deaths = deaths;
        this.kpd = kpd;
        this.kpm = kpm;
        this.tpm = tpm;
        this.score = score;
        this.winRate = winRate;
    }

    /**
     * Getters and Setters
     */

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public String getLastMatch() {
        return lastMatch;
    }

    public void setLastMatch(String lastMatch) {
        this.lastMatch = lastMatch;
    }

    public int getMinutesPlayed() {
        return minutesPlayed;
    }

    public void setMinutesPlayed(int minutesPlayed) {
        this.minutesPlayed = minutesPlayed;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getTop10() {
        return top10;
    }

    public void setTop10(int top10) {
        this.top10 = top10;
    }

    public int getTop25() {
        return top25;
    }

    public void setTop25(int top25) {
        this.top25 = top25;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public double getKpd() {
        return kpd;
    }

    public void setKpd(double kdp) {
        this.kpd = kdp;
    }

    public double getKpm() {
        return kpm;
    }

    public void setKpm(double kpm) {
        this.kpm = kpm;
    }

    public double getTpm() {
        return tpm;
    }

    public void setTpm(double tpm) {
        this.tpm = tpm;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getWinRate() {
        return winRate;
    }

    public void setWinRate(double winRate) {
        this.winRate = winRate;
    }




}
