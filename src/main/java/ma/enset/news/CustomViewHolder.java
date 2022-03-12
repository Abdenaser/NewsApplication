package ma.enset.news;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {
    TextView txt_source,txt_title;
    ImageView imgHeadline;
    CardView cardView;
    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        txt_title=itemView.findViewById(R.id.text_Title);
        txt_source=itemView.findViewById(R.id.Text_Source);
        imgHeadline=itemView.findViewById(R.id.Image_headline);
        cardView=itemView.findViewById(R.id.main_container);
    }
}
