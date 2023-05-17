package com.web.app.smartroof.Fragment.Home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.web.app.smartroof.API.Api;
import com.web.app.smartroof.API.Model.Category;
import com.web.app.smartroof.API.RetrofitBuilder;
import com.web.app.smartroof.Adapter.AdapterRecyclerviewCycleHome;
import com.web.app.smartroof.Adapter.AdapterRecyclerviewLinearHome;
import com.web.app.smartroof.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.MyLinearLayoutManager;

public class HomeFragment extends Fragment {
    RecyclerView recyclerViewCycleHome;
    RecyclerView recyclerViewLinearHome;
    Api api;
    ArrayList<Category> categories ;
    ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerViewCycleHome = root.findViewById(R.id.recyclerview_cycle_home);
        api= RetrofitBuilder.getRetrofitInstance().create(Api.class);
        Call< ArrayList<Category> >  call = api.getCategory();
        progressBar=root.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                if (!(response.body() == null)) {
                    categories = response.body();
                    List<Category> categorieslist;
                    if (categories.size()<6){
                        categorieslist = categories.subList(0, categories.size());
                    }
                    else {
                        categorieslist = categories.subList(0, 6);
                    }
                    AdapterRecyclerviewCycleHome adapterRecyclerviewCycleHome = new AdapterRecyclerviewCycleHome(getContext(), categorieslist);
                    recyclerViewCycleHome.setAdapter(adapterRecyclerviewCycleHome);
                    recyclerViewCycleHome.setLayoutManager(new GridLayoutManager(getContext(), 3));
                    progressBar.setVisibility(View.GONE);
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
                try {
                    Toast.makeText(getContext(), "check your Network", Toast.LENGTH_SHORT).show();
                }catch (Exception exception){
                    Log.i("TAG", "onFailure: "+exception.getMessage());
                }
                    progressBar.setVisibility(View.GONE);
            }
        });

        recyclerViewLinearHome = root.findViewById(R.id.recyclerview_linear_news_home);

//        ArrayList<News>SubjectHome = new ArrayList<>();
//        SubjectHome.add(new News("عمار الذي اكل الحمار والخيار وفتحي ","Tuesday, November 9, 2021","المقالة نوع من الأدب، هي قطعة إنشائية، ذات طول معتدل تُكتب نثراً، وتُهتمُّ بالمظاهر الخارجية للموضوع بطريقة سهلةٍ سريعة، ولا تعنى إلا بالناحية التي تمسُّ الكاتب عن قرب. رأى النور في عصر النهضة الأوروبية، واتخذ مفهومه من محاولات التي أطلق عليها اسم Essais، و\"الفصل\" (صيد الخاطر) كما عرفه العرب أقدم رائد للمقالة في الآداب العالمية، ذلك أن الفصل في الأدب العربي قد ظهر قبل ظهور مقالات مونتاني إمام هذا الفن غير مدافع بين الأوروبيين، فقد ظهر فن المقالة لأول مرة في فرنسا سنة 1571م، ثم ظهر بعد ذلك ببضع عشرة سنة في كتابات "));
//        SubjectHome.add(new News("عمار الذي اكل الحمار والخيار وفتحي ","Tuesday, November 9, 2021","المقالة نوع من الأدب، هي قطعة إنشائية، ذات طول معتدل تُكتب نثراً، وتُهتمُّ بالمظاهر الخارجية للموضوع بطريقة سهلةٍ سريعة، ولا تعنى إلا بالناحية التي تمسُّ الكاتب عن قرب. رأى النور في عصر النهضة الأوروبية، واتخذ مفهومه من محاولات التي أطلق عليها اسم Essais، و\"الفصل\" (صيد الخاطر) كما عرفه العرب أقدم رائد للمقالة في الآداب العالمية، ذلك أن الفصل في الأدب العربي قد ظهر قبل ظهور مقالات مونتاني إمام هذا الفن غير مدافع بين الأوروبيين، فقد ظهر فن المقالة لأول مرة في فرنسا سنة 1571م، ثم ظهر بعد ذلك ببضع عشرة سنة في كتابات "));
//        SubjectHome.add(new News("عمار الذي اكل الحمار والخيار وفتحيتسييسبتمنسيب سيتمبىسشميابمنشسيتبشسي سبينتبتمشسنبتشسخ ","Tuesday, November 9, 2021","المقالة نوع من الأدب، هي قطعة إنشائية، ذات طول معتدل تُكتب نثراً، وتُهتمُّ بالمظاهر الخارجية للموضوع بطريقة سهلةٍ سريعة، ولا تعنى إلا بالناحية التي تمسُّ الكاتب عن قرب. رأى النور في عصر النهضة الأوروبية، واتخذ مفهومه من محاولات التي أطلق عليها اسم Essais، و\"الفصل\" (صيد الخاطر) كما عرفه العرب أقدم رائد للمقالة في الآداب العالمية، ذلك أن الفصل في الأدب العربي قد ظهر قبل ظهور مقالات مونتاني إمام هذا الفن غير مدافع بين الأوروبيين، فقد ظهر فن المقالة لأول مرة في فرنسا سنة 1571م، ثم ظهر بعد ذلك ببضع عشرة سنة في كتابات "));
//        SubjectHome.add(new News("عمار الذي اكل الحمار والخيار وفتحي ","Wednesday, August 25, 2021","المقالة نوع من الأدب، هي قطعة إنشائية، ذات طول معتدل تُكتب نثراً، وتُهتمُّ بالمظاهر الخارجية للموضوع بطريقة سهلةٍ سريعة، ولا تعنى إلا بالناحية التي تمسُّ الكاتب عن قرب. رأى النور في عصر النهضة الأوروبية، واتخذ مفهومه من محاولات التي أطلق عليها اسم Essais، و\"الفصل\" (صيد الخاطر) كما عرفه العرب أقدم رائد للمقالة في الآداب العالمية، ذلك أن الفصل في الأدب العربي قد ظهر قبل ظهور مقالات مونتاني إمام هذا الفن غير مدافع بين الأوروبيين، فقد ظهر فن المقالة لأول مرة في فرنسا سنة 1571م، ثم ظهر بعد ذلك ببضع عشرة سنة في كتابات "));
//        SubjectHome.add(null);
//        AdapterRecyclerviewLinearHome adapterRecyclerviewLinearHomeHome =
//                new AdapterRecyclerviewLinearHome(getContext(),SubjectHome);
//        MyLinearLayoutManager linearLayoutManager= new MyLinearLayoutManager(getContext());
//        recyclerViewLinearHome.setLayoutManager(linearLayoutManager);
//        recyclerViewLinearHome.setAdapter(adapterRecyclerviewLinearHomeHome);
//        recyclerViewLinearHome.stopScroll();
//        recyclerViewLinearHome.stopNestedScroll();
        return root;

    }

}