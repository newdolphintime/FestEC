package com.paly.zv.latty.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.paly.zv.latty.app.Latty;
import com.paly.zv.latty.net.callback.IRequest;
import com.paly.zv.latty.net.callback.ISuccess;
import com.paly.zv.latty.util.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

public class SaveFileTask extends AsyncTask<Object,Void,File> {

    private final IRequest iRequest;
    private final ISuccess iSuccess;

    public SaveFileTask(IRequest iRequest, ISuccess iSuccess) {
        this.iRequest = iRequest;
        this.iSuccess = iSuccess;
    }

    @Override
    protected File doInBackground(Object[] objects) {
        String downloaddir = (String) objects[0];
        String extension = (String) objects[1];
        final ResponseBody responseBody = (ResponseBody) objects[2];
        String name = (String) objects[3];
        final InputStream in = responseBody.byteStream();
        if (downloaddir == null || downloaddir.equals("")){
            downloaddir = "down_loads";
        }
        if (extension == null || extension.equals("")){
            extension = "";
        }
        if (name == null ){
            return FileUtil.writeToDisk(in,downloaddir,extension.toUpperCase(),extension);
        }else {
            return  FileUtil.writeToDisk(in,downloaddir,name);
        }

    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (iSuccess !=null){
            iSuccess.onSuccess(file.getPath());


        }
        if (iRequest !=null){
            iRequest.onRequestEnd();
        }
        autoInstallApk(file);
    }

    private void autoInstallApk(File file) {
        if (FileUtil.getExtension(file.getPath()).equals("apk")) {
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            Latty.getApplicationContext().startActivity(install);
        }
    }
}
