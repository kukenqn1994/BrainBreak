package vlth.brainbreak.Adapter;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import vlth.brainbreak.Model.ItemGame;
import vlth.brainbreak.R;

public class ListGameAdapter extends ArrayAdapter<ItemGame> {

    Context context;
    int[] imgId;

    public ListGameAdapter(Context context, int resourceId,
                                 List<ItemGame> items, int[] values) {
        super(context, resourceId, items);
        this.context = context;
        imgId = values;
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        TextView txtDesc;
        TextView txtBestScore;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        ItemGame rowItem = getItem(position);
        int img = imgId[position];

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.game_row, null);
            holder = new ViewHolder();
//            holder.txtDesc = (TextView) convertView.findViewById(R.id.desc);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.title);
            holder.txtBestScore = (TextView) convertView.findViewById(R.id.best_score);
            holder.imageView = (ImageView) convertView.findViewById(R.id.list_image);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/baisau.TTF");
        holder.txtBestScore.setTypeface(font);
        holder.txtTitle.setTypeface(font);
        holder.txtBestScore.setText("Best score: " + rowItem.getBest_score());
        holder.txtTitle.setText(rowItem.getTitle());
        holder.imageView.setImageResource(img);

        return convertView;
    }

}
