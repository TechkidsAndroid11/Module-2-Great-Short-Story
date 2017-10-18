package techkids.vn.greatstory11.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import techkids.vn.greatstory11.R;
import techkids.vn.greatstory11.databases.DatabaseHandle;
import techkids.vn.greatstory11.databases.StoryModel;

public class DescriptionActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivBack;
    private ImageView ivBookmark;
    private ImageView ivStory;
    private TextView tvTitle;
    private TextView tvAuthor;
    private TextView tvDes;
    private Button btStartReading;

    private StoryModel storyModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        setupUI();
        addListeners();
        loadData();
    }

    private void loadData() {
        storyModel = (StoryModel) getIntent().getSerializableExtra(MainActivity.STORY_KEY);

        String[] base64 = storyModel.getImage().split(",");
        byte[] bytesFromBase64 = Base64.decode(base64[1], Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytesFromBase64,
                0, bytesFromBase64.length);

        ivStory.setImageBitmap(bitmap);
        tvTitle.setText(storyModel.getTitle());
        tvDes.setText(storyModel.getDescription());
        tvAuthor.setText(storyModel.getAuthor());

        if (storyModel.isBookmark()) {
            ivBookmark.setImageResource(R.drawable.ic_bookmark_black_24dp);
        } else {
            ivBookmark.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
        }
    }

    private void addListeners() {
        ivBack.setOnClickListener(this);
        ivBookmark.setOnClickListener(this);
        btStartReading.setOnClickListener(this);
    }

    private void setupUI() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivBookmark = (ImageView) findViewById(R.id.iv_bookmark);
        ivStory = (ImageView) findViewById(R.id.iv_story);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvAuthor = (TextView) findViewById(R.id.tv_author);
        tvDes = (TextView) findViewById(R.id.tv_des);
        btStartReading = (Button) findViewById(R.id.bt_start_reading);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back: {
                super.onBackPressed();
                break;
            }
            case R.id.iv_bookmark: {
                setBookmark();
                break;
            }
            case R.id.bt_start_reading: {
                Intent intent = new Intent(this, ReadingActivity.class);
                intent.putExtra(MainActivity.STORY_KEY, storyModel);
                startActivity(intent);
                break;
            }
        }
    }

    private void setBookmark() {
        if (storyModel.isBookmark()) {
            ivBookmark.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
            DatabaseHandle.getInstance(this).setBookmark(storyModel, false);
            storyModel.setBookmark(false);
        } else {
            ivBookmark.setImageResource(R.drawable.ic_bookmark_black_24dp);
            DatabaseHandle.getInstance(this).setBookmark(storyModel, true);
            storyModel.setBookmark(true);
        }
    }
}
