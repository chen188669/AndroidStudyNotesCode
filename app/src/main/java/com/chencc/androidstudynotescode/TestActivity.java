package com.chencc.androidstudynotescode;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.StringKt;

import com.chencc.androidstudynotescode.androidjvm_class_test.Test;
import com.chencc.androidstudynotescode.kotlin.Simple1Kt;

import kotlin.io.FilesKt;


public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Test.test();
    }
}
