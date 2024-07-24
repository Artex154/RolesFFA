package be.artex.rolesffa.util.cooldown;

public enum CooldownType {
    IMMENSE_SPEED(70*20L);

    private final long time;

    CooldownType(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }
}
