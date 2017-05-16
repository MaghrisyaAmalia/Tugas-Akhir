package id.sch.smktelkom_mlg.privateassignment.xirpl617.tugasakhir;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    public String url = "https://image.tmdb.org/t/p/w500";
    String Id, des, img, gambar, tgl, vot, pop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        Id = intent.getStringExtra("title");
        img = intent.getStringExtra("poster_path");
        des = intent.getStringExtra("overview");
        tgl = intent.getStringExtra("release_date");
        pop = intent.getStringExtra("popularity");
        vot = intent.getStringExtra("vote_count");
        setTitle(Id);
        gambar = url + img;
        ImageView detail = (ImageView) findViewById(R.id.imageViewDetail);
        TextView deskripsi = (TextView) findViewById(R.id.deskripsi);
        TextView tanggal = (TextView) findViewById(R.id.tanggal);
        TextView popularity = (TextView) findViewById(R.id.popularitas);
        TextView vote = (TextView) findViewById(R.id.vote_count);
        Glide.with(this).load(gambar)
                .crossFade()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(detail);
        deskripsi.setText(des);
        tanggal.setText(tgl);
        popularity.setText(pop);
        vote.setText(vot);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
