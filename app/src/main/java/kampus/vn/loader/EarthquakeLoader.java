package kampus.vn.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.List;

import kampus.vn.EarthQuake;
import kampus.vn.QueryUtils;

/**
 * Created by TanNT on 12/23/17.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<EarthQuake>> {
    private String TAG = "EarthquakeLoader";

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link EarthquakeLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.e(TAG, "onStartLoading");
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<EarthQuake> loadInBackground() {
        Log.e(TAG, "loadInBackground");
        if (mUrl == null)
            return null;
        List<EarthQuake> earthQuakes = QueryUtils.fetchEarthquakeData(mUrl);
        return earthQuakes;
    }
}
