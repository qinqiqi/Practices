package com.shmj.mouzhai.downloaddemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shmj.mouzhai.downloaddemo.adapter.FileListAdapter;
import com.shmj.mouzhai.downloaddemo.entities.FileInfo;
import com.shmj.mouzhai.downloaddemo.services.DownloadService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvFile;

    private List<FileInfo> fileInfos;
    private FileInfo fileInfo, fileInfo1, fileInfo2, fileInfo3;
    private FileListAdapter fileListAdapter;

    public static final String FILE_URL = "http:www.imooc.com/mobile/imooc.apk";
    public static final String FILE_URL1 = "http:www.imooc.com/download/Activator.exe";
    public static final String FILE_URL2 = "http:www.imooc.com/download/iTunes64Setup.exe";
    public static final String FILE_URL3 = "http:www.imooc.com/download/BaiduPlayerNetSetup_100.exe";
    public static final String FILE_NAME = "imooc.apk";
    public static final String FILE_NAME1 = "Activator.exe";
    public static final String FILE_NAME2 = "iTunes64Setup.exe";
    public static final String FILE_NAME3 = "BaiduPlayerNetSetup_100.exe";

    //更新 UI 的广播接收器
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (DownloadService.ACTION_UPDATE.equals(intent.getAction())) {
                int finished = intent.getIntExtra("finished", 0);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDatas();
        initEvents();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    private void initView() {
        lvFile = (ListView) findViewById(R.id.lv_file);
    }

    private void initDatas() {
        //创建文件集合
        fileInfos = new ArrayList<>();
        //创建文件对象
        fileInfo = new FileInfo(0, FILE_URL, FILE_NAME, 0, 0);
        fileInfo1 = new FileInfo(1, FILE_URL1, FILE_NAME1, 0, 0);
        fileInfo2 = new FileInfo(2, FILE_URL2, FILE_NAME2, 0, 0);
        fileInfo3 = new FileInfo(3, FILE_URL3, FILE_NAME3, 0, 0);
        fileInfos.add(fileInfo);
        fileInfos.add(fileInfo1);
        fileInfos.add(fileInfo2);
        fileInfos.add(fileInfo3);
        //创建适配器
        fileListAdapter = new FileListAdapter(this, fileInfos);
        //设置适配器
        lvFile.setAdapter(fileListAdapter);

        //注册广播接收器
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DownloadService.ACTION_UPDATE);
        registerReceiver(receiver, intentFilter);
    }

    private void initEvents() {

    }
}
