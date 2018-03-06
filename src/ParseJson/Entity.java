package ParseJson;

public class Entity {
    private String name;
    private String unit;
    private String type;
    private float valueMin;
    private float valueMax;
    private int accuracy;
    private String data_type;
    private String cn;
    private String cdescr;
    private String integration;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getValueMin() {
        return this.valueMin;
    }

    public void setValueMin(float valueMin) {
        this.valueMin = valueMin;
    }

    public float getValueMax() {
        return this.valueMax;
    }

    public void setValueMax(float valueMax) {
        this.valueMax = valueMax;
    }

    public int getAccuracy() {
        return this.accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public String getData_type() {
        return this.data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public String getCn() {
        return this.cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getCdescr() {
        return this.cdescr;
    }

    public void setCdescr(String cdescr) {
        this.cdescr = cdescr;
    }

    public String getIntegration() {
        return this.integration;
    }

    public void setIntegration(String integration) {
        this.integration = integration;
    }
}
