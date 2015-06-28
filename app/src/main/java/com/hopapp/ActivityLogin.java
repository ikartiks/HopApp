package com.hopapp;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.hopapp.pojos.VUser;
import com.hopapp.utility.Constant;
import com.hopapp.utility.FileUtil;
import com.hopapp.utility.LoggerGeneral;
import com.hopapp.utility.RestTemplateAsyncTask;
import com.hopapp.utility.StringUtil;
import com.kartiks.ui.CustomButtonView;
import com.kartiks.ui.CustomEditText;

import org.springframework.http.HttpMethod;

public class ActivityLogin extends ActivityBase implements View.OnClickListener{

    CustomEditText name,number,email,password,countryCode;
    CustomButtonView submit;

    Activity activity=this;
    Context context=this;

    Resources resources;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_login);

        Toolbar toolbar= (Toolbar) findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);

        resources=getResources();

        showLoader();

        name        = (CustomEditText) findViewById(R.id.Name);
        number      = (CustomEditText) findViewById(R.id.Number);
        email       = (CustomEditText) findViewById(R.id.Email);
        password    = (CustomEditText) findViewById(R.id.Password);
        countryCode = (CustomEditText) findViewById(R.id.CountryCode);
        submit      = (CustomButtonView) findViewById(R.id.Submit);
        submit.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_activity_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        if(v==submit){

            String namex=name.getText().toString();
            if(namex.isEmpty()){
                showToast("Name cannot be empty");
            }

            String emailx=email.getText().toString();
            if(!StringUtil.isValidEmailAddress(emailx)){
                showCustomMessage("enter a valid email address");
                return;
            }

            String passwordx=password.getText().toString();
            if (passwordx.length()<5){
                showToast("password must be atleast 5 characters");
                return;
            }
            String countryCodex=countryCode.getText().toString();
            if(countryCodex.isEmpty()||countryCodex.length()!=2){
                showToast("Enter a valid country code");
                return;
            }
            String numberx=number.getText().toString();
            if(numberx.isEmpty()||numberx.length()!=10){
                showToast("Enter a valid no");
                return;
            }

            countryCodex+=numberx;
            VUser user=new VUser(emailx,passwordx,namex,countryCodex);


            if(isConnected())
                new RestTemplateAsyncTask(activity, Constant.createUserUrl).execute(HttpMethod.POST,user,VUser.class);
            else
                showToast(resources.getString(R.string.NoNet));
        }
    }

    public void createUserCallBack(VUser user){

        FileUtil fileUtil=new FileUtil(context);
        LoggerGeneral.info(user.toString());
        fileUtil.writeObject(user,Constant.createUserUrl);
    }

}
