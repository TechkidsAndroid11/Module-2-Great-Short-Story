package techkids.vn.greatstory11.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import techkids.vn.greatstory11.databases.DatabaseHandle;
import techkids.vn.greatstory11.R;
import techkids.vn.greatstory11.adapters.StoryAdapter;
import techkids.vn.greatstory11.databases.StoryModel;

public class MainActivity extends AppCompatActivity {
    public static final String STORY_KEY = "story_key";
    private ListView lvStory;
    private List<StoryModel> storyModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvStory = (ListView) findViewById(R.id.lv_story);

        lvStory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DescriptionActivity.class);
                intent.putExtra(STORY_KEY, storyModelList.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        storyModelList = DatabaseHandle.getInstance(this).getListStory();
        StoryAdapter storyAdapter = new StoryAdapter(
                this,
                R.layout.item_list_story,
                storyModelList);
        lvStory.setAdapter(storyAdapter);
    }
}
