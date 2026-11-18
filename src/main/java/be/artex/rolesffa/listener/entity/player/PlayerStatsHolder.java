package be.artex.rolesffa.listener.entity.player;

public class PlayerStatsHolder {
    private int killAmount;
    private int KSAmount;
    private int deathAmount;

    public PlayerStatsHolder() {
        this.deathAmount = 0;
        this.KSAmount = 0;
        this.killAmount = 0;
    }

    public void addKill() {
        killAmount++;
        KSAmount++;
    }

    public void addDeath() {
        deathAmount++;
        KSAmount = 0;
    }

    public int getKillAmount() {
        return killAmount;
    }

    public int getKillStreakAmount() {
        return KSAmount;
    }

    public int getDeathAmount() {
        return deathAmount;
    }

    public int getKDA() {
        if (killAmount == 0 || deathAmount == 0)
            return 0;

        return killAmount / deathAmount;
    }
}
