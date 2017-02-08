package dvan.ListView.Aptech.com;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by D'van on 1/12/2017.
 */

public class CustomAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private int mResource;
    private String[] mSubject;
    private int[] mIcons;


    public CustomAdapter(Context context, int resource, String[] mSubject, int[] mIcons) {
        super(context, resource, mSubject);
        this.mContext = context;
        this.mResource = resource;
        this.mSubject = mSubject;
        this.mIcons = mIcons;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
        }

        ImageView icon = (ImageView) convertView.findViewById(R.id.ivIcon);
        TextView text = (TextView) convertView.findViewById(R.id.tvText);

        icon.setBackgroundResource(mIcons[position]);
        text.setText(mSubject[position]);

        return convertView;
    }
}
