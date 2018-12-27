package com.hhkj.cyf.socialsecuritycardcollection.tools;

import android.content.Context;
import android.content.res.AssetManager;

import com.hhkj.cyf.socialsecuritycardcollection.bean.SelectItemBean;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

public class AssetsTools {

    private static String getXml(String fileName, Context context) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static ArrayList<SelectItemBean> getSelectItemBeans(Context context, String mId) {
        InputStream xml = new ByteArrayInputStream(AssetsTools.getXml("item.xml", context).getBytes());
        boolean isAdd = false;
        ArrayList<SelectItemBean> selectItemBeans = null;
        SelectItemBean selectItemBean = null;
        // 创建一个xml解析的工厂  
        XmlPullParserFactory factory = null;
        try {
            factory = XmlPullParserFactory.newInstance();
            // 获得xml解析类的引用
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(xml, "UTF-8");
            // 获得事件的类型
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        selectItemBeans = new ArrayList<SelectItemBean>();
                        break;
                    case XmlPullParser.START_TAG:
                        if ("select".equals(parser.getName())) {
                            String id = parser.getAttributeValue(0);
                            isAdd = id.equals(mId);
                        } else if ("option".equals(parser.getName())) {
                            if (isAdd) {
                                selectItemBean = new SelectItemBean();
                                selectItemBean.setId(parser.getAttributeValue(0));
                                selectItemBean.setName(parser.nextText());
                                selectItemBeans.add(selectItemBean);
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("SelectItemBean".equals(parser.getName())) {
                            selectItemBean = null;
                        }
                        break;
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return selectItemBeans;
    }

    public static void copy(String name, String path, Map<String, String> map, Context context) {
        String text;
        try {
            InputStream is = context.getAssets().open(name);
            int size = is.available();
            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            // Convert the buffer into a string.
            text = new String(buffer, "UTF-8");
            // Finally stick the string into the text view.
            for (int i = 0; i < map.size(); i++) {
                if (i < 9) {
                    text = text.replaceAll("##0" + (i + 1), map.get("et" + i));
                } else {
                    text = text.replaceAll("##" + (i + 1), map.get("et" + i));
                }
            }
        } catch (IOException e) {
            // Should never happen!
            throw new RuntimeException(e);
        }
        String filenameStr = path;
        try {
            save(filenameStr, text);
        } catch (Exception e) {

        }
    }

    /**
     * 保存文件
     *
     * @param filenameStr    文件名称
     * @param filecontentStr 文件内容
     * @throws Exception
     */
    public static void save(String filenameStr, String filecontentStr)
            throws Exception {
        FileOutputStream fos = null;
        File file = new File(filenameStr);
        if (file.exists()) {
            file.delete();
        }
        fos = new FileOutputStream(file, true);
        fos.write(filecontentStr.getBytes());
        fos.close();
    }

}
