package learning.josejesusrl.fortnitestats;

public class PlataformStats {
    private GmodeStats pc = null;
    private GmodeStats ps4 = null;
    private GmodeStats xbox = null;

    public GmodeStats fromPC() {
        return pc;
    }

    public void setPcStats(GmodeStats pc) {
        this.pc = pc;
    }

    public GmodeStats fromPS4() {
        return ps4;
    }

    public void setPs4Stats(GmodeStats ps4) {
        this.ps4 = ps4;
    }

    public GmodeStats fromXBOX() {
        return xbox;
    }

    public void setXboxStats(GmodeStats xbox) {
        this.xbox = xbox;
    }

}
