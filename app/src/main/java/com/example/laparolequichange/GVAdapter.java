package com.example.laparolequichange;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class GVAdapter extends ArrayAdapter<Integer> {

    public GVAdapter(@NonNull Context context, ArrayList<Integer> nbreChapitreArrayList) {
        super(context, 0, nbreChapitreArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.item_chapitres, parent, false);
        }

        Integer interger = getItem(position);
        TextView tvItemChapitre = listitemView.findViewById(R.id.tvItemChapitre);


        tvItemChapitre.setText(interger.toString());

        return listitemView;
    }
}
