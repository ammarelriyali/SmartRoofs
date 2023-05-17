package com.web.app.smartroof.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
import com.web.app.smartroof.Adapter.AdapterRecyclerViewPropertiesToolName;
import com.web.app.smartroof.Adapter.AdapterViewPagesToolName;
import com.web.app.smartroof.R;

import java.util.ArrayList;

public class NameToolActivity extends AppCompatActivity {
    TextView textView;
    ViewPager2 viewPager2;
    AdapterViewPagesToolName AdapterViewPagesToolName;
    DotsIndicator dotsIndicator;
    ImageView arrowRight,arrowLeft;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayList<String> properties = new ArrayList<>();
    RecyclerView recyclerView;
    AdapterRecyclerViewPropertiesToolName adapterRecyclerViewPropertiesToolName;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_tool);
        textView= findViewById(R.id.text_view_number_tool_name);
        viewPager2=findViewById(R.id.viewPager2);
        dotsIndicator =findViewById(R.id.dots_indicator);
        arrowLeft= findViewById(R.id.image_view_arrow_left);
        arrowRight=findViewById(R.id.image_view_arrow_right);
        recyclerView=findViewById(R.id.recyclerview_properties_tool_name);
        tabLayout=findViewById(R.id.tab_layout);

        properties.add("تحتاج جزئي");
        properties.add("تحتاج جزئي");
        properties.add("تحتاج جزئي");
        properties.add("تحتاج جزئي");
        properties.add("نجوم حديقة الربيع ، زهور التوليب هي من بين المصابيح الزهرية الأكثر شعبية ، والتي تقدر بألوانها وأشكالها النابضة بالحياة. تسوق من خلال مجموعتنا الكبيرة من زهور التوليب الهولندية في متجرنا على شبكة الإنترنت ، واشترِ مباشرةً من مخزوننا بأفضل الأسعار");
        adapterRecyclerViewPropertiesToolName=new AdapterRecyclerViewPropertiesToolName(this,properties);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterRecyclerViewPropertiesToolName);



        arrayList.add("Item 1");
        arrayList.add("Item 2");
        arrayList.add("Item 3");
        arrayList.add("Item 4");
        arrayList.add("Item 5");
        AdapterViewPagesToolName = new AdapterViewPagesToolName(this, arrayList);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager2.setAdapter(AdapterViewPagesToolName);
        dotsIndicator.setViewPager2(viewPager2);
        new TabLayoutMediator(tabLayout,viewPager2,(tab, position) -> {}).attach();

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);

            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == arrayList.size() - 1) {
                    arrowRight.setImageResource(R.drawable.icon_arrow_right_wight);
                } else if (position == 0)
                    arrowLeft.setImageResource(R.drawable.icon_arrow_left);
                else{
                    arrowRight.setImageResource(R.drawable.icon_arrow_right);
                    arrowLeft.setImageResource(R.drawable.icon_arrow_left_black);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    public void goHome(View view) {
        finish();
    }

    public void plusItem(View view) {
        int conut=Integer.valueOf(textView.getText().toString());
        Toast.makeText(this, "plus"+conut, Toast.LENGTH_SHORT).show();
        textView.setText(String.valueOf(conut+1));

    }

    public void subItem(View view) {
        int conut=Integer.valueOf(textView.getText().toString());
        Toast.makeText(this, "plus"+conut, Toast.LENGTH_SHORT).show();
        textView.setText(String.valueOf(conut-1));
    }

    public void addToCar(View view) {
        Toast.makeText(this, "addtocar", Toast.LENGTH_SHORT).show();
    }

    public void goBackImage(View view) {
        if (viewPager2.getCurrentItem()==0){


        }else if(viewPager2.getCurrentItem()==1){
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() - 1);
            arrowLeft.setImageResource(R.drawable.icon_arrow_left);

        }else {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() -1);
            arrowRight.setImageResource(R.drawable.icon_arrow_right);

        }


    }

    public void goForwardImage(View view) {
        if (viewPager2.getCurrentItem()==arrayList.size()-2){
            arrowRight.setImageResource(R.drawable.icon_arrow_right_wight);
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }else if(viewPager2.getCurrentItem()==arrayList.size()-1){

        }else {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
            arrowLeft.setImageResource(R.drawable.icon_arrow_left_black);
        }
    }
}