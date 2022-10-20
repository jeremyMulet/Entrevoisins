package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import java.util.Locale;
import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoNeighbourActivity extends AppCompatActivity {

    // UI components
    @BindView(R.id.imageView)
    ImageView avatar;
    @BindView(R.id.btn_back)
    ImageButton btnBack;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.cardInfoTitle)
    TextView tvInfoTitle;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvWebLink)
    TextView tvWebLink;
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

        // Remove title activity action bar
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

        mApiService = DI.getNeighbourApiService();

        // retrieve neighbour using id in param
        neighbour = mApiService.getNeighbourById(getIntent().getLongExtra(NEIGHBOUR_ID, 0));

        if (neighbour != null) {

            btnBack.setOnClickListener(view -> onBackPressed());
            Glide.with(avatar.getContext())
                    .load(neighbour.getAvatarUrl())
                    .into(avatar);

            tvName.setText(neighbour.getName());
            tvInfoTitle.setText(neighbour.getName());

            tvAddress.setText(neighbour.getAddress());
            tvPhone.setText(neighbour.getPhoneNumber());
            tvWebLink.setText(String.format("www.facebook.fr/%s", neighbour.getName().toLowerCase(Locale.ROOT)));

            fab.setOnClickListener(view -> {
                neighbour.setFavorite(!neighbour.isFavorite());
                setFabColor();
            });

            tvAboutMe.setText(neighbour.getAboutMe());
            setFabColor();
        }
    }

    public static void navigate(FragmentActivity activity, long neighbourId) {
        Intent intent = new Intent(activity, InfoNeighbourActivity.class);
        intent.putExtra("neighbourId", neighbourId);
        ActivityCompat.startActivity(activity, intent, null);
    }

    private void setFabColor() {
        if(neighbour.isFavorite()) {
            fab.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.favorite)));
        } else {
            fab.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
        }
    }
}