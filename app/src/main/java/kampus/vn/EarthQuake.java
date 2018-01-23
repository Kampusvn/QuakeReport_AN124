package kampus.vn;


public class EarthQuake {

    /**
     * Magnitude of the earthquake
     */
    private double mMagnitude;
    /**
     * Location of the earthquake
     */
    private String mLocation;
    /**
     * Date of the earthquake
     */
    private long mTimeInMilliseconds;

    /**
     * Url of the Earthquake
     */
    private String mUrl;

    /**
     * Constructs a new {@link EarthQuake} object
     *
     * @param magnitude is magnitude of the earthquake
     * @param location  is the city location of the earthquake
     * @param timeInMilliseconds      is the time in milliseconds when the earthquake  happened
     * @param url is url for view details earthquake on browser
     */

    public EarthQuake(double magnitude, String location, long timeInMilliseconds, String url) {
        this.mMagnitude = magnitude;
        this.mLocation = location;
        this.mTimeInMilliseconds = timeInMilliseconds;
        this.mUrl = url;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public void setMagnitude(double mMagnitude) {
        this.mMagnitude = mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public void setTimeInMilliseconds(long mTimeInMilliseconds) {
        this.mTimeInMilliseconds = mTimeInMilliseconds;
    }
}
