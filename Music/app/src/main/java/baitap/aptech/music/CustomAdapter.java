package baitap.aptech.music;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by D'van on 1/15/2017.
 */

public class CustomAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private int mResource;
    private String[] mSongs;
    private String[] mSingers;
    private int[] mImages;

    public CustomAdapter(Context context, int resource, String[] songs, String[] singers, int[] images) {
        super(context, resource, songs);

        this.mContext = context;
        this.mResource = resource;
        this.mSongs = songs;
        this.mSingers = singers;
        this.mImages = images;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
        }

        ImageView icon = (ImageView) convertView.findViewById(R.id.mIvCaSi);
        TextView song = (TextView) convertView.findViewById(R.id.mTvTenBaiHat);
        TextView singer = (TextView) convertView.findViewById(R.id.mTvTenCaSi);


        icon.setBackgroundResource(mImages[position]);
        song.setText(mSongs[position]);
        singer.setText(mSingers[position]);

        return convertView;

    }
}
