package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.TestLooperManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoNeighbourActivity extends AppCompatActivity {

    // UI components
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.cardInfoTitle)
    TextView tvInfoTitle;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvWebLink)
    TextView tvWebLink;
    @BindView(R.id.aboutTitle)
    TextView aboutTitle;
    @BindView(R.id.tvAboutMe)
    TextView tvAboutMe;
    @BindView(R.id.floatingActionButton)
    FloatingActionButton fab;

    private NeighbourApiService mApiService;
    private Neighbour neighbour;

    // Extra name for the id in parameter
    public static final String NEIGHBOUR_ID = "neighbourId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_neighbour);
        ButterKnife.bind(this);

        mApiService = new DummyNeighbourApiService();

        // retrieve neighbour using id in param
        neighbour = mApiService.getNeighbourById(getIntent().getLongExtra(NEIGHBOUR_ID, 0));

        if (neighbour != null) {
            String s = neighbour.getAddress();
            tvAddress.setText(s);
        }
    }

    public static void navigate(FragmentActivity activity, long neighbourId) {
        Intent intent = new Intent(activity, InfoNeighbourActivity.class);
        intent.putExtra("neighbourId", neighbourId);
        ActivityCompat.startActivity(activity, intent, null);
    }
}