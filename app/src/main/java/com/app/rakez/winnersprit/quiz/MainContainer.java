package com.app.rakez.winnersprit.quiz;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.rakez.winnersprit.EntryPointActivity;
import com.app.rakez.winnersprit.FirebaseHandler.FirebaseHelper;
import com.app.rakez.winnersprit.R;
import com.app.rakez.winnersprit.data.SharedPref;
import com.app.rakez.winnersprit.util.ImageUtils;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainContainer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ActivityCommunicator, View.OnClickListener {

    private static String TAG = "Main Caontainer";

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;
    View navHeaderView;
    ImageView navImage;
    TextView navName;
    TextView navEmail;

    SharedPref sharedPref;
    ImageUtils imageUtils;

    //Elements of profile
    @BindView(R.id.profile_layout) RelativeLayout profileLayout;
    @BindView(R.id.profile_image_IV) ImageView profileImage;
    @BindView(R.id.display_name_TV) TextView userNameTV;
    @BindView(R.id.course_name_TV) TextView courseNameTV;
    @BindView(R.id.score_val_TV) TextView scoreValueTV;
    @BindView(R.id.level_val_TV) TextView levelValueTV;
    @BindView(R.id.scoreboard_fab) FloatingActionButton scoreboardFAB;
    @BindView(R.id.theory_fab) FloatingActionButton theoryFAB;
    String userName, courseId, courseName, uId, email, loginProvider;
    Bitmap userImage;
    boolean imageExist;

    //Signout elements
    FirebaseAuth firebaseAuth;

    //Fragment elements
    FragmentTransaction fragmentTransaction;
    LevelFragment levelFragment;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_container);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navHeaderView = navigationView.inflateHeaderView(R.layout.nav_header_main_container);
        navImage = navHeaderView.findViewById(R.id.nav_image);
        navName = navHeaderView.findViewById(R.id.nav_name);
        navEmail = navHeaderView.findViewById(R.id.nav_email);
        firebaseAuth = FirebaseAuth.getInstance();
        sharedPref = new SharedPref(this);
        imageUtils = new ImageUtils();
        databaseReference = FirebaseHelper.getDatabase().getReference();
        levelFragment = new LevelFragment();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, levelFragment);
        fragmentTransaction.commit();
        initializeUserProfile();
        scoreboardFAB.setOnClickListener(this);
    }

    private void initializeUserProfile(){
        userName = sharedPref.getStringData("name");
        uId  =sharedPref.getStringData("uid");
        courseName = sharedPref.getStringData("course_name");
        courseId = sharedPref.getStringData("course_id");
        email = sharedPref.getStringData("email");
        loginProvider = sharedPref.getStringData("login_provider");
        imageExist = sharedPref.getBooleanData("image_exist");
        if(imageExist){
            userImage = imageUtils.loadImageBitmap(this,uId);
            profileImage.setImageBitmap(userImage);
            navImage.setImageBitmap(userImage);
        }else{
            navImage.setImageResource(R.drawable.ic_user_image);
        }
        navName.setText(userName);
        navEmail.setText(email);
        userNameTV.setText(userName);
        courseNameTV.setText(courseName);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_container, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_faq) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_logout){
            signOut();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    void signOut(){
        if(loginProvider.equals("google")){
            signOutGoogle();
        }else if(loginProvider.equals("facebook")){
            signOutFacebook();
        }else if(loginProvider.equals("email")){
            signOutEmail();
        }
        sharedPref.clearAll();
    }

    private void signOutEmail() {
        firebaseAuth.signOut();
        Intent intent = new Intent(this, EntryPointActivity.class);
        startActivity(intent);
        finish();
    }

    private void signOutFacebook() {
        firebaseAuth.signOut();
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(this, EntryPointActivity.class);
        startActivity(intent);
        finish();
    }

    private void signOutGoogle() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        firebaseAuth.signOut();
        // Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d(TAG, "Google Account signed out");
                        Intent intent = new Intent(MainContainer.this, EntryPointActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }

    @Override
    public void hideProfile() {
        if(scoreboardFAB.isShown()){
            scoreboardFAB.hide();
        }
        if(theoryFAB.isShown()){
            theoryFAB.hide();
        }
        if(profileLayout.getVisibility()==View.VISIBLE){
            profileLayout.animate()
                    .alpha(0.0f)
                    .setDuration(500)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            profileLayout.setVisibility(View.GONE);
                        }
                    });
        }

    }

    @Override
    public void showProfile() {
        if(profileLayout.getVisibility()==View.GONE){
            profileLayout.animate()
                    .alpha(1.0f)
                    .setDuration(500)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            profileLayout.setVisibility(View.VISIBLE);
                            if(!scoreboardFAB.isShown()){
                                scoreboardFAB.show();
                            }
                            if(!theoryFAB.isShown()){
                                theoryFAB.show();
                            }
                        }
                    });
        }
    }

    @Override
    public void updateScoreandLevel(Integer obtainedscore,Integer totalScore, Integer level) {
        scoreValueTV.setText(obtainedscore+"/"+totalScore);
        levelValueTV.setText(String.valueOf(level));
        sharedPref.saveData("obtained_score",obtainedscore);
        sharedPref.saveData("total_score", totalScore);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.scoreboard_fab:
                showScorecard();
                break;
            case R.id.theory_fab:
                break;
            default:
                break;

        }
    }

    public void showScorecard(){
        AccessToken token = AccessToken.getCurrentAccessToken();
        if(!token.getPermissions().contains("publish_actions")){
            LoginManager manager = LoginManager.getInstance();
            manager.logInWithPublishPermissions(this, Arrays.asList("publish_actions"));
        }
        GraphRequest graphRequest = GraphRequest.newGraphPathRequest(token, "/"+getResources().getString(R.string.facebook_app_id)+"/scores", new GraphRequest.Callback() {
            @Override
            public void onCompleted(GraphResponse response) {
                Log.d(TAG, response.toString());
                if(response.getError()==null) {
                    handleResponse(response);
                    try {
                        Log.d(TAG, response.getJSONObject().getJSONArray("data").toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d(TAG, e.getLocalizedMessage());
                    }
                }
            }
        });
        graphRequest.executeAsync();
    }

    private void handleResponse(GraphResponse response) {
        try {
            AccessToken token = AccessToken.getCurrentAccessToken();
            JSONArray topScoreData = response.getJSONObject().getJSONArray("data");
            for(int i = 0 ; i < topScoreData.length() ; i++){
                JSONObject userData = topScoreData.getJSONObject(i);
                String itemUserId = userData.getJSONObject("user").getString("id");
                Log.d(TAG, itemUserId);
                Log.d(TAG, itemUserId);
                GraphRequest request = GraphRequest.newGraphPathRequest(
                        token,
                        "/"+itemUserId+"/picture?redirect=false",
                        new GraphRequest.Callback() {
                            @Override
                            public void onCompleted(GraphResponse response) {
                                if(response.getError()==null){
                                    try {
                                        Log.d(TAG, response.getJSONObject().getJSONObject("data").getString("url"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });

                request.executeAsync();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
