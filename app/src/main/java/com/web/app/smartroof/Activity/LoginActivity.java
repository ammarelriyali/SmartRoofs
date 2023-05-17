package com.web.app.smartroof.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;
import com.web.app.smartroof.R;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {
    CountryCodePicker ccp;
    EditText editText, codeTxt, txtName;
    TextInputLayout textInputLayout;
    TextView textView;
    private FirebaseAuth mAuth;
    PhoneAuthProvider.ForceResendingToken forceResendingTokens;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    String mVerificationId;
    FirebaseAuth firebaseAuth;
    String selected_country_code = "+20";
    String number;
    Button sendCode, done;
    LinearLayout lCode, lNumber;
    ProgressBar progressBar;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ccp = findViewById(R.id.ccp);
        editText = findViewById(R.id.txt_edit_number_login);
        textView = findViewById(R.id.txt_count_code);
        codeTxt = findViewById(R.id.txt_edit_code_login);
        txtName = findViewById(R.id.txt_edit_name_login);
        sendCode = findViewById(R.id.button_send_code);
        done = findViewById(R.id.button_ok);
        progressBar = findViewById(R.id.progressBar5);
        textInputLayout = findViewById(R.id.txt_name_login);

        lCode = findViewById(R.id.linear_code);
        lNumber = findViewById(R.id.linear_number);
        lCode.setVisibility(View.GONE);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mAuth = FirebaseAuth.getInstance();
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
                Toast.makeText(LoginActivity.this, "sus", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    editText.setError("الرقم خطأ");
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                }
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                startCount();
                lNumber.setVisibility(View.GONE);
                sendCode.setVisibility(View.GONE);
                lCode.setVisibility(View.VISIBLE);
                done.setVisibility(View.VISIBLE);
                txtName.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                textInputLayout.setVisibility(View.GONE);
                mVerificationId = s;
                forceResendingTokens = forceResendingToken;
            }
        };


    }


    public void signInBut(View view) {
        if (editText.getText().toString().trim().length() != 10) {
            editText.setError("الرقم خطأ");
        } else if (txtName.getText().toString().isEmpty()) {
            txtName.setError("ادخل اسمك");
        } else {
            progressBar.setVisibility(View.VISIBLE);
            number = selected_country_code + editText.getText().toString().trim();
            sendMassage(number);
        }
    }

    private void sendMassage(String number) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(number)       // Phone number to verify
                        .setTimeout(120L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    public void done(View view) {
        String code = codeTxt.getText().toString().trim();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = task.getResult().getUser();
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(getString(R.string.Key_user_name),txtName.getText().toString());
                            editor.putString(getString(R.string.Key_phone_number),user.getPhoneNumber());
                            editor.apply();
                            finish();
                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                codeTxt.setError("الكود خطأ");
                            }
                        }
                    }
                });
    }

    public void onCountryPickerClick(View view) {
        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                selected_country_code = ccp.getSelectedCountryCodeWithPlus();
            }
        });
    }
    void startCount(){
        new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                textView.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                lNumber.setVisibility(View.VISIBLE);
                sendCode.setVisibility(View.VISIBLE);
                lCode.setVisibility(View.GONE);
                done.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
                txtName.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                textInputLayout.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        }.start();
    }
}