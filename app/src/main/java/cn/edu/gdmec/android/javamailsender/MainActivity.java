package cn.edu.gdmec.android.javamailsender;


import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import javax.mail.Authenticator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        Button addImage = (Button)findViewById(R.id.send_email);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
        .detectDiskWrites()
        .detectDiskReads()
        .detectNetwork()
        .penaltyLog()
        .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
        .detectLeakedSqlLiteObjects()
        .detectLeakedClosableObjects()
        .penaltyLog()
        .penaltyDeath()
        .build());
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Mail m = new Mail("csharp1013@163.com","cs1013");
               String[] toArr = {"1308013168@qq.com","gdmecbook@163.com"};
               m.set_to(toArr);
               m.set_from("csharp1013@163.com");
               m.set_subject("This is an email sent using my Mail JavaMail wrapper from an Android device");
               m.setBody("Email sending Body");

               try{
                   if (m.send()){
                       Toast.makeText(MainActivity.this,"Email was sent successfully.",Toast.LENGTH_LONG).show();

                   }else {
                       Toast.makeText(MainActivity.this,"Email was not sent .",Toast.LENGTH_LONG).show();
                   }
               }catch (Exception e){
                   Log.e("MailApp","Could not send Email",e);
               }
            }
        });
    }
}
