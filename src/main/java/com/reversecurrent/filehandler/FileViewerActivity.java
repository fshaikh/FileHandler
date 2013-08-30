package com.reversecurrent.filehandler;
//import android.R;
import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by fshaikh on 8/29/13.
 */
public class FileViewerActivity extends FragmentActivity implements IMediator {

    private String _fileContent = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fileviewer);


        Intent intent = getIntent(); // get the intent
        Uri data = intent.getData(); // get the intent data.
        if(data != null)
        {
            String scheme = data.getScheme();  // get the URI scheme. Could be either file or HTTP
            if(ContentResolver.SCHEME_FILE.equals(scheme)) // we handle only File scheme
            {
                String encodedPath = data.getEncodedPath(); // get the file path
                TextView tv = (TextView)findViewById(R.id._textView);
                tv.setText(encodedPath);

                _fileContent = ReadFileContents(encodedPath); // Read file contents

                AddFileViewerFragment();
            }

        }
    }

    private void AddFileViewerFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        WebViewFragment fragment = new WebViewFragment();
        ft.add(R.id._fragmentContainer,fragment,"TAG");
        ft.commit();
    }

    private String ReadFileContents(String data)
    {
        String fileContentStr = "";
        // open the file.
        File file  = new File(data);
        FileInputStream inputStream = null;
        try
        {
            inputStream = new FileInputStream(data);
            byte fileContent[] = new byte[(int)inputStream.available()];
            // read the entire contents
            inputStream.read(fileContent);

            fileContentStr = new String(fileContent);
            return fileContentStr;
        }
        catch(FileNotFoundException ex)
        {
            return fileContentStr;
        }
        catch(IOException ex)
        {
            return fileContentStr;
        }
        finally
        {
            Dispose(inputStream);
        }

        // show in the text view
    }

    private void Dispose(FileInputStream inputStream)
        {
            try
            {
                if(inputStream != null)
                {
                    inputStream.close();
                    inputStream = null;
                }
            }
            catch(IOException ex)
            {
            }

            finally
            {
            }
        }

    public Payload GetFileContent()
    {
        Payload payload = new Payload();
        payload.FileContent = _fileContent;
        return payload;
    }

}
