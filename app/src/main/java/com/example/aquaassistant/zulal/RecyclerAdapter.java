package com.example.aquaassistant.zulal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aquaassistant.R;

import java.util.ArrayList;
/**
 *This adapter helps for using recyclerView in FAQ class. In this adapter,
 * questions and their answers are listed
 * @author Zülal Nur Hıdıroğlu
 * @version 1.0 (April 30, 2020) - completed
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.PostHolder> {
private ArrayList<String> questionList;
private ArrayList<String> contentList;

public RecyclerAdapter(ArrayList<String> questionList, ArrayList<String> contentList) {
    this.questionList = questionList;
    this.contentList = contentList;

}

@NonNull
@Override
public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
    View view = layoutInflater.inflate(R.layout.recycler_view,parent,false);
    return new PostHolder(view);
}

@Override
public void onBindViewHolder(@NonNull PostHolder holder, int position) {
    holder.questionText.setText(questionList.get(position));
    holder.contentText.setText(contentList.get(position));


}


@Override
public int getItemCount() {
    return questionList.size();
}
    /**
     * This inner class initialize the tools which are used in layouts of the Faqactivity
     */
class PostHolder extends RecyclerView.ViewHolder {
    TextView questionText;
    TextView contentText;

    public PostHolder(@NonNull View itemView) {
        super(itemView);
        questionText = itemView.findViewById(R.id.recycler_view_question);
        contentText = itemView.findViewById(R.id.recycler_view_content);
    }
    //Z
}
}
