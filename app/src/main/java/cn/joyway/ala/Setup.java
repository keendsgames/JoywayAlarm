package cn.joyway.ala.activity;

import android.app.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import cn.joyway.ala.R;

public class Setup extends Activity {
        EditText macintext,smsintext,phoneintext,keyintext;
        public String tagMacAd, sosMsg, phoneNo, sosKey;
        public void onCreate(Bundle icicle)
        {
            super.onCreate(icicle);
            setContentView(R.layout.setup);

	      //add 4 input text fields: tagmac, sos message, phone#, keyword in layout
	      macintext = (EditText)findViewById(R.id.macinputtext);
	      //macintext.setText("12:11:11:11:11");
	      smsintext = (EditText)findViewById(R.id.smsinputtext);
	      //smsintext.setText("SOS help message");
	      phoneintext = (EditText)findViewById(R.id.phoneinputtext);
	      //phoneintext.setText("18881234567");
	      keyintext = (EditText)findViewById(R.id.keyinputtext);
	      //keyintext.setText("James Bond");

            System.out.println("starting reading routine in setup...");
            //String folder = getApplication().getFilesDir().getAbsolutePath()+File.separator+"smscontacts";
            //File root = new File(folder);
            //File secondInputFile = new File(root,"mysms.dat");

//reading data file from internal storage .........

            try {
                /*
                File secondInputFile = new File(root,"mysms.dat");
                //InputStream secondInputStream = new BufferedInputStream(new FileInputStream(secondInputFile));
                //BufferedReader r = new BufferedReader(new FileReader(new File(folder,"mysms.dat")));
                //BufferedReader r
                //        = new BufferedReader(new FileReader(secondInputFile));
                String line;
                InputStream secondInputStream = new BufferedInputStream(new FileInputStream(secondInputFile));
                BufferedReader r = new BufferedReader(new InputStreamReader(secondInputStream));
*/
                /*
                String folder = getApplication().getFilesDir().getAbsolutePath()+File.separator+"smscontacts";
                File root = new File(folder);
                File secondInputFile = new File(root,"mysms.dat");
                //InputStream secondInputStream = new BufferedInputStream(new FileInputStream(secondInputFile));

                BufferedReader r = new BufferedReader(new FileReader(new File(folder,"mysms.dat")));
                //BufferedReader r = new BufferedReader(new FileReader(new File(folder,"mysms1.txt")));
                //BufferedReader r = new BufferedReader(new InputStreamReader(secondInputStream));
                String line;
 */
                FileInputStream fileIn=openFileInput("mytextfile.txt");
                InputStreamReader InputRead= new InputStreamReader(fileIn);
                BufferedReader r = new BufferedReader(new InputStreamReader(fileIn));
                System.out.println("starting reading now in setup...");
                String line;
                /*
                if (!secondInputFile.exists())
                    System.out.println("setup could not find mysms.dat file ....");
                else
                    System.out.println("setup finds mysms.dat file ....");
                    */

                // read strings
                line=r.readLine();
                if (line == null)
                    System.out.println("reading null data in data file !!!");
                tagMacAd=line.trim();
                macintext.setText(tagMacAd);
                System.out.println("Data read from file."+tagMacAd);
                line=r.readLine();
                sosMsg=line.trim();
                smsintext.setText(sosMsg);
                System.out.println("Data read from file."+sosMsg);
                line=r.readLine();
                phoneNo=line.trim();
                phoneintext.setText(phoneNo);
                System.out.println("Data read from file."+phoneNo);
                line=r.readLine();
                sosKey=line.trim();
                keyintext.setText(sosKey);
                System.out.println("Data read from file."+sosKey);
                r.close();
                //secondInputStream.close();
                //delete original mysms.dat file
                //secondInputFile.delete();
                System.out.println("closing read file setup...");
            } catch (Exception e) {
                System.out.println("error in reading setup...");
            }
//reading data from internal storage ends .........

/*
//reading data file from external storage .........
            System.out.println("starting reading routine in setup...");
            try {
                File root = new File(Environment.getExternalStorageDirectory(),"smscontacts");
                File secondInputFile = new File(root,"mysms.dat");

                InputStream secondInputStream = new BufferedInputStream(new FileInputStream(secondInputFile));
                BufferedReader r = new BufferedReader(new InputStreamReader(secondInputStream));
                String line;

                // read strings
                line=r.readLine();
                tagMacAd=line.trim();
                macintext.setText(tagMacAd);
                System.out.println("Data read from file."+tagMacAd);
                line=r.readLine();
                sosMsg=line.trim();
                smsintext.setText(sosMsg);
                System.out.println("Data read from file."+sosMsg);
                line=r.readLine();
                phoneNo=line.trim();
                phoneintext.setText(phoneNo);
                System.out.println("Data read from file."+phoneNo);
                line=r.readLine();
                sosKey=line.trim();
                keyintext.setText(sosKey);
                System.out.println("Data read from file."+sosKey);
                r.close();
                secondInputStream.close();
                //delete original mysms.dat file
                //secondInputFile.delete();
                System.out.println("closing read file setup...");
            } catch (Exception e) {
                System.out.println("error in reading setup...");
            }
//reading data from external storage ends .........
*/
            //done button
            Button b = (Button) findViewById(R.id.btnClickw);

            b.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {


            //return information to calling activity

                    //update input data to mysms.dat file in internal storage .............
                    System.out.println("Creating Updated sos data file in setup....");
                    try {
                        /*
                        String folder = getApplication().getFilesDir().getAbsolutePath() + File.separator + "smscontacts";
                        File root = new File(folder);
                        File secondInputFile = new File(root,"mysms.dat");
                        //FileOutputStream fos = new FileOutputStream(secondFile);
                        //creating new mysms.dat file
                        //secondInputFile.createNewFile();
                        FileOutputStream fos = new FileOutputStream(new File(root,"mysms.dat"));
                        System.out.println("FileOutputStream fos created....");
                        OutputStreamWriter myOutWriter =
                                new OutputStreamWriter(fos);
                        */
                        FileOutputStream fileout=openFileOutput("mytextfile.txt", MODE_PRIVATE);
                        OutputStreamWriter myOutWriter=new OutputStreamWriter(fileout);
                        System.out.println("OutputStreamWriter myOutWriter created....");
                        tagMacAd = macintext.getText().toString();
                        System.out.println("Setup: updating tagMacAd sos data file :"+tagMacAd);
                        myOutWriter.append(tagMacAd);
                        myOutWriter.append("\r\n");
                        sosMsg = smsintext.getText().toString();
                        System.out.println("Setup: updating sosMsg sos data file :"+sosMsg);
                        myOutWriter.append(sosMsg);
                        myOutWriter.append("\r\n");
                        phoneNo = phoneintext.getText().toString();
                        System.out.println("Setup: updating phoneNo sos data file :"+phoneNo);
                        myOutWriter.append(phoneNo);
                        myOutWriter.append("\r\n");
                        sosKey = keyintext.getText().toString();
                        System.out.println("Setup: updating sosKey sos data file :"+sosKey);
                        myOutWriter.append(sosKey);
                        myOutWriter.append("\r\n");
                        //myOutWriter.append("\r\n");

                        myOutWriter.close();
                        //fos.close();
                    } catch (Exception e) {
                        System.out.println("error in creating file in setup...");
                    }
//update  input data in mysms.dat in internal storage end ..................
/*
            //update input data to mysms.dat file in external storage .............
                    //myOutWriter.write("Write test line in gamepanel");
                    System.out.println("Updating sos data file in setup....");
                    try {
                        File root = new File(Environment.getExternalStorageDirectory(),"smscontacts");
                        File secondFile = new File(root,"mysms.dat");
                        if (!secondFile.exists()) {
                            System.out.println("Creating new mysms.dat file....");
                        } else
                        {
                            System.out.println("mysms.dat file exists and to be updated....");
                        }
                        //FileOutputStream fos = new FileOutputStream(secondFile);
                        //creating new mysms.dat file
                        secondInputFile.createNewFile();
                        FileOutputStream fos = new FileOutputStream(new File(root,"mysms.dat"));
                        System.out.println("FileOutputStream fos created....");
                        OutputStreamWriter myOutWriter =
                                new OutputStreamWriter(fos);
                        System.out.println("OutputStreamWriter myOutWriter created....");
                        tagMacAd = macintext.getText().toString();
                        System.out.println("Setup: updating tagMacAd sos data file :"+tagMacAd);
                        myOutWriter.append(tagMacAd);
                        myOutWriter.append("\r\n");
                        sosMsg = smsintext.getText().toString();
                        System.out.println("Setup: updating sosMsg sos data file :"+sosMsg);
                        myOutWriter.append(sosMsg);
                        myOutWriter.append("\r\n");
                        phoneNo = phoneintext.getText().toString();
                        System.out.println("Setup: updating phoneNo sos data file :"+phoneNo);
                        myOutWriter.append(phoneNo);
                        myOutWriter.append("\r\n");
                        sosKey = keyintext.getText().toString();
                        System.out.println("Setup: updating sosKey sos data file :"+sosKey);
                        myOutWriter.append(sosKey);
                        myOutWriter.append("\r\n");
                        //myOutWriter.append("\r\n");

                        myOutWriter.close();
                        fos.close();
                    } catch (Exception e) {
                        System.out.println("error in creating file in setup...");
                    }
//update  input data in mysms.dat in external storage end ..................
 */
                    setResult(RESULT_OK);
                    finish();

                }
            });

        }
    }



