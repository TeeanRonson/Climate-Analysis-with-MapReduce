package edu.usfca.cs.mr.util;

/**
 * A class that retrieves information from the input string
 */
public class InfoGrabber {

    private String[] info;
    private String infoAsString;
    public InfoGrabber(String s) {
        this.info = s.split("\\s+");
        this.infoAsString = s;

    }

    public String getStationWBAN() {
        return info[0];
    }

    public String getStationWBAN2() {
        return infoAsString.substring(0, 5);
    }

    public Integer getUTCDate() {
        return Integer.parseInt(info[1]);
    }


    public Float getLong() {
        float lon = Float.parseFloat(info[6]);
        return lon;
    }

    public Float getLat() {
        float lat = Float.parseFloat(info[7]);
        return lat;
    }

    public Float getAirTemp() {

        if (info[8].equals(CONSTANTS.MISSING1DEC)) return 999.0f;

        Float value = Float.parseFloat(info[8]);
        return value;
    }

    public Float getSurfaceTemp() {
        return Float.parseFloat(info[12]);
    }
}
