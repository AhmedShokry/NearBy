package com.shokry.nearby.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shokry.nearby.Models.ForsquareResponseModel;
import com.shokry.nearby.R;
import com.shokry.nearby.Tools.Connection.ServerTool;
import com.shokry.nearby.Tools.PermissionTool;
import com.shokry.nearby.Tools.SharedTool.SharedPreferencesTool;
import com.shokry.nearby.Tools.Utils;
import com.shokry.nearby.adapters.RecyclerAdapter;

import java.util.Date;

import okhttp3.ResponseBody;


public class MainActivity extends AppCompatActivity {
    private Boolean exit = false;
    public String latAndLong;
    public String currentDateandTime;
    private ForsquareResponseModel data;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private LinearLayout errorLinearLayout;
    private TextView errorTextView;
    private ImageView errorImageView;
    GPSTracker mGPS;
    private BroadcastReceiver receiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final String[] permissions = {PermissionTool.ACCESS_NETWORK, PermissionTool.PERMISSON_INTERNET, PermissionTool.PERMISSION_LOCATION, PermissionTool.PERMISSION_location_COARSE};
        PermissionTool.checkAllPermission(this, permissions);


        mGPS = new GPSTracker(this);
        if (mGPS.canGetLocation) {
            mGPS.getLocation();
            SharedPreferencesTool.setString(this, "lat", String.valueOf(mGPS.getLatitude()));
            SharedPreferencesTool.setString(this, "long", String.valueOf(mGPS.getLongitude()));
            SharedPreferencesTool.setString(this, "alt", String.valueOf(mGPS.getAltitude()));
            latAndLong = mGPS.getLatitude() + "," + mGPS.getLongitude();

        } else {

            System.out.println("Unable");
        }

        recyclerView = (RecyclerView) findViewById(R.id.places_recycler_view);
        errorLinearLayout = (LinearLayout) findViewById(R.id.error_layout);
        errorImageView = (ImageView) findViewById(R.id.error_image);
        errorTextView = (TextView) findViewById(R.id.error_messege);

        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        currentDateandTime = sdf.format(new Date());
        data = new ForsquareResponseModel();


//        latAndLong = mGPS.getLatitude() + "," + mGPS.getLongitude();

        if (Utils.isNetworkAvailable(this)) {
            GetAcceptedProducts(SharedPreferencesTool.clientID, SharedPreferencesTool.clientSecretID, currentDateandTime, latAndLong);
            recyclerView.setVisibility(View.VISIBLE);
            errorLinearLayout.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.GONE);
            errorImageView.setImageDrawable(getResources().getDrawable(R.drawable.network_error));
            errorTextView.setText("Something went wrong !!");
            errorLinearLayout.setVisibility(View.VISIBLE);
        }


        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String s = intent.getStringExtra(GPSTracker.BROADCAST_MESSAGE);
                // do something here.
                if (Utils.isNetworkAvailable(MainActivity.this)) {
                    GetAcceptedProducts(SharedPreferencesTool.clientID, SharedPreferencesTool.clientSecretID, currentDateandTime, s);
                    recyclerView.setVisibility(View.VISIBLE);
                    errorLinearLayout.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    errorImageView.setImageDrawable(getResources().getDrawable(R.drawable.network_error));
                    errorTextView.setText("Something went wrong !!");
                    errorLinearLayout.setVisibility(View.VISIBLE);
                }

            }
        };


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (exit) {
            finish();
        } else {
            Toast.makeText(MainActivity.this, getString(R.string.back_click),
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }
    }


    @Override
    protected void onStart() {
        super.onStart();

        LocalBroadcastManager.getInstance(this).registerReceiver((receiver),
                new IntentFilter(GPSTracker.BROADCAST_RESULT)
        );
    }

    @Override
    protected void onStop() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.operational_mode) {
            showConfirmRemoveDialog(this);
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (SharedPreferencesTool.getString(this, "operational_mode").equals("single")) {
            menu.findItem(R.id.operational_mode).setTitle(getString(R.string.single));
        } else if (SharedPreferencesTool.getString(this, "operational_mode") == null) {
            menu.findItem(R.id.operational_mode).setTitle(getString(R.string.realtime));
        } else {
            menu.findItem(R.id.operational_mode).setTitle(getString(R.string.realtime));
        }

        return super.onPrepareOptionsMenu(menu);
    }

    public void GetAcceptedProducts(String clientID, String clientSecret, String date, String latAndLong) {
        ServerTool.getJsonData(MainActivity.this, clientID, clientSecret, date, latAndLong, new ServerTool.APICallBack<ForsquareResponseModel>() {

            @Override
            public void onSuccess(final ForsquareResponseModel response) {
                if (response.getMeta().getCode() == 200) {
                    recyclerView.setVisibility(View.VISIBLE);
                    errorLinearLayout.setVisibility(View.GONE);
                    data = response;
                    adapter = new RecyclerAdapter(data, MainActivity.this);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);


                } else {
                    recyclerView.setVisibility(View.GONE);
                    errorLinearLayout.setVisibility(View.VISIBLE);
                    errorImageView.setImageDrawable(getResources().getDrawable(R.drawable.network_error));
                    errorTextView.setText("Something went wrong !!");

                }

            }

            @Override
            public void onFailed(int statusCode, ResponseBody responseBody) {
                recyclerView.setVisibility(View.GONE);
                errorLinearLayout.setVisibility(View.VISIBLE);
                errorImageView.setImageDrawable(getResources().getDrawable(R.drawable.network_error));
                errorTextView.setText("Something went wrong !!");

            }
        });

    }


    private Dialog showConfirmRemoveDialog(final Context context) {
        final Dialog dialog = new Dialog(this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.setContentView(R.layout.confirm_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        Button realTimeButton = (Button) dialog.findViewById(R.id.dialog_real_time_button);
        Button singleUpdateButton = (Button) dialog.findViewById(R.id.dialog_single_update_button);

        realTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferencesTool.setString(context, "operational_mode", "real");
                finish();
                startActivity(getIntent());

                dialog.dismiss();


            }
        });

        singleUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferencesTool.setString(context, "operational_mode", "single");
                finish();
                startActivity(getIntent());

                dialog.dismiss();

            }
        });

        dialog.setCancelable(true);
        dialog.show();
        return dialog;
    }


}
