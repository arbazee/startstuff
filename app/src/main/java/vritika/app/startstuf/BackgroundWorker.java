package vritika.app.startstuf;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWorker extends AsyncTask<String,String,String> {
    Context context;
    AlertDialog alertDialog;

    BackgroundWorker(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String enquiry="http://startstuffapp.in/startstuff/enquiry.php";
       if (type.equals("Enquiry")) {
            try {
                String compony = params[1];
                String name = params[2];
                String mobile =params[3];
                String mail = params[4];
                String remark = params[5];
                URL url = new URL(enquiry);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data =URLEncoder.encode("compony", "UTF-8") + "=" + URLEncoder.encode(compony, "UTF-8")+ "&"
                        + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&"
                        + URLEncoder.encode("mobile", "UTF-8") + "=" + URLEncoder.encode(mobile, "UTF-8") + "&"
                        + URLEncoder.encode("mail", "UTF-8") + "=" + URLEncoder.encode(mail, "UTF-8") + "&"
                        + URLEncoder.encode("remark", "UTF-8") + "=" + URLEncoder.encode(remark, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch(NumberFormatException e){
                e.printStackTrace();

            }
            catch(NullPointerException e){
                e.printStackTrace();
                String msg = (e.getMessage()==null)?"Login failed!":e.getMessage();
                Log.i("Login Error1",msg);

            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute (String result){
        // alertDialog.setMessage(result);
        // alertDialog.show();

        // Log.d("result:",result);
        if(result.contentEquals("Insertsuccess")) {
            Log.d("result",result);
            Toast toast= Toast.makeText(context, "Thank you for your request,We will get back to you soon ", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @Override
    protected void onProgressUpdate (String...values){
        super.onProgressUpdate(values);
    }
}
