package com.example.indiannew;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.assist.AssistStructure;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RenderNode;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;

import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;
import androidx.recyclerview.widget.RecyclerView.State;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import javax.net.ssl.HttpsURLConnection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.example.hp.indiannews.MainActivity */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String PREFS_FILE_NAME = "Prefs_File";
    AlbumsAdapter adapter;

    /* renamed from: b1 */
    Button f4b1;
    /* access modifiers changed from: private */
    public String category = "business";
    CoordinatorLayout clayout;
    String content;
    SharedPreferences.Editor editor;
    NewsFragment fragment;
    int[] images = {R.drawable.news11, R.drawable.earth, R.drawable.news14};
    ImageView img;

    /* renamed from: li */
    ListView f5li;
    public Menu menu;
    private String myurl;
    ArrayList<HashMap<String, String>> newslist;
    RecyclerView recyclerView;
    SharedPreferences sharedPreferences;
    SwipeRefreshLayout swipe;
    /* access modifiers changed from: private */
    public String url;
    private ViewFlipper viewFlipper;
    private SearchView mSearchview;
    private RelativeLayout layoutContent;
    private AppCompatButton button;
    private LinearLayout layoutmenu;
    private boolean isOpen=false;
    private RelativeLayout layoutButtons;
    private CoordinatorLayout layoutMain;
    private FloatingActionButton fab;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.business:
                editor.putString("category","business").apply();
                newslist=new ArrayList<>();
                runVolley();
                break;
            case R.id.sports:
                editor.putString("category","sports").apply();
                newslist=new ArrayList<>();
                runVolley();
                break;
            case R.id.science:
                editor.putString("category","science").apply();
                newslist=new ArrayList<>();
                runVolley();
                break;
            case R.id.entertainment:
                editor.putString("category","entertainment").apply();
                newslist=new ArrayList<>();
                runVolley();
                break;
            case R.id.general:
                editor.putString("category","general").apply();
                newslist=new ArrayList<>();
                runVolley();
                break;
            case R.id.technology:
                editor.putString("category","technology").apply();
                newslist=new ArrayList<>();
                runVolley();
                break;
            case R.id.health:
                editor.putString("category","health").apply();
                newslist=new ArrayList<>();
                runVolley();
                break;



        }
        close_reveal();
    }


    /* renamed from: com.example.hp.indiannews.MainActivity$GridSpacingItemDecoration */
    private class GridSpacingItemDecoration extends ItemDecoration {
        private boolean includeEdge;
        private int spacing;
        private int spanCount;

        public GridSpacingItemDecoration(int spanCount2, int spacing2, boolean includeEdge2) {
            this.spanCount = spanCount2;
            this.spacing = spacing2;
            this.includeEdge = includeEdge2;
        }

        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
            int position = parent.getChildAdapterPosition(view);
            int i = this.spanCount;
            int column = position % i;
            if (this.includeEdge) {
                int i2 = this.spacing;
                outRect.left = i2 - ((column * i2) / i);
                outRect.right = ((column + 1) * i2) / i;
                if (position < i) {
                    outRect.top = i2;
                }
                outRect.bottom = this.spacing;
                return;
            }
            int i3 = this.spacing;
            outRect.left = (column * i3) / i;
            outRect.right = i3 - (((column + 1) * i3) / i);
            if (position >= i) {
                outRect.top = i3;
            }
        }
    }
AppCompatImageButton b1,b2,b3,b4,b5,b6,b7;

    /* access modifiers changed from: protected */
    @SuppressLint("CommitPrefEdits")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusbarcolor();
        setContentView((int) R.layout.activity_main);
        newslist = new ArrayList<>();
        layoutMain=((CoordinatorLayout)findViewById(R.id.main_content));
        layoutButtons = (RelativeLayout) findViewById(R.id.layoutButtons);
        layoutContent = (RelativeLayout) findViewById(R.id.layoutContent);

        b1=findViewById(R.id.business);
        b2=findViewById(R.id.sports);
        b3=findViewById(R.id.science);
        b4=findViewById(R.id.entertainment);
        b5=findViewById(R.id.health);
        b6=findViewById(R.id.general);
        b7=findViewById(R.id.technology);


        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);

