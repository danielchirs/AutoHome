package com.reggie.au.autohome.asynctasks;

import android.os.AsyncTask;

/**
 * Created by daniel on 2015/6/4.
 */
public class BaseAsyncTask extends AsyncTask<String,Integer,String> {


    /**
     * 业务处理住方法
     * 这里的String参数对应AsyncTask中的第一个参数
     * 这里的String返回值对应AsyncTask的第三个参数
     * @param params
     * @return
     */
    @Override
    protected String doInBackground(String... params) {




        return null;
    }


    /**
     * 业务完成处理方法
     * 这里的String参数对应AsyncTask中的第三个参数（也就是接收doInBackground的返回值）
     * @param s
     */
    @Override
    protected void onPostExecute(String str) {
        //处理发送完成的数据

    }

    /**
     * 业务关闭处理方法
     */
    @Override
    protected void onCancelled() {
        super.onCancelled();
    }


    protected void showProgress(){


    }


    /**
     * 这里的Intege参数对应AsyncTask中的第二个参数
     * 在doInBackground方法当中，，每次调用publishProgress方法都会触发onProgressUpdate执行
     * onProgressUpdate是在UI线程中执行，所有可以对UI空间进行操作
     * @param values
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}
