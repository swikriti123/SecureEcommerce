package com.example.ecosmetics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlertDialog;
import android.app.Notification;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecosmetics.API.LoginAPI;
import com.example.ecosmetics.Broadcast.BroadCastReceiver;
import com.example.ecosmetics.Model.LoginResponse;
import com.example.ecosmetics.Model.User;
import com.example.ecosmetics.URL.url;
import com.example.ecosmetics.bll.LoginBLL;
import com.example.ecosmetics.createchannel.CreateChannel;
import com.example.ecosmetics.strictmode.StrictModeClass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private Button btnlogin,btnloginwithfb,btnsignup;
    private EditText etusername,etpassword;
    private TextView forgetpassword;
    Vibrator vibrator;
    public NotificationManagerCompat notificationManagerCompat;
    public SensorManager sensorManager;
    public final static String TAG = "LoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateChannel channel = new CreateChannel(this);
        channel.createChannel();
        proximity();
        //sensorLight();

        etusername=findViewById(R.id.username);
        etpassword=findViewById(R.id.password);
        btnlogin=findViewById(R.id.btnlogin);
        btnloginwithfb=findViewById(R.id.btnLoginWithFb);
        btnsignup=findViewById(R.id.btnSignup);
        forgetpassword=findViewById(R.id.txtforgetpassword);
        sensorGyro();

        btnloginwithfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Login with facebook", Toast.LENGTH_SHORT).show();
            }
        });
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openSignup = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(openSignup);
            }
        });
        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openForgetpwd = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(openForgetpwd);
            }
        });


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();

            }
        });
    }

//    private void sensorLight() {
//        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
//        Sensor sensor= sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
//        SensorEventListener sensorEventListener= new SensorEventListener() {
//            @Override
//            public void onSensorChanged(SensorEvent event) {
//                if(event.sensor.getType()==Sensor.TYPE_LIGHT){
//                   // Toast.makeText(LoginActivity.this,"onSensor Change:" + event.values[0], Toast.LENGTH_SHORT).show();
//
//                }
//            }
//
//            @Override
//            public void onAccuracyChanged(Sensor sensor, int accuracy) {
//
//            }
//        };
//        sensorManager.registerListener(sensorEventListener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
//    }

    private void proximity() {
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        SensorEventListener sensorEventListener= new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[0]<=4){
                    // lout();
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    private void sensorGyro() {

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        SensorEventListener sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                if (event.values[1] < 0) {
                    login();
                    finish();

                } else if (event.values[1] > 0) {

                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        if (sensor != null) {
            sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        } else {
            //Toast.makeText(this, "No sensor found", Toast.LENGTH_SHORT).show();
        }
    }


    private void login(){

        String username = etusername.getText().toString();
        String password = etpassword.getText().toString();

        if (TextUtils.isEmpty(etusername.getText())) {
            etusername.setError("Enter username");
            return;

        } else if (TextUtils.isEmpty(etpassword.getText())) {
            etpassword.setError("Enter password");
            return;
        }

        LoginBLL loginBLL = new LoginBLL(username,password);
        StrictModeClass.StrictMode();
        if (loginBLL.checkUser()) {
            notifiy();
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();

        } else {
            Toast.makeText(this, "Either username or password is incorrect", Toast.LENGTH_SHORT).show();
            etusername.requestFocus();
            Vibrator vibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(5000);
        }
    }
    private void notifiy() {
        Notification notification = new NotificationCompat.Builder(this, CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.ic_shopping_cart_black_24dp)
                .setContentTitle("My E-Cosmetics app")
                .setContentText("Login success :" + etusername.getText().toString())
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(1, notification);
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        builder.setMessage("Are you sure you want to exit")
//                .setCancelable(false)
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
//                        System.exit(0);
//                    }
//                })
//
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }

    BroadCastReceiver broadCastReceiver= new BroadCastReceiver(this);

    protected void onStart(){
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadCastReceiver,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadCastReceiver);
    }


}