//        fab=(FloatingActionButton)findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewMenu();
//            }
//        });

//        layoutmenu=(LinearLayout)findViewById(R.id.layoutmenu);
        LocalDateTime ldt = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            ldt = LocalDateTime.now();
            url = "https://newsapi.org/v2/everything?language=en&from=" + DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt) + "&to=" + DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt) + "&sortBy=popularity&qInTitle=corona&apiKey=e862c226fef44b42ac9fa5fb27b13014";
        }

        sharedPreferences = getSharedPreferences(PREFS_FILE_NAME, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        this.clayout = (CoordinatorLayout) findViewById(R.id.main_content);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            runVolley();
        }
        //this.category = getIntent().hasExtra(str) ? getIntent().getStringExtra(str) : "science";


//        new NewsData(this, this.category).execute(new String[0]);
//        this.swipe = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
//        this.swipe.setOnRefreshListener(new OnRefreshListener() {
//            public void onRefresh() {
//                new Handler().postDelayed(new Runnable() {
//                    @RequiresApi(api = Build.VERSION_CODES.O)
//                    public void run() {
//                        swipe.setRefreshing(false);
//                        newslist = new ArrayList<>();
//                        runVolley();
////                        new NewsData(MainActivity.this.getApplicationContext(), MainActivity.this.category).execute(new String[0]);
//                    }
//                }, 3000);
//            }
//        });
        this.viewFlipper = (ViewFlipper) findViewById(R.id.backdrop);
        this.viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
        this.viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_out));
        for (int imageResource : this.images) {
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ScaleType.CENTER_CROP);
            imageView.setImageResource(imageResource);
            this.viewFlipper.addView(imageView);
        }
        if (!this.viewFlipper.isFlipping()) {
            this.viewFlipper.setAutoStart(true);
            this.viewFlipper.setFlipInterval(2000);
            this.viewFlipper.startFlipping();
        }
        this.clayout.setBackgroundResource(R.drawable.india);
        initCollapsingToolbar();
        this.recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.img = (ImageView) findViewById(R.id.im1);
        TextView textView = (TextView) findViewById(R.id.t1);
        try {
            Glide.with((FragmentActivity) this).load(R.drawable.india).into(this.img);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu2) {
        getMenuInflater().inflate(R.menu.options_menu, menu2);
        this.menu = menu2;
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void runVolley() {
        RequestQueue q = Volley.newRequestQueue(this);

        if (sharedPreferences.contains("category")&&(sharedPreferences.getString("type","cat").equals("cat"))) {
           String str = sharedPreferences.getString("category", "science");
            url = "https://newsapi.org/v2/top-headlines?country=in&category=" + str + "&apiKey=e862c226fef44b42ac9fa5fb27b13014";
        }
        else if (sharedPreferences.contains("search")&&(sharedPreferences.getString("type","search").equals("search"))) {
            String search = sharedPreferences.getString("search", "modi");
            url = "https://newsapi.org/v2/everything?language=en&q=" + search + "&sortBy=relevancy&apiKey=e862c226fef44b42ac9fa5fb27b13014";
        }
        JsonObjectRequest
                jsonObjectRequest
                = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        JSONObject res = (JSONObject) response;
                        JSONArray news = null;
                        try {
                            news = res.getJSONArray("articles");

                            int i = 0;
                            while (i < news.length()) {
                                JSONObject c = news.getJSONObject(i);
                                String title = c.getString("title");
                                JSONObject description = c.getJSONObject("source");
                                String urlToImage = c.getString("urlToImage");
                                String content = c.getString("content");
                                String url1 = c.getString("url");
                                String date=c.getString("publishedAt");
                                HashMap<String, String> hnews = new HashMap<>();
                                StringBuilder sb = new StringBuilder();
                                sb.append(i + 1);
                                sb.append(". ");
                                sb.append(title);
                                hnews.put("title", sb.toString());
                                hnews.put("source", description.getString("name"));
                                hnews.put("urlToImage", urlToImage);
                                hnews.put("content", content);
                                hnews.put("url", url1);
                                hnews.put("publishedAt",date);
                                newslist.add(hnews);
                                i++;

                            }
                            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                            adapter = new AlbumsAdapter(MainActivity.this, newslist);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.phase_in));
                            recyclerView.setAdapter(adapter);
                            clayout.setBackgroundColor(Color.BLACK);
                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "This category is not available for now", Toast.LENGTH_LONG).show();
                            Log.d("Problem", e.getMessage());
                        }

                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Log.d("Problem2", error.getMessage());
                        NetworkResponse response = error.networkResponse;
                        if (error instanceof ServerError && response != null) {
                            try {
                                String res = new String(response.data,
                                        HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                                // Now you can use any deserializer to make sense of data
                                JSONObject obj = new JSONObject(res);
                            } catch (UnsupportedEncodingException e1) {
                                // Couldn't properly decode data to string
                                e1.printStackTrace();
                            } catch (JSONException e2) {
                                // returned data is not JSONObject?
                                e2.printStackTrace();
                            }
                        }
                        Toast.makeText(MainActivity.this, "Problemo" + error.getMessage(), Toast.LENGTH_LONG).show();
//                        Log.d("Dikkat",error.getMessage());

                    }


                }){
            @Override

            public Map<String,String> getHeaders() throws AuthFailureError{
                HashMap<String, String> headers = new HashMap<String, String>();
                //headers.put("Content-Type", "application/json");
                headers.put("User-Agent", "Mozilla/5.0");
                return headers;

            }
        };


        q.add(jsonObjectRequest);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean onOptionsItemSelected(MenuItem item) {

//        if (item.getItemId() == R.id.entertainment) {
//            editor.putString("category", "entertainment").apply();
//            this.newslist = new ArrayList<>();
// editor.putString("type","cat").apply();
//            runVolley();
//        } else if (item.getItemId() == R.id.health) {
//            editor.putString("category", "health").apply();
//            editor.putString("type","cat").apply();
//            this.newslist = new ArrayList<>();
//            runVolley();
//        } else if (item.getItemId() == R.id.busines) {
//            editor.putString("category", "business").apply();
//            editor.putString("type","cat").apply();
//            this.newslist = new ArrayList<>();
//            runVolley();
//        } else if (item.getItemId() == R.id.sports) {
//            editor.putString("category", "sports").apply();
//            editor.putString("type","cat").apply();
//            this.newslist = new ArrayList<>();
//            runVolley();
//
//        }
//        else if (item.getItemId() == R.id.technology) {
//            editor.putString("category", "technology").apply();
//            editor.putString("type","cat").apply();
//            this.newslist = new ArrayList<>();
//            runVolley();
//
//        }
//        else if (item.getItemId() == R.id.general) {
//            editor.putString("category", "general").apply();
//            editor.putString("type","cat").apply();
//            this.newslist = new ArrayList<>();
//            runVolley();
//
//        }else if (item.getItemId() == R.id.Science) {
//            editor.putString("category", "science").apply();
//            editor.putString("type","cat").apply();
//            this.newslist = new ArrayList<>();
//            runVolley();
//
//        }
         if (item.getItemId() == R.id.search) {
            //slideFromRightToLeft(findViewById(R.id.search));
            editor.putString("type","search").apply();

            mSearchview = (SearchView) menu.findItem(R.id.search).getActionView();
            mSearchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public boolean onQueryTextSubmit(String query) {
                    editor.putString("search", query).apply();
                    newslist=new ArrayList<>();
                    runVolley();
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {

                    return false;
                }
            });
        }
         else if(item.getItemId()==R.id.button)
         {
             editor.putString("type","cat").apply();
//             button=((AppCompatButton)menu.findItem(R.id.button).getActionView());
//             button.setOnClickListener(new View.OnClickListener() {
//                 @Override
//                 public void onClick(View v) {
//                     viewMenu();
//                 }
//             });
             viewMenu();
         }

        return super.onOptionsItemSelected(item);

    }




