package learning.josejesusrl.fortnitestats;

public class PlayerStats {
    private GmodeStats pc;
    private GmodeStats ps4;
    private GmodeStats xbox;

    public GmodeStats getPc() {
        return pc;
    }

    public void setPc(GmodeStats pc) {
        this.pc = pc;
    }

    public GmodeStats getPs4() {
        return ps4;
    }

    public void setPs4(GmodeStats ps4) {
        this.ps4 = ps4;
    }

    public GmodeStats getXbox() {
        return xbox;
    }

    public void setXbox(GmodeStats xbox) {
        this.xbox = xbox;
    }

}
