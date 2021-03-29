package draylar.infintus.config;

public class Singularity {

    private String name;
    private String sourceID;
    private int singularityColor;
    private int cost;
    private boolean autoHue;

    public Singularity(String name, String sourceID, int singularityColor, int cost, boolean autoHue) {
        this.name = name;
        this.sourceID = sourceID;
        this.singularityColor = singularityColor;
        this.cost = cost;
        this.autoHue = autoHue;
    }

    public String getName() {
        return name;
    }

    public String getSourceID() {
        return sourceID;
    }

    public int getColor() {
        return singularityColor;
    }

    public int getCost() {
        return cost;
    }

    public boolean doAutoHue() {
        return autoHue;
    }

    public boolean isCraftable() {
        return !sourceID.equals("minecraft:air");
    }

    public static class Builder {

        private String name = "apple";
        private String sourceID = "minecraft:air";
        private int singularityColor = 0x1ED4D1;
        private int cost = 10000;
        private boolean autoHue = true;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder fromMaterial(String id) {
            this.sourceID = id;
            return this;
        }

        public Builder withColor(int hex) {
            this.singularityColor = hex;
            return this;
        }

        public Builder costs(int cost) {
            this.cost = cost;
            return this;
        }

        public Builder autoHue(boolean autoHue) {
            this.autoHue = autoHue;
            return this;
        }

        public Singularity build() {
            return new Singularity(name, sourceID, singularityColor, cost, autoHue);
        }
    }
}
