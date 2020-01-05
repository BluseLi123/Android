package cn.bluseli.android.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import cn.bluseli.android.R;

public class XmlActivity extends AppCompatActivity {

    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_button);

        btn_1=findViewById(R.id.btn_1);
        btn_2=findViewById(R.id.btn_2);
        btn_3=findViewById(R.id.btn_3);
        btn_4=findViewById(R.id.btn_4);

        btn_1.setText("点我创建SharedPreference");
        btn_2.setText("点我读取SharedPreference");
        btn_3.setText("点我保存XML");
        btn_4.setText("点我读取XML");

        //有点像window下的配置文件
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username="www.bluseli.cn";
                String password="www.bluseli.cn";
                // 1.通过Context对象创建一个SharedPreference对象
                SharedPreferences sharedPreferences = XmlActivity.this.getSharedPreferences("config",Context.MODE_PRIVATE);
                // 2.通过sharedPreferences对象获取一个Editor对象
                SharedPreferences.Editor editor = sharedPreferences.edit();
                // 3.往Editor中添加数据
                editor.putString("username", username);
                editor.putString("password", password);

                // 4.提交Editor对象
                editor.commit();
                Toast.makeText(XmlActivity.this, "创建SharedPreferences？", Toast.LENGTH_SHORT).show();
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = XmlActivity.this.getSharedPreferences("config",Context.MODE_PRIVATE);
                String username= sharedPreferences.getString("username", "感觉就像");
                String password= sharedPreferences.getString("password", "配置文件");
                Toast.makeText(XmlActivity.this, username+password, Toast.LENGTH_SHORT).show();
            }
        });

        //XML
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xmlSave(view);
            }
        });
        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xmlParser(view);
            }
        });
    }

    public void xmlSave(View view) {

        List<Book> list = new ArrayList();
        for (int i = 1; i <= 10; i++) {
            Book book = new Book();
            book.name = "西游记" + i;
            book.author = "吴承恩";
            list.add(book);
        }

        XmlSerializer serializer = Xml.newSerializer();
        File file = new File(this.getFilesDir(), "books.xml");
        try {
            OutputStream out = new FileOutputStream(file);
            serializer.setOutput(out, "utf-8");
            serializer.startDocument("utf-8", true);

            serializer.startTag(null, "books");

            for (Book book : list) {
                serializer.startTag(null, "book");
                serializer.startTag(null, "name");
                serializer.text(book.name);
                serializer.endTag(null, "name");
                serializer.startTag(null, "author");
                serializer.text(book.author);
                serializer.endTag(null, "author");
                serializer.endTag(null, "book");
            }

            serializer.endTag(null, "books");

            serializer.endDocument();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void xmlParser(View view) {
        List<Book> list = new ArrayList<>();
        Book book = null;
        XmlPullParser parser = Xml.newPullParser();
        File file = new File(this.getFilesDir(), "books.xml");
        try {
            InputStream inputStream = new FileInputStream(file);
            parser.setInput(inputStream, "utf-8");
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if(eventType == XmlPullParser.START_DOCUMENT) {
                    System.out.println("Start document");
                } else if(eventType == XmlPullParser.START_TAG) {
                    System.out.println("Start tag "+parser.getName());
                    if (parser.getName().equals("book")) {
                        book = new Book();
                        list.add(book);
                    } else if (parser.getName().equals("name")) {
                        String name = parser.nextText();
                        book.name=name;
                    }else if (parser.getName().equals("author")) {
                        String author = parser.nextText();
                        book.setAuthor(author);
                    }
                } else if(eventType == XmlPullParser.END_TAG) {
                    System.out.println("End tag "+parser.getName());
                } else if(eventType == XmlPullParser.TEXT) {
                    System.out.println("Text "+parser.getText());
                }
                eventType = parser.next();
            }

            System.out.println("-----------------");
            for (Book book_temp : list) {
                //System.out.println(book_temp.name+" , "+book_temp.author);
                Toast.makeText(XmlActivity.this, book_temp.name+" "+book_temp.author, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
