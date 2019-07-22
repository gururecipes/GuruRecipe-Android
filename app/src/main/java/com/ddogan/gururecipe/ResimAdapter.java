package com.ddogan.gururecipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 20.07.2019.
 */

public class ResimAdapter extends BaseAdapter {

    List<Resim> resimler = new ArrayList<>();
    LayoutInflater layoutInflater;
    Context context;

    public ResimAdapter(Context context, List<Resim> resimlerListe) {
        this.context = context;
        this.resimler = resimlerListe;
    }


    @Override
    public int getCount() {
        return resimler.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater = LayoutInflater.from(context);
        View resim = layoutInflater.inflate(R.layout.resimler, null);
        ImageView tarifResim = (ImageView) resim.findViewById(R.id.resimTarif);
        Resim r = resimler.get(position);
        Picasso.with(context).load(r.getUrl()).into(tarifResim);
        return resim;
    }
}
