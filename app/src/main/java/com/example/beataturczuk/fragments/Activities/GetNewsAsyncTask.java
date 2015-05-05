//package com.example.beataturczuk.fragments.Activities;
//
//import android.app.ActionBar;
//import android.app.Activity;
//import android.content.ContentValues;
//import android.content.Context;
//import android.os.AsyncTask;
//import android.widget.TextView;
//
//import com.example.beataturczuk.fragments.DataBase.DbHelper;
//import com.example.beataturczuk.fragments.DataBase.DbManage;
//import com.example.beataturczuk.fragments.DataBase.dbTables.TableNews;
//import com.example.beataturczuk.fragments.DataBase.dbTables.TableQuote;
//import com.example.beataturczuk.fragments.Helpers.ApplicationConstants;
//import com.example.beataturczuk.fragments.Helpers.CommandData;
//import com.example.beataturczuk.fragments.Internet.JsonParser;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//public class GetNewsAsyncTask extends AsyncTask <String, String, String> {
//
//
//   public GetNewsAsyncTask(Activity activity, Context context, TextView textView) {
//           this.mActivity = activity;
//           this.mTextView = textView;
//           this.mContext = context;
//           mMydb = new DbHelper(context);
//           mDbManage = new DbManage(context);
//           mContentValues = new ContentValues();
//
//   }
//
//   private TableQuote mTableQuote;
//   private Activity mActivity;
//   private DbHelper mMydb;
//   private TextView mTextView;
//   private Context mContext;
//   private DbManage mDbManage;
//   private ContentValues mContentValues;
//
//           @Override
//               protected void onPreExecute() {
//               CommandData.networkConnection(mContext, mActivity);
//           }
//
//
//                    @Override
//                    protected String doInBackground(String... result) {
//                        //return JsonParser.httpGetRequest(CommandData.NEWS_URL_ADDRESS)
//                        try {
//                            JSONObject json = new JSONObject(String.valueOf(result));
//                            JSONArray news = json.getJSONArray(ApplicationConstants.NewsConstants.PRODUCTS);
//
//                            String nius;
//                            String type;
//                            String created;
//                            String published;
//                            String comment_count;
//                            String user_id;
//                            String source;
//                            String image;
//                            String title;
//                            String body;
//
//
//                            for (int i = 0; i < news.length(); i++) {
//                                try {
//                                    JSONObject jObject = news.getJSONObject(i);
//                                    type = jObject.getString(ApplicationConstants.NewsConstants.TYPE);
//                                    created = jObject.getString(ApplicationConstants.NewsConstants.CREATED);
//                                    published = jObject.getString(ApplicationConstants.NewsConstants.PUBLISHED);
//                                    comment_count = jObject.getString(ApplicationConstants.NewsConstants.COMMENT_COUNT);
//                                    user_id = jObject.getString(ApplicationConstants.NewsConstants.USER_ID);
//                                    source = jObject.getString(ApplicationConstants.NewsConstants.SOURCE);
//                                    image = jObject.getString(ApplicationConstants.NewsConstants.IMAGE);
//                                    title = jObject.getString(ApplicationConstants.NewsConstants.TITLE);
//                                    body = jObject.getString(ApplicationConstants.NewsConstants.BODY);
//
//                    ContentValues mContentValues = new ContentValues();
//                   mContentValues.put(TableNews.COLUMN_TYPE, type);
//                    mContentValues.put(TableNews.COLUMN_CREATED, created);
//                    mContentValues.put(TableNews.COLUMN_PUBLISHED, published);
//                   mContentValues.put(TableNews.COLUMN_COMMENT_COUNT, comment_count);
//                    mContentValues.put(TableNews.COLUMN_USER_ID, user_id);
//                   mContentValues.put(TableNews.COLUMN_SOURCE, source);
//                    mContentValues.put(TableNews.COLUMN_IMAGE, image);
//                    mContentValues.put(TableNews.COLUMN_TITLE, title);
//                    mContentValues.put(TableNews.COLUMN_BODY, body);
//
//               } catch (JSONException e) {
//                   e.printStackTrace();
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//       return "";
//    }
//
//    @Override
//    protected void onPostExecute(String mContentValues) {
//
//       mDbManage.open();
//       mDbManage.close();
//
//    }
//}