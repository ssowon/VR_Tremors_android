package com.example.juho;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class JoinActivity extends AppCompatActivity {

    private EditText et_id, et_pass, et_name, et_age, et_pass_test;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        et_pass_test = findViewById(R.id.et_pass_test);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);

        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = et_id.getText().toString();
                final String userPass = et_pass.getText().toString();
                final String userPasstest = et_pass_test.getText().toString();
                String userName = et_name.getText().toString();
                int userAge = Integer.parseInt(et_age.getText().toString());

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success && userPass.equals(userPasstest)) {
                                Toast.makeText(getApplicationContext(), "회원 등록에 성공하였습니다.",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(JoinActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                                else {
                                Toast.makeText(getApplicationContext(), "회원 등록에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(userID, userPass, userName, userAge, responseListener);
                RequestQueue queue = Volley.newRequestQueue(JoinActivity.this);
                queue.add(registerRequest);

            }
        });

//        bt_join.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sNM = et_Name.getText().toString();
//                sID = et_ID.getText().toString();
//                sPW = et_PW.getText().toString();
//                sPWchk = et_PWchk.getText().toString();
//
//                if (sPW.equals(sPWchk)){
//                    Intent intent = new Intent(getApplicationContext(), JoinscActivity.class);
//                    startActivity(intent);
//                    /* 데이터베이스에 저장하는 코드// */
//                }
//                else {
//                    Intent intent = new Intent(getApplicationContext(), JoinflActivity.class);
//                    startActivity(intent);
//                }
//            }
//        });
    }
}


