package tarrotsystem.com.playmovie.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import tarrotsystem.com.playmovie.database.MovieContract.FavoriteEntry;
import tarrotsystem.com.playmovie.utilities.JSONObjectUtil;

/**
 * Created by DOTECH on 13/05/2017.
 */

public class MovieDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "favorite.db";
    private static final int DATABASE_VERSION = 1;

    final String SQL_CREATE_FAVORITE_TABLE =  "CREATE TABLE " + FavoriteEntry.TABLE_NAME + " (" +
            FavoriteEntry._ID  + "  INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FavoriteEntry.COLUMN_ID  + " INTEGER UNIQUE , " +
            FavoriteEntry.COLUMN_RELEASEDATE  + " TEXT NOT NULL, " +
            FavoriteEntry.COLUMN_TITLE  + " TEXT NOT NULL, " +
            FavoriteEntry.COLUMN_VOTEAVERAGE  + " TEXT NOT NULL, " +
            FavoriteEntry.COLUMN_OVERVIEW  + " TEXT NOT NULL, " +
            FavoriteEntry.COLUMN_POSTER + " TEXT NOT NULL, " +
            FavoriteEntry.COLUMN_BACKDROP + " TEXT NOT NULL);";


    public MovieDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_FAVORITE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FavoriteEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
        onUpgrade(db,oldVersion,newVersion);
    }

    public static ContentValues getMovieContentValues(JSONObjectUtil.Movies movies) {

        ContentValues values = new ContentValues();

        values.put(FavoriteEntry.COLUMN_ID, movies.getId());
        values.put(FavoriteEntry.COLUMN_RELEASEDATE, movies.getRelease_date());
        values.put(FavoriteEntry.COLUMN_TITLE, movies.getOriginal_title());
        values.put(FavoriteEntry.COLUMN_VOTEAVERAGE, movies.getVote_average());
        values.put(FavoriteEntry.COLUMN_OVERVIEW, movies.getOverview());
        values.put(FavoriteEntry.COLUMN_POSTER, movies.getPoster_path());
        values.put(FavoriteEntry.COLUMN_BACKDROP, movies.getBackdrop_path());

        return values;
    }
}
