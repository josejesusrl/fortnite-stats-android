package learning.josejesusrl.fortnitestats;

public class PlayerStats {
    private PlataformStats pc;
    private PlataformStats ps4;
    private PlataformStats xbox;

    public PlataformStats getPc() {
        return pc;
    }

    public void setPc(PlataformStats pc) {
        this.pc = pc;
    }

    public PlataformStats getPs4() {
        return ps4;
    }

    public void setPs4(PlataformStats ps4) {
        this.ps4 = ps4;
    }

    public PlataformStats getXbox() {
        return xbox;
    }

    public void setXbox(PlataformStats xbox) {
        this.xbox = xbox;
    }

}
