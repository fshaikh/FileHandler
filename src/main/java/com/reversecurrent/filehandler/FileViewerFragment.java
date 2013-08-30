package com.reversecurrent.filehandler;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by fshaikh on 8/29/13.
 */
public class FileViewerFragment extends Fragment {
    private EditText _fileViewer = null;
    private IMediator _mediator = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fileviewer,container,false);
        _fileViewer = (EditText)view.findViewById(R.id._fileViewer);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        _mediator = (IMediator)activity;
    }

    @Override
    public void onStart() {
        super.onStart();
        _fileViewer.setText(_mediator.GetFileContent().FileContent);

    }
}
