package com.zandroid.metromvp.mvp.ui.line

import com.zandroid.metromvp.mvp.base.BasePresenter
import com.zandroid.metromvp.mvp.base.BaseView
import com.zandroid.metromvp.mvp.data.model.Line
import retrofit2.Response

interface LineContracts {

    interface View:BaseView{
        fun showLines(lines: List<Line.ResponseLinesItem>)
    }

    interface Presenter:BasePresenter{
        fun callAllLines()


    }
}