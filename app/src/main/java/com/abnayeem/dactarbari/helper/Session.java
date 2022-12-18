package com.abnayeem.dactarbari.helper;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Session {

    public static final String PREFER_NAME = "abmn_find_doctor";

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Activity activity;
    int PRIVATE_MODE = 0;

    public Session(Activity activity) {
        try {
            this.activity = activity;
            pref = activity.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
            editor = pref.edit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getData(String id) {
        return pref.getString(id, "");
    }

    public void setData(String id, String val) {
        editor.putString(id, val);
        editor.commit();
    }

    public boolean getBoolean(String id) {
        return pref.getBoolean(id, false);
    }

    public void setBoolean(String id, boolean val) {
        editor.putBoolean(id, val);
        editor.commit();
    }

    public void writeJSONObjectFile(Activity activity, JSONObject jsonObject, String fileName) {
        String data = jsonObject.toString();
        String dirName = "abmn_file";
        try {
            File dir = new File(dirName);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File storageDir = activity.getExternalFilesDir(dirName);
            File file = new File(storageDir, fileName + ".txt");
            try (FileOutputStream stream = new FileOutputStream(file)) {
                String e = CryptUtil.encrypt(data);
                stream.write(e.getBytes());
            }
        } catch (IOException ioException) {
            Log.e("ioException", "File write failed: " + ioException);
        } catch (Exception e) {
            Log.w("exception", e.toString());
        }
    }

    public JSONObject readJSONObjectFile(Activity activity, String fileName) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader in;
        File storageDir = activity.getExternalFilesDir("abmn_file");
        try {
            in = new BufferedReader(new FileReader(new File(storageDir, fileName + ".txt")));
            while ((line = in.readLine()) != null) stringBuilder.append(line);
        } catch (FileNotFoundException exception) {
            Log.e("fnfException", "File not found: " + exception);
        } catch (IOException ioException) {
            Log.e("ioException", "Can not read file: " + ioException);
        }
        String string = CryptUtil.decrypt(stringBuilder.toString());
        JSONObject json = null;
        try {
            json = new JSONObject(string);
            Log.d("makeJson", json.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("makeJson", e.getMessage());
        }
        return json;
    }

    public void writeJSONArrayFile(Activity activity, JSONArray jsonArray, String fileName) {
        String data = jsonArray.toString();
        String dirName = "abmn_file";
        try {
            File dir = new File(dirName);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File storageDir = activity.getExternalFilesDir(dirName);
            File file = new File(storageDir, fileName + ".txt");
            try (FileOutputStream stream = new FileOutputStream(file)) {
                String e = CryptUtil.encrypt(data);
                stream.write(e.getBytes());
            }
        } catch (IOException ioException) {
            Log.e("ioException", "File write failed: " + ioException);
        } catch (Exception e) {
            Log.w("exception", e.toString());
        }
    }

    public JSONArray readJSONArrayFile(Activity activity, String fileName) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader in;
        File storageDir = activity.getExternalFilesDir("abmn_file");
        try {
            in = new BufferedReader(new FileReader(new File(storageDir, fileName + ".txt")));
            while ((line = in.readLine()) != null) stringBuilder.append(line);
        } catch (FileNotFoundException exception) {
            Log.e("fnfException", "File not found: " + exception);
        } catch (IOException ioException) {
            Log.e("ioException", "Can not read file: " + ioException);
        }
        String string = CryptUtil.decrypt(stringBuilder.toString());
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(string);
            Log.d("makeJson", jsonArray.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("makeJson", e.getMessage());
        }
        return jsonArray;
    }

    public void logoutUser(Activity activity) {
        editor.clear();
        editor.commit();
        Common.transferActivityFinished(activity, Constant.AUTH, "1");
    }

    public void logoutUserConfirmation(final Activity activity) {

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
        alertDialog.setTitle("Log Out");
        alertDialog.setMessage("Are you sure, do you want to logout.?");
        alertDialog.setCancelable(false);
        final AlertDialog alertDialog1 = alertDialog.create();

        alertDialog.setPositiveButton("Yes", (dialog, which) -> logoutUser(activity));
        alertDialog.setNegativeButton("No", (dialog, which) -> alertDialog1.dismiss());
        alertDialog.show();
    }

    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * Constant.ALPHA_NUMERIC_STRING.length());
            builder.append(Constant.ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

//    public void setJSONArray(String key, JSONArray list) {
//        editor.putString(key, list.toString());
//        editor.apply();     // This line is IMPORTANT !!!
//        editor.commit();
//    }
//
//    public JSONArray getJSONArray(String key) {
//        String json = pref.getString(key, null);
//        try {
//            return new JSONArray(json);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

}
