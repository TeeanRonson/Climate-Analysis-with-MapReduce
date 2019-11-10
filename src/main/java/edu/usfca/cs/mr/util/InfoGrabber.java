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

    /**
     * Column 0
     * @return
     */
    public String getStationWBAN() {



        return info[0];
    }

    public String getStationWBANUsingSubstring() {
        return infoAsString.substring(0, 5);
    }

    /**
     * Column 1
     * - YYYYMMDD
     * @return
     */
    public Integer getUTC_Date() {
        return Integer.parseInt(info[1]);
    }

    /**
     * Column 1 Get YYYYMM
     * @return
     */
    public Integer getUTCDateByMonth() {
        return Integer.parseInt(info[1].substring(0, 6));
    }

    /**
     * HHmm
     * @return
     */
    public Integer getUTCTime() {
        return Integer.parseInt(info[2]);
    }

    public Integer get_LST_Date() {
        return Integer.parseInt(info[3]);
    }

    public Integer get_LST_Time() {
        return Integer.parseInt(info[4]);
    }


    /**
     * Column 6
     * @return
     */
    public Float get_Longitude() {
        float lon = Float.parseFloat(info[6]);
        return lon;
    }

    /**
     * Column 7
     * @return
     */
    public Float get_Latitude() {
        float lat = Float.parseFloat(info[7]);
        return lat;
    }

    /**
     * Column 8
     * @return
     */
    public Float get_Air_Temp() {


        Float value = Float.parseFloat(info[8]);

        return value;
    }

    /**
     *
     */
    public Float get_Precipitation() {
        return Float.parseFloat(info[9]);
    }

    public Integer get_Solar_Radiation() {
        return Integer.parseInt(info[10]);
    }

    /**
     * Column 11
     * @return
     */
    public Integer get_Solar_Radiation_Flag() {
        return Integer.parseInt(info[11]);
    }

    /**
     * Column 12
     * @return
     */
    public Float get_Surface_Temp() {

        Float value = Float.parseFloat(info[12]);
        return value;
    }

    public Character get_Surface_Temp_Type() {
        return info[13].charAt(0);
    }

    public Integer get_Surface_Temp_Flag() {
        return Integer.parseInt(info[14]);

    }

    public Integer get_Relative_Humidity() {
        return Integer.parseInt(info[15]);
    }

    public Integer get_Relative_Humidity_Flag() {
        return Integer.parseInt(info[16]);
    }


    public Integer get_Soil_Moisture_5() {

        System.out.println(info[17]);

        System.out.println(Float.parseFloat(info[17]));
        return Integer.parseInt(info[17]);
    }

    public Integer get_Soil_Temperature_5() {
        return Integer.parseInt(info[18]);
    }

    public Integer get_Wetness() {
        return Integer.parseInt(info[19]);
    }

    public Integer get_Wet_Flag() {
        return Integer.parseInt(info[20]);
    }

    public Float get_Wind_Speed_1_5() {
        return Float.parseFloat(info[21]);
    }

    public Integer get_Wind_Flag() {
        return Integer.parseInt(info[22]);
    }

}
