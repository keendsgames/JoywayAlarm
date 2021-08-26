package cn.joyway.ala.activity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import cn.joyway.ala.R;
//joyway tap panic button program
//August 17 '21
//needs permissions for sms messaging, external storage read/write
//needs app permission for messaging ...setting>Apps>smsmessaging (app name)> Permission ON
//data storage for sms messaging info ==> smscontacts>mymsm.dat : tap mac, sos msg, phone #, sos key
//Use setup page to update personal message info
//Use run page to monitor tap button: press (periodically) connect button to check tap pair conditions
//Once paired, 2 short taps=send sos;   1 long tap=reset
//August 18 '21
//added working mysms.dat in internal storage ==> initialize mytextfile.txt in Menu.java;  update mytextfile in setup; read mytextfile in activity


//menu page
    public class Menu extends Activity
    {
        //TourActivity ob;
        String name="John";
        int grade=9;
        String group="red";
        Spinner spinner;
        //EditText nametext;
        RadioGroup gradeRadioGroup;
        final int SetUpCode=006;
        final int Activity_baseCode=005;
        public String tagMacAd, sosMsg, phoneNo, sosKey;


        public void onCreate(Bundle icicle)
        {
            super.onCreate(icicle);
            setContentView(R.layout.menu);

//initialize default sms data file for the first time after installation

//internal storage data file initialization routine starts here  ....
//initialize default sms data file for the first time after installation
//alt method to write mysms1.txt
            if (fileExist("mytextfile.txt"))
                System.out.println("mytextfile.txt exists...");
            else
                System.out.println("mytextfile.txt does not exist....");
            if (!fileExist("mytextfile.txt")) {
                try {
                    FileOutputStream fileout = openFileOutput("mytextfile.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                    //outputWriter.write("data in mytextfile.txt");
                    System.out.println("Menu initialize defaulted mac add in alt data file....");
                    outputWriter.append("C7:9D:FE:F0:31:48                                ");
                    outputWriter.append("\r\n");
                    System.out.println("Menu initialize defaulted sos msg add in alt data file....");
                    outputWriter.append("SOS Help... I'm in trouble (default)             ");
                    outputWriter.append("\r\n");
                    System.out.println("Menu initialize defaulted phone no in alt data file....");
                    outputWriter.append("18881234567                                      ");
                    outputWriter.append("\r\n");
                    System.out.println("Menu initialize defaulted sos key in alt data file....");
                    outputWriter.append("James Bond (defaulted)                           ");
                    outputWriter.append("\r\n");
                    outputWriter.append("dummy line                                       ");
                    outputWriter.append("\r\n");
                    outputWriter.close();

                    //display file saved message
                    System.out.println("alternative creating data in mytextfile.txt done writing........");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }//filenotexist
            //reading mytextfile.txt data
            try {
                FileInputStream fileIn=openFileInput("mytextfile.txt");
                InputStreamReader InputRead= new InputStreamReader(fileIn);
                BufferedReader r = new BufferedReader(new InputStreamReader(fileIn));
                String line;
                line=r.readLine();
                if (line == null)
                    System.out.println("reading null data in data file !!!");
                tagMacAd=line.trim();
                System.out.println("Data read from file."+tagMacAd);
                line=r.readLine();
                sosMsg=line.trim();
                System.out.println("Data read from file."+sosMsg);
                line=r.readLine();
                phoneNo=line.trim();
                System.out.println("Data read from file."+phoneNo);
                line=r.readLine();
                sosKey=line.trim();
                System.out.println("Data read from file."+sosKey);
                r.close();
                System.out.println("alternative creating data in mytextfile.txt done reading.......");
                InputRead.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


//internal storage data file initialization routine done ....
/*
//external storage data file initialization routine starts here  ....
//initialize default sms data file for the first time after installation
// checks if external storage is available for read and write

            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)){
                System.out.println("External Storage available");
                File root = new File(Environment.getExternalStorageDirectory(),"smscontacts");
                if (!root.exists()) {
                    root.mkdirs();
                    System.out.println("Creating directory smscontacts....");
                }
                else System.out.println("directory smscontacts exists already....");
            }
            else  {
                System.out.println("External Storage is not availble!!");
            }

//writing file
            System.out.println("create mysms data file if not existing in Menu.......");
            try {
                File root = new File(Environment.getExternalStorageDirectory(),"smscontacts");
                //change this file name to togglein.dat when ready
                //File secondFile = new File(root,"mysms.dat");
                if (!secondFile.exists()) {
                    System.out.println("Creating new mysms.dat file....");
                    secondFile.createNewFile();
                    FileOutputStream fos = new FileOutputStream(secondFile);
                    OutputStreamWriter myOutWriter =
                            new OutputStreamWriter(fos);
                    //myOutWriter.write("Write test line in gamepanel");
                    System.out.println("Menu initialize defaulted mac add in data file....");
                    myOutWriter.append("C7:9D:FE:F0:31:48                                ");
                    myOutWriter.append("\r\n");
                    System.out.println("Menu initialize defaulted sos msg add in data file....");
                    myOutWriter.append("SOS Help... I'm in trouble (default)             ");
                    myOutWriter.append("\r\n");
                    System.out.println("Menu initialize defaulted phone no in data file....");
                    myOutWriter.append("18881234567                                      ");
                    myOutWriter.append("\r\n");
                    System.out.println("Menu initialize defaulted sos key in data file....");
                    myOutWriter.append("James Bond (defaulted)                           ");
                    myOutWriter.append("\r\n");
                    myOutWriter.append("dummy line                                       ");
                    myOutWriter.append("\r\n");
                    myOutWriter.close();
                    fos.close();
                }
                else System.out.println("mysms.dat exist already!!.");
                //over writing mysms data
            } catch (Exception e) {
                System.out.println("error in creating file ...");
            }
//external storage data file initializaiton routine done .......
*/

            //exit button  ==> finish()
            ImageButton e = (ImageButton)findViewById(R.id.btnClicke);
            //Button e = (Button) findViewById(R.id.btnClicke);

            e.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    //return information to calling activity
                    //Intent i = getIntent();
                    //System.out.println("passing data : "+name+","+grade+","+group);
                    setResult(RESULT_OK);
                    finish();

                }
            });
            //run program button ==> run tag program
            ImageButton r = (ImageButton)findViewById(R.id.btnClickr);
            //Button r = (Button) findViewById(R.id.btnClickr);
            r.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    //return information to calling activity

                    //Intent ii = getIntent();
                    //name=nametext.getText().toString();
                    Intent ii = new Intent(Menu.this,Activity_base.class);
                    startActivityForResult(ii,Activity_baseCode);
                    //System.out.println("passing data : "+name+","+grade+","+group);
                    setResult(RESULT_OK, ii);
                    //finish();

                }
            });

            //setup button
            ImageButton b = (ImageButton)findViewById(R.id.btnClickw);
            //Button b = (Button) findViewById(R.id.btnClickw);
            b.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    //return information to calling activity

                    //Intent i = getIntent();
                    Intent i = new Intent(Menu.this,Setup.class);
                    startActivityForResult(i,SetUpCode);
                    //name=nametext.getText().toString();
                    setResult(RESULT_OK, i);
                }
            });
        }
        protected void onActivityResult(int requestCode, int resultCode, Intent data)
        {	super.onActivityResult(requestCode,resultCode,data);
            //read and write contact data on file
            if(requestCode==SetUpCode)
            {
                if(resultCode == RESULT_OK) {
                    if(null!=data)
                    {
                   //additional routines if necessary
                    }
                }//if resultok

            }//if setupcode
            if(requestCode==Activity_baseCode)
            {
                if(resultCode == RESULT_OK) {
                 //additional routines if neccessary
                }//if resultok

            }//if Activity_baseCode
        }
        public boolean fileExist(String fname){    File file = getBaseContext().getFileStreamPath(fname);    return file.exists();}
    }


