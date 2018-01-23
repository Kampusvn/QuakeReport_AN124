package kampus.vn;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<EarthQuake> {

    private String LOCATION_SEPARATOR = "of ";

    public EarthquakeAdapter(@NonNull Context context, @NonNull List<EarthQuake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rootView = convertView;
        if (rootView == null) {
            rootView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }

        EarthQuake currentEarthQuake = getItem(position);

        TextView magnitude = rootView.findViewById(R.id.magnitude);
        TextView primaryLocationView = rootView.findViewById(R.id.primary_location);
        TextView locationOffsetView = rootView.findViewById(R.id.location_offset);
        TextView date = rootView.findViewById(R.id.date);
        TextView time = rootView.findViewById(R.id.time);

        //Bind data from View to each item
        magnitude.setText(formatMagnitude(currentEarthQuake.getMagnitude()));
        //Get object magnitude color for display
        MagnitudeColor magnitudeColor = getMagnitudeColor(currentEarthQuake.getMagnitude());
        magnitude.setTextColor(getContext().getResources().getColor(magnitudeColor.getTextColor()));
        magnitude.setBackgroundDrawable(getContext().getResources().getDrawable(magnitudeColor.getCircleDrawable()));

        String originalLocation = currentEarthQuake.getLocation();
        String locationOffset = "";
        String primaryLocation = "";

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_by);
            primaryLocation = originalLocation;
        }

        primaryLocationView.setText(primaryLocation);
        locationOffsetView.setText(locationOffset);

        Date dateObject = new Date(currentEarthQuake.getTimeInMilliseconds());
        date.setText(formatDate(dateObject));
        time.setText(formatTime(dateObject));
        return rootView;
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private MagnitudeColor getMagnitudeColor(double magnitude) {
        int mag = (int) Math.floor(magnitude);
        MagnitudeColor magColor = new MagnitudeColor(R.color.colorRed, R.drawable.red_circle);
        switch (mag) {
            case 0:
            case 1:
            case 2:
                magColor.setTextColor(R.color.colorGreen);
                magColor.setCircleDrawable(R.drawable.green_circle);
                break;
            case 3:
            case 4:
                magColor.setTextColor(R.color.colorBlue);
                magColor.setCircleDrawable(R.drawable.blue_circle);
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                magColor.setTextColor(R.color.colorRed);
                magColor.setCircleDrawable(R.drawable.red_circle);
                break;
            default:
                break;

        }
        return magColor;
    }

    class MagnitudeColor {
        private int textColor;
        private int circleDrawable;

        public MagnitudeColor(int textColor, int circleDrawable) {
            this.textColor = textColor;
            this.circleDrawable = circleDrawable;
        }

        public int getTextColor() {
            return textColor;
        }

        public void setTextColor(int textColor) {
            this.textColor = textColor;
        }

        public int getCircleDrawable() {
            return circleDrawable;
        }

        public void setCircleDrawable(int circleDrawable) {
            this.circleDrawable = circleDrawable;
        }
    }
}
