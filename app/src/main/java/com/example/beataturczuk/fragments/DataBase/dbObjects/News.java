package com.example.beataturczuk.fragments.DataBase.dbObjects;

/**
 * Created by beataturczuk on 27.04.15.
 */
public class News {

    public String id;
    public String type;
    public String created;
    public String published;
    public String comment_count;
    public String user_id;
    public String source;
    public String image;
    public String title;
    public String body;

    public News(String i, String t, String c, String p,
                String cc, String u, String s, String im, String tit, String b) {

        this.id = i;
        this.type = t;
        this.created = c;
        this.published = p;
        this.comment_count = cc;
        this.user_id = u;
        this.source = s;
        this.image = im;
        this.title = tit;
        this.body = b;
    }


}
