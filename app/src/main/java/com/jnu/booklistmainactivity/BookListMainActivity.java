package com.jnu.booklistmainactivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BookListMainActivity extends AppCompatActivity {

    private ArrayList<Book> bookArrayList;
    private ListView listView;
    private BookArrayAdapter theAdaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list_main);

        listView = (ListView) findViewById(R.id.list_view_books);

        bookArrayList = new ArrayList<Book>();
        bookArrayList.add(new Book("软件项目管理案例教程（第4版）", R.drawable.book_2));
        bookArrayList.add(new Book("创新工程实践", R.drawable.book_no_name));
        bookArrayList.add(new Book("信息安全数学基础（第2版）", R.drawable.book_1));

        theAdaper = new BookArrayAdapter(this, R.layout.list_item, bookArrayList);

        listView.setAdapter(theAdaper);
        this.registerForContextMenu(listView);
    }

    public List<Book> getListBooks() {
        return bookArrayList;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        int itemPostion=((AdapterView.AdapterContextMenuInfo)menuInfo).position;
        menu.setHeaderTitle(bookArrayList.get(itemPostion).getTitle());

        menu.add(0,1,0,"新建");
        menu.add(0,2,0,"删除");
        menu.add(0,3,0,"关于");

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case 1:{
                AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                bookArrayList.add(menuInfo.position,new Book("new book",R.drawable.book_2));
                theAdaper.notifyDataSetChanged();;

                Toast.makeText(this, "你选择了新建" ,Toast.LENGTH_SHORT).show();
                break;
            }
            case 2:{
                AdapterView.AdapterContextMenuInfo menuInfo=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
                final int itemPosition=menuInfo.position;
                new android.app.AlertDialog.Builder(this)
                        .setTitle("询问")
                        .setMessage("删除吗")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                bookArrayList.remove(itemPosition);
                                theAdaper.notifyDataSetChanged();
                                Toast.makeText(BookListMainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create().show();
                break;
            }
            case 3:{
                AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                Toast.makeText(this, bookArrayList.get(menuInfo.position).getTitle(), Toast.LENGTH_SHORT).show();
                break;
            }

            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }
        return super.onContextItemSelected(item);
    }

    protected class BookArrayAdapter extends ArrayAdapter<Book> {
        private int resourceId;

        public BookArrayAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Book> objects) {
            super(context, resource, objects);
            resourceId = resource;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater mInflater = LayoutInflater.from(this.getContext());
            View item = mInflater.inflate(this.resourceId, null);

            ImageView img = (ImageView) item.findViewById(R.id.image_view_book_cover);
            TextView name = (TextView) item.findViewById(R.id.text_view_book_title);


            Book book_item = this.getItem(position);
            img.setImageResource(book_item.getCoverResourceId());
            name.setText(book_item.getTitle());

            return item;
        }

    }
}