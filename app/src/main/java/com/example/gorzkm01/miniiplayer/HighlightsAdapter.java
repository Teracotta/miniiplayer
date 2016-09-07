package com.example.gorzkm01.miniiplayer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gorzkm01 on 06/09/2016.
 */
public class HighlightsAdapter extends RecyclerView.Adapter<HighlightsAdapter.ViewHolder> {

    private ArrayList<String> mDataset = new ArrayList();

    private episodesCallbackInterface theListener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View myView;
        public ViewHolder(View v) {
            super(v);
            myView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public HighlightsAdapter(ArrayList myDataset, episodesCallbackInterface theListener) {
        mDataset = myDataset;
        this.theListener = theListener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public HighlightsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        TextView textBlock = (TextView) holder.myView.findViewById(R.id.info_text);
        final String title = mDataset.get(position);
        textBlock.setText(title);

        holder.myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log.d("MyAdapter - function", title);
                theListener.callbackCall(title);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
