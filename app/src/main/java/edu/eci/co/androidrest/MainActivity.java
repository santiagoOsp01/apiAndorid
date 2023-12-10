package edu.eci.co.androidrest;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import edu.eci.co.androidrest.databinding.ActivityMainBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView breedText;
    private DogApiService service;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        imageView = binding.imageDog;
        breedText = binding.textBreed;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dog.ceo/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(DogApiService.class);
        binding.buttonRandom.setOnClickListener(v -> fetchRandomDogImage());
    }


    private void fetchRandomDogImage() {
        Log.d("MainActivity", "Init");
        service.getRandomDogImage().enqueue(new Callback<DogResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<DogResponse> call, Response<DogResponse> response) {
                Log.d("MainActivity", "Init On Response");
                if (response.isSuccessful()) {
                    String imageUrl = response.body().getMessage();
                    Glide.with(imageView.getContext())
                            .load(imageUrl)
                            .error(R.drawable.not_found)
                            .into(imageView);
                    String breed = imageUrl.split("/")[4];
                    breedText.setText(breed);
                } else {
                    breedText.setText("Bad Petition");
                }
            }

            @Override
            public void onFailure(Call<DogResponse> call, Throwable t) {
                call.cancel();
            }

        });
    }
}