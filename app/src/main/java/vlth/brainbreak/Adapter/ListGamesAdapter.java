package vlth.brainbreak.Adapter;

/**
 * Created by Administrator on 11/6/2015.
 */
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vlth.brainbreak.Model.ItemGame;
import vlth.brainbreak.R;

public class ListGamesAdapter extends RecyclerView.Adapter<ListGamesAdapter.RecyclerViewHolders>{

    private List<ItemGame> itemList;
    private Context context;
    private static MyClickListener myClickListener;

    public ListGamesAdapter(Context context, List<ItemGame> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_row, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/Comic.ttf");
        Typeface font2 = Typeface.createFromAsset(context.getAssets(), "fonts/BradBun.ttf");
        holder.title.setTypeface(font2);
        holder.best_score.setTypeface(font);
        holder.tut.setTypeface(font);

        holder.title.setText(itemList.get(position).getTitle());
        holder.best_score.setText("Best: " + itemList.get(position).getBest_score());
        holder.tut.setText(itemList.get(position).getTutorial());
        holder.cover.setImageResource(itemList.get(position).getCover());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }



    public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView title;
        public TextView best_score;
        public TextView tut;
        public ImageView cover;
        public RecyclerViewHolders(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
            best_score = (TextView)itemView.findViewById(R.id.best_score);
            tut = (TextView)itemView.findViewById(R.id.desc);
            cover = (ImageView)itemView.findViewById(R.id.list_image);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getPosition(), v);
        }
    }
    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }
    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

}