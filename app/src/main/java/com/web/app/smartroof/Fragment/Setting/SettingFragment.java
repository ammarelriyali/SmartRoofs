package com.web.app.smartroof.Fragment.Setting;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.web.app.smartroof.R;

public class SettingFragment extends Fragment {

    LinearLayout linearName,linearNumber,linearSignIn;
    TextView Name, Number, data,logout;
    View view;
    SharedPreferences sharedPreferences;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_setting, container, false);
        linearSignIn = root.findViewById(R.id.linear_sign_in);
        Name=root.findViewById(R.id.text_view_name_setting);
        Number=root.findViewById(R.id.text_view_number_setting);
        data=root.findViewById(R.id.textView4);
        view=root.findViewById(R.id.divider);
        linearName=root.findViewById(R.id.linear_name);
        linearNumber=root.findViewById(R.id.linear_number_sett);
        logout=root.findViewById(R.id.logout);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearSignIn.setVisibility(View.VISIBLE);
                linearNumber.setVisibility(View.GONE);
                linearName.setVisibility(View.GONE);
                data.setVisibility(View.GONE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putString(getString(R.string.Key_user_name),"null");
                editor.putString(getString(R.string.Key_phone_number),"null");
                editor.apply();
            }
        });



        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        String name =sharedPreferences.getString(getString(R.string.Key_user_name),"null");
        String number =sharedPreferences.getString(getString(R.string.Key_phone_number),"null");
        if ((!(name.equals("null"))) && (!(number.equals("null")))){
            linearSignIn.setVisibility(View.GONE);
            linearNumber.setVisibility(View.VISIBLE);
            linearName.setVisibility(View.VISIBLE);
            data.setVisibility(View.VISIBLE);
            Name.setText(name);
            Number.setText(number);
        }
    }
}