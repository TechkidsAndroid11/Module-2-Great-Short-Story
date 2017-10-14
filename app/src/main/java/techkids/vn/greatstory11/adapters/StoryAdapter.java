package techkids.vn.greatstory11.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import techkids.vn.greatstory11.R;
import techkids.vn.greatstory11.databases.StoryModel;

/**
 * Created by Admins on 10/7/2017.
 */

public class StoryAdapter extends ArrayAdapter<StoryModel> {
    private Context context;
    private int resource;
    private List<StoryModel> storyModels;

    public StoryAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<StoryModel> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.storyModels = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //set UI
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(resource, parent, false);

        TextView tvTitle = convertView.findViewById(R.id.tv_title);
        TextView tvAuthor = convertView.findViewById(R.id.tv_author);
        ImageView imageView = convertView.findViewById(R.id.iv_story);

        //set data
        tvTitle.setText(storyModels.get(position).getTitle());
        tvAuthor.setText(storyModels.get(position).getAuthor());

        //tách chuỗi ra thành 2 phần (ngăn cách bởi dấu ","; phần sau là phần base64 mà ta cần dùng để decode ra ảnh
        String[] base64 = storyModels.get(position).getImage().split(",");

        //decode từ base64 ra byte[]
        byte[] bytesFromBase64 = Base64.decode(base64[1], Base64.DEFAULT);

        //decode từ byte[] ra bitmap
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytesFromBase64, 0, bytesFromBase64.length);

        imageView.setImageBitmap(bitmap);
        return convertView;
    }
}
