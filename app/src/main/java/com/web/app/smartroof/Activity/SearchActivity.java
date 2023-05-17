package com.web.app.smartroof.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.web.app.smartroof.Adapter.AdapterRecyclerviewFavoirte;
import com.web.app.smartroof.R;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    String txt;
    boolean isArticlesSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        txt=getIntent().getStringExtra("search_txt");
        isArticlesSearch=getIntent().getBooleanExtra("isArticle",false);

        TextView textView=findViewById(R.id.text_search_activity);
        textView.setText(txt);
        RecyclerView recyclerView;
        recyclerView= findViewById(R.id.recyclerview_search_activity);
//        ArrayList<News> SubjectHome = new ArrayList<>();
//        SubjectHome.add(new News("عمار الذي اكل الحمار والخيار وفتحي ", "Tuesday, November 9, 2021", "المقالة نوع من الأدب، هي قطعة إنشائية، ذات طول معتدل تُكتب نثراً، وتُهتمُّ بالمظاهر الخارجية للموضوع بطريقة سهلةٍ سريعة، ولا تعنى إلا بالناحية التي تمسُّ الكاتب عن قرب. رأى النور في عصر النهضة الأوروبية، واتخذ مفهومه من محاولات التي أطلق عليها اسم Essais، و\"الفصل\" (صيد الخاطر) كما عرفه العرب أقدم رائد للمقالة في الآداب العالمية، ذلك أن الفصل في الأدب العربي قد ظهر قبل ظهور مقالات مونتاني إمام هذا الفن غير مدافع بين الأوروبيين، فقد ظهر فن المقالة لأول مرة في فرنسا سنة 1571م، ثم ظهر بعد ذلك ببضع عشرة سنة في كتابات "));
//        SubjectHome.add(new News("عمار الذي اكل الحمار والخيار وفتحي ", "Tuesday, November 9, 2021", "المقالة نوع من الأدب، هي قطعة إنشائية، ذات طول معتدل تُكتب نثراً، وتُهتمُّ بالمظاهر الخارجية للموضوع بطريقة سهلةٍ سريعة، ولا تعنى إلا بالناحية التي تمسُّ الكاتب عن قرب. رأى النور في عصر النهضة الأوروبية، واتخذ مفهومه من محاولات التي أطلق عليها اسم Essais، و\"الفصل\" (صيد الخاطر) كما عرفه العرب أقدم رائد للمقالة في الآداب العالمية، ذلك أن الفصل في الأدب العربي قد ظهر قبل ظهور مقالات مونتاني إمام هذا الفن غير مدافع بين الأوروبيين، فقد ظهر فن المقالة لأول مرة في فرنسا سنة 1571م، ثم ظهر بعد ذلك ببضع عشرة سنة في كتابات "));
//        SubjectHome.add(new News("عمار الذي اكل الحمار والخيار وفتحيتسييسبتمنسيب سيتمبىسشميابمنشسيتبشسي سبينتبتمشسنبتشسخ ", "Tuesday, November 9, 2021", "المقالة نوع من الأدب، هي قطعة إنشائية، ذات طول معتدل تُكتب نثراً، وتُهتمُّ بالمظاهر الخارجية للموضوع بطريقة سهلةٍ سريعة، ولا تعنى إلا بالناحية التي تمسُّ الكاتب عن قرب. رأى النور في عصر النهضة الأوروبية، واتخذ مفهومه من محاولات التي أطلق عليها اسم Essais، و\"الفصل\" (صيد الخاطر) كما عرفه العرب أقدم رائد للمقالة في الآداب العالمية، ذلك أن الفصل في الأدب العربي قد ظهر قبل ظهور مقالات مونتاني إمام هذا الفن غير مدافع بين الأوروبيين، فقد ظهر فن المقالة لأول مرة في فرنسا سنة 1571م، ثم ظهر بعد ذلك ببضع عشرة سنة في كتابات "));
//        SubjectHome.add(new News("عمار الذي اكل الحمار والخيار وفتحي ", "Wednesday, August 25, 2021", "المقالة نوع من الأدب، هي قطعة إنشائية، ذات طول معتدل تُكتب نثراً، وتُهتمُّ بالمظاهر الخارجية للموضوع بطريقة سهلةٍ سريعة، ولا تعنى إلا بالناحية التي تمسُّ الكاتب عن قرب. رأى النور في عصر النهضة الأوروبية، واتخذ مفهومه من محاولات التي أطلق عليها اسم Essais، و\"الفصل\" (صيد الخاطر) كما عرفه العرب أقدم رائد للمقالة في الآداب العالمية، ذلك أن الفصل في الأدب العربي قد ظهر قبل ظهور مقالات مونتاني إمام هذا الفن غير مدافع بين الأوروبيين، فقد ظهر فن المقالة لأول مرة في فرنسا سنة 1571م، ثم ظهر بعد ذلك ببضع عشرة سنة في كتابات "));
//        AdapterRecyclerviewFavoirte adapterRecyclerviewFavoirte = new AdapterRecyclerviewFavoirte(SubjectHome);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapterRecyclerviewFavoirte);

    }

    @Override
    public void onBackPressed() {
        Intent intent =new Intent(this,HomeActivity.class);
        intent.putExtra("search_txt",txt);
        intent.putExtra("isArticle",isArticlesSearch);
        intent.putExtra("isSearch",true);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    public void goBackDialog(View view) {
        Intent intent =new Intent(this,HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);


    }

    public void backSearch(View view) {
        Intent intent =new Intent(this,HomeActivity.class);
        intent.putExtra("search_txt",txt);
        intent.putExtra("isArticle",isArticlesSearch);
        intent.putExtra("isSearch",true);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
}