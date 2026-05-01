package ratplaguesurvivors.entity.pc;

import ratplaguesurvivors.weapons.Sword;

public enum PCType {
    CAT("Gato das Botas", 100, 25, new Sword());

    private String name;
    private final int baseHealth;
    private final int baseDamage;
    private final Sword attackType;

    PCType(String name, int baseHealth, int baseDamage, Sword attackType) {
        this.name = name;
        this.baseHealth = baseHealth;
        this.baseDamage = baseDamage;
        this.attackType = attackType;
    }

    public Sword getAttackType() {
        return attackType;
    }

    public String getName() {
        return name;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public String setName(String playerName){
         name = playerName;
         return name;
    }
}
