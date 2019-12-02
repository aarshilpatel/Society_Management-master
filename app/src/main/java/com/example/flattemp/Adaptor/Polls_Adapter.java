package com.example.flattemp.Adaptor;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flattemp.Model.Polls_Model;
import com.example.flattemp.R;

import java.util.List;

public class Polls_Adapter extends PagerAdapter {
    List<Polls_Model> list;
    Context context;
    LayoutInflater inflater;



    public Polls_Adapter(Context context, List<Polls_Model> list) {
        this.context=context;
        this.list=list;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((View)object);



    }
    @Override
    public Object instantiateItem(final ViewGroup container, int position) {
        final View convertView = this.inflater.inflate(R.layout.item_poll, container, false);
        TextView question=convertView.findViewById(R.id.text_view_question);
        RadioButton ans1=convertView.findViewById(R.id.radio1);
        RadioButton ans2=convertView.findViewById(R.id.radio2);
        RadioButton ans3=convertView.findViewById(R.id.radio3);
        RadioButton ans4=convertView.findViewById(R.id.radio4);
        //   final TextView score=convertView.findViewById(R.id.score);

        //   score.setText(String.valueOf(sc));

  //      final Button button = convertView.findViewById(R.id.button1);
        final RadioGroup radioGroup = convertView.findViewById(R.id.radioGroup);
        Polls_Model model=list.get(position);
        question.setText(model.getSubject());
        //  a.setText(model.getAns());
        ans1.setText(model.getAns1());
        ans2.setText(model.getAns2());
        ans3.setText(model.getAns3());
        ans4.setText(model.getAns4());

        //final String ans=model.getAns();


        final View finalConvertView = convertView;
       /* button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton r1=(RadioButton) finalConvertView.findViewById(selectedId);
                if (selectedId== -1) {
                    Toast.makeText(context,"Nothing selected",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (r1.getText().toString().equals(ans)){
                        sc++;
                        button.setVisibility(View.GONE);
                        r1.setTextColor(Color.GREEN);
                        //   score.setText(String.valueOf(sc));
                        Toast.makeText(context, String.valueOf(sc), Toast.LENGTH_SHORT).show();

                    }else {
                        button.setVisibility(View.GONE);
                        r1.setTextColor(Color.RED);

                    }
                    // Toast.makeText(context,r1.getText(),Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        container.addView(convertView);

        /*displayImage.setImageResource(this.dataObjectList.get(position).getImageId());
        imageText.setText(this.dataObjectList.get(position).getImageName());*/


        return convertView;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);



    }


}
