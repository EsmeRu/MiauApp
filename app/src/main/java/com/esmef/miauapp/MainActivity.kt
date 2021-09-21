package com.esmef.miauapp

import android.graphics.Color
import android.graphics.drawable.Drawable
import com.bumptech.glide.request.target.Target
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener


class MainActivity : AppCompatActivity() {

    private var btnMaullar: Button? = null
    private var btnRonronear: Button? = null
    private var btnStop: Button? = null
    private var btnGato: ImageButton? = null
    private var liCat: LinearLayout? = null
    private var mediaPlayerBtnM: MediaPlayer? = null
    private var mediaPlayerBtnR: MediaPlayer? = null
    private var mediaPlayerBtnG: MediaPlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaPlayerBtnM = MediaPlayer.create(this, R.raw.sounds_meow)
        mediaPlayerBtnR = MediaPlayer.create(this, R.raw.animal_6)
        mediaPlayerBtnG = MediaPlayer.create(this, R.raw.sounds_cymbal)
        liCat = findViewById(R.id.layoutBtns)
        btnMaullar = findViewById(R.id.btnMaullar)
        btnRonronear = findViewById(R.id.btnRonronear)
        btnStop = findViewById(R.id.btnStop)
        btnGato = findViewById(R.id.btnGato)

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        btnMaullar?.setOnClickListener {
            mediaPlayerBtnM?.start()
        }

        btnRonronear?.setOnClickListener {
            mediaPlayerBtnR?.start()
        }

        btnStop?.setOnClickListener {
            mediaPlayerBtnM?.stop()
            mediaPlayerBtnR?.stop()
            mediaPlayerBtnG?.stop()
            mediaPlayerBtnM = MediaPlayer.create(this, R.raw.sounds_meow)
            mediaPlayerBtnR = MediaPlayer.create(this, R.raw.animal_6)
            mediaPlayerBtnG = MediaPlayer.create(this, R.raw.sounds_cymbal)
            Glide.with(this).onStop().to(btnGato)

        }

        btnGato?.setOnClickListener {
            val color1 = rand(); val color2 = rand(); val  color3 = rand()

            mediaPlayerBtnG?.start()
            liCat?.setBackgroundColor(Color.rgb(color1, color2, color3))
            btnMaullar?.setTextColor(Color.rgb(color2, color3, color1))
            btnRonronear?.setTextColor(Color.rgb(color2, color3, color1))
            Glide.with(this).load(R.raw.gato).listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(p0: GlideException?, p1: Any?, p2: Target<Drawable>?, p3: Boolean): Boolean {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
                override fun onResourceReady(p0: Drawable?, p1: Any?, p2: Target<Drawable>?, p3: DataSource?, p4: Boolean): Boolean {
                    (p0 as GifDrawable).setLoopCount(1)
                    p0.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
                        override fun onAnimationEnd(drawable: Drawable) {
                            println("animation ends")
                        }
                    })
                    return false
                }
            }).into(btnGato!!)
        }
    }

    private fun rand(): Int {
        return (Math.random() * (255 - 1 + 1)).toInt() + 1
    }
}