//    private void slideFromRightToLeft(View view) {
//        TranslateAnimation animate = new TranslateAnimation(view.getWidth(), 0f, 0f, 0f);
//        animate.setDuration( 100);
//        animate.setFillAfter(true);
//        view.startAnimation(animate);
//    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);
        appBarLayout.addOnOffsetChangedListener((OnOffsetChangedListener) new OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (this.scrollRange == -1) {
                    //collapsingToolbar.setTitle(" Indian News");
                    this.scrollRange = appBarLayout.getTotalScrollRange();
                }
                if(scrollRange==0)
                {
                    collapsingToolbar.setTitle(" Indian News");
                }
                if (this.scrollRange + verticalOffset == 0) {
//                    collapsingToolbar.setTitle("Indian News");
                    this.isShow = true;
                } else if (this.isShow) {

                    this.isShow = false;
                }
            }
        });
    }


@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public void close_reveal()
{
    int x = layoutButtons.getRight();
    int y = layoutButtons.getTop();

    int startRadius = Math.max(layoutContent.getWidth(), layoutContent.getHeight());
    int endRadius = 0;

//            fab.setBackgroundTintList(ColorStateList.valueOf(ResourcesCompat.getColor(getResources(),R.color.colorAccent,null)));
//            fab.setImageResource(R.drawable.grade);

    Animator anim = ViewAnimationUtils.createCircularReveal(layoutButtons, x, y, startRadius, endRadius);
    anim.addListener(new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animator) {

        }

        @Override
        public void onAnimationEnd(Animator animator) {
            layoutButtons.setVisibility(View.GONE);
        }

        @Override
        public void onAnimationCancel(Animator animator) {

        }

        @Override
        public void onAnimationRepeat(Animator animator) {

        }
    });
    anim.start();

    isOpen = false;
}

    public void onResume() {
        super.onResume();

        //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void onPause() {
        super.onPause();
        this.editor.apply();
        this.editor.commit();
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setStatusbarcolor()
    {
        Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorblack));
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void viewMenu() {

        if (!isOpen) {

            int x = layoutContent.getRight();
            int y = layoutContent.getTop();

            int startRadius = 0;
            int endRadius = (int) Math.hypot(layoutMain.getWidth(), layoutMain.getHeight());

//            fab.setBackgroundTintList(ColorStateList.valueOf(ResourcesCompat.getColor(getResources(),android.R.color.white,null)));
//            fab.setImageResource(R.drawable.grad);

            Animator anim = ViewAnimationUtils.createCircularReveal(layoutButtons, x, y, startRadius, endRadius);

            layoutButtons.setVisibility(View.VISIBLE);
            anim.start();

            isOpen = true;

        } else {

            int x = layoutButtons.getRight();
            int y = layoutButtons.getTop();

            int startRadius = Math.max(layoutContent.getWidth(), layoutContent.getHeight());
            int endRadius = 0;

//            fab.setBackgroundTintList(ColorStateList.valueOf(ResourcesCompat.getColor(getResources(),R.color.colorAccent,null)));
//            fab.setImageResource(R.drawable.grade);

            Animator anim = ViewAnimationUtils.createCircularReveal(layoutButtons, x, y, startRadius, endRadius);
            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    layoutButtons.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            anim.start();

            isOpen = false;
        }
    }
}

