package com.example.androidarchitectures.mvc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidarchitectures.R

class MVCActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvcactivity);
    }
    public stat Intent getIntent(Context context){
        return new Intent(context, MVCActitvity.class)
    }
}