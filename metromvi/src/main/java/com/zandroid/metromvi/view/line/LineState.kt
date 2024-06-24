package com.zandroid.metromvi.view.line

import com.zandroid.metromvi.data.model.Line

sealed class LineState {
    object Loading:LineState()

   data class ShowLineList(val lines:List<Line.ResponseLinesItem>):LineState()
   data class ShowError(val message:String):LineState()

}