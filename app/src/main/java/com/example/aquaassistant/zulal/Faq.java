package com.example.aquaassistant.zulal;

import java.io.Serializable;

public class Faq implements Serializable {
    String textContent;
    String headQuestion;
    public Faq( String textContent, String headQuestion)
    {
        this.headQuestion= headQuestion;
        this.textContent = textContent;
    }
    public String getQuestion(){
        return headQuestion;
    }

    public String getTextContent() {
        return textContent;
    }
}
