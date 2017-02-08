package baitap.aptech.music;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTvTitle;
    private ListView mLvMusic;
    private CustomAdapter adapter;
    private String[] mSongs;
    private String[] mSingers;
    private final int[] mUriSongs = new int[]{
            R.raw.con_tim_tan_vo,
            R.raw.di_ve_dau,
            R.raw.lac_troi,
            R.raw.minh_la_gi_cua_nhau,
            R.raw.phia_sau_mot_co_gai,
            R.raw.trai_tim_ben_le,
            R.raw.vo_tan,
            R.raw.dung_tin_em_manh_me
    };
    private int currentIndex;
    private MediaPlayer mMediaPlayer = new MediaPlayer();
    private MediaPlayer.OnCompletionListener completionListener;
    private int total;
    private float leftVolume = 1;
    private float rightVolume = 1;


    private Button mBtnMute;
    private Button mBtnPlayPause;
    private Button mBtnStop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvTitle = (TextView) findViewById(R.id.mTvTitle);
        mLvMusic = (ListView) findViewById(R.id.mLvMusic);


        mBtnMute = (Button) findViewById(R.id.mBtnMute);
        mBtnPlayPause = (Button) findViewById(R.id.mBtnPlayPause);
        mBtnStop = (Button) findViewById(R.id.mBtnStop);

        //set default 2 button play va pause
        mBtnMute.setEnabled(true);
        mBtnPlayPause.setEnabled(false);
        mBtnStop.setEnabled(false);

        final int[] mImages = new int[]{
                R.drawable.phan_manh_quynh,
                R.drawable.tien_tien,
                R.drawable.son_tung,
                R.drawable.lou_hoang,
                R.drawable.soobin_hoang_son,
                R.drawable.bang_kieu,
                R.drawable.trinh_thang_binh,
                R.drawable.jang_mi
        };
        mSongs = getResources().getStringArray(R.array.Songs);
        mSingers = getResources().getStringArray(R.array.Singers);
        adapter = new CustomAdapter(this, R.layout.item_list_music, mSongs, mSingers, mImages);
        mLvMusic.setAdapter(adapter);
        mLvMusic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                currentIndex = position;
                itemClick();
            }
        });


        //set media player
        completionListener = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                total = mSongs.length;
                if (currentIndex == (total - 1)) currentIndex = 0;
                else currentIndex += 1;
                onLoop();
            }
        };


    }

    private void onLoop() {
        mMediaPlayer.stop();
        Prepared();
    }

    private void Prepared() {
        mMediaPlayer = MediaPlayer.create(getApplicationContext(), mUriSongs[currentIndex]);
        mMediaPlayer.setOnCompletionListener(completionListener);
        mMediaPlayer.start();
        mTvTitle.setText(mSongs[currentIndex] + " - " + mSingers[currentIndex]);
        mMediaPlayer.setVolume(leftVolume,rightVolume);
        mBtnPlayPause.setEnabled(true);
        mBtnPlayPause.setText("Pause");
        mBtnStop.setEnabled(true);
    }

    private void itemClick() {

        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
            Prepared();
        } else Prepared();

    }

    public void onMute(View view) {
        if (mBtnMute.getText().equals("Mute")) {
            mMediaPlayer.setVolume(0, 0);
            leftVolume = 0;
            rightVolume = 0;
            mBtnMute.setText("Muted");
        } else {
            mMediaPlayer.setVolume(1, 1);
            leftVolume = 1;
            rightVolume = 1;
            mBtnMute.setText("Mute");
        }
    }

    public void onPlayPause(View view) {
        if (mBtnPlayPause.getText().equals("Pause")) {
            mMediaPlayer.pause();
            mBtnPlayPause.setText("Play");
        } else {
            mMediaPlayer.start();
            mBtnPlayPause.setText("Pause");
        }
    }

    public void onStop(View view) {
        mMediaPlayer.stop();
        mMediaPlayer.reset();
        mBtnStop.setEnabled(false);
        mBtnPlayPause.setEnabled(false);
    }
}
