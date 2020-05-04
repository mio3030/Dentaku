package com.example.dentaku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    var nStr : String = ""
    val nList = ArrayList<Double>() // 数式に含まれる数を保持する配列
    val oList = ArrayList<Char>() // arraylist to store operations

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        num0.setOnClickListener {
            formula.text = "${formula.text}0" //表示する数式に0を追加
            nStr += "0"                       //数字の文字列に0を追加
        }
        num1.setOnClickListener {
            formula.text = "${formula.text}1" //表示する数式に1を追加
            nStr += "1"
        }
        num2.setOnClickListener {
            formula.text = "${formula.text}2" //表示する数式に2を追加
            nStr += "2"
        }
        num3.setOnClickListener {
            formula.text = "${formula.text}3" //表示する数式に3を追加
            nStr += "3"
        }
        num4.setOnClickListener {
            formula.text = "${formula.text}4" //表示する数式に4を追加
            nStr += "4"
        }
        num5.setOnClickListener {
            formula.text = "${formula.text}5" //表示する数式に5を追加
            nStr += "5"
        }
        num6.setOnClickListener {
            formula.text = "${formula.text}6" //表示する数式に6を追加
            nStr += "6"
        }
        num7.setOnClickListener {
            formula.text = "${formula.text}7" //表示する数式に7を追加
            nStr += "7"
        }
        num8.setOnClickListener {
            formula.text = "${formula.text}8" //表示する数式に8を追加
            nStr += "8"
        }
        num9.setOnClickListener {
            formula.text = "${formula.text}9" //表示する数式に9を追加
            nStr += "9"
        }

        //小数点
        point.setOnClickListener {
            formula.text = "${formula.text}."
            nStr += "."
        }

        equal.setOnClickListener {
            formula.text = "${formula.text}="
            addList(nStr)
            var result = calcualte().toString()
            formula.text = result
            nStr = result
            nList.clear()
            oList.clear()

                add.setOnClickListener {
                    formula.text = "${formula.text}+"
                    addList(nStr,'+')                 //後述、nStrを小数に変換しnListに入れ、"+"をoListに入れる。
                    nStr = ""                         //nStrを空に戻す
                }

            subtract.setOnClickListener {
                formula.text = "${formula.text}-"
                addList(nStr,'-')
                nStr = ""
            }
            multiply.setOnClickListener {
                formula.text = "${formula.text}*"
                addList(nStr,'*')
                nStr = ""
            }
            divide.setOnClickListener {
                formula.text = "${formula.text}/"
                addList(nStr, '/')
                nStr = ""
            }

        delete.setOnClickListener {
            var formulaStr = formula.text.toString()
            if (!formulaStr.isEmpty()) {
                formula.text = formulaStr.subSequence(0,formulaStr.lastIndex)
            }
            if (!nStr.isEmpty()) {
                nStr = nStr.substring(0, nStr.lastIndex)
            }
        }
            percent.setOnClickListener {
                formula.text = "${formula.text}%"
            }
            sign.setOnClickListener {

            }
            clear.setOnClickListener {
                formula.text = ""
                nStr = ""
                nList.clear()
                oList.clear()
            }

        } // end fun onCreate

        fun addList(str : String, ope : Char) {
            try {
                var num = str.toDouble()
                nList.add(num)
                oList.add(ope)
            }catch(e:Exception){
                formula.text = "Numeric error"
            }
        }

        fun addList(str : String) {
            try {
                var num = str.toDouble()
                nList.add(num)
            }catch(e:Exception){
                formula.text = "Numeric error"
            }
        }

        fun calcualte() : Double {

            var i = 0
            while (i < oList.size) {

                //先に掛け算、割り算を前から順に行う
                if(oList.get(i) == '*' || oList.get(i) == '/') {
                    var result = if (oList.get(i) == '*') nList.get(i) * nList.get(i+1) else nList.get(i) / nList.get(i+1)
                    nList.set(i,result)   //計算に使った一つ目の数を計算結果に置き換え
                    nList.removeAt(i+1)   //二つ目の数をリストから削除
                    oList.removeAt(i)     //使い終わった演算子をリストから削除
                    i--                   //リストの次の要素が一つ手前に来たのでiを一つ戻す
                }

                // 引き算を足し算に変える
                else if(oList.get(i) == '-'){
                    oList.set(i,'+')
                    nList.set(i+1,nList.get(i+1) * -1) //引く数を-1倍
                }
                i++
            }

            // 足し算だけ残るので、リストに残った数を合計する
            var result = 0.0
            for (i in nList){
                result += i
            }
            return result
        }// end fun calcualte


    }
}
