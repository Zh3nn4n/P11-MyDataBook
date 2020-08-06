package com.example.p11_mydatabook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<Drawer> {
    Context context;
    ArrayList<Drawer> drawers;
    int resource;
    ImageView iv;
    TextView tvName;

    public ListViewAdapter(Context context, int resource, ArrayList<Drawer> drawers) {
        super(context, resource, drawers);
        this.context = context;
        this.drawers = drawers;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row, parent, false);

        //Match the UI components with Java variables
        tvName = (TextView)rowView.findViewById(R.id.textViewDrawer);
        iv = (ImageView)rowView.findViewById(R.id.imageViewIcon);


        Drawer current = drawers.get(position);
        String name = current.getName();
        tvName.setText(name);

        if (name.equalsIgnoreCase("Anniversary")){
            iv.setImageResource(R.drawable.anniversary);
        } else if (name.equalsIgnoreCase("Bio")){
            iv.setImageResource(R.drawable.bio);
        } else if (name.equalsIgnoreCase("About")){
            iv.setImageResource(R.drawable.about);
        } else if (name.equalsIgnoreCase("Vaccination")){
            iv.setImageResource(R.drawable.vaccination);
        }


        return rowView;
    }
}
