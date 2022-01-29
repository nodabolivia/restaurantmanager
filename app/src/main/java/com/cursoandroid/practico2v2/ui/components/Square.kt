package com.cursoandroid.practico2v2.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.cursoandroid.practico2v2.R

class Square(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

init{
    val inflater= context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    inflater.inflate(R.layout.square,this)
}
}