package contactmanagement.learn.aptech.contactmanagement.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import contactmanagement.learn.aptech.contactmanagement.R;
import contactmanagement.learn.aptech.contactmanagement.model.Contact;

/**
 * Created by D'van on 1/17/2017.
 */

public class ContactAdapter extends ArrayAdapter<Contact> {
    private Context mContext;
    private int mResource;
    //    private int[] mImages;
//    private String[] mNames;
//    private String[] mPhones;
    private ArrayList<Contact> mList;

    public ContactAdapter(Context context, int resource, ArrayList<Contact> list) {
        super(context, resource,list);

        mContext = context;
        mResource = resource;
        mList = list;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(mResource, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvHoTen);
            viewHolder.tvPhone = (TextView) convertView.findViewById(R.id.tvPhone);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Contact contact = mList.get(position);

        viewHolder.tvName.setText(contact.getName());
        viewHolder.tvPhone.setText(contact.getPhone());

        return convertView;

    }

    private class ViewHolder {
        public TextView tvName, tvPhone;
    }
}
