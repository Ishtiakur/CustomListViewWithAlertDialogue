package com.example.ishtiak.listview;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.resource;
public class ListActivity extends AppCompatActivity {
    Context cn ;
    String name [] = {"Ishtiak","tajul","saif","rahman","shuvojit","Rupa's Lover"};
    int image[] = {R.drawable.picture,
            R.drawable.pic, R.drawable.unnamed,
            R.drawable.icon_hammer_2,
            R.drawable.unnamed,
            R.drawable.icon_hammer_small,
            R.drawable.logo,
            R.drawable.logo2,
            R.drawable.nav_back_2};
    String[] phn_number = {"01703490802","01703490802","01703490802","01762741757","0176223359", "01762741757"};

    List<String>  nameArray = new ArrayList();
    List<Integer> imageArray = new ArrayList();
    List<String> phn = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ListView listView = (ListView) findViewById(R.id.lv);
        cn = this;
        for (int i =0; i<=10; i++){
            for (int j=0; j<name.length && j< image.length && j<phn_number.length; j++){
                nameArray.add(name[j]);
                imageArray.add(image[j]);
                phn.add(phn_number[j]);
            }
        }
        listView.setAdapter(new myAdapter(this, nameArray, imageArray, phn));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog alertDialog = new AlertDialog.Builder(cn).create();
                alertDialog.setTitle("Attention");
                alertDialog.setMessage("Are you sure you want to call this guy :)");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phn.get(position))));
                                dialog.dismiss(); }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();

            }
        });
    }
}

    class myAdapter extends ArrayAdapter{
        List<String> mName;
        List<Integer> mImage;
        List mPhn;
        Context con;

        public myAdapter( Context context, List name, List image, List phn ) {
            super(context, 0,name);
            this.mName = name;
            this.mImage = image;
            this.mPhn = phn;
            this.con = context;

        }

        @Override
        public View getView(int position, View convertView,  ViewGroup parent) {
         if  (convertView==null){
               LayoutInflater layoutInflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
               convertView = layoutInflater.inflate(R.layout.custom_layout, parent, false);
          }
            ImageView imageView = (ImageView) convertView.findViewById(R.id.tv_img);
            TextView textView = (TextView) convertView.findViewById(R.id.textView);
            textView.setText(mName.get(position));
            ((TextView) convertView.findViewById(R.id.tv_phn)).setText(mPhn.get(position).toString());
            imageView.setImageResource(mImage.get(position));
            Log.i("GetView Methood","Getview Method call ");
            Toast.makeText(con, "Position : "+ position, Toast.LENGTH_SHORT).show();
            return convertView;
        }
    }
