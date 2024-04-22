package com.example.quiz_app_demo.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quiz_app_demo.R;
import com.example.quiz_app_demo.model.QuizListModel;
import com.example.quiz_app_demo.model.ResultModel;

import java.util.List;

public class HistoryResultAdapter extends RecyclerView.Adapter<HistoryResultAdapter.ViewHolder> {
    private OnItemClickedListner onItemClickedListner;
    private List<QuizListModel> quizListModels;


    public HistoryResultAdapter(OnItemClickedListner onItemClickedListner) {
        this.onItemClickedListner = onItemClickedListner;
    }

    public void setQuizListModels(List<QuizListModel> quizListModels) {
        this.quizListModels = quizListModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryResultAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.each_quiz_result,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryResultAdapter.ViewHolder holder, int position) {

        if (quizListModels != null) {
            QuizListModel model = quizListModels.get(position);
            holder.title.setText(model.getTitle());
            Glide.with(holder.itemView).load(model.getImage()).into(holder.quizImage);
        }
    }

    @Override
    public int getItemCount() {
        if (quizListModels==null){
            return 0;
        }else{
            return quizListModels.size();
        }
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title, score;
        private ImageView quizImage;
        private ConstraintLayout constraintLayout;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            title=itemView.findViewById(R.id.quizTitleList1);
            quizImage= itemView.findViewById(R.id.imageView1);

            constraintLayout=itemView.findViewById(R.id.constraintLayout);
            constraintLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickedListner.onItemClick(getAdapterPosition());
        }
    }
    public interface OnItemClickedListner {
        void onItemClick(int position);
    }
}
