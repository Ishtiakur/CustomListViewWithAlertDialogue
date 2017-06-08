package com.example.ishtiak.listview;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    String name[] = {"ishtiak", "sujon", "rayhan"};
    int image[] = {R.drawable.picture, R.drawable.pic, R.drawable.unnamed,
            R.drawable.icon_hammer_2, R.drawable.unnamed,
            R.drawable.icon_hammer_small, R.drawable.logo,
            R.drawable.logo2, R.drawable.nav_back_2};
    Context cn;
    List <String>nameArray = new ArrayList();
    List <Integer>imageArray = new ArrayList();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        cn = this;
        for (int i=0; i<10; i++){
            for (int j=0 ; j<image.length && j<name.length; j++){
                nameArray.add(name[j]);
                imageArray.add(image[j]);
            }
        }
        listView.setAdapter(new CustomAdpater(cn, nameArray, imageArray));

    }
}
class  CustomAdpater extends  ArrayAdapter{
     List<String > name;
     List <Integer>image;
     Context contex;
    public CustomAdpater(@NonNull Context context, List name, List image) {
        super(context, R.layout.custom_layout, image);
        this.name = name;
        this.image = image;
        this.contex  = context;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView==null){
            LayoutInflater inflater = (LayoutInflater) contex.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_layout, parent, false);

        }
        ImageView imageView = (ImageView) convertView.findViewById(R.id.tv_img);
        TextView textView = (TextView) convertView.findViewById(R.id.textView);
        imageView.setImageResource(image.get(position));
        textView.setText(name.get(position));

        return convertView;
    }


}