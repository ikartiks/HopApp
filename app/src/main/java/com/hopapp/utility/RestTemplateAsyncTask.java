package com.hopapp.utility;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.hopapp.ActivityBase;
import com.hopapp.ActivityLogin;
import com.hopapp.pojos.VString;
import com.hopapp.pojos.VUser;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kartik on 22/6/15.
 */
public class RestTemplateAsyncTask extends AsyncTask<Object,Integer,Object>{

    //Sample examples
    //new RestAsyncTask(activity).execute(Constants.getBaseUrl()+"test/greeting", HttpMethod.DELETE,greeting,null);
    //new RestAsyncTask(activity).execute(Constants.getBaseUrl()+"test/greeting", HttpMethod.PUT,greeting,Greeting.class);
    //new RestAsyncTask(activity).execute(Constants.getBaseUrl()+"test/greeting", HttpMethod.GET,null,Greeting.class);


    //    FileSystemResource fileSystemResource= new FileSystemResource("/storage/sdcard/Download/ic_launcher.png");
    //    MultiValueMap<String, Object> p = new LinkedMultiValueMap<String, Object>();
    //    p.add("file", fileSystemResource);
    //    p.add("name", "it works");

    Activity activity;
    String url;

    public RestTemplateAsyncTask(Activity activity,String url) {

        this.activity=activity;
        this.url=url;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(activity instanceof ActivityBase){
            ((ActivityBase)activity).showLoader();
        }
    }


    /*
    *
    * @params 0 HttpMethod
    * @params 1 Object to pass to server, NULLABLE
    * @params 2 Class of object to receive from server, NULLABLE
    *
    * */
    @Override
    protected Object doInBackground(Object[] params) {

        HttpMethod method= (HttpMethod) params[0];
        Object objectToPass=params[1];
        Class returnObjectClass= (Class) params[2];

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        if(objectToPass instanceof MultiValueMap)
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        //rest template adds content length to 2 bytes by default, so it gives below error
        // content-length promised 2 bytes, but received 0
        //to remove that when delete request is made ,we do following
        if(returnObjectClass==null)
            headers.add("Content-Length", "0");


        //this is to convert timestamp to date object
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {

            @Override
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {

                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String date = json.getAsJsonPrimitive().getAsString();
                try {
                    return format.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });

        GsonHttpMessageConverter jsonConverter = new GsonHttpMessageConverter();
        jsonConverter.setGson(builder.create());


        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());//kuch toh hoga iska bhi
        restTemplate.getMessageConverters().add(jsonConverter);// for normal java objects passed in entity
        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());//for multipart/form data

        restTemplate.setErrorHandler(new MyErrorResponseHandler());

        HttpEntity httpEntity =new HttpEntity(objectToPass,headers);
        ResponseEntity<?> response = restTemplate.exchange(url, method, httpEntity,returnObjectClass);

        if(response.hasBody()){

            Object responseBody = response.getBody();
            LoggerGeneral.info(" ---------------------- " );
            LoggerGeneral.info(url +" " + responseBody);
            return  responseBody;
        }
        return null;

    }

    public class MyErrorResponseHandler implements ResponseErrorHandler {

        @Override
        public void handleError(ClientHttpResponse response) throws IOException {

            LoggerGeneral.e("response is " + response.getStatusCode() + " " + response.getStatusText());
            String responseString= IOUtils.toString(response.getBody(), "UTF-8");

            LoggerGeneral.e("error resposne is "+responseString);
            Gson gson = new GsonBuilder().create();
            VString x = gson.fromJson(responseString, VString.class);

            //since data is still executing in background thread, so call this
            //and we cant even call runOnUIThread ,or run showToast everytime on UI Thread
            Looper.prepare();
            ((ActivityBase) activity).showToast(x.getValue());
            Looper.loop();

            return;//PS this wont throw IO Exception,never ever remove this
        }

        @Override
        public boolean hasError(ClientHttpResponse response) throws IOException {

            if ((response.getStatusCode() == HttpStatus.OK) || (response.getStatusCode() == HttpStatus.NO_CONTENT)) {
                return false;
            }
            return true;
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        if(activity instanceof ActivityBase){
            ((ActivityBase)activity).hideLoader();
        }
        if(o ==null)
            return;

        if(o instanceof VString){
            VString x= (VString) o;
            ((ActivityBase) activity).showToast(x.getValue());
        }

        if((activity instanceof ActivityLogin) && (url.equals(Constant.createUserUrl)) ){
            ((ActivityLogin) activity).createUserCallBack((VUser)o);
        }



    }
}





//---------------------- ORIGINAL -------------------

//--------------------
//        // Create a new RestTemplate instance
//        RestTemplate restTemplate = new RestTemplate();
//
//
//        String url=(String)params[0];
//        HttpMethod method= (HttpMethod) params[1];
//        Object entityObj=params[2];
//        Class classType= (Class) params[3];
//        //Object entityObj=params[2];
//        MultiValueMap<String, Object> parts= (MultiValueMap<String, Object>) params[4];
//
//        restTemplate.setErrorHandler(new MyErrorResponseHandler());
//
//        // Add the String message converter
//        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
//        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
//
//        HttpHeaders headers = new HttpHeaders();
//
//
//        //rest template adds content length to 2 bytes by default, so it gives error
//        // content-length promised 2 bytes, but received 0
//        //to remove that when delete request is made ,we do following
//        if(classType==null)
//        headers.add("Content-Length", "0");
//
//        if(parts==null){
//
//        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
//        HttpEntity httpEntity =new HttpEntity(entityObj,headers);
//
//        ResponseEntity<?> response = restTemplate.exchange(url, method, httpEntity,classType);
//
//
//        if(response.hasBody()){
//        Object responseBody = response.getBody();
//        LoggerGeneral.info(" ---------------------- " );
//        LoggerGeneral.info(url +" " + responseBody);
//        return  responseBody;
//        }
//        return null;
//        }else{
//
//        }