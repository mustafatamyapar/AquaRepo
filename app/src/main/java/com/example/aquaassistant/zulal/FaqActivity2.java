package com.example.aquaassistant.zulal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.aquaassistant.R;

public class FaqActivity2 extends AppCompatActivity {

    TextView content ;
    TextView questionPanel;
    Intent intent;
    String content1;
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_faq2);
    content = findViewById(R.id.infor);
    questionPanel = findViewById(R.id.questionPanel);
    intent= getIntent();
    Faq selectedContent = (Faq) intent.getSerializableExtra("selectedContent");
    //add them to activity
    content.setText(selectedContent.textContent);
    questionPanel.setText(selectedContent.headQuestion);

}
}
